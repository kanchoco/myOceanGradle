package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Chatting;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.type.ReadStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ChattingDTO {
    private String chattingContent;


    public Chatting toEntity(){
        return Chatting.builder()
                .chattingContent(chattingContent)
                .build();
    }
}
