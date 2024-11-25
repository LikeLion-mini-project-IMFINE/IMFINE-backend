package org.zerock.likelion.imfinebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.likelion.imfinebackend.dto.QuizResponseDto;
import org.zerock.likelion.imfinebackend.service.QuizService;

import java.util.UUID;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {
    private final QuizService quizService;

    // UUID로 특정 뉴스에 해당하는 퀴즈 반환
    @GetMapping("/{newsId}")
    public ResponseEntity<QuizResponseDto> getQuiz(
            @PathVariable UUID newsId) {
        return ResponseEntity.ok(quizService.getQuiz(newsId));
    }
}
