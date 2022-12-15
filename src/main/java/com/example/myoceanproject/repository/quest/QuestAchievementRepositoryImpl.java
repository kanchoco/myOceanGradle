package com.example.myoceanproject.repository.quest;

import java.util.List;

import com.example.myoceanproject.domain.QQuestDTO;
import com.example.myoceanproject.domain.QuestDTO;
import com.example.myoceanproject.type.QuestType;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.example.myoceanproject.entity.QQuest.quest;
import static com.example.myoceanproject.entity.QQuestAchievement.questAchievement;

@Repository
@RequiredArgsConstructor
public class QuestAchievementRepositoryImpl implements QuestAchievementCustomRepository{

    private final JPAQueryFactory queryFactory;


    @Override
//    유저 아이디를 이용해 해당 유저가 달성한 퀘스트를 dto로 받아온다.
    public Page<QuestDTO> findQuestAchievementByUserId(Long userId, Pageable pageable){
        List<QuestDTO> questDTOList = queryFactory.select(new QQuestDTO(
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();

        long total = queryFactory.selectFrom(questAchievement)
                .where(questAchievement.user.userId.eq(userId))
                .fetch().size();

        return new PageImpl<>(questDTOList, pageable, total);
    }
    @Override
    public Boolean checkDuplicatedById(Long userId, Long questId){
//        비어있으면 트루!!
        return queryFactory.selectFrom(questAchievement)
                .where(questAchievement.user.userId.eq(userId).and(questAchievement.quest.questId.eq(questId))).fetch().isEmpty();
    }

    @Override
    public int countBadge(Long userId){
        return Math.toIntExact(queryFactory.select(questAchievement.questAchievementId.count()).from(questAchievement)
                .where(questAchievement.user.userId.eq(userId)).fetchFirst());
    }


    @Override
    public List<Tuple> findMonthlyAchievementCount(Long userId){
        return queryFactory.from(questAchievement)
                .where(questAchievement.user.userId.eq(userId).and(questAchievement.quest.questType.eq(QuestType.TODAY)))
                .groupBy(questAchievement.createDate.yearMonth())
                .select(questAchievement.createDate.yearMonth(), questAchievement.createDate.yearMonth().count() )
                .fetch();

    }




}
