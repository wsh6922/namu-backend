package com.namu.thenamu.post.controller;

import com.namu.thenamu.post.domain.Post;
import com.namu.thenamu.post.dto.PostListDto;
import com.namu.thenamu.post.dto.PostRequestDto;
import com.namu.thenamu.post.service.PostService;
import com.namu.thenamu.user.domain.User;
import com.namu.thenamu.user.repository.UserDetail;
import com.namu.thenamu.utils.annotation.CurrentUser;
import com.namu.thenamu.utils.response.ResponseHandler;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

//    @PostMapping("/post/create")
//    public ResponseEntity<Object> createPostApi(@CurrentUser UserDetail userDetail, @Valid @ModelAttribute PostRequestDto postRequestDto) {
//        User user = userDetail.getUser();
//        Post post = this.postService.create(postRequestDto, user);
//        log.info("post content: {}", post.getContent());
//        return ResponseHandler.responseBuilder(HttpStatus.OK
//                ,"Post successfully register",
//                post);
//    }

    @PostMapping("/post/create")
    public ResponseEntity<Object> createPostApi(@CurrentUser UserDetail userDetail, @Valid @ModelAttribute PostRequestDto postRequestDto) {
        User user = userDetail.getUser();
        Post post = this.postService.create(postRequestDto, user);
        log.info("post content: {}", post.getContent());
        return ResponseHandler.responseBuilder(HttpStatus.OK
                ,"Post successfully register",
                post);
    }

    @GetMapping("/post/read-one")
    public ResponseEntity<Object> readPostOneApi(@RequestParam("id") Long postId) {
        Post post = this.postService.readPost(postId);
        return ResponseHandler.responseBuilder(
                HttpStatus.OK,
                null,
                post
        );
    }

    @GetMapping("/post/read")
    public ResponseEntity<Object> readPostApi(@RequestParam("id") Long boardId) {
        List<PostListDto> postList = this.postService.readPosts(boardId);
        return ResponseHandler.responseBuilder(
                HttpStatus.OK,
                null,
                postList
        );
    }

    @GetMapping("/post/test")
    public ResponseEntity<Object> test(@CurrentUser UserDetail userDetail) {
        User user = userDetail.getUser();
        return ResponseHandler.responseBuilder(
                HttpStatus.OK,
                "Test",
                user
        );
    }
}
