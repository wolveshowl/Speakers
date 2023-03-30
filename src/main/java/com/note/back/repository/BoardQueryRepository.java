package com.note.back.repository;

import com.note.back.dto.BoardSearchDto;
import com.note.back.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardQueryRepository {


    List<Board> searchBoardList(BoardSearchDto boardDto);

    Optional<Board> findByBoardIdAndMemberId(Long boardId, Long memberId);
}
