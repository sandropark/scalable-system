package com.sandro.service.comment.domain;

import com.sandro.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false, length = 100)
    private String author;

    @Builder
    public Comment(Long postId, String content, String author) {
        this.postId = postId;
        this.content = content;
        this.author = author;
    }

    public void update(String content) {
        this.content = content;
    }
}