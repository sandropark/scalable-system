package com.sandro.service.comment.controller;

import com.sandro.service.comment.service.request.CommentCreateRequest;
import com.sandro.service.comment.service.response.CommentPageResponse;
import com.sandro.service.comment.service.response.CommentResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class CommentControllerTest {
    RestClient restClient = RestClient.create("http://localhost:8082");

    @Test
    void create() {
        CommentResponse response1 = createComment(new CommentCreateRequest("my comment1", 1L, null, 1L));
        CommentResponse response2 = createComment(new CommentCreateRequest("my comment2", 1L, response1.id(), 1L));
        CommentResponse response3 = createComment(new CommentCreateRequest("my comment3", 1L, response1.id(), 1L));

        System.out.printf("commentId=%s%n", response1.id());
        System.out.printf("\tcommentId=%s%n", response2.id());
        System.out.printf("\tcommentId=%s%n", response3.id());

//        commentId=223298375465172992
//        commentId=223298375708442624
//        commentId=223298375792328704
    }

    CommentResponse createComment(CommentCreateRequest request) {
        return restClient.post()
                .uri("/v1/comments")
                .body(request)
                .retrieve()
                .body(CommentResponse.class);
    }

    @Test
    void read() {
        CommentResponse response = restClient.get()
                .uri("/v1/comments/{commentId}", 223299449265438720L)
                .retrieve()
                .body(CommentResponse.class);

        System.out.println("response = " + response);
    }

    @Test
    void delete() {
        restClient.delete()
                .uri("/v1/comments/{commentId}", 223299449810698240L)
                .retrieve()
                .toBodilessEntity();
    }

    @Test
    void getAll() throws Exception {
        CommentPageResponse response = restClient
                .get()
                .uri("/v1/comments?postId=1&page=50000&size=30")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(CommentPageResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.count()).isEqualTo(301);
        assertThat(response.content()).hasSize(30);

        for (CommentResponse res : response.content()) {
            if (res.parentCommentId().equals(res.id()))
                System.out.print("\t");
            System.out.println("res = " + res.id());
        }
    }

    @Test
    void getAllInfiniteScroll() throws Exception {
        List<CommentResponse> response = restClient
                .get()
                .uri("/v1/comments/infinite-scroll?postId=1&size=30")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        assertThat(response).hasSize(30);

        CommentResponse last = response.getLast();

        response = restClient
                .get()
                .uri("/v1/comments/infinite-scroll?postId=1&lastParentCommentId=" + last.parentCommentId() + "&lastCommentId=" + last.id() + "&size=30")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        assertThat(response).hasSize(30);
    }
}