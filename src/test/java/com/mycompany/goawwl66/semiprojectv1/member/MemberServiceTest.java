package com.mycompany.goawwl66.semiprojectv1.member;

import com.mycompany.goawwl66.semiprojectv1.domain.MemberDTO;
import com.mycompany.goawwl66.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class MemberServiceTest {

    private final MemberService memberService;

    public void newMemberTest() {
        // Given
        MemberDTO dto = MemberDTO.builder().userid("abc1234").passwd("987xyz").name("abc").email("abc@gmail.com").build();

        // When
        boolean result = memberService.newMember(dto);

        // Then
        assertTrue(result);



    }


}
