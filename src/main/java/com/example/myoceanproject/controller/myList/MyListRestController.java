package com.example.myoceanproject.controller.myList;

import com.example.myoceanproject.domain.*;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.entity.Diary;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.DiaryRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.service.DiaryService;
import com.example.myoceanproject.type.CommunityCategory;
import com.example.myoceanproject.type.DiaryCategory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sun.istack.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/myList/*")
public class MyListRestController {

    private final DiaryService diaryService;
    private final DiaryRepository diaryRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private UserRepository userRepository;

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

    // 게시글 작성 후 커뮤니티 페이지로 이동
    @Transactional
    @Modifying
    @PostMapping(value="/index", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public String diaryWriting(@RequestBody DiaryDTO diaryDTO, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        log.info("diaryDTO:"+diaryDTO);

        log.info("mylistrestcontroller in");
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        Long randomReceiver=0L;
        UserDTO selectedReceiver=null;



        Diary diary=diaryDTO.toEntity();
        User users=userRepository.findById(userId).get();
        diary.setUser(users);
        log.info("diaryDTO:"+diaryDTO);
        log.info("diary:"+diary);

        int receiverNullSearchNum=diaryService.registerShareDiary(userId,DiaryCategory.change(diaryDTO.getDiaryCategory()));
//        int searchMyDiaryDuplicate=diaryService.searchMyDiaryCount(userId,DiaryCategory.change(diaryDTO.getDiaryCategory()));
        int checkSameUser=diaryService.checkSameUser(userId);

        if(diaryDTO.getDiaryCategory().equals("나의 일기")){
            log.info("나의 일기 들어옴");
//            if(searchMyDiaryDuplicate==0) {
            User myDiary = userRepository.findById(userId).get();
            Diary myDiaryRegist = diaryDTO.toEntity();
            myDiaryRegist.setUser(myDiary);
            diaryRepository.save(myDiaryRegist);
            return "MyDiary";
//            }else{
//                log.info("나의 일기 중복 들어옴");
//                return "duplicate";
//            }
        }else if(diaryDTO.getDiaryCategory().equals("교환 일기")){
            log.info("교환 일기 들어옴");

            log.info("receiverNullSearchNum:"+receiverNullSearchNum);
            log.info("checkSameUser:"+checkSameUser);

//      유저가 처음 교환일기를 등록하는 부분
            if (receiverNullSearchNum == 0 && checkSameUser == 0) {
                log.info("유저가 처음 교환일기를 등록하는 부분");
                log.info("firstRegisterUser");
                log.info("diary:" + diary);
                diaryRepository.save(diary);
                return "standby";
            }
//      동일한 유저가 일기를 작성후 재작성시 처리 로직
            else if (receiverNullSearchNum == 0 && checkSameUser == 1) {
                log.info("동일한 유저가 일기를 작성후 재작성시 부분");
                log.info("registeredUser");
                return "alreadyRegistered";
            }
//      처음 일기를 등록한 사람이 있으며 다른 회원이 교환일기 신청시 교환되게 하는 부분
            else if (receiverNullSearchNum == 1 && checkSameUser == 0) {
                log.info("처음 일기를 등록한 사람이 있으며 다른 회원이 교환일기 신청시 교환되게 하는 부분");
//          이전에 교환일기를 신청한 회원의 정보에 현재 교환일기를 요청한 회원의 번호(receverId)를 저장한다.
                randomReceiver = diaryService.findNullReceiver(DiaryCategory.OPENDIARY).getUserId();
//                log.info("randomReceiver:"+randomReceiver);
                Diary diary1=diaryRepository.findByUser_UserId(randomReceiver);
                diary1.setReceiverUser(userRepository.findById(userId).get());

                log.info("after diary1:"+diary1);

//              교환일기를 신청한 회원의 정보를 저장한다.userId
                User requestExDiaryUser=userRepository.findById(userId).get();
                log.info("requestExDiaryUser:"+requestExDiaryUser);



                User responseExDiaryUser=userRepository.findById(diary1.getUser().getUserId()).get();
                log.info("responseExDiaryUser:"+responseExDiaryUser);


                Diary diary2 = diaryDTO.toEntity();
                log.info("diary2:"+diary2);


                diary2.setUser(requestExDiaryUser);
                log.info("diary2.setUser:"+diary2);


                diary2.setReceiverUser(responseExDiaryUser);
                log.info("diary2.setReceiverUser:"+diary2);


                diaryRepository.save(diary2);
                return "exchangeDiary";
            } else {
                log.info("??????????????????????????????????");
                log.info("other");
            }
        }
        return "communityIndex";
    }
}