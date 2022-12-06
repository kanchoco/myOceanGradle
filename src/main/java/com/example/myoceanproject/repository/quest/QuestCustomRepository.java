package com.example.myoceanproject.repository.quest;

import com.example.myoceanproject.domain.QuestDTO;
import com.example.myoceanproject.entity.Quest;

public interface QuestCustomRepository {
    public Quest saveQuest(QuestDTO questDTO);
}
