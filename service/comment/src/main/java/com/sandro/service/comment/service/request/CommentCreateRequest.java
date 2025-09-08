package com.sandro.service.comment.service.request;

public record CommentCreateRequest(
        String content,
        Long postId,
        Long parentCommentId,
        Long writerId
) {
}
