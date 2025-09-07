package com.sandro.service.post.controller;

import com.sandro.service.post.service.response.PostPageResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

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
}