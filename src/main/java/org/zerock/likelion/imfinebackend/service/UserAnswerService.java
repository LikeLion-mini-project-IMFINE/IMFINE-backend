package org.zerock.likelion.imfinebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.likelion.imfinebackend.dto.UserAnswerRequestDto;
import org.zerock.likelion.imfinebackend.dto.UserAnswerResponseDto;
import org.zerock.likelion.imfinebackend.entity.QuizEntity;
import org.zerock.likelion.imfinebackend.entity.UserAnswerEntity;
import org.zerock.likelion.imfinebackend.entity.UserEntity;
import org.zerock.likelion.imfinebackend.repository.QuizRepository;
import org.zerock.likelion.imfinebackend.repository.UserAnswerRepository;
import org.zerock.likelion.imfinebackend.repository.UserRepository;


@Service
@RequiredArgsConstructor
@Transactional
public class UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    public UserAnswerResponseDto postUserAnswer(UserAnswerRequestDto userAnswerRequestDto) {
        QuizEntity quiz = quizRepository.findById(userAnswerRequestDto.getQuizId())
                .orElseThrow(() -> new RuntimeException("quiz not found"));

        UserEntity user = userRepository.findById(userAnswerRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("user not found"));

        Boolean isCorrect = userAnswerRequestDto.getAnswer().equals(quiz.getAnswer());

        // UserAnswer 엔티티 생성 후 DB에 저장
        UserAnswerEntity userAnswer = UserAnswerEntity.builder()
                .user(user)
                .quiz(quiz)
                .answer(userAnswerRequestDto.getAnswer())
                .isCorrect(isCorrect)
                .build();

        userAnswer = userAnswerRepository.save(userAnswer);

        return convertToUserAnswerResponseDTO(userAnswer);
    }

    private UserAnswerResponseDto convertToUserAnswerResponseDTO(UserAnswerEntity userAnswer) {
        return UserAnswerResponseDto.builder()
                .id(userAnswer.getId())
                .userId(userAnswer.getUser().getId())
                .quizId(userAnswer.getQuiz().getId())
                .userAnswer(userAnswer.getAnswer())
                .isCorrect(userAnswer.getIsCorrect())
                .build();
    }
}

