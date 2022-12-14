package com.example.myoceanproject.controller.myQuest;

import com.example.myoceanproject.domain.QuestDTO;
import com.example.myoceanproject.service.quest.QuestAchievementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    // 완료한 퀘스트 페이지
    @GetMapping(value = "/myCollection")
    public List<QuestDTO> completeQuest(HttpServletRequest request){
        log.info("================================REST CONTROLLER 들어옴===================================");
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        log.info(userId.toString());
        List<QuestDTO> questDTOList = questAchievementService.showMyAchievement(userId);
       return questDTOList;
    }
}
