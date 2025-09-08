package com.sandro.service.popular.domain;

import com.sandro.common.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "popular_posts",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"post_id", "rank_date"})
        })
public class PopularPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "rank_date", nullable = false)
    private LocalDate rankDate;

    @Column(nullable = false)
    private Integer ranking;

    @Column(nullable = false)
    private Long viewCount;

    @Column(nullable = false)
    private Long likeCount;

    @Column(nullable = false)
    private Double score;

    @Builder
    public PopularPost(Long postId, LocalDate rankDate, Integer ranking,
                       Long viewCount, Long likeCount, Double score) {
        this.postId = postId;
        this.rankDate = rankDate;
        this.ranking = ranking;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.score = score;
    }
}