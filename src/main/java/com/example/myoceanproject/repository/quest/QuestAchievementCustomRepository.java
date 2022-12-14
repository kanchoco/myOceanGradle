package com.example.myoceanproject.repository.quest;

import com.example.myoceanproject.domain.QuestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestAchievementCustomRepository {

    Page<QuestDTO> findQuestAchievementByUserId(Long userId, Pageable pegeable);
}
