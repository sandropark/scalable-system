package com.sandro.service.comment.service.response;

import java.util.List;

public record CommentPageResponse(
        List<CommentResponse> content,
        long count
) {
}
