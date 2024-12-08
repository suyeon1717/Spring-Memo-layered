package com.example.layered.repository;

import com.example.layered.dto.MemoResponseDto;
import com.example.layered.entity.Memo;

import java.util.List;

public interface MemoRepository {

    Memo saveMemo(Memo memo); //memo는 id가 없는 상태로 Repository에 전달됨

    List<MemoResponseDto> findAllMemos();

    Memo findMemoById(Long id);

    void deleteMemo(Long id);
}
