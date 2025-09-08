package com.sandro.service.post.service;

import com.sandro.common.domain.util.PageLimitCalculator;
import com.sandro.common.snowflake.Snowflake;
import com.sandro.service.post.domain.Post;
import com.sandro.service.post.repository.PostRepository;
import com.sandro.service.post.service.request.PostCreateRequest;
import com.sandro.service.post.service.request.PostUpdateRequest;
import com.sandro.service.post.service.response.PostPageResponse;
import com.sandro.service.post.service.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final Snowflake snowflake = new Snowflake();
    private final PostRepository postRepository;

    @Transactional
    public PostResponse create(PostCreateRequest request) {
        Post post = new Post(
                snowflake.nextId(),
                request.title(),
                request.content(),
                request.boardId(),
                request.writerId()
        );
        return PostResponse.of(postRepository.save(post));
    }

    @Transactional
    public PostResponse update(Long id, PostUpdateRequest request) {
        Post post = postRepository.findById(id).orElseThrow();
        post.update(request.title(), request.content());
        return PostResponse.of(post);
    }

    public PostResponse get(Long id) {
        return PostResponse.of(postRepository.findById(id).orElseThrow());
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    public PostPageResponse getAll(Long boardId, Pageable pageable) {
        return new PostPageResponse(
                postRepository.findAll(
                                boardId,
                                pageable.getOffset(),
                                pageable.getPageSize()
                        )
                        .stream()
                        .map(PostResponse::of)
                        .toList(),
                postRepository.count(
                        boardId,
                        PageLimitCalculator.calculatePageLimit(
                                pageable.getPageNumber(),
                                pageable.getPageSize()
                        )
                )
        );
    }

    public List<PostResponse> getAllInfiniteScroll(Long boardId, Long id, int size) {
        List<Post> posts = id == null
                ? postRepository.findAllInfiniteScroll(boardId, size)
                : postRepository.findAllInfiniteScroll(boardId, id, size);
        return posts.stream().map(PostResponse::of).toList();
    }
}
