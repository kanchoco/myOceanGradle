package com.example.myoceanproject.controller.mySpace;

import com.example.myoceanproject.domain.ToDoListDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/myspace/*")
@RequiredArgsConstructor
@Slf4j
public class MySpaceController {
    private final MySpaceService mySpaceService;
    // 나만의 공간 페이지
    @GetMapping("/index")
    public String mySpace(Model model){
        //모델(Model)은 자바쪽 내용을 화면으로 전달하는 역할을 한다.
        //model.addAttribute("화면쪽에서 사용할 키 값", "전달할 내용")
//        th:text=${키값}

        model.addAttribute("noticeDayWrap",mySpaceService.returnToday());
        model.addAttribute("nowTime",mySpaceService.returnToday());
        model.addAttribute("todayList",mySpaceService.showAllByToday());
        return "app/mySpace/mySpace";
    }

    @PostMapping("/index")
    public RedirectView mySpaceResister(String toDoListContent, String toDoListSelectDate) {

        mySpaceService.post(toDoListContent,toDoListSelectDate);
        return new RedirectView("/myspace/index"); }




}
