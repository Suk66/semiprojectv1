package com.mycompany.goawwl66.semiprojectv1.member;

import com.mycompany.goawwl66.semiprojectv1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class MemberControllerTest {

    private final MockMvc mockMvc;
    private final MemberRepository memberRepository;

    @Test
    @DisplayName("/join POST request test")
    public void joinOk() throws Exception {
        // Given
        String userid = "abc1232";
        String passwd = "987xyz";
        String name = "abc123";
        String email = "abc123@gmail.com";

        // When
        mockMvc.perform(post("/member/join")
                        .param("userid", userid)
                        .param("passwd", passwd)
                        .param("name", name)
                        .param("email", email))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/member/login"));
    }
}
