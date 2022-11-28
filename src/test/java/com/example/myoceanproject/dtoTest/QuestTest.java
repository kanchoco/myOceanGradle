package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.entity.Quest;
import com.example.myoceanproject.repository.QuestRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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

//    @Test
//    public void saveTest(){
////        //      1번 유저 불러오기
////        Optional<User> user = Repository.findById(1L);
//        QuestDTO questDTO = new QuestDTO();
//
//        file.setFilePath("akjhdkghla");
//        file.setFileOriginName("이렇게해서 안되면 진짜 다 죽이고 집간다");
//
//
////      alarmDTO에 필요한 값 저장
//        questDTO.setQuestCategory("활동");
//        questDTO.setQuestContent("회원가입시 지급");
//        questDTO.setQuestName("회원가입뱃지");
//        questDTO.setFile(file);
//
//
////      alarmDTO에 저장한 값들을 entity로 변환
//        Quest quest1 = questDTO.toEntity();
//
//
////      alarm 엔티티에 해당 값들을 모두 저장
//        questRepository.save(quest1);
//    }

    @Test
    public void findAllTest(){
        List<Quest> quests = jpaQueryFactory.selectFrom(quest)
                .fetch();
        quests.stream().map(Quest::toString).forEach(log::info);
    }

    @Test
    public void findByIdTest(){
        Quest quest1 = jpaQueryFactory.selectFrom(quest)
                .where(quest.questId.eq(213L))
                .fetchOne();

        log.info(quest1.toString());
    }

    @Test
    public void updateTest(){
        Long count = jpaQueryFactory.update(quest)
                .where(quest.questCategory.eq("회원가입"))
                .set(quest.questContent, "회원가입하면 줌")
                .execute();

        log.info(count.toString());

    }

    @Test
    public void deleteTest(){
        Long count = jpaQueryFactory
                .delete(quest)
                .where(quest.questId.eq(213L))
                .execute();
    }

}
