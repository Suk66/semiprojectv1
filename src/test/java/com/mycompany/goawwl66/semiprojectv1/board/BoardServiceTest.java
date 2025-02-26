package com.mycompany.goawwl66.semiprojectv1.board;

import com.mycompany.goawwl66.semiprojectv1.domain.BoardDTO;
import com.mycompany.goawwl66.semiprojectv1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class BoardServiceTest {

    private final BoardService boardService;

    @Test
    @DisplayName("BoardService readall test")
    public void readAllTest() {
        // Given
        int cpg = 1;    // 1페이지 분량을 읽어옴
        // When
        List<BoardDTO> results = boardService.readBoard(cpg);

        // Then
        assertNotNull(results);



    }


}
