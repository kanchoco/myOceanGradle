package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.*;
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
    private DiaryRepository diaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveDiaryTest(){
//        시나리오 :userRepository를 이용해서 일기를 작성한 유저와 교환일기를 받을 유저를 저장한다, 이때 교환일기가 아니라면
//        교환일기를 받을 유저는 넣지 않는다.
//        이후 사용자가 입력한 다이어리 내용을 저장한다.
        Optional<User> user = userRepository.findById(1L);
        Optional<User> receiverUser = userRepository.findById(2L);

//      diaryDTO에 필요한 값 저장
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setDiaryTitle("다이어리 두번쨰");
        diaryDTO.setDiaryContent("다이어리 두번째 내용");
//        diaryDTO 엔티티화
        Diary diary1 = diaryDTO.toEntity();
        diary1.setUser(user.get());
        diary1.setReceiverUser(receiverUser.get());

//      diary 엔티티에 해당 값들을 모두 저장
        diaryRepository.save(diary1);
    }

    @Test
    public void findAllTest(){
        List<DiaryDTO> diaries = jpaQueryFactory.select(new QDiaryDTO(
                diary.user.userId,
                diary.diaryTitle,
                diary.diaryContent,
                diary.receiverUser.userId
        )).from(diary).fetch();

        log.info("------------------------------------------------------------");
        diaries.stream().map(DiaryDTO::toString).forEach(log::info);
        log.info("------------------------------------------------------------");

    }

    @Test
    public void findById(){
        List<DiaryDTO> diaries = jpaQueryFactory.select(new QDiaryDTO(
                diary.user.userId,
                diary.diaryTitle,
                diary.diaryContent,
                diary.receiverUser.userId
        )).from(diary).where(diary.receiverUser.userId.eq(2L)).fetch();

        log.info("------------------------------------------------------------");
        diaries.stream().map(DiaryDTO::toString).forEach(log::info);
        log.info("------------------------------------------------------------");
    }

    @Test
    public void deleteTest(){
        Diary diary = diaryRepository.findById(7L).get();
        diaryRepository.delete(diary);
    }
}
