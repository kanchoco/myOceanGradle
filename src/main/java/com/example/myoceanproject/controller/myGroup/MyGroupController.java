package com.example.myoceanproject.controller.myGroup;

import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.DiaryDTO;
import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.service.MyGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/myGroup/*")
@Slf4j
public class MyGroupController {

    @Autowired
    private MyGroupService myGroupService;

    // 내가 참여한 모임 페이지
    @GetMapping("/joinGroup")
    public String joinGroup(Model model, Criteria criteria, HttpServletRequest request){
        HttpSession session=request.getSession();

        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        Page<GroupDTO> groupDTOPage= myGroupService.showJoinGroup(pageable,(Long)session.getAttribute("userId"), criteria);
        int endPage = (int)(Math.ceil(groupDTOPage.getNumber()+1 / (double)10)) * 10;
        if(groupDTOPage.getTotalPages() < endPage) {
            endPage = groupDTOPage.getTotalPages() == 0 ? 1 : groupDTOPage.getTotalPages();
        }
        log.info(endPage + "end");

        model.addAttribute("joinGroups", groupDTOPage);
        model.addAttribute("pagination", groupDTOPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("criteria", criteria);
        model.addAttribute("endPage", endPage);

        return "app/myGroup/myJoinGroup";
    }

    // 내가 오픈한 모임 페이지
    @GetMapping("/openGroup")
    public String openGroup(Model model,Criteria criteria, HttpServletRequest request){
        HttpSession session=request.getSession();

        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        Page<GroupDTO> groupDTOPage= myGroupService.showOpenGroup(pageable,(Long)session.getAttribute("userId"), criteria);
        int endPage = (int)(Math.ceil(groupDTOPage.getNumber()+1 / (double)10)) * 10;
        if(groupDTOPage.getTotalPages() < endPage) {
            endPage = groupDTOPage.getTotalPages() == 0 ? 1 : groupDTOPage.getTotalPages();
        }
        log.info(endPage + "end");

        model.addAttribute("openGroups", groupDTOPage);
        model.addAttribute("pagination", groupDTOPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("criteria", criteria);
        model.addAttribute("endPage", endPage);

        return "app/myGroup/myOpenGroup";
    }
}
