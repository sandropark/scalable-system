package com.sandro.service.post.controller;

import com.sandro.service.post.service.response.PostPageResponse;
import com.sandro.service.post.service.response.PostResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PostControllerTest {
    RestClient restClient = RestClient.create("http://localhost:8081");

    @Test
    void getAll() throws Exception {
        PostPageResponse response = restClient
                .get()
                .uri("/v1/posts?boardId=1&page=1&size=30")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(PostPageResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.count()).isEqualTo(301);
        assertThat(response.content()).hasSize(30);
    }

    @Test
    void getAllInfiniteScroll() throws Exception {
        List<PostResponse> response = restClient
                .get()
                .uri("/v1/posts/infinite-scroll?boardId=1&size=30")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        assertThat(response)
                .hasSize(30)
                .extracting(PostResponse::id)
                .isSortedAccordingTo(Comparator.reverseOrder());
    }
}