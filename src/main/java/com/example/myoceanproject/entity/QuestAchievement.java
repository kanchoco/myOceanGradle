package com.example.myoceanproject.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_QUEST_ACHIEVEMENT")
@Getter
@ToString(exclude = {"user", "quest"})
public class QuestAchievement extends Period{

    @Id
    @GeneratedValue
    private Long questAchievementId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user; //FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUEST_ID")
    private Quest quest; //FK

    public QuestAchievement(Long questAchievementId, User user, Quest quest) {
        this.questAchievementId = questAchievementId;
        this.user = user;
        this.quest = quest;
    }

    public void changeUser(User user){
        this.user = user;
        user.getQuestAchievements().add(this);
    }
    public void changeQuest(Quest quest){
        this.quest = quest;
    }
}
