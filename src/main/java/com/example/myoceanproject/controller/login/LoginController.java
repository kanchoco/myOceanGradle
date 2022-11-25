package com.example.myoceanproject.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/*")
public class LoginController {

    // 로그인 페이지
    @GetMapping("/index")
    public String login(){
        return "app/login/login";
    }

    // 로그인 페이지
    @GetMapping("/findPw")
    public String findPw(){
        return "app/login/findPw";
    }

    // 로그인 페이지
    @GetMapping("/findPwComplete")
    public String findPwComplete(){
        return "app/login/findPwComplete";
    }

    // 로그인 페이지
    @GetMapping("/changePassword")
    public String changePassword(){
        return "app/login/changePassword";
    }

    // 로그인 페이지
    @GetMapping("/changePwComplete")
    public String changePwComplete(){
        return "app/login/changePwComplete";
    }
}
