package com.example.layered.repository;

import com.example.layered.entity.Memo;

public interface MemoRepository {

    Memo saveMemo(Memo memo); //memo는 id가 없는 상태로 Repository에 전달됨

}
