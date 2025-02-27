package com.mycompany.goawwl66.semiprojectv1.service;

import com.mycompany.goawwl66.semiprojectv1.domain.BoardDTO;
import com.mycompany.goawwl66.semiprojectv1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardMapper;
    @Value("${board.page-size}") private int pageSize;

    @Override
    public List<BoardDTO> readBoard(int cpg) {
        // cpg에 따라 시작위치값 계산
        int stnum = (cpg - 1) * 35;

        return boardMapper.selectBoard(stnum, pageSize);
    }

    @Override
    public int countBoard() {
        return boardMapper.countPagesBoard(pageSize);
    }
}
