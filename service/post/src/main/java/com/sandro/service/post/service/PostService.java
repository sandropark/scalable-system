package com.sandro.service.post.service;

import com.sandro.common.snowflake.Snowflake;
import com.sandro.service.post.domain.Post;
import com.sandro.service.post.repository.PostRepository;
import com.sandro.service.post.service.request.PostCreateRequest;
import com.sandro.service.post.service.request.PostUpdateRequest;
import com.sandro.service.post.service.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
