package com.example.layered.controller;

import com.example.layered.dto.MemoRequestDto;
import com.example.layered.dto.MemoResponseDto;
import com.example.layered.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController // @Controller + @ResponseBody
@RequestMapping("/memos")
public class MemoController {

    // 데이터베이스(Repository)
    private final Map<Long, Memo> memoList = new HashMap<>();

    // 1. 요청(Controller)
    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {

        // 2. 비지니스 로직
        // MemoId 식별자 계산(Repository)
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;

        // 요청받은 데이터로 Memo 객체 생성(Service)
        Memo memo = new Memo(memoId, dto.getTitle(), dto.getContents());

        // 3. 데이터베이스 상호작용
        // Inmemory DB에 Memo 저장(Repository)
        memoList.put(memoId, memo);

        // 4. 응답(Controller)
        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.CREATED);
    }

}
