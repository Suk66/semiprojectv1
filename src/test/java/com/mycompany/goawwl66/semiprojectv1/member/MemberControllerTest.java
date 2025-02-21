package com.mycompany.goawwl66.semiprojectv1.member;

import com.mycompany.goawwl66.semiprojectv1.controller.MemberController;
import com.mycompany.goawwl66.semiprojectv1.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Mock
    private MemberService memberService;    // 가짜임.

    @InjectMocks
    private MemberController memberController;  // 얘가 제대로 받아서 작동이 되는지.

    private MockMvc mockMvc;

    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    @DisplayName("/join POST request test")
    public void joinOk() throws Exception {
        mockMvc.perform(post("/member/join").param("userid", "abc1231")
                            .param("password", "987xyz")
                .param("name", "abc123")
                .param("email", "abc@gmail.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/member/login"));
    }
}
