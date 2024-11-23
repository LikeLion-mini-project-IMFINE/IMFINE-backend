package org.zerock.likelion.imfinebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsListResponseDto {
    // 뉴스 전체 목록 보기용
    private UUID id;
    private LocalDate date;
    private String title;
    private String summary;
}
