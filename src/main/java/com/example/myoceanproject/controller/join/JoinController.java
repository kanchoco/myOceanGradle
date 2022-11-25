package com.example.myoceanproject.controller.join;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join/*")
public class JoinController {
    // 첫번째 회원가입 페이지
    @GetMapping("/joinOne")
    public String joinOne(){
        return "app/Join/before_join";
    }

    // 두번째 회원가입 페이지
    @GetMapping("/joinTwo")
    public String joinTwo(){
        return "app/Join/join_in";
    }

    // 세번째 회원가입 페이지
    @GetMapping("/joinThree")
    public String joinThree(){
        return "app/Join/join_in_second";
    }

}
