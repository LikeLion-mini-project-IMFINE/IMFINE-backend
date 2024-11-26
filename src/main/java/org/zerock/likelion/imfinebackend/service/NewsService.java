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
    public NewsResponseDto getTodayNews(Long userId) {
        LocalDate now = LocalDate.now();
        // 이전 주의 시작일과 종료일 계산
        LocalDate weekStart = now.minusWeeks(1)
                .with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1);
        LocalDate weekEnd = weekStart.plusDays(6);

        // 지난주의 모든 뉴스를 가져옴 (읽은/안읽은 상관없이)
        List<NewsEntity> weeklyNews = newsRepository.findByDateBetween(weekStart, weekEnd);

        if(weeklyNews.isEmpty()) {
            throw new RuntimeException("지난 주의 뉴스가 없습니다");
        }

        // 랜덤 선택을 위해 현재 날짜를 시드로 사용
        int randomIndex = (int) (now.toEpochDay() % weeklyNews.size());
        NewsEntity todayNews = weeklyNews.get(randomIndex);

        return convertToNewsDto(todayNews);
    }

    // 뉴스 전체 목록 조회 ('목록보기' 버튼)
    public List<NewsListResponseDto> getAllNews() {
        return newsRepository.findAll().stream()
                .map(this::convertToNewsListDto)
                .sorted((a, b) -> b.getDate().compareTo(a.getDate())) // 최신 날짜순 정렬
                .collect(Collectors.toList());
    }

    // UUID로 뉴스 접근 (목록에서 선택 시 필요)
    public NewsResponseDto getNews(UUID newsId) {
        NewsEntity news = newsRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found"));
        return convertToNewsDto(news);
    }

    // Entity -> NewsListResponseDto 변환
    private NewsListResponseDto convertToNewsListDto(NewsEntity news) {
        return NewsListResponseDto.builder()
                .id(news.getId())
                .date(news.getDate())
                .title(news.getTitle())
                .summary(news.getSummary())
                .build();
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
