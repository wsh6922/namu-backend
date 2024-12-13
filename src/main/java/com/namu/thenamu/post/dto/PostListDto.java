package com.namu.thenamu.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostListDto {

    private Long id;
    private String title;
    private String thumbnailImage;
    private Long userId;
    private Long boardId;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBoardId() {
        return boardId;
    }
}
