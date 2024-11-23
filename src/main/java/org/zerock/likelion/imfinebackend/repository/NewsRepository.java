package org.zerock.likelion.imfinebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.likelion.imfinebackend.entity.NewsEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NewsRepository extends JpaRepository<NewsEntity, UUID> {
    // 특정 날짜의 뉴스 조회
    @Query("SELECT n FROM NewsEntity n WHERE n.date = :date")
    Optional<NewsEntity> findNewsByDate(@Param("date") LocalDate date);

    // 특정 사용자가 특정 뉴스의 퀴즈를 풀었는지 확인
    @Query("SELECT CASE WHEN COUNT(ua) > 0 THEN true ELSE false END " +
            "FROM UserAnswerEntity ua " +
            "WHERE ua.user.id = :userId " +
            "AND ua.quiz.news.id = :newsId")
    boolean hasUserAnsweredQuiz(@Param("userId") Long userId, @Param("newsId") UUID newsId);
}
