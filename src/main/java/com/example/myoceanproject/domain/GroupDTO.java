package com.example.myoceanproject.domain;

import com.example.myoceanproject.embeddable.GroupMemberLimit;
import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.type.GroupLocationType;
import com.example.myoceanproject.type.GroupStatus;
import com.example.myoceanproject.type.MessageType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Component
@Data
@NoArgsConstructor
public class GroupDTO {

    private Long groupId;
    private Long userId;
    private String userNickName;
    private String userFileName;
    private String userFilePath;
    private Long userFileSize;
    private String userFileUuid;
    private String groupName;
    private String groupCategory;
    private String groupContent;
    private int groupPoint;
    private String groupOverSea;
    private String groupLocationName;
    private String groupLocation;
    private String groupLocationDetail;
    private String groupParkingAvailable;
    private String groupMoreInformation;
    private GroupLocationType groupLocationType;
    private GroupStatus groupStatus;
    private String groupFilePath;
    private String groupFileName;

    private String groupFileUuid;
    private Long groupFileSize;

    private String reason;

//    임베드 타입 가져옴(이렇게 가져오는 것이 맞는지는 불확실함. 생성자와 toEntity에도 추가함)
    private Integer maxMember;
    private Integer minMember;

    private String createDate;
    private String updatedDate;

    private List<GroupDTO> groupList;

    private int endPage;

    private static Map<Long, WebSocketSession> sessions = new HashMap<>();




    @QueryProjection
    public GroupDTO(Long groupId, Long userId, String userFileName, String userFilePath, Long userFileSize, String userFileUuid, String userNickName, String groupName, String groupCategory, String groupContent, int groupPoint, String groupOverSea, String groupLocationName, String groupLocation, String groupLocationDetail, String groupParkingAvailable, String groupMoreInformation, GroupLocationType groupLocationType, GroupStatus groupStatus, String groupFilePath, String groupFileName, String groupFileUuid, Long groupFileSize, Integer maxMember, Integer minMember, LocalDateTime createDate, LocalDateTime updatedDate, String reason) {
        this.groupId = groupId;
        this.userId = userId;
        this.userNickName = userNickName;
        this.userFileName = userFileName;
        this.userFilePath = userFilePath;
        this.userFileSize = userFileSize;
        this.userFileUuid = userFileUuid;
        this.groupName = groupName;
        this.groupCategory = groupCategory;
        this.groupContent = groupContent;
        this.groupPoint = groupPoint;
        this.groupOverSea = groupOverSea;
        this.groupLocationName = groupLocationName;
        this.groupLocation = groupLocation;
        this.groupLocationDetail = groupLocationDetail;
        this.groupParkingAvailable = groupParkingAvailable;
        this.groupMoreInformation = groupMoreInformation;
        this.groupLocationType = groupLocationType;
        this.groupStatus = groupStatus;
        this.groupFilePath = groupFilePath;
        this.groupFileName = groupFileName;
        this.groupFileUuid = groupFileUuid;
        this.groupFileSize = groupFileSize;
        this.maxMember = maxMember;
        this.minMember = minMember;
        this.createDate = createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.updatedDate = updatedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.reason = reason;
    }

    public Group toEntity(){
        GroupMemberLimit groupMemberLimit = new GroupMemberLimit();

        groupMemberLimit.setMaxMember(maxMember);
        groupMemberLimit.setMinMember(minMember);


        return Group.builder()
                .groupName(groupName)
                .groupCategory(groupCategory)
                .groupContent(groupContent)
                .groupPoint(groupPoint)
                .groupOverSea(groupOverSea)
                .groupLocationName(groupLocationName)
                .groupLocation(groupLocation)
                .groupLocationDetail(groupLocationDetail)
                .groupParkingAvailable(groupParkingAvailable)
                .groupMoreInformation(groupMoreInformation)
                .groupLocationType(groupLocationType)
                .groupStatus(GroupStatus.WAITING)
                .groupMemberLimit(groupMemberLimit)
                .groupName(groupName)
                .groupFileName(groupFileName)
                .groupFilePath(groupFilePath)
                .groupFileUuid(groupFileUuid)
                .groupFileSize(groupFileSize)
                .reason(reason)
                .build();
    }
    public void handleMessage(WebSocketSession session, ChattingDTO chattingDTO,
                              ObjectMapper objectMapper) throws IOException {
            log.info("===========================GroupDTO handleMessage 들어옴======================================");
//            http 세션에 저장된 유저 아이디
        if(chattingDTO.getMessageType().equals(MessageType.ENTER.toString())){ // 사용자가 채팅방에 입장하여 "확인"을 눌렀을 때는 해당 닉네임 접속을 환영한다는 문구 출력
            chattingDTO.setChattingContent(chattingDTO.getSenderUserNickName() + "님이 입장하셨습니다.");
            sessions.put(chattingDTO.getSenderUserId(), session);
        }else {
            chattingDTO.setChattingContent(chattingDTO.getSenderUserNickName() + " : " + chattingDTO.getChattingContent());
        }
        log.info(session.getAttributes().toString());
        log.info(chattingDTO.getSenderUserId().toString());
        log.info(session.toString());
        log.info(sessions.toString());
        send(chattingDTO,objectMapper);

    }

    private void send(ChattingDTO chattingDTO, ObjectMapper objectMapper) throws IOException {
        log.info("===========================GroupDTO send 메서드 들어옴======================================");
        log.info(chattingDTO.toString());
        TextMessage textMessage = new TextMessage(objectMapper.
                writeValueAsString(chattingDTO.getChattingContent()));
        log.info(sessions.toString());
        for(WebSocketSession sess : sessions.values()){
            sess.sendMessage(textMessage);
        }
    }
}