package com.example.myoceanproject.hadler;

import com.example.myoceanproject.domain.ChattingDTO;
import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.entity.GroupMember;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.GroupMemberRepository;
import com.example.myoceanproject.repository.GroupRepositoryImpl;
import com.example.myoceanproject.repository.UserRepositoryImpl;
import com.example.myoceanproject.repository.chatting.ChattingRepositoryImpl;
import com.example.myoceanproject.service.chattingService.ChattingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
//    List<WebSocketSession> sessions = new ArrayList<>();
    private final GroupRepositoryImpl groupRepositoryImpl;
    private final ObjectMapper objectMapper;
    private final ChattingRepositoryImpl chattingRepository;

    private final UserRepositoryImpl userRepositoryImple;
    private final GroupMemberRepository groupMemberRepository;
    private final ChattingService chattingService;

//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) {
//        System.out.println("afterConnectionEstablished:" + session);
//        sessions.add(session);
//    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        log.info("메세지 전송 = {} : {}",session,message.getPayload());
        String msg = message.getPayload();
        ChattingDTO chattingDTO = objectMapper.readValue(msg,ChattingDTO.class);
        GroupDTO groupDTO = groupRepositoryImpl.findGroupByGroupId(chattingDTO.getGroupId());
        UserDTO userDTO = userRepositoryImple.findUserById(chattingDTO.getSenderUserId());
        Long groupMemberId =chattingRepository.findGroupMemberIdByUserIdAndGroupId(chattingDTO.getSenderUserId(), chattingDTO.getGroupId());
        chattingDTO.setSenderUserFileSize(234342L);
        chattingDTO.setSenderUserFilePath(userDTO.getUserFilePath());
        chattingDTO.setSenderUserFileName("userFileName");
        chattingDTO.setSenderUserFileUuid("kjsdahgalsdkj");
        chattingDTO.setSenderGroupMemberId(groupMemberId);
        chattingDTO.setGroupId(groupDTO.getGroupId());
        chattingDTO.setGroupName(groupDTO.getGroupName());
        chattingDTO.setReceiverGroupMemberId(1L);
        chattingDTO.setChattingCreateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        chattingDTO.setChattingCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));

        Long userId = chattingDTO.getSenderUserId();
        Long groupId = chattingDTO.getGroupId();

        chattingService.saveMessage(userId, groupId, chattingDTO);


        log.info(chattingDTO.toString());
        groupDTO.handleMessage(session,chattingDTO,objectMapper);

    }

//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        System.out.println("afterConnectionEstablished:" + session + ":" + status);
//    }

}
