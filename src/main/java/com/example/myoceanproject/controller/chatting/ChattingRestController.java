package com.example.myoceanproject.controller.chatting;

import com.example.myoceanproject.domain.ChattingDTO;
import com.example.myoceanproject.service.chattingService.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/chatting/*")
public class ChattingRestController {


    private final ChattingService chattingService;
    @GetMapping("/groupId/{groupId}")
    public List<ChattingDTO> list(@PathVariable("groupId") Long groupId){

        System.out.println("====================================================================");
        System.out.println("컨트롤러"+ groupId);
        System.out.println("====================================================================");
        List<ChattingDTO> chattingDTOList= chattingService.showChatting(groupId);
        return chattingDTOList;
    }


}