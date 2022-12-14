package com.example.myoceanproject.repository.quest;

import com.example.myoceanproject.domain.QuestDTO;

import java.util.List;

public interface QuestAchievementCustomRepository {

    List<QuestDTO> findQuestAchievementByUserId(Long userId);
}
