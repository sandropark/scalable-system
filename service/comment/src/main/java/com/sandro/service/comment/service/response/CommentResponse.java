package com.sandro.service.comment.service.response;


import com.sandro.service.comment.domain.Comment;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        String content,
        Long postId,
        Long parentCommentId,
        Long writerId,
        LocalDateTime createdAt,
        Boolean deleted
) {

    public static CommentResponse of(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getPostId(),
                comment.getParentCommentId(),
                comment.getWriterId(),
                comment.getCreatedAt(),
                comment.getDeleted()
        );
    }
}
