package com.example.myoceanproject.service.quest;

import com.example.myoceanproject.domain.QuestDTO;
import com.example.myoceanproject.entity.QuestAchievement;
import com.example.myoceanproject.repository.quest.QuestAchievementRepositoryImpl;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestAchievementService {

    private final QuestAchievementRepositoryImpl questAchievementRepositoryImpl;

//    유저 아이디를 넘겨주면 해당 유저가 달성한 퀘스트를 dto로 반환한다.
    public Page<QuestDTO> showMyAchievement(Long userId, Pageable pageable){
        return questAchievementRepositoryImpl.findQuestAchievementByUserId(userId, pageable);

    }

    public int showMonthlyAchievementCount(Long userId, int month){
        return questAchievementRepositoryImpl.findMonthlyAchievementCount(userId, month);
    }

    public int showMyBadgeNumber(Long userId){
        return questAchievementRepositoryImpl.countBadge(userId);
    }
}
