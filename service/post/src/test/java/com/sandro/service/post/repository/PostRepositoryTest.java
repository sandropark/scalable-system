package com.sandro.service.post.repository;

import com.sandro.service.post.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    void findAll() throws Exception {
        List<Post> all = postRepository.findAll(1L, 5159970L, 30);
        assertThat(all)
                .hasSize(30)
                .extracting(Post::getId)
                .isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    void count() throws Exception {
        int count = postRepository.count(1L, 10000L);
        System.out.println("count = " + count);
    }

    @Test
    void findAllInfiniteScroll() throws Exception {
        List<Post> result = postRepository.findAllInfiniteScroll(1L, 30);
        assertThat(result)
                .hasSize(30)
                .extracting(Post::getId)
                .isSortedAccordingTo(Comparator.reverseOrder());

        Long lastPostId = result.getLast().getId();
        result = postRepository.findAllInfiniteScroll(1L, lastPostId, 30);

        assertThat(result)
                .hasSize(30)
                .extracting(Post::getId)
                .isSortedAccordingTo(Comparator.reverseOrder())
                .allSatisfy(id -> assertThat(id).isLessThan(lastPostId));
    }

}