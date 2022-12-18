package com.example.myoceanproject.handler;

import com.example.myoceanproject.domain.ChattingDTO;
import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.repository.GroupMemberRepository;
import com.example.myoceanproject.repository.GroupRepositoryImpl;
import com.example.myoceanproject.repository.UserRepositoryImpl;
import com.example.myoceanproject.repository.chatting.ChattingRepositoryImpl;
import com.example.myoceanproject.service.chattingService.ChattingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final GroupRepositoryImpl groupRepositoryImpl;
    private final ObjectMapper objectMapper;
    private final ChattingRepositoryImpl chattingRepository;
//    public static List<WebSocketSession> sessions = new ArrayList<>();

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
        Long groupMemberId =chattingRepository.findGroupMemberIdByUserIdAndGroupId(chattingDTO.getSenderUserId(), chattingDTO.getGroupId());
        chattingDTO.setSenderUserFileUuid(userRepositoryImple.findByUserId(chattingDTO.getSenderUserId()).getUserFileUuid());
        chattingDTO.setSenderUserFilePath(userRepositoryImple.findByUserId(chattingDTO.getSenderUserId()).getUserFilePath());
        chattingDTO.setSenderUserFileName(userRepositoryImple.findByUserId(chattingDTO.getSenderUserId()).getUserFileName());
        chattingDTO.setSenderGroupMemberId(groupMemberId);
//        chattingService.saveMessage(userId, groupId, chattingDTO);

        log.info(chattingDTO.toString());
//        세션 가지고 있음
        log.info(session.toString());
        groupDTO.handleMessage(session,chattingDTO,objectMapper);
    }

//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        GroupDTO.sessions.remove(session);
//        System.out.println("afterConnectionEstablished:" + session + ":" + status);
//    }

}