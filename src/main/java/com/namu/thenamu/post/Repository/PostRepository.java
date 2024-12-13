package com.namu.thenamu.post.Repository;

import com.namu.thenamu.post.domain.Post;
import jakarta.servlet.annotation.WebListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Transactional
    @Query("select p from Post p where p.board.id = :board_id")
    List<Post> getPostByBoardId(Long board_id);
}
