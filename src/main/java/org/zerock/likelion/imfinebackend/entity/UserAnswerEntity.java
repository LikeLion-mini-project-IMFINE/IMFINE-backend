package org.zerock.likelion.imfinebackend.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="user_answer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // user와의 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // quiz와의 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private QuizEntity quiz;

    @Column
    private Boolean answer;

    @Column(name="is_correct")
    private Boolean isCorrect;

}
