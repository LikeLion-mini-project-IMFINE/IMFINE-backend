package org.zerock.likelion.imfinebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.likelion.imfinebackend.entity.QuizEntity;

import java.util.Optional;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
    Optional<QuizEntity> findByNews_Id(UUID newsId);
}
