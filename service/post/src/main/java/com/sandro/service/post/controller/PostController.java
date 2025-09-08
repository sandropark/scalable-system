package com.sandro.service.post.controller;

import com.sandro.service.post.service.PostService;
import com.sandro.service.post.service.request.PostCreateRequest;
import com.sandro.service.post.service.request.PostUpdateRequest;
import com.sandro.service.post.service.response.PostPageResponse;
import com.sandro.service.post.service.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/posts")
@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody PostCreateRequest request) {
        return ResponseEntity.ok(postService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> update(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        return ResponseEntity.ok(postService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(postService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PostPageResponse> getAll(@RequestParam Long boardId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(postService.getAll(boardId, pageable));
    }

    @GetMapping("/infinite-scroll")
    public ResponseEntity<List<PostResponse>> getAllInfiniteScroll(
            @RequestParam Long boardId,
            @RequestParam(required = false) Long id,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(postService.getAllInfiniteScroll(boardId, id, size));
    }
}
