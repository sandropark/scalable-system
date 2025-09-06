package com.sandro.service.postread.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostDetailDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private Long viewCount;
    private Long likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentDto> comments;

    @Builder
    public PostDetailDto(Long id, String title, String content, String author,
                         Long viewCount, Long likeCount,
                         LocalDateTime createdAt, LocalDateTime updatedAt,
                         List<CommentDto> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.comments = comments;
    }

    @Getter
    @NoArgsConstructor
    public static class CommentDto {
        private Long id;
        private String content;
        private String author;
        private LocalDateTime createdAt;

        @Builder
        public CommentDto(Long id, String content, String author, LocalDateTime createdAt) {
            this.id = id;
            this.content = content;
            this.author = author;
            this.createdAt = createdAt;
        }
    }
}