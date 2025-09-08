package com.sandro.service.like.domain;

import com.sandro.common.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "likes",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"post_id", "user_id"})
        })
public class Like extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "user_id", nullable = false, length = 100)
    private String userId;

    @Builder
    public Like(Long postId, String userId) {
        this.postId = postId;
        this.userId = userId;
    }
}