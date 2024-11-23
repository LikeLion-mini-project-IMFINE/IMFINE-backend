package org.zerock.likelion.imfinebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.likelion.imfinebackend.dto.UserAnswerRequestDto;
import org.zerock.likelion.imfinebackend.dto.UserAnswerResponseDto;
import org.zerock.likelion.imfinebackend.service.UserAnswerService;


@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class UserAnswerController {
    private final UserAnswerService userAnswerService;

    // UUID로 특정 뉴스에 해당하는 퀴즈 사용자 답 받기
    @PostMapping()
    public ResponseEntity<UserAnswerResponseDto> postUserAnswer(
            @RequestBody UserAnswerRequestDto userAnswerRequestDto
    ) {
        return ResponseEntity.ok(userAnswerService.postUserAnswer(userAnswerRequestDto));
    }
}
