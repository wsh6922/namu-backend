package com.namu.thenamu.board.service;

import com.namu.thenamu.board.domain.Board;
import com.namu.thenamu.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getCategories() {
        return boardRepository.findAll();
    }
}
