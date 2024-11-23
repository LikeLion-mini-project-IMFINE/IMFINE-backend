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
    // 이번 주의 안 읽은 뉴스 조회
    @Query("SELECT DISTINCT n FROM NewsEntity n " +
            "LEFT JOIN n.quizzes q " +
            "LEFT JOIN q.userAnswers ua " +
            "WHERE n.date BETWEEN :weekStart AND :weekEnd " +
            "AND (ua.user.id IS NULL OR ua.user.id != :userId) " +
            "ORDER BY n.date ASC")  // 날짜순 정렬 추가
    List<NewsEntity> findUnreadNewsInWeek(
            @Param("weekStart") LocalDate weekStart,
            @Param("weekEnd") LocalDate weekEnd,
            @Param("userId") Long userId);
}
