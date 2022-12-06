//package com.example.myoceanproject.controller.main;
//
//import com.example.myoceanproject.domain.GroupDTO;
//import com.example.myoceanproject.service.chattingService.ChattingService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/main/*")
//public class MainController {
//    private final ChattingService chattingService;
//
//
//
//    // 메인 페이지
//    @GetMapping("/index")
//    public String main(HttpServletRequest request, Model model){
//        HttpSession session=request.getSession();
//        Long userId = (Long)session.getAttribute("userId");
//
//        if(session.getAttribute("userId") != null) {
//            List<GroupDTO> groupDTOList = chattingService.show(userId);
//            model.addAttribute("groupDTOList", groupDTOList);
//            return "app/Main/main";
//        }else {
//            return "app/Main/main";
//        }
//    }
//
//}