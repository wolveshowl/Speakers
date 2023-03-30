package com.note.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardSearchDto {

    private String writer;
    private String title;
}
