package com.example.myoceanproject.controller.host;

import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/host/*")
public class HostController {

    private final GroupService groupService;

    // 게시글 목록
    @GetMapping("/group-list")
    public String list(Model model){
        model.addAttribute("groupDTOs", groupService.show());
        return "app/bulletin_board/bulletin_board";
    }

    @GetMapping("/display-list")
    public byte[] display(String fileName) throws IOException {
        log.info("fileName: " + fileName);
        File file = new File("C:/upload/group", fileName);
        return FileCopyUtils.copyToByteArray(file);
    }

    // 소모임 작성 페이지
    @GetMapping("/index")
    public String host(Model model){
        model.addAttribute("groupDTO", new GroupDTO());
        return "app/host/host";
    }

    // 게시글 수정, 게시글 상세보기
    @GetMapping(value = {"/read", "/update"})
    public void read(Long groupId, Model model){
        log.info("========================");
        log.info("groupDTO : " + groupId);
        log.info("model: " + model);
        model.addAttribute("groupDTO", groupService.find(groupId));
    }

    @PostMapping("/update")
    public RedirectView update(GroupDTO groupDTO, Criteria criteria, RedirectAttributes redirectAttributes){
        Group group = groupDTO.toEntity();
        groupService.update(group);

        redirectAttributes.addAttribute("groupId", group.getGroupId());
//        redirectAttributes.addAttribute("page", criteria.getPage());
//        redirectAttributes.addAttribute("amount", criteria.getAmount());
        return new RedirectView("/group/detail");
    }

}
