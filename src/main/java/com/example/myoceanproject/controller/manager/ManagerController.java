package com.example.myoceanproject.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/*")
public class ManagerController {
    // 고민상담 게시글 관리
    @GetMapping("/counselingBoard")
    public String counselingBoard(){
        return "app/manager/admin_counseling_board";
    }

    // 고민상담 댓글 관리
    @GetMapping("/counselingReply")
    public String counselingReply(){
        return "app/manager/admin_counseling_board_reply";
    }

    // 자유게시판 게시글 관리
    @GetMapping("/freeBoard")
    public String freeBoard(){
        return "app/manager/admin_free_board";
    }

    // 자유게시판 댓글 관리
    @GetMapping("/freeReply")
    public String freeReply(){
        return "app/manager/admin_free_board_reply";
    }

    //  모임 개설 신청 관리
    @GetMapping("/groupOpenRequest")
    public String groupopenRequest(){
        return "app/manager/admin_group_open_request";
    }

    //  대쉬보드
    @GetMapping("/index")
    public String layOut(){
        return "app/manager/admin_dashboard";
    }

    //  배너 관리
    @GetMapping("/pageBanner")
    public String pageBanner(){
        return "app/manager/admin_page_banner";
    }

    //  퀘스트 관리
    @GetMapping("/questList")
    public String questList(){
        return "app/manager/admin_quest_list";
    }

    //  문의사항 목록
    @GetMapping("/questions")
    public String questions(){
        return "app/manager/admin_questions";
    }

    //  회원 목록
    @GetMapping("/userList")
    public String userList(){
        return "app/manager/admin_user_list";
    }









}
