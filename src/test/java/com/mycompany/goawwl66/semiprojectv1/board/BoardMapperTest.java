package com.mycompany.goawwl66.semiprojectv1.board;

import com.mycompany.goawwl66.semiprojectv1.domain.BoardDTO;
import com.mycompany.goawwl66.semiprojectv1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.TestConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Slf4j
@MybatisTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)   // 생성자 주입시 반드시 필요한 코드.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // h2 데이터베이스 사용중지
public class BoardMapperTest {
    // autowired가 아닌 생성자를 이용한 의존성 주입 사용
    private final BoardRepository boardMapper;
    @Value("${board.page-size}") private int pageSize;

    @Test
    @DisplayName("BoardMapper select test")
    void selectTest() {
        // GIVEN 테스트할 데이터 제공
        int stnum = 0;  // 조회할 시작위치

        List<BoardDTO> results = boardMapper.selectBoard(stnum, pageSize);

        // Then : 호출되고 난 후 결과값 확인
        log.info("result : {}", results);
        // 결과값이 넘어온 값이 1이 맞는지 0이 넘어오면 테스트 실패.
        assertNotNull(results);
    }


    @Test
    @DisplayName("BoardMapper find test")
    void findTest() {
        // GIVEN : 테스트에 사용할 데이터 제공
        Map<String, Object> params = new HashMap();
        params.put("stnum", 0);
        params.put("pageSize", 35);
        params.put("findtype", "title");
        params.put("findkey", "기본");


//        List<BoardDTO> results = boardMapper.selectFindBoard(0, 35, "title", "기본");

//        List<BoardDTO> results = boardMapper.selectFindBoard(0, 35, "userid", "abc123");

        List<BoardDTO> results = boardMapper.selectFindBoard(params);


        // Then : 호출되고 난 후 결과값 확인
        log.info("result : {}", results);
        // 결과값이 넘어온 값이 1이 맞는지 0이 넘어오면 테스트 실패.
        assertNotNull(results);             // null 여부 확인 - 리스트일 경우 의미 없는 검사
        assertThat(results).isNotEmpty();   // 비어있는지 여부 확인
        assertThat(results.size()).isGreaterThan(0);    // 결과 갯수 확인
    }


}
