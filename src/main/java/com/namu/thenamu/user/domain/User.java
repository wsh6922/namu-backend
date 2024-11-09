package com.namu.thenamu.user.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.namu.thenamu.board.domain.Board;
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
    private Role role = Role.USER;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Board> boardList;

    // TODO
    // Setter 메소드 대신에 비즈니스 메소드로 수정할 것
    // 객체 생성을 생성자 말고 정적팩토리메소드로 해볼 것
    public User createUser(Long id, String name, String userId, String password, Role role, List<Board> boardList) {
        return new User(id, name, userId, password, role, boardList);
    }

    public User() {
    }

    public User(Long id, String name, String userId, String password, Role role, List<Board> boardList) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.boardList = boardList;
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

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public List<Board> getBoardList() {
        return boardList;
    }
}

//    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&]).+$",
//            message = "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다.")