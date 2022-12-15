package com.example.myoceanproject.controller.myQuest;

import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.QuestDTO;
import com.example.myoceanproject.service.PointService;
import com.example.myoceanproject.service.UserService;
import com.example.myoceanproject.service.quest.QuestAchievementService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/myCompleteQuest/*")
public class MyQuestRestController {
    //    브라우저에서 JSON 타입으로 데이터를 전송하고 서버에서는 댓글의 처리 결과에 따라 문자열로 결과를 리턴한다.
//    consumes : 전달받은 데이터의 타입
//    produces : 콜백함수로 결과를 전달할 때의 타입
//    @RequestBody : 전달받은 데이터를 알맞는 매개변수로 주입
//    ResponseEntity : 서버의 상태코드, 응답 메세지 등을 담을 수 있는 타입
    private final QuestAchievementService questAchievementService;
    private final UserService userService;

    private final PointService pointService;
    // 완료한 퀘스트 페이지
    @GetMapping(value = "/{page}")
    public QuestDTO completeQuest(@PathVariable int page,@PathVariable(required = false) String keyword, HttpServletRequest request){

        Criteria criteria = new Criteria();
        criteria.setPage(page);

        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 4);
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        log.info(userId.toString());
        Page<QuestDTO> questDTOList = questAchievementService.showMyAchievement(userId, pageable);
        int endPage = (int)(Math.ceil(questDTOList.getNumber()+1 / (double)4)) * 10;
        if(questDTOList.getTotalPages() < endPage){
            endPage = questDTOList.getTotalPages() == 0 ? 1 : questDTOList.getTotalPages();
        }

        QuestDTO questDTO = new QuestDTO();

        questDTO.setQuestList(questDTOList.getContent());
        questDTO.setEndPage(endPage);


        return questDTO;
    }


    @GetMapping(value = "myBadge/{page}")
    public QuestDTO myBadge(@PathVariable int page,@PathVariable(required = false) String keyword, HttpServletRequest request){
        log.info("================================REST CONTROLLER 들어옴===================================");

        Criteria criteria = new Criteria();
        criteria.setPage(page);

        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        log.info(userId.toString());
        Page<QuestDTO> questDTOList = questAchievementService.showMyAchievement(userId, pageable);
        int endPage = (int)(Math.ceil(questDTOList.getNumber()+1 / (double)10)) * 10;
        if(questDTOList.getTotalPages() < endPage){
            endPage = questDTOList.getTotalPages() == 0 ? 1 : questDTOList.getTotalPages();
        }

        QuestDTO questDTO = new QuestDTO();

        questDTO.setQuestList(questDTOList.getContent());
        questDTO.setEndPage(endPage);


        return questDTO;
    }

    @GetMapping(value = "/badge")
    public List<QuestDTO> myBadge(HttpServletRequest request) throws JSONException {
        log.info("================================REST CONTROLLER 들어옴===================================");
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        List<QuestDTO> questDTOList = new ArrayList<>();
        for (int i = 0; i<12 ;i++) {
            QuestDTO questDTO = new QuestDTO();
            questDTO.setUserFilePath(userService.findUser(userId).getUserFilePath());
            questDTO.setUserFileName(userService.findUser(userId).getUserFileName());
            questDTO.setUserFileUuid(userService.findUser(userId).getUserFileUuid());
            questDTO.setUserFileName(userService.findUser(userId).getUserFileName());
            questDTO.setUserNickName(userService.findUser(userId).getUserNickname());
            questDTO.setRewardPointTotal(pointService.showRewardPointTotal(userId));
            questDTO.setMonth(i+1);
            questDTO.setMonthlyCount(questAchievementService.showMonthlyAchievementCount(userId, i+1));
            questDTO.setBadgeCount(questAchievementService.showMyBadgeNumber(userId));
            questDTOList.add(questDTO);
            log.info("============================================================================");
            log.info("============================================================================");
            log.info("============================================================================");
            log.info(String.valueOf(questAchievementService.showMonthlyAchievementCount(userId, i+1)));
            log.info("============================================================================");
            log.info("============================================================================");
            log.info("============================================================================");

        }
        log.info("================================REST CONTROLLER 들어옴===================================");
        return questDTOList;
    }
}
