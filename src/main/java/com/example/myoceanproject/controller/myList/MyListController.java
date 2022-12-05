package com.example.myoceanproject.controller.myList;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.entity.QCommunityPost;
import com.example.myoceanproject.repository.community.post.CommunityPostRepository;
import com.example.myoceanproject.service.community.CommunityPostService;
import com.example.myoceanproject.service.community.CommunityReplyService;
import com.example.myoceanproject.type.CommunityCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static com.example.myoceanproject.entity.QCommunityPost.communityPost;

@Slf4j
@Controller
@RequestMapping("/myList/*")
public class MyListController {
    @Autowired
    private CommunityPostService postService;

    @Autowired
    private CommunityPostRepository communityPostRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    // 내가 쓴 일기 페이지
    @GetMapping("/myDiary")
    public String myDiary(){
        return "app/myList/myDiary";
    }

    // 내가 보낸 교환일기 페이지
    @GetMapping("/myExchangeDiary")
    public String myExchangeDiary(){
        return "app/myList/myExchangeDiary";
    }

    // 카테고리 ( 합치는 부분!)
    @GetMapping("/myListCategory")
    public String myListCategory(){
        return "app/myList/myList";
    }

    //  책 이야기
    @GetMapping("/myListBook")
    public String myListBook(Model model,Criteria criteria){
        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        Page<CommunityPostDTO> postDTOPage= postService.showCounseling(pageable, CommunityCategory.BOOK, criteria);
        int endPage = (int)(Math.ceil(postDTOPage.getNumber()+1 / (double)10)) * 10;
        if(postDTOPage.getTotalPages() < endPage) {
            endPage = postDTOPage.getTotalPages() == 0 ? 1 : postDTOPage.getTotalPages();
        }
        log.info(endPage + "end");

        model.addAttribute("books", postDTOPage.getContent());
        model.addAttribute("pagination", postDTOPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("criteria", criteria);
        model.addAttribute("endPage", endPage);

        return "app/myList/myListBookBoard";
    }

    //  요리 이야기
    @GetMapping("/myListCook")
    public String myListCook(Model model,Criteria criteria){
        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        Page<CommunityPostDTO> postDTOPage= postService.showCounseling(pageable, CommunityCategory.COOK, criteria);
        int endPage = (int)(Math.ceil(postDTOPage.getNumber()+1 / (double)10)) * 10;
        if(postDTOPage.getTotalPages() < endPage) {
            endPage = postDTOPage.getTotalPages() == 0 ? 1 : postDTOPage.getTotalPages();
        }
        log.info(endPage + "end");

        model.addAttribute("cooks", postDTOPage.getContent());
        model.addAttribute("pagination", postDTOPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("criteria", criteria);
        model.addAttribute("endPage", endPage);

        return "app/myList/myListCookBoard";
    }

    //  고민상담
    @GetMapping("/myListCounseling")
    public String myListCounseling(Model model,Criteria criteria){
        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        Page<CommunityPostDTO> postDTOPage= postService.showCounseling(pageable, CommunityCategory.COUNSELING, criteria);
        int endPage = (int)(Math.ceil(postDTOPage.getNumber()+1 / (double)10)) * 10;
        if(postDTOPage.getTotalPages() < endPage) {
            endPage = postDTOPage.getTotalPages() == 0 ? 1 : postDTOPage.getTotalPages();
        }
        log.info(endPage + "end");

        model.addAttribute("counselings", postDTOPage.getContent());
        model.addAttribute("pagination", postDTOPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("criteria", criteria);
        model.addAttribute("endPage", endPage);

        return "app/myList/myListCounselingBoard";
    }

    //  운동 이야기
    @GetMapping("/myListExercise")
    public String myListExercise(Model model,Criteria criteria){
        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        Page<CommunityPostDTO> postDTOPage= postService.showCounseling(pageable, CommunityCategory.EXERCISE, criteria);
        int endPage = (int)(Math.ceil(postDTOPage.getNumber()+1 / (double)10)) * 10;
        if(postDTOPage.getTotalPages() < endPage) {
            endPage = postDTOPage.getTotalPages() == 0 ? 1 : postDTOPage.getTotalPages();
        }
        log.info(endPage + "end");

        model.addAttribute("exercises", postDTOPage.getContent());
        model.addAttribute("pagination", postDTOPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("criteria", criteria);
        model.addAttribute("endPage", endPage);

        return "app/myList/myListExerciseBoard";
    }

    // 자유게시판
    @GetMapping("/myListFree")
    public String myListFree(Model model,Criteria criteria){
        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        Page<CommunityPostDTO> postDTOPage= postService.showCounseling(pageable, CommunityCategory.FREEBOARD, criteria);
        int endPage = (int)(Math.ceil(postDTOPage.getNumber()+1 / (double)10)) * 10;
        if(postDTOPage.getTotalPages() < endPage) {
            endPage = postDTOPage.getTotalPages() == 0 ? 1 : postDTOPage.getTotalPages();
        }
        log.info(endPage + "end");

        model.addAttribute("freeboards", postDTOPage.getContent());
        model.addAttribute("pagination", postDTOPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("criteria", criteria);
        model.addAttribute("endPage", endPage);

        return "app/myList/myListFreeBoard";
    }

    // 영화이야기
    @GetMapping("/myListMovie")
    public String myListMovie(Model model,Criteria criteria){
        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        Page<CommunityPostDTO> postDTOPage= postService.showCounseling(pageable, CommunityCategory.MOVIE, criteria);
        int endPage = (int)(Math.ceil(postDTOPage.getNumber()+1 / (double)10)) * 10;
        if(postDTOPage.getTotalPages() < endPage) {
            endPage = postDTOPage.getTotalPages() == 0 ? 1 : postDTOPage.getTotalPages();
        }
        log.info(endPage + "end");

        model.addAttribute("movies", postDTOPage.getContent());
        model.addAttribute("pagination", postDTOPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("criteria", criteria);
        model.addAttribute("endPage", endPage);

        return "app/myList/myListMovieBoard";
    }

    // 게시글 없는 페이지 표시할때!
    @GetMapping("/myListNocontents")
    public String myListNoContents(){
        return "app/myList/myListNoContents";
    }

    // 내가 쓴 게시글 (전체)
    @GetMapping("/myListTotal")
    public String myListTotal(Model model, Criteria criteria){
        //        0부터 시작,
        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        Page<CommunityPostDTO> postDTOPage= postService.showPost(pageable, criteria);
        int endPage = (int)(Math.ceil(postDTOPage.getNumber()+1 / (double)10)) * 10;
        if(postDTOPage.getTotalPages() < endPage){
            endPage = postDTOPage.getTotalPages() == 0 ? 1 : postDTOPage.getTotalPages();
        }
        log.info(endPage + "end");

        List<CommunityPost> posts=jpaQueryFactory.selectFrom(communityPost).orderBy(communityPost.communityPostId.desc()).fetch();
        log.info("posts size:"+posts.size());
        log.info("postDTOpage size:"+postDTOPage.getContent().size());
        for(int i=0;i<10;i++){

//            try {
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//
//                // 문자열 -> Date
//                Date date = formatter.parse(postDTOPage.getContent().get(i).getCreateDate());
//                LocalDateTime localDateTime= (LocalDateTime)Jsr310Converters.StringToLocalDateTimeConverter.valueOf(postDTOPage.getContent().get(i).getCreateDate());
//                log.info("date:"+date);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }

        }


        model.addAttribute("listTotals", postDTOPage.getContent());
        model.addAttribute("pagination", postDTOPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("criteria", criteria);
        model.addAttribute("endPage", endPage);

        return "app/myList/myListTotal";
    }



}
