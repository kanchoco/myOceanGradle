package com.example.myoceanproject.controller.mySpace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myspace/*")
public class MySpaceController {
    // 나만의 공간 페이지
    @GetMapping("/index")
    public String mySpace(){
        return "app/mySpace/mySpace";
    }

}
