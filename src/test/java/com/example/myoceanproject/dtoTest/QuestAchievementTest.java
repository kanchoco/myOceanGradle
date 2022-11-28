package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.entity.Quest;
import com.example.myoceanproject.entity.QuestAchievement;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.QuestAchievementRepository;
import com.example.myoceanproject.repository.QuestRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.myoceanproject.entity.QQuestAchievement.questAchievement;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class QuestAchievementTest {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    QuestAchievementRepository questAchievementRepository;

    @Autowired
    QuestRepository questRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveTest(){
        Optional<User> user = userRepository.findById(3L);
        Optional<Quest> quest = questRepository.findById(1L);
        QuestAchievement questAchievement = new QuestAchievement();

        questAchievement.changeQuest(quest.get());
        questAchievement.changeUser(user.get());

        questAchievementRepository.save(questAchievement);

    }

    @Test
    public void findAllTest(){
        List<QuestAchievement> questAchievements = jpaQueryFactory.selectFrom(questAchievement)
                .join(questAchievement.user)
                .fetchJoin()
                .fetch();

        questAchievements.stream().map(QuestAchievement::toString).forEach(log::info);
    }

    @Test
    public void findByIdTest(){
        List<QuestAchievement> questAchievements = jpaQueryFactory.selectFrom(questAchievement)
                .join(questAchievement.user)
                .where(questAchievement.user.userId.eq(3L))
                .fetchJoin()
                .fetch();
        questAchievements.stream().map(QuestAchievement::toString).forEach(log::info);
    }

    @Test
    public void deleteTest(){
        Long count = jpaQueryFactory
                .delete(questAchievement)
                .where(questAchievement.questAchievementId.eq(215L))
                .execute();
    }
}
