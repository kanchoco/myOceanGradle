package com.example.myoceanproject.controller.questionBoard;

import com.example.myoceanproject.domain.AskDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.service.AskService;
import com.example.myoceanproject.type.AskCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

import java.util.List;

import static com.example.myoceanproject.entity.QAsk.ask;

@Slf4j
@Controller
@RequestMapping("/questionBoard/*")
public class QusetionBoardController {

    @Autowired
    private AskService askService;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    // 자주 묻는 질문 페이지
    @GetMapping("/index")
    public String questionBoard(Model model, Criteria criteria, HttpServletRequest request){

        HttpSession session=request.getSession();

        log.info("index in");
        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        Page<AskDTO> askDTOPage= askService.showAllQuestion(pageable, criteria,(Long)session.getAttribute("userId"));

        int endPage = (int)(Math.ceil((askDTOPage.getNumber()+1) / (double)10)) * 10;
        if(askDTOPage.getTotalPages() < endPage){
            endPage = askDTOPage.getTotalPages() == 0 ? 1 : askDTOPage.getTotalPages();
        }
        log.info(endPage + "end");

        for(AskDTO pages:askDTOPage){
            log.info("datas:"+pages);
        }
//        for(int i=0;i<10;i++){
//            log.info("askdata:"+askDTOPage.getContent());
//            log.info("askStatus:"+askDTOPage.getContent().get(i).getAskStatus());
//            log.info("criteria:"+criteria);
//            log.info("pageable:"+pageable);
//        }
        log.info("pagenation:"+model.getAttribute("pagination"));
        log.info("criteria:"+criteria.getPage());

        model.addAttribute("Questions", askDTOPage.getContent());
        model.addAttribute("pagination", askDTOPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("criteria", criteria);
        model.addAttribute("endPage", endPage);

        return "app/questionBoard/questionBoard";
    }

    @GetMapping("/questionUsingInfo")
    public String questionUsingInfo(Model model, Criteria criteria, HttpServletRequest request){
        HttpSession session=request.getSession();

        log.info("index in");
        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        log.info("criteria:"+criteria);

        Page<AskDTO> askDTOPage= askService.showQuestion(pageable, AskCategory.USINGINFO ,criteria,(Long)session.getAttribute("userId"));
        log.info("pageable:"+pageable);
        int endPage = (int)(Math.ceil((askDTOPage.getNumber()+1) / (double)10)) * 10;
        if(askDTOPage.getTotalPages() < endPage){
            endPage = askDTOPage.getTotalPages() == 0 ? 1 : askDTOPage.getTotalPages();
        }
        log.info(endPage + "end");

        for(AskDTO pages:askDTOPage){
            log.info("datas:"+pages);
        }

        log.info("pagenation:"+model.getAttribute("pagination"));
        log.info("criteria:"+criteria.getPage());

        model.addAttribute("questionUsingInfos", askDTOPage.getContent());
        model.addAttribute("pagination", askDTOPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("criteria", criteria);
        model.addAttribute("endPage", endPage);

        return "app/questionBoard/questionUsingInfo";
    }

    @GetMapping("/questionUserInfo")
    public String questionUserInfo(Model model, Criteria criteria, HttpServletRequest request){
        HttpSession session=request.getSession();

        log.info("index in");
        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        Page<AskDTO> askDTOPage= askService.showQuestion(pageable, AskCategory.ACCOUNTINFO ,criteria,(Long)session.getAttribute("userId"));
        int endPage = (int)(Math.ceil((askDTOPage.getNumber()+1) / (double)10)) * 10;
        if(askDTOPage.getTotalPages() < endPage){
            endPage = askDTOPage.getTotalPages() == 0 ? 1 : askDTOPage.getTotalPages();
        }
        log.info(endPage + "end");

        for(AskDTO pages:askDTOPage){
            log.info("datas:"+pages);
        }

        log.info("pagenation:"+model.getAttribute("pagination"));
        log.info("criteria:"+criteria.getPage());

        model.addAttribute("questionUserInfos", askDTOPage.getContent());
        model.addAttribute("pagination", askDTOPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("criteria", criteria);
        model.addAttribute("endPage", endPage);

        return "app/questionBoard/questionUserInfo";
    }

    @GetMapping("/questionPointInfo")
    public String questionPointInfo(Model model, Criteria criteria, HttpServletRequest request){
        HttpSession session=request.getSession();

        log.info("index in");
        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        Page<AskDTO> askDTOPage= askService.showQuestion(pageable, AskCategory.POINTINFO ,criteria,(Long)session.getAttribute("userId"));
        int endPage = (int)(Math.ceil((askDTOPage.getNumber()+1) / (double)10)) * 10;
        if(askDTOPage.getTotalPages() < endPage){
            endPage = askDTOPage.getTotalPages() == 0 ? 1 : askDTOPage.getTotalPages();
        }
        log.info(endPage + "end");

        for(AskDTO pages:askDTOPage){
            log.info("datas:"+pages);
        }

        log.info("pagenation:"+model.getAttribute("pagination"));
        log.info("criteria:"+criteria.getPage());

        model.addAttribute("questionPointInfos", askDTOPage.getContent());
        model.addAttribute("pagination", askDTOPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("criteria", criteria);
        model.addAttribute("endPage", endPage);

        return "app/questionBoard/questionPointInfo";
    }

    @GetMapping("/questionQuestInfo")
    public String questionQuestInfo(Model model, Criteria criteria, HttpServletRequest request){
        HttpSession session=request.getSession();

        log.info("index in");
        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        Page<AskDTO> askDTOPage= askService.showQuestion(pageable, AskCategory.QUESTINFO ,criteria,(Long)session.getAttribute("userId"));
        int endPage = (int)(Math.ceil((askDTOPage.getNumber()+1) / (double)10)) * 10;
        if(askDTOPage.getTotalPages() < endPage){
            endPage = askDTOPage.getTotalPages() == 0 ? 1 : askDTOPage.getTotalPages();
        }
        log.info(endPage + "end");

        for(AskDTO pages:askDTOPage){
            log.info("datas:"+pages);
        }

        log.info("pagenation:"+model.getAttribute("pagination"));
        log.info("criteria:"+criteria.getPage());

        model.addAttribute("questionQuestInfos", askDTOPage.getContent());
        model.addAttribute("pagination", askDTOPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("criteria", criteria);
        model.addAttribute("endPage", endPage);

        return "app/questionBoard/questionQuestInfo";
    }

    // 자주 묻는 질문 중 나의 질문 페이지
    @GetMapping("myQuestion")
    public String myQuestionBoard(Model model, Criteria criteria, HttpServletRequest request){
        HttpSession session=request.getSession();

        log.info("myquestion in");
        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        Page<AskDTO> askDTOPage= askService.showAllMyAsk(pageable, criteria,(Long)session.getAttribute("userId"));
        int endPage = (int)(Math.ceil((askDTOPage.getNumber()+1) / (double)10)) * 10;
        if(askDTOPage.getTotalPages() < endPage){
            endPage = askDTOPage.getTotalPages() == 0 ? 1 : askDTOPage.getTotalPages();
        }
        log.info(endPage + "end");

//        for(int i=0;i<10;i++){
//            log.info("askdata:"+askDTOPage.getContent());
//            log.info("askStatus:"+askDTOPage.getContent().get(i).getAskStatus());
//            log.info("criteria:"+criteria);
//            log.info("pageable:"+pageable);
//        }
        log.info("pagenation:"+model.getAttribute("pagination"));
        log.info("criteria:"+criteria.getPage());

        model.addAttribute("myQuestions", askDTOPage.getContent());
        model.addAttribute("pagination", askDTOPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("criteria", criteria);
        model.addAttribute("endPage", endPage);

        return "app/questionBoard/myQuestion";
    }
    // 문의 사항 작성 페이지
    @GetMapping("myQuestionWrite")
    public String myQuestionWrite(){return "app/questionBoard/myQuestionWrite";}
    // 유저 문의 사항
    @GetMapping("managerAnswer")
    public String managerAnswer(){return "app/questionBoard/managerAnswer";}
}
