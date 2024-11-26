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

    public ImageService(S3Template s3Template) {
        this.s3Template = s3Template;
    }

    public String uploadImage(MultipartFile file, String saveDir) {
        log.info("image original filename: {}", file.getOriginalFilename());
        String filename = file.getOriginalFilename(); // 업로드 파일명
        String storedFilename = generateStoredFilename(filename);

        if (!isImageFile(file)) throw new IllegalArgumentException("허용되지 않는 형식의 파일입니다: " + file.getContentType());

        String key = saveDir + "/" + storedFilename;

        try (InputStream inputStream = file.getInputStream()) {
            S3Resource s3Resource = s3Template.upload(bucketName, key, inputStream, objectMetadata(file));
            log.info("image successfully uploaded to S3 bucket - filename: {}", key);
            return s3Resource.getURI().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * @param originalFilename
     * @return 객체 키로 만들 저장할 이미지 파일명 난수와 구분점과 함께 생성 후 문자열로 반환
     */
    public static String generateStoredFilename(String originalFilename) {
        String fileExtension = extractExtension(originalFilename);
        return UUID.randomUUID() + "." + fileExtension;
    }

    /**
     *
     * @param originalFilename
     * @return 순수 확장자만 추출해서 소문자로 반환
     */
    public static String extractExtension(String originalFilename) {
        int fileExtensionStartIndex = originalFilename.lastIndexOf(".") + 1;
        return originalFilename.substring(fileExtensionStartIndex).toLowerCase();
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
