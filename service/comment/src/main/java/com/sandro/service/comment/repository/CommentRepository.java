package com.sandro.service.comment.repository;

import com.sandro.service.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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
            @Param("limit") long limit
    );

    @Query(
            value = """
                    select count(*)
                    from (
                            select id
                            from comments
                            where post_id = :postId
                            limit :limit
                    ) t
                    """,
            nativeQuery = true
    )
    long countBy(
            @Param("postId") Long postId,
            @Param("limit") long limit
    );

    @Query(
            value = """
                    select c.*
                    from (
                        select id
                        from comments
                        where post_id = :postId
                        order by comments.parent_comment_id, id
                        limit :limit offset :offset
                    ) t
                    left join comments c on c.id = t.id
                    """,
            nativeQuery = true
    )
    List<Comment> findAll(
            @Param("postId") Long postId,
            @Param("offset") Long offset,
            @Param("limit") long limit
    );

    @Query(
            value = """
                    select *
                    from comments
                    where post_id = :postId
                    order by comments.parent_comment_id, id
                    limit :limit
                    """,
            nativeQuery = true
    )
    List<Comment> findAllInfiniteScroll(
            @Param("postId") Long postId,
            @Param("limit") long limit
    );

    @Query(
            value = """
                    select *
                    from comments
                    where post_id = :postId and (
                        parent_comment_id > :lastParentCommentId or
                        (parent_comment_id = :lastParentCommentId and id > :lastCommentId)
                    )
                    order by comments.parent_comment_id, id
                    limit :limit
                    """,
            nativeQuery = true
    )
    List<Comment> findAllInfiniteScroll(
            @Param("postId") Long postId,
            @Param("lastParentCommentId") Long lastParentCommentId,
            @Param("lastCommentId") Long lastCommentId,
            @Param("limit") long limit
    );
}
