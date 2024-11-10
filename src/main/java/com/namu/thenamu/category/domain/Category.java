package com.namu.thenamu.category.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.namu.thenamu.board.domain.Board;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", updatable = false)
    private Long id;

    @Column(name = "category_name", nullable = false)
    private String name;

    @Column(name = "category_slug", nullable = false)
    private String slug;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Board> boardList;

    protected Category() {}

    public Category(Long id, String name, String slug, List<Board> boardList) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.boardList = boardList;
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

    public List<Board> getBoardList() {
        return boardList;
    }
}
