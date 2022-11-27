package com.example.myoceanproject.dtoTest;

import antlr.collections.List;
import com.example.myoceanproject.domain.AlarmDTO;
import com.example.myoceanproject.domain.QuestDTO;
import com.example.myoceanproject.embeddable.File;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.Quest;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.QuestAchievementRepository;
import com.example.myoceanproject.repository.QuestRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.ReadStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Embedded;
import java.util.Optional;

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

}
