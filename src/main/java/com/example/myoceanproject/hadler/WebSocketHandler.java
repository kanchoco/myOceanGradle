package com.example.myoceanproject.hadler;

import com.example.myoceanproject.domain.ChattingDTO;
import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.repository.GroupRepository;
import com.example.myoceanproject.repository.GroupRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    List<WebSocketSession> sessions = new ArrayList<>();
    private final GroupRepositoryImpl groupRepositoryImpl;
    private final ObjectMapper objectMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("afterConnectionEstablished:" + session);
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("메세지 전송 = {} : {}",session,message.getPayload());
        String msg = message.getPayload();
        ChattingDTO chattingDTO = objectMapper.readValue(msg,ChattingDTO.class);
        log.info(chattingDTO.toString());
        GroupDTO groupDTO = groupRepositoryImpl.findGroupByGroupId(chattingDTO.getGroupId());
        groupDTO.handleMessage(session,chattingDTO,objectMapper);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("afterConnectionEstablished:" + session + ":" + status);
    }

}
