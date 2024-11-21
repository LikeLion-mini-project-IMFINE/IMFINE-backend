package org.zerock.likelion.imfinebackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="news")
@Getter
@Setter
@NoArgsConstructor
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String originalUrl;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String reporter;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String summary;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // quiz과의 관계
    @OneToMany(mappedBy = "news")
    private List<QuizEntity> quizzes = new ArrayList<>();

    // news_term와의 관계
    @OneToMany(mappedBy = "news")
    private List<NewsTermEntity> newsTerms = new ArrayList<>();

}
