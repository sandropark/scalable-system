package com.sandro.service.post.service.request;

public record PostUpdateRequest(
        String title,
        String content
) {
}
