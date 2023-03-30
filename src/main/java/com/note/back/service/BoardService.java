package com.note.back.service;

import com.note.back.dto.BoardDto;
import com.note.back.dto.BoardSearchDto;
import com.note.back.entity.Board;
import com.note.back.repository.BoardQueryRepository;
import com.note.back.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private BoardRepository boardRepository;
    private BoardQueryRepository boardQueryRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, BoardQueryRepository boardQueryRepositoryImpl) {
        this.boardRepository = boardRepository;
        this.boardQueryRepository = boardQueryRepositoryImpl;
    }

    /**
     * 게시글 전체 조회(페이징 리스트)
     */
    @Transactional(readOnly = true)
    public Page<BoardDto> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable).map(board -> BoardDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .viewCount(board.getViewCount())
                .member(board.getMember()).build());
    }

    /**
     * 게시글 단건 조회
     */
    @Transactional(readOnly = true)
    public Optional<BoardDto> findById(Long boardId) {
        Optional<Board> board = boardRepository.findById(boardId);
        if (board.isEmpty()) {
            return Optional.empty();
        }

        return board.map(board1 -> BoardDto.builder()
                .title(board1.getTitle())
                .content(board1.getContent())
                .viewCount(board1.getViewCount())
                .member(board1.getMember()).build());
    }

    /**
     * 게시글 작성
     */
    @Transactional
    public Long writeActionBoard(BoardDto boardDto, Long memberId) {
        Board savedBoard = boardDto.toEntity();
        Board board = boardRepository.save(savedBoard);
        return board.getId();
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long updateActionBoard(BoardDto boardDto, Long memberId) {


        return null;
    }

    /**
     * 게시글 검색(닉네임, 제목)
     */
    @Transactional(readOnly = true)
    public List<BoardDto> searchPosts(BoardSearchDto boardDto) {
        List<Board> findBoard = boardQueryRepository.searchBoardList(boardDto);
        return findBoard.stream().map(board -> BoardDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .viewCount(board.getViewCount())
                .member(board.getMember()).build()).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public Optional<BoardDto> getBoardUpdateForm(Long boardId, Long MemberId) {
        Optional<Board> board = boardQueryRepository.findByBoardIdAndMemberId(boardId, MemberId);
        return board.map(b -> BoardDto.builder()
                .title(b.getTitle())
                .content(b.getContent())
                .viewCount(b.getViewCount())
                .member(b.getMember()).build());
    }
}
