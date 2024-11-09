package com.namu.thenamu.domain.development;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "development")
@Builder
@DynamicInsert
@DynamicUpdate
public class Development {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dev_id", updatable = false)
    private Long id;

    @NotNull(message = "제목을 반드시 입력해야 합니다.")
    @Column(name = "dev_content_title", nullable = false)
    private String title;

    @Lob
    @Column(name = "dev_content", columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(name = "dev_content_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "dev_content_updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "dev_content_thumbnailImage")
    private String thumbnailImage;

    // TODO
    // Setter 메소드 대신에 비즈니스 메소드로 수정할 것
    // 객체 생성을 생성자 말고 정적팩토리메소드로 해볼 것

    public Development() {}

    public Development(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }
}
