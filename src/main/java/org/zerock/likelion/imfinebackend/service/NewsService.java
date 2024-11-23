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
        LocalDate today = LocalDate.now();

        NewsEntity todayNews = newsRepository.findNewsByDate(today)
                .orElseThrow(() -> new RuntimeException("오늘의 뉴스가 존재하지 않습니다"));

        boolean hasAnswered = newsRepository.hasUserAnsweredQuiz(userId, todayNews.getId());

        return convertToNewsDto(todayNews, hasAnswered);
    }

    // Entity -> NewsResponseDto로 변환
    private NewsResponseDto convertToNewsDto(NewsEntity news, boolean hasAnswered) {
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
                .hasAnswered(hasAnswered)
                .build();
    }
}
