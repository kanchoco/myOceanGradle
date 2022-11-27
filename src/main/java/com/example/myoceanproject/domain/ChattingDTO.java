package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Chatting;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.type.ReadStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ChattingDTO {
    private String chattingContent;
    private User user;

//  readStatus는 디폴트로 UNREAD 값이 들어가있으므로 화면에서 새롭게 값을 저장하지 않는다.
    public Chatting toEntity(){
        return Chatting.builder()
                .user(user)
                .chattingContent(chattingContent)
                .build();
    }
}
