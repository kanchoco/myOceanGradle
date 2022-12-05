package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Chatting;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.type.ReadStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ChattingDTO {
//    보내는 사람 관련 유저 정보
    private Long senderUserId;
    private String senderUserNickName;
    private String senderUserFileName;
    private String senderUserFilePath;
    private Long senderUserFileSize;
    private String senderUserFileUuid;


//    그룹 관련
    private Long groupId;
    private String groupName;
    private String groupFilePath;
    private String groupFileName;

    private String groupFileUuid;
    private Long groupFileSize;
//    보내는 사람의 groupMemberId
    private Long senderGroupMemberId;
//    받는 사람의 Group Member ID
    private Long receiverGroupMemberId;
//    채팅 관련
    private Long chattingId;
    private String chattingContent;

    @QueryProjection
    public ChattingDTO(Long chattingId, Long senderUserId, String senderUserNickName, String senderUserFileName, String senderUserFilePath, Long senderUserFileSize, String senderUserFileUuid, Long groupId, String groupName, String groupFilePath, String groupFileName, String groupFileUuid, Long groupFileSize, Long senderGroupMemberId, String chattingContent) {
        this.chattingId = chattingId;
        this.senderUserId = senderUserId;
        this.senderUserNickName = senderUserNickName;
        this.senderUserFileName = senderUserFileName;
        this.senderUserFilePath = senderUserFilePath;
        this.senderUserFileSize = senderUserFileSize;
        this.senderUserFileUuid = senderUserFileUuid;
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupFilePath = groupFilePath;
        this.groupFileName = groupFileName;
        this.groupFileUuid = groupFileUuid;
        this.groupFileSize = groupFileSize;
        this.senderGroupMemberId = senderGroupMemberId;
        this.chattingContent = chattingContent;
    }





    public Chatting toEntity(){
        return Chatting.builder()
                .chattingContent(chattingContent)
                .build();
    }
}
