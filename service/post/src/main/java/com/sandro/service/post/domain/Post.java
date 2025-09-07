package com.sandro.service.post.domain;

import com.sandro.common.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "posts",
        indexes = {
                @Index(name = "idx_board_id_id_id", columnList = "board_id ASC, id DESC")
        }
)
public class Post extends BaseEntity {
    @Id
    private Long id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private Long boardId;
    private Long writerId;

    public Post(Long id, String title, String content, Long boardId, Long writerId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.boardId = boardId;
        this.writerId = writerId;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}