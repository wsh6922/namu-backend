package com.namu.thenamu.post.controller;

import com.namu.thenamu.post.domain.Post;
import com.namu.thenamu.post.dto.PostRequestDto;
import com.namu.thenamu.post.service.PostService;
import com.namu.thenamu.user.domain.User;
import com.namu.thenamu.utils.annotation.CurrentAuthUser;
import com.namu.thenamu.utils.response.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post/create")
    public ResponseEntity<Object> createPostApi(@CurrentAuthUser User user, @Valid @ModelAttribute PostRequestDto postRequestDto) {

        Post post = postService.create(postRequestDto, user);
        return ResponseHandler.responseBuilder(HttpStatus.OK
                ,"Post successfully register",
                post);
    }

    @GetMapping("/post/test")
    public ResponseEntity<Object> test(@CurrentAuthUser User user) {
        return ResponseHandler.responseBuilder(
                HttpStatus.OK,
                "Test",
                user
        );
    }
}
