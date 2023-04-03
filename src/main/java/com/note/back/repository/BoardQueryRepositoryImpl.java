package com.note.back.repository;

import antlr.StringUtils;
import com.note.back.dto.BoardSearchDto;
import com.note.back.entity.Board;
import com.note.back.entity.QBoard;
import com.note.back.entity.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static com.note.back.entity.QBoard.board;
import static com.note.back.entity.QMember.member;

@Repository
public class BoardQueryRepositoryImpl implements BoardQueryRepository{

    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory query;

    public BoardQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Board> searchBoardList(BoardSearchDto boardDto) {
        return query
                .select(board)
                .from(board)
                .where(eqTitle(boardDto.getTitle()), eqWriter(boardDto.getWriter()))
                .fetch();
    }

    @Override
    public Optional<Board> findByBoardIdAndMemberId(Long boardId, Long memberId) {
        List<Board> result = em.createQuery("select b from Board b where b.id = :boardId and b.member = :memberId", Board.class)
                .setParameter("boardId", boardId)
                .setParameter("memberId", memberId)
                .getResultList();
        return result.stream().findAny();
    }

    private BooleanExpression eqTitle(String title) {
        if (title.isBlank()) {
            return null;
        }
        return board.title.eq(title);
    }

    private BooleanExpression eqWriter(String writer) {
        if (writer.isBlank()) {
            return null;
        }
        return board.title.eq(writer);
    }



}
