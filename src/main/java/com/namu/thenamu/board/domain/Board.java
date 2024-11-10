package com.namu.thenamu.board.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.namu.thenamu.category.domain.Category;
import com.namu.thenamu.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Builder
@DynamicInsert
@DynamicUpdate
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", updatable = false)
    private Long id;

    @NotNull(message = "제목을 반드시 입력해야 합니다.")
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "thumbnail_image")
    private String thumbnailImage;

    @ManyToOne
    @JoinColumn(name = "id")
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private Category category;

    // TODO
    // Setter 메소드 대신에 비즈니스 메소드로 수정할 것
    // 객체 생성을 생성자 말고 정적팩토리메소드로 해볼 것

    protected Board() {}

    public Board(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String thumbnailImage, User user, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.thumbnailImage = thumbnailImage;
        this.user = user;
        this.category = category;
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

    public User getUser() {
        return user;
    }

    public Category getCategory() {
        return category;
    }
}
