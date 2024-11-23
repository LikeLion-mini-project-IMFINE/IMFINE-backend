package org.zerock.likelion.imfinebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsTermResponseDto {
    private Long id;
    private String term;
    private String meaning;
}
