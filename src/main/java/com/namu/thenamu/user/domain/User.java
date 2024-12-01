package com.namu.thenamu.user.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.namu.thenamu.post.domain.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;


@Entity
@Table(name = "users")
@Builder
@DynamicInsert
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotNull(message = "이름은 필수 입력 항목입니다.")
    @Column(name = "user_name", length = 50, nullable = false)
    private String name;

    @NotNull(message = "아이디는 필수 입력 항목입니다.")
    @Column(name = "user_id", length = 100, nullable = false, unique = true)
    private String userId;

    @NotNull(message = "비밀번호는 필수 입력 항목입니다.")
    @Column(name = "user_password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", length = 20)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Post> postList;

    protected User() {}

    public User(Long id, String name, String userId, String password, Role role, List<Post> postList) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.postList = postList;
    }

    // TODO
    // Setter 메소드 대신에 비즈니스 메소드로 수정할 것

    // 객체 생성을 인스턴스 말고 정적팩토리메소드로 해볼 것
    public static User createUser(String name, String userId, String password, Role role) {
        return User.builder()
                .name(name)
                .userId(userId)
                .password(password)
                .role(role)
                .build();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public Role getRole() {
        return role;
    }

    @JsonIgnore
    public List<Post> getPostList() {
        return postList;
    }
}