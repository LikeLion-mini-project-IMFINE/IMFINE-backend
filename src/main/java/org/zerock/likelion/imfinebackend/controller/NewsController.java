package org.zerock.likelion.imfinebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.likelion.imfinebackend.dto.NewsListResponseDto;
import org.zerock.likelion.imfinebackend.dto.NewsResponseDto;
import org.zerock.likelion.imfinebackend.service.NewsService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class NewsController {
    private final NewsService newsService;

    // 오늘의 뉴스 읽기
    @GetMapping("/today")
    public ResponseEntity<NewsResponseDto> getTodayNews(
            @RequestParam Long userId) {
        return ResponseEntity.ok(newsService.getTodayNews(userId));
    }

    // UUID로 특정 뉴스 조회 (목록보기 > 뉴스 선택 시 필요)
    @GetMapping("/{newsId}")
    public ResponseEntity<NewsResponseDto> getNews(
            @PathVariable UUID newsId) {
        return ResponseEntity.ok(newsService.getNews(newsId));
    }

    // 뉴스 목록보기
    @GetMapping("/list")
    public ResponseEntity<List<NewsListResponseDto>> getAllNews() {
        return ResponseEntity.ok(newsService.getAllNews());
    }
}
