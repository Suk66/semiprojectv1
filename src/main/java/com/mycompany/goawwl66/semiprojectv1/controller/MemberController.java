package com.mycompany.goawwl66.semiprojectv1.controller;

import com.mycompany.goawwl66.semiprojectv1.domain.MemberDTO;
import com.mycompany.goawwl66.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join() {
        return "views/member/join";
    }

    @PostMapping("/join")
    public String joinOk(MemberDTO member) {
        String returnPage = "redirect:/member/error";
        log.info("submit된 회원 정보 : {}", member);
        // If
        if (memberService.newMember(member))
            return "redirect:/member/login";

        return returnPage;
    }

    @GetMapping("/myinfo")
    public String myinfo() {
        return "views/member/myinfo";
    }

    @GetMapping("/login")
    public String login() {

        return "views/member/login";
    }
}
