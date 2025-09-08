package com.sandro.service.comment.repository;

import com.sandro.service.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(
            value = """
                    select count(*)
                    from (
                            select id
                            from comments
                            where post_id = :postId and parent_comment_id = :parentCommentId
                            limit :limit
                    ) t
                    """,
            nativeQuery = true
    )
    long countBy(
            @Param("postId") Long postId,
            @Param("parentCommentId") Long parentCommentId,
            @Param("limit") int limit);
}
