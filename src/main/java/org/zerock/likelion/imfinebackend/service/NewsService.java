package org.zerock.likelion.imfinebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.likelion.imfinebackend.dto.NewsListResponseDto;
import org.zerock.likelion.imfinebackend.dto.NewsResponseDto;
import org.zerock.likelion.imfinebackend.dto.NewsTermResponseDto;
import org.zerock.likelion.imfinebackend.dto.QuizResponseDto;
import org.zerock.likelion.imfinebackend.entity.NewsEntity;
import org.zerock.likelion.imfinebackend.repository.NewsRepository;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsService {
    private final NewsRepository newsRepository;

    // 오늘의 뉴스 읽기 (이번 주 뉴스 중 안읽은 뉴스 랜덤 선택)
    // 읽은지 여부는 퀴즈 응시 여부로 판단
    public List<NewsListResponseDto> getUnreadNewsList(Long userId) {
        LocalDate now = LocalDate.now();
        LocalDate weekStart = now.with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1);
        LocalDate weekEnd = weekStart.plusDays(6);

        List<NewsEntity> unreadNews = newsRepository.findUnreadNewsInWeek(weekStart, weekEnd, userId);

        if(unreadNews.isEmpty()) {
            throw new RuntimeException("이번 주의 뉴스는 전부 다 읽으셨네요");
        }

        // 오늘의 인덱스를 계산 (날짜를 시드로 사용)
        int todayIndex = (int) (now.toEpochDay() % unreadNews.size());

        // 오늘의 인덱스에 해당하는 뉴스를 선택
        NewsEntity todayNews = unreadNews.get(todayIndex);

        return convertToNewsDto(todayNews);
    }

    // Entity -> NewsResponseDto로 변환
    private NewsResponseDto convertToNewsDto(NewsEntity news) {
        List<NewsTermResponseDto> terms = news.getNewsTerms().stream()
                .map(term -> NewsTermResponseDto.builder()
                        .id(term.getId())
                        .term(term.getTerm())
                        .meaning(term.getMeaning())
                        .build())
                .collect(Collectors.toList());

        List<QuizResponseDto> quizzes = news.getQuizzes().stream()
                .map(quiz -> QuizResponseDto.builder()
                        .id(quiz.getId())
                        .question(quiz.getQuestion())
                        .build())
                .collect(Collectors.toList());

        return NewsResponseDto.builder()
                .id(news.getId())
                .originalUrl(news.getOriginalUrl())
                .date(news.getDate())
                .reporter(news.getReporter())
                .title(news.getTitle())
                .summary(news.getSummary())
                .content(news.getContent())
                .terms(terms)
                .quizzes(quizzes)
                .build();
    }
}
