package com.example.myoceanproject.controller.myPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myPage/*")
public class MyPageController {
    // 마이 페이지
    @GetMapping("/index")
    public String myPage(){
        return "app/myPage/myPage";
    }

    // 비밀번호 변경 페이지
    @GetMapping("/passwordChange")
    public String passwordChange(){
        return "app/myPage/passwordChange";
    }
}
