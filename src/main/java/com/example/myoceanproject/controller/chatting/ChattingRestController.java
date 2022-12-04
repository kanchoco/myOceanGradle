package com.example.myoceanproject.controller.chatting;

import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.service.chattingService.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/chatting/*")
public class ChattingRestController {


//    @Autowired
//    private SimpMessageSendingOperations messagingTemplate;
//
//    @MessageMapping("/TTTx")
//    @SendTo("/topic/message")
//    public String tttx(String message) throws Exception {
//        System.out.println("TTT>>" + message);
//        return message;
//    }
//
//    @MessageMapping("/TTT")
////	@SendTo("/topic/message")
//    public Message ttt(Message message) throws Exception {
//        System.out.println("TTT>>" + message);
////        System.out.println("ID=" + message.get);
////        System.out.println("MSG=" + message.getMsg());
//
////        messagingTemplate.convertAndSend("/topic/" + message.getRoomid(), message.getMsg());
////		messagingTemplate.convertAndSendToUser(message.getId(), "/topic/" + message.getRoomid(), message.getMsg());
//
//
//        return message;
//
//
//    }
}