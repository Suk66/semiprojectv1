package com.mycompany.goawwl66.semiprojectv1.board;

import com.mycompany.goawwl66.semiprojectv1.domain.BoardDTO;
import com.mycompany.goawwl66.semiprojectv1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.TestConstructor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@MybatisTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)   // 생성자 주입시 반드시 필요한 코드.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // h2 데이터베이스 사용중지
public class BoardMapperTest {
    // autowired가 아닌 생성자를 이용한 의존성 주입 사용
    private final BoardRepository boardMapper;

    @Test
    @DisplayName("MemberMapper select test")
    void selectTest() {
        // GIVEN 테스트할 데이터 제공

        List<BoardDTO> results = boardMapper.selectBoard();

        // Then : 호출되고 난 후 결과값 확인
        log.info("result : {}", results);
        // 결과값이 넘어온 값이 1이 맞는지 0이 넘어오면 테스트 실패.
        assertNotNull(results);
    }




}
