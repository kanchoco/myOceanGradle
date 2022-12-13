package com.example.myoceanproject.controller.myList;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.DiaryDTO;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.myoceanproject.entity.QDiary.diary;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/myList/*")
public class MyListRestController {

    private final DiaryService diaryService;
    private final DiaryRepository diaryRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @ResponseBody
    @PostMapping("/calendar")
    public String getCalendarList(@RequestBody ObjectNode objectNode){
        int year=Integer.parseInt(objectNode.get("year").asText().substring(0,4));
        int month=Integer.parseInt(objectNode.get("month").asText().substring(0,2));
        int day=objectNode.get("day").asInt();
//        LocalDateTime localDateTime1=null;
//        LocalDateTime localDateTime2=null;

//        if(year>0){
//            localDateTime1=LocalDateTime.of(year,12,1,1,1,1);
//            localDateTime2=LocalDateTime.of(year,12,31,1,1,1);
//            Page<DiaryDTO> diaryDTOS=diaryService.showYear()
//        }if(month>0){
//            localDateTime1=LocalDateTime.of(year,1,1,1,1,1);
//            localDateTime2=LocalDateTime.of(year,12,31,1,1,1);
//        }
//        List<DiaryDTO> diaryDTOS=jpaQueryFactory.selectFrom(diary).where(diary.createDate.between(localDateTime1,localDateTime2)).fetch();
        LocalDateTime localDateTime1=LocalDateTime.of(2021,0,0,0,0);

        log.info("getDayOfMonth()"+localDateTime1.getDayOfMonth());
        log.info("getDayOfWeek()"+localDateTime1.getDayOfWeek());
        log.info("getDayOfYear()"+localDateTime1.getDayOfYear());
        log.info("getHour()"+localDateTime1.getHour());
        log.info("getMinute()"+localDateTime1.getMinute());
        log.info("getSecond()"+localDateTime1.getSecond());


//        log.info("localDateTime:"+localDateTime);
        log.info("year:"+year);
        log.info("month:"+month);
        log.info("day:"+day);

        return "success";
    }
}
