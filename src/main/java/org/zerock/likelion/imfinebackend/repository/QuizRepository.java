package org.zerock.likelion.imfinebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.likelion.imfinebackend.entity.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
}
