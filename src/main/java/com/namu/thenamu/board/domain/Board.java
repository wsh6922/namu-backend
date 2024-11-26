package com.namu.thenamu.board.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.namu.thenamu.post.domain.Post;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", updatable = false)
    private Long id;

    @Column(name = "board_category_name", nullable = false)
    private String boardName;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Post> postList;

    protected Board() {
    }

    public Board(Long id, String boardName, List<Post> postList) {
        this.id = id;
        this.boardName = boardName;
        this.postList = postList;
    }

    public Long getId() {
        return id;
    }

    public String getBoardName() {
        return boardName;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
