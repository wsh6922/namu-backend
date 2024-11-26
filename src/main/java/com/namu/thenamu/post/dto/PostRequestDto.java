package com.namu.thenamu.post.dto;

import com.namu.thenamu.board.domain.Board;
import com.namu.thenamu.post.domain.Post;
import com.namu.thenamu.user.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class PostRequestDto {

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    private String title;

    private String content;

    @NotBlank
    private Long boardId;

    @NotNull(message = "업로드한 파일이 존재하지 않습니다.")
    private MultipartFile thumbnailImage;

    private User user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public MultipartFile getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(MultipartFile thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post toPost(String imageUrl, Board board) {
        return Post.builder()
                .title(title)
                .content(content)
                .thumbnailImage(imageUrl)
                .user(user)
                .board(board)
                .build();
    }
}
