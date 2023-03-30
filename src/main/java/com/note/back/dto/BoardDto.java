package com.note.back.dto;

import com.note.back.entity.Board;
import com.note.back.entity.Category;
import com.note.back.entity.Member;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private Long viewCount;
    private Member member;
    private Category category;

    public Board toEntity() {
        Board board = Board.builder()
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .member(member)
                .category(category)
                .build();

        return board;
    }


}
