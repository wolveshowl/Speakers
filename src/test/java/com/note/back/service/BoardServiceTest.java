package com.note.back.service;

import com.note.back.dto.BoardDto;
import com.note.back.dto.BoardSearchDto;
import com.note.back.entity.Board;
import com.note.back.entity.Member;
import com.note.back.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
class BoardServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    BoardService boardService;

    @Test
    @DisplayName("게시판 글쓰기 구현")
    void writeBoardTest() {
        //given
        Member member1 = new Member(null, "1234", Role.ADMIN, "set", "dipper", "dipper25@nate.com", 'M', 30, (byte) 1);
        Member member2 = new Member(null, "1234", Role.NORMAL, "dfg", "nam", "nam@nate.com", 'W', 62, (byte) 1);
        Member member3 = new Member(null, "1234", Role.NORMAL, "rth", "test", "test@nate.com", 'M', 14, (byte) 1);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        Board board1 = new Board(null, "안녕하세요1111", "안녕 콘텐츠1111", 0L, member1, null);
        Board board2 = new Board(null, "안녕하세요2222", "안녕 콘텐츠2222", 0L, member2, null);
        Board board3 = new Board(null, "안녕하세요3333", "안녕 콘텐츠3333", 0L, member3, null);

        //when
        Long boardId1 = boardService.writeActionBoard(BoardDto.builder()
                .title(board1.getTitle())
                .content(board1.getContent())
                .viewCount(board1.getViewCount())
                .member(board1.getMember()).build(), member1.getId());
        Long boardId2 = boardService.writeActionBoard(BoardDto.builder()
                .title(board2.getTitle())
                .content(board2.getContent())
                .viewCount(board2.getViewCount())
                .member(board2.getMember()).build(), member2.getId());
        Long boardId3 = boardService.writeActionBoard(BoardDto.builder()
                .title(board3.getTitle())
                .content(board3.getContent())
                .viewCount(board3.getViewCount())
                .member(board3.getMember()).build(), member3.getId());

        //then

        assertThat(boardId1).isEqualTo(member1.getId());
        assertThat(boardId2).isEqualTo(member2.getId());
        assertThat(boardId3).isEqualTo(member3.getId());

        log.info("board1={}", board1.getMember().getEmail());
    }

    @Test
    @DisplayName("멤버 게시글 수정 폼 테스트")
    void findBoardByMemberId() {
        //given
        Optional<BoardDto> boardUpdateForm = boardService.getBoardUpdateForm(3L, null);
        log.info("boardUpdateForm={}", boardUpdateForm);

    }

    @Test
    @DisplayName("검색 기능 구현 테스트")
    void searchTest() {

        Member member1 = new Member(null, "1234", Role.ADMIN, "set", "dipper", "dipper25@nate.com", 'M', 30, (byte) 1);
        Member member2 = new Member(null, "1234", Role.NORMAL, "dfg", "nam", "nam@nate.com", 'W', 62, (byte) 1);
        Member member3 = new Member(null, "1234", Role.NORMAL, "rth", "test", "test@nate.com", 'M', 14, (byte) 1);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        Board board1 = new Board(null, "안녕하세요1111", "안녕 콘텐츠1111", 0L, member1, null);
        Board board2 = new Board(null, "안녕하세요2222", "안녕 콘텐츠2222", 0L, member2, null);
        Board board3 = new Board(null, "안녕하세요3333", "안녕 콘텐츠3333", 0L, member3, null);

        em.persist(board1);
        em.persist(board2);
        em.persist(board3);

        BoardSearchDto dto = new BoardSearchDto("test", "안녕하세");

        List<BoardDto> result = boardService.searchPosts(dto);

        for (BoardDto boardDto : result) {
            log.info("boardDto={}", boardDto.getContent());
        }


    }

    @Test
    @DisplayName("게시글 삭제 구현 테스트")
    void deleteActionTest() {
        Member member1 = new Member(null, "1234", Role.ADMIN, "set", "dipper", "dipper25@nate.com", 'M', 30, (byte) 1);
        em.persist(member1);
        Board board1 = new Board(null, "안녕하세요1111", "안녕 콘텐츠1111", 0L, member1, null);
        em.persist(board1);
        Long deleteBoardId = boardService.deleteActionBoard(board1.getId(), 1L);
        log.info("deleteBoardId={}", deleteBoardId);

    }

}