package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.Quest;
import com.example.myoceanproject.entity.QuestAchievement;
import com.example.myoceanproject.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class QuestAchievementDTO {

    private User user;
    private Quest quest;


//    public QuestAchievement toEntity(){
//        return QuestAchievement.builder()
//                .user(user)
//                .quest(quest)
//                .build();
//    }
}
