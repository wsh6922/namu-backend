package com.namu.thenamu.board.repository;

import com.namu.thenamu.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
