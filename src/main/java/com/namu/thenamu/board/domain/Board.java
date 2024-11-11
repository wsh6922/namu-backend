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
    private String name;

    @Column(name = "board_slug", nullable = false)
    private String slug;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Post> postList;

    protected Board() {
    }

    public Board(Long id, String name, String slug, List<Post> postList) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.postList = postList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public List<Post> getPostList() {
        return postList;
    }
}
