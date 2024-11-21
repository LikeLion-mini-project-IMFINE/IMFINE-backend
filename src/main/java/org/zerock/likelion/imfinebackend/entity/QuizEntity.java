package org.zerock.likelion.imfinebackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="quiz")
@Getter
@Setter
@NoArgsConstructor
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // news와의 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private NewsEntity news;

    @Column(length = 500)
    private String question;

    @Column(nullable = false)
    private Boolean answer;

    // user_answer와의 관계
    @OneToMany(mappedBy = "quiz")
    private List<UserAnswerEntity> userAnswers = new ArrayList<>();
}
