package com.example.myoceanproject.controller.host;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/host/*")
public class HostController {

    // 소모임 작성 페이지
    @GetMapping("/index")
    public String host(){
        return "app/host/host";
    }
}
