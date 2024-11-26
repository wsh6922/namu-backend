package com.namu.thenamu.post.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.namu.thenamu.board.domain.Board;
import com.namu.thenamu.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "post")
@Builder
@DynamicInsert
@DynamicUpdate
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", updatable = false)
    private Long id;

    @NotNull(message = "제목은 필수 입력 항목입니다.")
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

//    @CreationTimestamp
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;

    @Column(name = "thumbnail_image")
    private String thumbnailImage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonManagedReference
    private Board board;

    // TODO
    // Setter 메소드 대신에 비즈니스 메소드로 수정할 것
    // 객체 생성을 생성자 말고 정적팩토리메소드로 해볼 것

    protected Post() {
    }

    public Post(Long id, String title, String content, String thumbnailImage, User user, Board board) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.thumbnailImage = thumbnailImage;
        this.user = user;
        this.board = board;
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

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public User getUser() {
        return user;
    }

    public Board getBoard() {
        return board;
    }
}
