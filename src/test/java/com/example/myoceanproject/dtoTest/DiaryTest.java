package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.AlarmDTO;
import com.example.myoceanproject.domain.DiaryDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.Diary;
import com.example.myoceanproject.entity.QuestAchievement;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.DiaryRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.ReadStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//import static com.example.myoceanproject.entity.QAlarm.alarm;
import static com.example.myoceanproject.entity.QDiary.diary;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class DiaryTest {
    @Autowired
    private DiaryDTO diaryDTO;

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveDiaryTest(){

//      1번 유저 불러오기
        Optional<User> user = userRepository.findById(1L);
        DiaryDTO diaryDTO = new DiaryDTO();
        Diary diary=new Diary();

//      diaryDTO에 필요한 값 저장
        diaryDTO.setDiaryTitle("다이어리 첫번쨰");
        diaryDTO.setDiaryContent("다이어리 첫번째 내용");
        diary.changeUser(user.get());
        diary.changeReceiverUser(user.get());

//      diary 엔티티에 해당 값들을 모두 저장
        diaryRepository.save(diary);
    }

    @Test
    public void findAllTest(){
        List<Diary> diarys = jpaQueryFactory.selectFrom(diary)
                .join(diary.user)
                .fetchJoin()
                .fetch();
        diarys.stream().map(Diary::toString).forEach(log::info);
    }

    @Test
    public void findById(){
        List<Diary> diarys = jpaQueryFactory.selectFrom(diary)
                .join(diary.user)
                .where(diary.user.userId.eq(1L))
                .fetchJoin()
                .fetch();

        diarys.stream().map(Diary::toString).forEach(log::info);

    }

    @Test
//    public void updateTest(){
//
//        Long count = jpaQueryFactory.update(alarm).set(alarm.alarmContent, "수정").where(alarm.user.userId.eq(1L)).execute();
//        log.info(count.toString());
//        Diary diary1 = jpaQueryFactory.selectFrom(diary)
//                .where(diary.diaryContent.eq("첫 알람입니다."))
//                .fetchOne();
//
//        diary1.update(ReadStatus.UNREAD);
//
//
//    }

    //    여러개의 컬럼을 update할땐 set을 여러번 사용한다
//    @Test
//    public void updateMultipleTest(){
//
//
//        List<Alarm> alarms = jpaQueryFactory.selectFrom(alarm)
//                .where(alarm.readStatus.eq(ReadStatus.UNREAD))
//                .fetch();
//
//        alarms.stream().forEach(v->{v.update(ReadStatus.READ);});
//    }
//
//    @Test
    public void deleteTest(){
        Long count = jpaQueryFactory
                .delete(diary)
                .where(diary.diaryContent.eq("첫번째수정"))
                .execute();
    }
}
