package org.zerock.likelion.imfinebackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name="character_img_url")
    private String characterImgUrl;

    // user_answer와의 관계
    @OneToMany(mappedBy = "user")
    private List<UserAnswerEntity> userAnswers = new ArrayList<>();

}
