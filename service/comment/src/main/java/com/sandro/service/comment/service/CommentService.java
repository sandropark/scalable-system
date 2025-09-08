package com.sandro.service.comment.service;

import com.sandro.common.domain.util.PageLimitCalculator;
import com.sandro.common.snowflake.Snowflake;
import com.sandro.service.comment.domain.Comment;
import com.sandro.service.comment.repository.CommentRepository;
import com.sandro.service.comment.service.request.CommentCreateRequest;
import com.sandro.service.comment.service.response.CommentPageResponse;
import com.sandro.service.comment.service.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.function.Predicate.not;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final Snowflake snowflake = new Snowflake();
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponse create(CommentCreateRequest request) {
        Optional<Comment> parentComment = findParentComment(request.parentCommentId());

        Comment comment = new Comment(
                snowflake.nextId(),
                request.content(),
                request.postId(),
                parentComment
                        .map(Comment::getId)
                        .orElse(null),
                request.writerId()
        );
        return CommentResponse.of(commentRepository.save(comment));
    }

    private Optional<Comment> findParentComment(Long parentCommentId) {
        if (parentCommentId == null) return Optional.empty();
        return commentRepository.findById(parentCommentId)
                .filter(not(Comment::getDeleted))
                .filter(Comment::isRoot);
    }

    public CommentResponse get(Long id) {
        return commentRepository.findById(id)
                .map(CommentResponse::of)
                .orElseThrow();
    }

    @Transactional
    public void delete(Long id) {
        commentRepository.findById(id)
                .filter(not(Comment::getDeleted))
                .ifPresent(comment -> {
                    if (hasChildren(comment)) comment.delete();
                    else delect(comment);
                });
    }

    private boolean hasChildren(Comment comment) {
        long count = commentRepository.countBy(
                comment.getPostId(),
                comment.getId(),
                2);
        return count == 2;
    }

    private void delect(Comment comment) {
        commentRepository.delete(comment);
        if (!comment.isRoot())
            commentRepository.findById(comment.getParentCommentId())
                    .filter(Comment::getDeleted)
                    .filter(not(this::hasChildren))
                    .ifPresent(this::delect);
    }

    public CommentPageResponse getAll(Long postId, Pageable pageable) {
        return new CommentPageResponse(
                commentRepository.findAll(
                                postId,
                                pageable.getOffset(),
                                pageable.getPageSize()
                        ).stream()
                        .map(CommentResponse::of)
                        .toList(),
                commentRepository.countBy(
                        postId,
                        PageLimitCalculator.calculatePageLimit(
                                pageable.getPageNumber(),
                                pageable.getPageSize()
                        )
                )
        );
    }

    public List<CommentResponse> getAllInfiniteScroll(Long postId, Long lastParentCommentId, Long lastCommentId, int size) {
        List<Comment> comments = lastParentCommentId == null || lastCommentId == null
                ? commentRepository.findAllInfiniteScroll(postId, size)
                : commentRepository.findAllInfiniteScroll(postId, lastParentCommentId, lastCommentId, size);
        return comments.stream().map(CommentResponse::of).toList();
    }
}
