package com.example.myoceanproject.controller.myList;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.DiaryDTO;
import com.example.myoceanproject.domain.QDiaryDTO;
import com.example.myoceanproject.repository.DiaryRepository;
import com.example.myoceanproject.service.DiaryService;
import com.example.myoceanproject.type.CommunityCategory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/myList/*")
public class MyListRestController {

    private final DiaryService diaryService;
    private final DiaryRepository diaryRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @GetMapping(value={"/diary/{page}","/diary/{page}/{dateData}"})
    public DiaryDTO getCalendarList(HttpServletRequest request,@PathVariable("page") int page,@PathVariable(value="dateData",required=false) List<String> dateData){
        log.info("/diary/{page} in");
        Page<DiaryDTO> diaryDTOpage=null;
        log.info("dateData:"+dateData);

        HttpSession session=request.getSession();
        Long userId=(Long)session.getAttribute("userId");

        String decodeKeyword="";
        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setKeyword(decodeKeyword);
        //        0부터 시작,
        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        if(dateData!=null) {
            diaryDTOpage = diaryService.showSelectDiarys(pageable, dateData, userId, criteria);
        }else{
            diaryDTOpage = diaryService.showFirstDiarys(pageable,userId, criteria);
        }

        int endPage = (int)(Math.ceil(diaryDTOpage.getNumber()+1 / (double)10)) * 10;
        if(diaryDTOpage.getTotalPages() < endPage){
            endPage = diaryDTOpage.getTotalPages() == 0 ? 1 : diaryDTOpage.getTotalPages();
        }
        log.info(endPage + "end");

        DiaryDTO diaryDTO = new DiaryDTO();

        diaryDTO.setDiaryList(diaryDTOpage.getContent());
        diaryDTO.setEndPage(endPage);

        diaryDTOpage.getContent().stream().map(DiaryDTO::toString).forEach(log::info);

        return diaryDTO;
    }
}
