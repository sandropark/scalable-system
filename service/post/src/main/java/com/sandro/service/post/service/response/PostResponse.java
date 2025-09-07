package com.sandro.service.post.service.response;

import com.sandro.service.post.domain.Post;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String title,
        String content,
        Long boardId,
        Long writerId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static PostResponse of(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getBoardId(),
                post.getWriterId(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
