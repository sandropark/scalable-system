package com.sandro.service.post.service.request;

public record PostCreateRequest(
        String title,
        String content,
        Long boardId,
        Long writerId
) {
}
