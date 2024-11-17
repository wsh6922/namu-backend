package com.namu.thenamu.fileUpload.controller;

import com.namu.thenamu.fileUpload.dto.ImageUploadDto;
import com.namu.thenamu.fileUpload.service.ImageService;
import com.namu.thenamu.utils.response.ResponseHandler;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/image/test")
    public ResponseEntity<Object> uploadImageToS3(@Valid @ModelAttribute ImageUploadDto imageUploadDto) {
        log.info("image upload request received - file: {}, size: {}",
                imageUploadDto.getImage(),
                imageUploadDto.getImage().getSize());

       try {
           String image = imageService.uploadImage(imageUploadDto.getImage());
           return ResponseHandler.responseBuilder(
                   HttpStatus.OK,
                   "이미지 업로드 완료",
                   image
           );
       } catch (Exception e) {
           log.error("failed upload image to S3 bucket - file: {}, error: {}",
                   imageUploadDto.getImage(),
                   e.getMessage());
           throw e;
       }
    }
}

// @RequestPart는 보통 DTO가 JSON 데이터와 파일 데이터를 모두 포함할 때 사용된다.
// JSON 데이터를 포함하는 필드와 파일을 포함하는 별도의 필드가필요하다.