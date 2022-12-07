//package com.example.myoceanproject.controller.questionBoard;
//
//import com.example.myoceanproject.domain.AskDTO;
//import com.example.myoceanproject.domain.CommunityPostDTO;
//import com.example.myoceanproject.domain.Criteria;
//import com.example.myoceanproject.entity.CommunityPost;
//import com.example.myoceanproject.service.AskService;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import java.util.List;
//
//import static com.example.myoceanproject.entity.QAsk.ask;
//
//@Slf4j
//@Controller
//@RequestMapping("/questionBoard/*")
//public class QusetionBoardController {
//
//    @Autowired
//    private AskService askService;
//
//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//
//    // 자주 묻는 질문 페이지
//    @GetMapping("/index")
//    public String questionBoard(){
//        return "app/questionBoard/questionBoard";
//    }
//    // 자주 묻는 질문 중 나의 질문 페이지
//    @GetMapping("myQuestion")
//    public String myQuestionBoard(Model model, Criteria criteria, HttpServletRequest request){
//        HttpSession session=request.getSession();
//
//        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);
//
//        Page<AskDTO> askDTOPage= askService.showPost(pageable, criteria,(Long)session.getAttribute("userId"));
//        int endPage = (int)(Math.ceil(askDTOPage.getNumber()+1 / (double)10)) * 10;
//        if(askDTOPage.getTotalPages() < endPage){
//            endPage = askDTOPage.getTotalPages() == 0 ? 1 : askDTOPage.getTotalPages();
//        }
//        log.info(endPage + "end");
//
//        log.info("pagenation:"+model.getAttribute("pagination"));
//        log.info("criteria:"+criteria.getPage());
//
//        model.addAttribute("listTotals", askDTOPage.getContent());
//        model.addAttribute("pagination", askDTOPage);
//        model.addAttribute("pageable", pageable);
//        model.addAttribute("criteria", criteria);
//        model.addAttribute("endPage", endPage);
//
//        return "app/questionBoard/myQuestion";
//    }
//    // 문의 사항 작성 페이지
//    @GetMapping("myQuestionWrite")
//    public String myQuestionWrite(){return "app/questionBoard/myQuestionWrite";}
//    // 유저 문의 사항
//    @GetMapping("managerAnswer")
//    public String managerAnswer(){return "app/questionBoard/managerAnswer";}
//}
