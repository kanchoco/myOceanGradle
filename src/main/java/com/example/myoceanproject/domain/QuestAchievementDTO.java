package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.Quest;
import com.example.myoceanproject.entity.QuestAchievement;
import com.example.myoceanproject.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class QuestAchievementDTO {

    private User user;
    private Quest quest;

    @QueryProjection
    public QuestAchievementDTO(User user, Quest quest) {
        this.user = user;
        this.quest = quest;
    }
}
