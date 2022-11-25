package com.example.myoceanproject.controller.communtiy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community/*")
public class CommunityController {
    // 커뮤니티 페이지
    @GetMapping("/index")
    public String Community(){
        return "app/community/community";
    }

    // 커뮤니티 댓글 페이지
    @GetMapping("/comment")
    public String comment(){
        return "app/community/community_comment";
    }

    // 커뮤니티 글쓰기 페이지
    @GetMapping("/register")
    public String register(){
        return "app/community/community_register";
    }

/*    @GetMapping("/write")
    public String write(){
        return "app/community/community_write";
    }*/

    // 커뮤니티 상세보기 페이지
    @GetMapping("/detail")
    public String detail(){
        return "app/community/detail";
    }

    /* 고민상담 게시판 */
    @GetMapping("/anonymous")
    public String anonymous(){ return "app/anonymous/anonymous"; }

    /* 모임 목록 */
    @GetMapping("/bulletin")
    public String bulletin(){ return "app/bulletin_board/bulletin_board"; }


}
