package com.note.back.repository;

import com.note.back.dto.BoardSearchDto;
import com.note.back.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepositoryImpl implements BoardQueryRepository{

    private final EntityManager em;

    @Override
    public List<Board> searchBoardList(BoardSearchDto boardDto) {
        return em.createQuery("select b from Board b join fetch b.member m where m.nickname like :nickname or b.title like :title", Board.class)
                .setParameter("nickname", boardDto.getWriter())
                .setParameter("title", boardDto.getTitle())
                .getResultList();
    }

    @Override
    public Optional<Board> findByBoardIdAndMemberId(Long boardId, Long memberId) {
        List<Board> result = em.createQuery("select b from Board b where b.id = :boardId and b.member = :memberId", Board.class)
                .setParameter("boardId", boardId)
                .setParameter("memberId", memberId)
                .getResultList();
        return result.stream().findAny();
    }
}
