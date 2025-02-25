package com.mycompany.goawwl66.semiprojectv1.controller;

import com.mycompany.goawwl66.semiprojectv1.domain.Member;
import com.mycompany.goawwl66.semiprojectv1.domain.MemberDTO;
import com.mycompany.goawwl66.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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

    // ResponseEntity는 스프링에서 HTTP와 관련된 기능을 구현할 때 사용
    // 이것을 활용하면 Used
    // 상태코드, HTTP헤더, HTTP본문등을 명시적으로 설정 가능
    @PostMapping("/join")
    public ResponseEntity<?> joinOk(MemberDTO member) {
        // 회원 가입 처리시
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        log.info("submit된 회원 정보 : {}", member);

        try {
            // 정상 처리시 상태코드 200으로 응답
            memberService.newMember(member);
                response = ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            // 비정상 처리시 상태코드 400으로 응답 - 클라이언트 잘못
            // 중복 아이디나 중복 이메일 사용시
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();
        }catch (Exception e) {
            // 비정상 처리시 상태코드 500으로 응답 - 서버 잘못
            e.printStackTrace();
        }

        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginOk(MemberDTO member, HttpSession session) {
        // 회원 가입 처리시
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        log.info("submit된 회원 정보 : {}", member);

        try {
            // 정상 처리시 상태코드 200으로 응답
            Member loginUser = memberService.loginMember(member);
            session.setAttribute("loginUser", loginUser);
            session.setMaxInactiveInterval(600);     // 세션 유지시간 10min

            response = ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            // 비정상 처리시 상태코드 400으로 응답 - 클라이언트 잘못
            // 아이디나 비번 잘못 입력시
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();
        }catch (Exception e) {
            // 비정상 처리시 상태코드 500으로 응답 - 서버 잘못
            e.printStackTrace();
        }

        return response;
    }
    // 2월 25일 11시 24분 modify
    @GetMapping("/myinfo")
    public String myinfo(HttpSession session) {
        // 로그인에서 myinfo 로 보내는 session
        String returnUrl = "views/member/loin";

        // 세션변수가 생성되어 있다면 myinfo로 이동
        if (session.getAttribute("loginUser") != null) {
            returnUrl = "views/member/myinfo";
        }
        return returnUrl;
    }

    @GetMapping("/login")
    public String login() {

        return "views/member/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();   // 세션 제거
        return "redirect:/";
    }


}
