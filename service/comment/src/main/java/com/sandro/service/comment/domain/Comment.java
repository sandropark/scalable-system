package com.sandro.service.comment.domain;

import com.sandro.common.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "comments",
        indexes = {
                @Index(
                        name = "idx_post_id_parent_comment_id_comment_id",
                        columnList = "post_id, parent_comment_id, id"
                )
        }
)
public class Comment extends BaseEntity {

    @Id
    private Long id;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "parent_comment_id")
    private Long parentCommentId;

    @Column(name = "writer_id", nullable = false)
    private Long writerId;

    @Column(name = "deleted", nullable = false)
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