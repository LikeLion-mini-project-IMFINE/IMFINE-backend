package org.zerock.likelion.imfinebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswerResponseDto {
    private Long id;
    private Long userId;
    private Long quizId;
    private Boolean userAnswer;
    private Boolean isCorrect;  // 정답 여부
}
