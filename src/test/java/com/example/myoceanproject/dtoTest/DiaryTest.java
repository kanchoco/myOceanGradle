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
//        시나리오 :userRepository를 이용해서 일기를 작성한 유저와 교환일기를 받을 유저를 저장한다, 이때 교환일기가 아니라면
//        교환일기를 받을 유저는 넣지 않는다.
//        이후 사용자가 입력한 다이어리 내용을 저장한다.
        Optional<User> user = userRepository.findById(1L);
        Optional<User> receiverUser = userRepository.findById(2L);

//      diaryDTO에 필요한 값 저장
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setDiaryTitle("다이어리 첫번쨰");
        diaryDTO.setDiaryContent("다이어리 첫번째 내용");
//        diaryDTO 엔티티화
        Diary diary1 = diaryDTO.toEntity();
        diary1.changeUser(user.get());
        diary1.changeReceiverUser(receiverUser.get());

//      diary 엔티티에 해당 값들을 모두 저장
        diaryRepository.save(diary1);
    }

    @Test
    public void findAllTest(){
        List<Diary> diaries = jpaQueryFactory.selectFrom(diary)
                .join(diary.user)
                .fetchJoin()
                .fetch();
        diaries.stream().map(Diary::toString).forEach(log::info);
    }

    @Test
    public void findById(){
        List<Diary> diaries = jpaQueryFactory.selectFrom(diary)
                .join(diary.user)
                .where(diary.user.userId.eq(1L))
                .fetchJoin()
                .fetch();

        diaries.stream().map(Diary::toString).forEach(log::info);

    }


    @Test
    public void deleteTest(){
        Long count = jpaQueryFactory
                .delete(diary)
                .where(diary.diaryId.eq(5L))
                .execute();
    }
}
