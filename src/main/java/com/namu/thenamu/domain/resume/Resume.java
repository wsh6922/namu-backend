package com.namu.thenamu.domain.resume;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "resume")
@Builder
@DynamicInsert
@DynamicUpdate
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id", updatable = false)
    private Long id;

    @NotNull(message = "제목을 반드시 입력해야 합니다.")
    @Column(name = "resume_title", nullable = false)
    private String title;

    @Column(name = "resume_subtitle")
    private String subTitle;

    @Lob
    @Column(name = "resume_content", columnDefinition = "TEXT")
    private String content;

    // TODO
    // Setter 메소드 대신에 비즈니스 메소드로 수정할 것
    // 객체 생성을 생성자 말고 정적팩토리메소드로 해볼 것

    public Resume() {}

    public Resume(Long id, String title, String subTitle, String content) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getContent() {
        return content;
    }
}
