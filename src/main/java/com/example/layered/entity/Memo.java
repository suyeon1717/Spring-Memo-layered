package com.example.layered.entity;

import com.example.layered.dto.MemoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Memo {

    private Long id;
    private String title;
    private String contents;

    public void update(MemoRequestDto dto) {
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }

    public void updateTitle(MemoRequestDto dto) {
        this.title = dto.getTitle();
    }
}