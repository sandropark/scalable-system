package com.sandro.service.comment.domain;

import com.sandro.common.domain.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Id
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private Long postId;

    private Long parentCommentId;

    @Column(nullable = false)
    private Long writerId;

    @Column(nullable = false)
    private Boolean deleted = false;

    public Comment(Long id, String content, Long postId, Long parentCommentId, Long writerId) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.parentCommentId = parentCommentId == null ? id : parentCommentId;
        this.writerId = writerId;
        this.deleted = false;
    }

    public void update(String content) {
        this.content = content;
    }

    public boolean isRoot() {
        return Objects.equals(this.parentCommentId, this.id);
    }

    public void delete() {
        this.deleted = true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}