package com.namu.thenamu.fileUpload.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class ImageUploadDto {

    @NotNull(message = "업로드한 파일이 존재하지 않습니다.")
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }
}
