package com.example.myoceanproject.controller.myPoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myPoint/*")
public class MyPointController {
    // 마이 포인트 페이지
    @GetMapping("/index")
    public String myPoint(){
        return "app/myPoint/myPoint";
    }
}
