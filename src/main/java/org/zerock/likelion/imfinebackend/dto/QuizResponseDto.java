package org.zerock.likelion.imfinebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponseDto {
    private Long id;
    private String question;
    // answer는 응답에서 제외 (정답을 바로 알려주지 않기 위해)
}
