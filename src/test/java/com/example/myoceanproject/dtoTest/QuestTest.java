package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.QuestDTO;
import com.example.myoceanproject.entity.Quest;
import com.example.myoceanproject.repository.QuestRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.myoceanproject.entity.QQuest.quest;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class QuestTest {
    @Autowired
    JPAQueryFactory jpaQueryFactory;


    @Autowired
    QuestRepository questRepository;

    @Test
    public void saveTest(){
//        시나리오 : 퀘스트는 fk 없이 독립적이기 때문에 별도의 시나리오는 필요없다고 판단. DTO에 값을 set해서 저장만 해주면됨
        QuestDTO questDTO = new QuestDTO();

        questDTO.setQuestCategory("활동");
        questDTO.setQuestContent("회원가입시 지급");
        questDTO.setQuestName("회원가입뱃지");
        questDTO.setQuestDeadLine(LocalDateTime.now());
        questDTO.setQuestFilePath("sdakhfjklsdg");
        questDTO.setQuestFileSize(392847L);
        questDTO.setQuestFileOriginName("sgahoidsfk");
        questDTO.setQuestFileUuid("oadishakng124u83u");


        Quest quest1 = questDTO.toEntity();


        questRepository.save(quest1);
    }

//    @Test
//    public void findAllTest(){
//        List<Quest> quests = jpaQueryFactory.selectFrom(quest)
//                .fetch();
//        quests.stream().map(Quest::toString).forEach(log::info);
//    }
//
//    @Test
//    public void findByIdTest(){
//        Quest quest1 = jpaQueryFactory.selectFrom(quest)
//                .where(quest.questId.eq(213L))
//                .fetchOne();
//
//        log.info(quest1.toString());
//    }
//
//    @Test
//    public void updateTest(){
//        Long count = jpaQueryFactory.update(quest)
//                .where(quest.questCategory.eq("회원가입"))
//                .set(quest.questContent, "회원가입하면 줌")
//                .execute();
//
//        log.info(count.toString());
//
//    }
//
//    @Test
//    public void deleteTest(){
//        Long count = jpaQueryFactory
//                .delete(quest)
//                .where(quest.questId.eq(213L))
//                .execute();
//    }

}
