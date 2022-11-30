package com.example.myoceanproject.controller.host;

import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.entity.Group;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequestMapping("/host/*")
public class HostController {

    // 소모임 작성 페이지
    @GetMapping("/index")
    public String host(Model model){
        model.addAttribute("groupDTO", new GroupDTO());
        return "app/host/host";
    }

    @PostMapping("/index")
    public RedirectView host(GroupDTO groupDTO, RedirectAttributes redirectAttributes){
        log.info("ddddd");
        log.info(groupDTO.getGroupName());
        return new RedirectView("index");
    }
}
