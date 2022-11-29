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
//    유저 관련
    private Long userId;
    private String userNickName;
    private String userFileName;
    private String userFilePath;
    private Long userFileSize;
    private Long userFileUuid;

//    그룹 관련
    private Long groupId;
    private String groupName;
    private String groupFilePath;
    private String groupFileName;

    private String groupFileUuid;
    private Long groupFileSize;

//    채팅 관련
    private String chattingContent;
    private ReadStatus readStatus;

    @QueryProjection
    public ChattingDTO(Long userId, String userNickName, String userFileName, String userFilePath, Long userFileSize, Long userFileUuid, Long groupId, String groupName, String groupFilePath, String groupFileName, String groupFileUuid, Long groupFileSize, String chattingContent, ReadStatus readStatus) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userFileName = userFileName;
        this.userFilePath = userFilePath;
        this.userFileSize = userFileSize;
        this.userFileUuid = userFileUuid;
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupFilePath = groupFilePath;
        this.groupFileName = groupFileName;
        this.groupFileUuid = groupFileUuid;
        this.groupFileSize = groupFileSize;
        this.chattingContent = chattingContent;
        this.readStatus = readStatus;
    }


    public Chatting toEntity(){
        return Chatting.builder()
                .chattingContent(chattingContent)
                .readStatus(readStatus)
                .build();
    }
}
