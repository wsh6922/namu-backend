package com.namu.thenamu.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class PostRequestDto {

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    private String title;

    private String content;

    @NotBlank
    private String category = "카테고리 없음";

    @NotNull(message = "업로드한 파일이 존재하지 않습니다.")
    private MultipartFile uploadedImage;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MultipartFile getUploadedImage() {
        return uploadedImage;
    }

    public void setUploadedImage(MultipartFile uploadedImage) {
        this.uploadedImage = uploadedImage;
    }
}
