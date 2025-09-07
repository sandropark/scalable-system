package com.sandro.service.post.service.response;

import java.util.List;

public record PostPageResponse(
        List<PostResponse> content,
        int count
) {
}
