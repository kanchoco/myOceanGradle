package com.example.myoceanproject.controller.chatting;

import com.example.myoceanproject.domain.ChattingDTO;
import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.repository.GroupRepositoryImpl;
import com.example.myoceanproject.service.chattingService.ChattingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chatting/*")
public class ChattingRestController {


    private final ChattingService chattingService;
    private final GroupRepositoryImpl groupRepositoryImpl;

    @GetMapping("/groupId/{groupId}")
    public List<ChattingDTO> list(@PathVariable("groupId") Long groupId, Model model){

        List<ChattingDTO> chattingDTOList= chattingService.showChatting(groupId);
        GroupDTO groupDTO =groupRepositoryImpl.findGroupByGroupId(groupId);
        model.addAttribute("room",groupDTO);
        return chattingDTOList;
    }

    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> write(@RequestBody ChattingDTO chattingDTO, HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session=request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        Long groupId = chattingDTO.getGroupId();

        chattingService.saveMessage(userId, groupId, chattingDTO);


        return new ResponseEntity<>(new String("write success".getBytes(), "UTF-8"), HttpStatus.OK);
    }


}