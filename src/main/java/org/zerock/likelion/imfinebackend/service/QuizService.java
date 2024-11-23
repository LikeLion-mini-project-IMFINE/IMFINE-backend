package org.zerock.likelion.imfinebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.likelion.imfinebackend.dto.QuizResponseDto;
import org.zerock.likelion.imfinebackend.entity.QuizEntity;
import org.zerock.likelion.imfinebackend.repository.QuizRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizService {
    private final QuizRepository quizRepository;

    // UUID로 특정 뉴스에 해당하는 퀴즈 반환
    public QuizResponseDto getQuiz(UUID newsId) {
        QuizEntity quiz = quizRepository.findByNews_Id(newsId)
                .orElseThrow(() -> new RuntimeException("quiz not found"));
        return convertToQuizResponseDTO(quiz);
    }

    private QuizResponseDto convertToQuizResponseDTO(QuizEntity quiz) {
        return QuizResponseDto.builder()
                .id(quiz.getId())
                .question(quiz.getQuestion())
                .build();
    }
}
