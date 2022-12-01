package com.example.myoceanproject.controller.chatting;

import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.service.GroupService;
import com.example.myoceanproject.service.chattingService.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController // ViewResolver 관여하지 않는다.
@RequiredArgsConstructor
@RequestMapping("/chatting/*")
public class ChattingRestController {

    private final ChattingService chattingService;

    @GetMapping(value = "/list/",consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<List<GroupDTO>> list(@RequestBody UserDTO userDTO) throws UnsupportedEncodingException {
        List<GroupDTO> groupDTOList = chattingService.show(userDTO.getUserId());
        return ResponseEntity.ok().body(groupDTOList);
    }



}
