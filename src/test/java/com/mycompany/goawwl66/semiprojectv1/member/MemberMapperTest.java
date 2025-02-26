package com.mycompany.goawwl66.semiprojectv1.member;

import com.mycompany.goawwl66.semiprojectv1.domain.MemberDTO;
import com.mycompany.goawwl66.semiprojectv1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)   // 생성자 주입시 반드시 필요한 코드.
public class MemberMapperTest {
    // autowired가 아닌 생성자를 이용한 의존성 주입 사용
    private final MemberRepository memberMapper;

    @Test
    @DisplayName("MemberMapper Insert test")
    public void insertTest() {
        // GIVEN 테스트할 데이터 제공
        MemberDTO dto = MemberDTO.builder().userid("abc1234").passwd("987xyz").name("abc").email("abc@gmail.com").build();

        // WHEN : 데이터로 테스트할 기능 호출
        int result = memberMapper.insertMember(dto);

        // Then : 호출되고 난 후 결과값 확인
        log.info("result : {}", result);
        // 결과값이 넘어온 값이 1이 맞는지 0이 넘어오면 테스트 실패.
        assertThat(result).isEqualTo(1);

    }




}
