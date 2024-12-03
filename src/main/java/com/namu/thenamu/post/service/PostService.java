package com.namu.thenamu.post.service;

import com.namu.thenamu.board.domain.Board;
import com.namu.thenamu.board.repository.BoardRepository;
import com.namu.thenamu.fileUpload.service.ImageService;
import com.namu.thenamu.post.Repository.PostRepository;
import com.namu.thenamu.post.domain.Post;
import com.namu.thenamu.post.dto.PostRequestDto;
import com.namu.thenamu.user.domain.User;
import com.namu.thenamu.user.repository.UserDetail;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController("/api")
public class PostService {

    private final PostRepository postRepository;

    private final BoardRepository boardRepository;

    private final ImageService imageService;

    private static final String SAVE_DIR = "tumbnailImage";

    public PostService(PostRepository postRepository, ImageService imageService, BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.imageService = imageService;
        this.boardRepository = boardRepository;
    }

//    public Post create(PostRequestDto postRequestDto, User user) {
//        Board boardCategory = boardRepository.findById(postRequestDto.getBoardId())
//                .orElseThrow(() -> new EntityNotFoundException("Board category not present in the database"));
//
//        String thumbnailImageUrl = imageService.uploadImage(postRequestDto.getThumbnailImage(), SAVE_DIR);
//
//        postRequestDto.setUser(user);
//        Post post = postRequestDto.toPost(thumbnailImageUrl, boardCategory);
//        return postRepository.save(post);
//    }

    public Post create(PostRequestDto postRequestDto, User user) {
        Board boardCategory = boardRepository.findById(postRequestDto.getBoardId())
                .orElseThrow(() -> new EntityNotFoundException("Board category not present in the database"));

        postRequestDto.setUser(user);
        Post post = postRequestDto.toPost(boardCategory);
        return postRepository.save(post);
    }
}
