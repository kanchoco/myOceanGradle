package com.example.myoceanproject.controller.host;

import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.service.GroupService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/host/*")
public class HostController {

    private final GroupService groupService;

    // 소모임 작성 페이지
    @GetMapping("/index")
    public String host(Model model){
        model.addAttribute("groupDTO", new GroupDTO());
        return "app/host/host";
    }

    @PostMapping("/index")
    public RedirectView host(GroupDTO groupDTO, RedirectAttributes redirectAttributes){
        log.info("==============================");
        log.info("그룹명: " + groupDTO.getGroupName());
        log.info("그룹카테고리: " + groupDTO.getGroupCategory());
        log.info("그룹 최소인원: " + groupDTO.getMinMember());
        log.info("그룹 최대인원: " + groupDTO.getMaxMember());
        log.info("그룹 포인트: " + groupDTO.getGroupPoint());
        log.info("국내/해외 여부: " + groupDTO.getGroupOverSea());
        log.info("모임 타입: " + groupDTO.getGroupLocationType());
        log.info("모임 장소 이름: " + groupDTO.getGroupLocationName());
        log.info("모임 장소: " + groupDTO.getGroupLocation());
        log.info("모임 상세 주소: " + groupDTO.getGroupLocationDetail());
        log.info("주차 가능 여부: " + groupDTO.getGroupParkingAvailable());
        log.info("모임 주의사항: " + groupDTO.getGroupMoreInformation());
        log.info("상세 내용: " + groupDTO.getGroupContent());
        Group group = groupDTO.toEntity();
        groupService.add(group);
        return new RedirectView("index");
    }
}
