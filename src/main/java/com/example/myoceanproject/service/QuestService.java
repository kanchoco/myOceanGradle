package com.example.myoceanproject.service;

import com.example.myoceanproject.domain.QuestDTO;
import com.example.myoceanproject.repository.quest.QuestRepository;
import com.example.myoceanproject.repository.quest.QuestRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestService {

    private final QuestRepositoryImpl questRepositoryImpl;
    private final QuestRepository questRepository;

    public void addQuest(QuestDTO questDTO){
        questRepository.save(questRepositoryImpl.saveQuest(questDTO));
    }
}
