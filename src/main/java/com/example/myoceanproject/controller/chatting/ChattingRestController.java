package com.example.myoceanproject.controller.chatting;

import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.service.GroupService;
import com.example.myoceanproject.service.chattingService.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController // ViewResolver 관여하지 않는다.
@RequiredArgsConstructor
@RequestMapping("/chatting/*")
public class ChattingRestController {

    private final ChattingService chattingService;

//    @GetMapping("/list/{user}")
//    public GroupDTO list(@PathVariable("user")Long userId){
//        return new List<GroupDTO>(chattingService.show(userId));
//    }



}
