package com.namu.thenamu.post.controller;

import com.namu.thenamu.post.domain.Post;
import com.namu.thenamu.post.dto.PostRequestDto;
import com.namu.thenamu.post.service.PostService;
import com.namu.thenamu.utils.response.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post/test")
    public ResponseEntity<Object> createPostApi(@Valid @ModelAttribute PostRequestDto postRequestDto) {

        Post post = postService.create(postRequestDto);
        return ResponseHandler.responseBuilder(HttpStatus.OK
                ,"Post successfully register",
                post);
    }
}
