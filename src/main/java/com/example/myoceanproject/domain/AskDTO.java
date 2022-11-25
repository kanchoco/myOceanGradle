package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Ask;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.type.AskCategory;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class AskDTO {
    private User user;//유저가 null일 경우 자주 묻는 질문
    private String askTitle;
    private String askContent;

    private AskCategory askCategory;


    public Ask toEntity(){
        return Ask.builder()
                .user(user)
                .askTitle(askTitle)
                .askContent(askContent)
                .askCategory(askCategory)
                .build();
    }
}
