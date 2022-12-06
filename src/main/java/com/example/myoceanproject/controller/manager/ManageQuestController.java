package com.example.myoceanproject.controller.manager;
import com.example.myoceanproject.domain.QuestDTO;
import com.example.myoceanproject.repository.quest.QuestRepository;
import com.example.myoceanproject.service.QuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/quest/*")
public class ManageQuestController {


    private final QuestService questService;

    //    브라우저에서 JSON 타입으로 데이터를 전송하고 서버에서는 댓글의 처리 결과에 따라 문자열로 결과를 리턴한다.
//    consumes : 전달받은 데이터의 타입
//    produces : 콜백함수로 결과를 전달할 때의 타입
//    @RequestBody : 전달받은 데이터를 알맞는 매개변수로 주입
//    ResponseEntity : 서버의 상태코드, 응답 메세지 등을 담을 수 있는 타입

    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> addQuest(@RequestBody QuestDTO questDTO) throws UnsupportedEncodingException {
        questService.addQuest(questDTO);
        return new ResponseEntity<>(new String("write success".getBytes(), "UTF-8"), HttpStatus.OK);
    }
}
