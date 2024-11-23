package org.zerock.likelion.imfinebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.likelion.imfinebackend.dto.UserAnswerRequestDto;
import org.zerock.likelion.imfinebackend.dto.UserAnswerResponseDto;
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

}
