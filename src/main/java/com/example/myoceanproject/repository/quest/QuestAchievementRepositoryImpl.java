package com.example.myoceanproject.repository.quest;

import java.util.List;

import com.example.myoceanproject.domain.QQuestDTO;
import com.example.myoceanproject.domain.QuestDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.example.myoceanproject.entity.QQuest.quest;
import static com.example.myoceanproject.entity.QQuestAchievement.questAchievement;

@Repository
@RequiredArgsConstructor
public class QuestAchievementRepositoryImpl implements QuestAchievementCustomRepository{

    private final JPAQueryFactory queryFactory;


    @Override
//    유저 아이디를 이용해 해당 유저가 달성한 퀘스트를 dto로 받아온다.
    public List<QuestDTO> findQuestAchievementByUserId(Long userId){
        return queryFactory.select(new QQuestDTO(
                        quest.questId,
                        quest.questCategory,
                        quest.questName,
                        quest.questContent,
                        quest.questType,
                        quest.questDeadLine,
                        quest.questPoint,
                        quest.questFilePath,
                        quest.questFileName,
                        quest.questFileUuid,
                        quest.questFileSize,
                        quest.createDate
                )).from(quest)
                .join(questAchievement)
                .on(questAchievement.quest.questId.eq(quest.questId))
                .where(questAchievement.user.userId.eq(userId))
                .fetch();
    }
}
