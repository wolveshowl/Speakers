package com.note.back.repository;

import com.note.back.dto.BoardDto;
import com.note.back.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardQueryRepository {

    Optional<BoardDto> findByMemberId(Long id);

}
