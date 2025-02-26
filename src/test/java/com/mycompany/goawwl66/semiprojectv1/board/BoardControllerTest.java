package com.mycompany.goawwl66.semiprojectv1.board;

import com.mycompany.goawwl66.semiprojectv1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class BoardControllerTest {

   private MockMvc mockMvc;
    private MemberRepository memberRepository;
    @Test
    @DisplayName("/list Get request test")
    public void list() throws Exception {
        // Given


        // When
        mockMvc.perform(get("/board/list"))

                .andExpect(status().isOk())
                .andDo(print());
    }
}
