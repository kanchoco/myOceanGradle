package com.example.myoceanproject.controller.questionBoard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questionBoard/*")
public class QusetionBoardController {
    // 자주 묻는 질문 페이지
    @GetMapping("/index")
    public String questionBoard(){
        return "app/questionBoard/questionBoard";
    }
    // 자주 묻는 질문 중 나의 질문 페이지
    @GetMapping("myQuestion")
    public String myQuestionBoard(){return "app/questionBoard/myQuestion";}
    // 문의 사항 작성 페이지
    @GetMapping("myQuestionWrite")
    public String myQuestionWrite(){return "app/questionBoard/myQuestionWrite";}
    // 유저 문의 사항
    @GetMapping("managerAnswer")
    public String managerAnswer(){return "app/questionBoard/managerAnswer";}
}
