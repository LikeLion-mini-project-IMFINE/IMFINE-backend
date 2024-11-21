package org.zerock.likelion.imfinebackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="news_term")
@Getter
@Setter
@NoArgsConstructor
public class NewsTermEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // news와의 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private NewsEntity news;

    @Column(nullable = false)
    private String term;

    @Column(nullable = false, length = 500)
    private String meaning;

}
