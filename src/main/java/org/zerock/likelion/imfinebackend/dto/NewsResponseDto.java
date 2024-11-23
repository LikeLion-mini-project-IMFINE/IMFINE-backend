package org.zerock.likelion.imfinebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponseDto {
    private UUID id;
    private String originalUrl;
    private LocalDate date;
    private String reporter;
    private String title;
    private String summary;
    private String content;
    private List<NewsTermResponseDto> terms;
    private List<QuizResponseDto> quizzes;
}
