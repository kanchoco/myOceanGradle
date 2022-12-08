package com.example.myoceanproject.controller.communtiy;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.service.community.CommunityPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/community/*")
public class CommunityController {

    private final CommunityPostService communityPostService;

    // 커뮤니티 페이지
    @GetMapping("/index")
    public String community(){
        return "app/community/community";
    }

    // 커뮤니티 상세 페이지
    @GetMapping("/read")
    public String communityDetail(Long communityPostId, Model model){
        model.addAttribute("communityPostDTO", communityPostService.find(communityPostId));
        return "app/community/detail";
    }

    // 게시글 수정하기
    @GetMapping("update")
    public String update(Long communityPostId, Model model){
        model.addAttribute("communityPostDTO", communityPostService.find(communityPostId));
        log.info(model.toString());
        return "app/community/community_register";
    }


    // 커뮤니티 글쓰기 페이지
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("communityPostDTO", new CommunityPostDTO());
        return "app/community/community_register";
    }

    // 게시글 삭제하기
    @GetMapping("/deleteBoard")
    public RedirectView delete(Long communityPostId){
        communityPostService.delete(communityPostId);
        return new RedirectView("/community/index");
    }

    /* 고민상담 게시판 */
    @GetMapping("/anonymous")
    public String anonymous(){ return "app/anonymous/anonymous"; }

    /* 모임 목록 */
    @GetMapping("/bulletin")
    public String bulletin(){ return "app/bulletin_board/bulletin_board"; }

}
