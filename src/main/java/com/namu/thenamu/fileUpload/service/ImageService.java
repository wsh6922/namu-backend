package com.namu.thenamu.fileUpload.service;

import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageService {

    private final S3Template s3Template;

    private static final String SAVE_DIR = "contentImage";

    public ImageService(S3Template s3Template) {
        this.s3Template = s3Template;
    }

    public String uploadImage(MultipartFile file) {
        String filename = file.getOriginalFilename(); // 업로드 파일명
        String fileExtension = filename.substring(filename.lastIndexOf("."));
        String uuidExtension = UUID.randomUUID() + fileExtension;

        if (!isImageFile(file)) throw new IllegalArgumentException("허용되지 않는 형식의 파일입니다: " + file.getContentType());

        String key = SAVE_DIR + "/" + uuidExtension;

        try (InputStream inputStream = file.getInputStream()) {
            S3Resource s3Uploaded = s3Template.upload(bucketName, key, inputStream, objectMetadata(file));
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
