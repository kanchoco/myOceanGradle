package com.example.myoceanproject.service;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.QuestDTO;
import com.example.myoceanproject.repository.quest.QuestRepository;
import com.example.myoceanproject.repository.quest.QuestRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestService {

    private final QuestRepositoryImpl questRepositoryImpl;
    private final QuestRepository questRepository;

    public void addQuest(QuestDTO questDTO){
        questRepository.save(questRepositoryImpl.saveQuest(questDTO));
    }

    public Page<QuestDTO> showPost(Pageable pageable, Criteria criteria){
        return criteria.getKeyword().equals("null") ? questRepositoryImpl.findAll(pageable) : questRepositoryImpl.findAll(pageable,criteria);
    }
}
