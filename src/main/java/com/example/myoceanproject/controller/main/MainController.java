package com.example.myoceanproject.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/*")
public class MainController {

    // 메인 페이지
    @GetMapping("/index")
    public String main(){
        return "app/main/main";
    }
}
