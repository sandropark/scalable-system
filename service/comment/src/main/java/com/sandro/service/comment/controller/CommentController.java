package com.sandro.service.comment.controller;

import com.sandro.service.comment.service.CommentService;
import com.sandro.service.comment.service.request.CommentCreateRequest;
import com.sandro.service.comment.service.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/comments")
@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody CommentCreateRequest request) {
        return ResponseEntity.ok(commentService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping
//    public ResponseEntity<PostPageResponse> getAll(@RequestParam Long boardId, @PageableDefault Pageable pageable) {
//        return ResponseEntity.ok(commentService.getAll(boardId, pageable));
//    }
}
