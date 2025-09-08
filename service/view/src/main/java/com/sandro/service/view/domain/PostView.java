package com.sandro.service.view.domain;

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
@Table(name = "post_views",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"post_id", "user_id", "view_date"})
        })
public class PostView extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "user_id", nullable = false, length = 100)
    private String userId;

    @Column(name = "view_date", nullable = false)
    private LocalDate viewDate;

    @Column(nullable = false)
    private Integer viewCount = 1;

    @Builder
    public PostView(Long postId, String userId, LocalDate viewDate) {
        this.postId = postId;
        this.userId = userId;
        this.viewDate = viewDate;
        this.viewCount = 1;
    }

    public void increaseViewCount() {
        this.viewCount++;
    }
}