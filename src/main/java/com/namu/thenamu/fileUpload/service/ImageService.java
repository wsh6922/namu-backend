package com.namu.thenamu.fileUpload.service;

import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class ImageService {

    private final S3Template s3Template;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    private static final String SAVE_DIR = "ImageContent";

    public ImageService(S3Template s3Template) {
        this.s3Template = s3Template;
    }

    public String uploadImage(MultipartFile file) {
        log.info("image original filename: {}", file.getOriginalFilename());
        String filename = file.getOriginalFilename(); // 업로드 파일명
        String fileExtension = filename.substring(filename.lastIndexOf("."));
        String uuidExtension = UUID.randomUUID() + fileExtension;

        if (!isImageFile(file)) throw new IllegalArgumentException("허용되지 않는 형식의 파일입니다: " + file.getContentType());

        String key = SAVE_DIR + "/" + uuidExtension;

        try (InputStream inputStream = file.getInputStream()) {
            S3Resource s3Uploaded = s3Template.upload(bucketName, key, inputStream, objectMetadata(file));
            log.info("image successfully uploaded to S3 bucket - filename: {}", key);
            return s3Uploaded.getURI().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isImageFile(MultipartFile file) {
        String contentType = Objects.requireNonNull(file.getContentType());
        return contentType.startsWith("image/");
    }

    public static ObjectMetadata objectMetadata(MultipartFile multipartFile) {
        return ObjectMetadata.builder()
                .contentType(multipartFile.getContentType())
                .build();
    }
}
