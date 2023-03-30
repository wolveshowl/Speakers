package com.note.back.controller;

import com.note.back.dto.BoardDto;
import com.note.back.entity.Board;
import com.note.back.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public Page<BoardDto> index(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return boardService.boardList(pageable);
    }

    @GetMapping("/{boardId}")
    public Optional<BoardDto> findOneBoard(@PathVariable Long boardId) {
        Optional<BoardDto> oneBoard = boardService.findById(boardId);
        return Optional.of(oneBoard.get());
    }

    @GetMapping("/write")
    public ResponseEntity<BoardDto> writeFormBoard(@ModelAttribute BoardDto boardDto) {
        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @PostMapping("/write")
    public Long writeActionBoard(@ModelAttribute BoardDto boardDto) {
        Long memberId = boardDto.getMember().getId();
        return boardService.writeActionBoard(boardDto, memberId);
    }

    @GetMapping("/update")
    public ResponseEntity<BoardDto> updateFormBoard(@ModelAttribute BoardDto boardDto) {
        Optional<BoardDto> board = boardService.getBoardUpdateForm(boardDto.getId(), boardDto.getMember().getId());
        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

}
