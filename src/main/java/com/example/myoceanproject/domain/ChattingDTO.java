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
    private String chattingContent;
    private User user;

    private ReadStatus readStatus;

    @QueryProjection
    public ChattingDTO(String chattingContent, User user, ReadStatus readStatus) {
        this.chattingContent = chattingContent;
        this.user = user;
        this.readStatus = readStatus;
    }




    //  readStatus는 디폴트로 UNREAD 값이 들어가있으므로 화면에서 새롭게 값을 저장하지 않는다.
    public Chatting toEntity(){
        return Chatting.builder()
                .chattingContent(chattingContent)
                .readStatus(readStatus)
                .build();
    }
}
