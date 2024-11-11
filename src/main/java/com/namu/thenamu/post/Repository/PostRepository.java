package com.namu.thenamu.post.Repository;

import com.namu.thenamu.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
