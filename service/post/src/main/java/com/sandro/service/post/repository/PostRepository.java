package com.sandro.service.post.repository;

import com.sandro.service.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(
            value = """
                    select p.*
                    from (
                        select posts.id
                        from posts
                        where board_id = :boardId
                        order by posts.id desc
                        limit :limit offset :offset
                    ) t
                    left join posts p on p.id = t.id
                    """,
            nativeQuery = true
    )
    List<Post> findAll(
            @Param("boardId") Long boardId,
            @Param("offset") Long offset,
            @Param("limit") int limit
    );

    @Query(
            value = """
                    select count(*)
                    from (
                        select posts.id
                        from posts
                        where board_id = :boardId
                        limit :limit
                    ) t
                    """,
            nativeQuery = true
    )
    int count(@Param("boardId") Long boardId, @Param("limit") Long limit);

    @Query(
            value = """
                    select *
                    from posts
                    where board_id = :boardId
                    order by posts.id desc
                    limit :limit
                    """,
            nativeQuery = true
    )
    List<Post> findAllInfiniteScroll(
            @Param("boardId") Long boardId,
            @Param("limit") int limit
    );

    @Query(
            value = """
                    select *
                    from posts
                    where board_id = :boardId and id < :lastPostId
                    order by posts.id desc
                    limit :limit
                    """,
            nativeQuery = true
    )
    List<Post> findAllInfiniteScroll(
            @Param("boardId") Long boardId,
            @Param("lastPostId") Long lastPostId,
            @Param("limit") int limit
    );
}
