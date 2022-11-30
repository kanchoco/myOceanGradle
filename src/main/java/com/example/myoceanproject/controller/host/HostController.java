package com.example.myoceanproject.controller.host;

import com.example.myoceanproject.entity.Group;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/host/*")
public class HostController {

    // 소모임 작성 페이지
    @GetMapping("/index")
    public String host(){
        return "app/host/host";
    }

    @PostMapping("/index")
    public RedirectView host(Group group, RedirectAttributes redirectAttributes){

    }

}
