package com.example.layered.service;


import com.example.layered.dto.MemoRequestDto;
import com.example.layered.dto.MemoResponseDto;
import com.example.layered.entity.Memo;
import com.example.layered.repository.MemoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.List;

/**
 * Annotation @Service는 @Component와 같다, Spring Bean으로 등록한다는 뜻.
 * Spring Bean으로 등록되면 다른 클래스에서 주입하여 사용할 수 있다.
 * 명시적으로 Service Layer 라는것을 나타낸다.
 * 비지니스 로직을 수행한다.
 */

@Service
public class MemoServiceImpl implements MemoService {

    // Repository Layer 접근
    private final MemoRepository memoRepository;

    // Repository 생성자 주입
    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public MemoResponseDto saveMemo(MemoRequestDto dto) {

        // 요청받은 데이터로 id가 없는 MEMO 객체 생성
        Memo memo = new Memo(dto.getTitle(), dto.getContents());

        // DB 저장 (Repository)
        Memo savedMemo = memoRepository.saveMemo(memo);

        return new MemoResponseDto(savedMemo);
    }

    @Override
    public List<MemoResponseDto> findAllMemos() {

        // 전체 조회 -> memoRepository 호출
        List<MemoResponseDto> allMemos = memoRepository.findAllMemos();

        return allMemos;

        // 바로 return 해도 됨
        // return memoRepository.findAllMemos();
    }

    @Override
    public MemoResponseDto findMemoById(Long id) {

        Memo memo = memoRepository.findMemoById(id);

        // 식별자의 Memo가 없다면?
        // 아래 코드 사용 불가.
        // if (memo == null) {
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
        if (memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id= " + id);
        }
        return new MemoResponseDto(memo);
    }

    @Override
    public MemoResponseDto updateMemo(Long id, String title, String contents) {

        // memo 조회
        Memo memo = memoRepository.findMemoById(id);

        // NPE 방지
        if (memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        // 필수값 검증
        if (title == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        // memo 수정
        memo.update(title, contents);

        return new MemoResponseDto(memo);
    }


}
