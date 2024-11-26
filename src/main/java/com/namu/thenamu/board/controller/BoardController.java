package com.namu.thenamu.board.controller;

import com.namu.thenamu.board.domain.Board;
import com.namu.thenamu.board.service.BoardService;
import com.namu.thenamu.utils.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/category/test")
    public ResponseEntity<Object> getCategoriesApi() {
        List<Board> categories = boardService.getCategories();
        return ResponseHandler.responseBuilder(
                HttpStatus.OK,
                "Category list loaded successfully",
                categories);
    }
}
