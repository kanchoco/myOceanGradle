package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.AlarmDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.QAlarm;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.AlarmRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.ReadStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;

import static com.example.myoceanproject.entity.QAlarm.alarm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class AlarmTest {
    @Autowired
    private AlarmDTO alarmDTO;
    @Autowired
    private AlarmRepository alarmRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    @Test
    public void saveAlarmTest(){

//      1번 유저 불러오기
        Optional<User> user = userRepository.findById(1L);
        AlarmDTO alarmDTO = new AlarmDTO();

//      alarmDTO에 필요한 값 저장
        alarmDTO.setAlarmContent("두번째 알람입니다.");
        alarmDTO.setReadStatus(ReadStatus.UNREAD);

//      alarmDTO에 저장한 값들을 entity로 변환
        Alarm alarm1 = alarmDTO.toEntity();

//      alarmDTO에 처음에 조회했던 유저 정보를 저장(optional이기 때문에 get 사용)
//      changeUser 메소드로 alarmDTO에 저장된 User값을 alarm1로 전달
        alarmDTO.setUserId(user.get().getUserId());
        alarm1.changeUser(user.get());

//      alarm 엔티티에 해당 값들을 모두 저장
        alarmRepository.save(alarm1);
    }

    @Test
    public void findAllTest(){
        List<Alarm> alarms = jpaQueryFactory.selectFrom(new QAlarm(alarm))
                .join(alarm.user)
                .fetchJoin()
                .fetch();
        alarms.stream().map(Alarm::toString).forEach(log::info);
    }

    @Test
    public void findById(){
        List<Alarm> alarms = jpaQueryFactory.selectFrom(alarm)
                .join(alarm.user)
                .where(alarm.user.userId.eq(1L))
                .fetchJoin()
                .fetch();

        alarms.stream().map(Alarm::toString).forEach(log::info);

    }

    @Test
    public void updateTest(){

        Alarm alarm1 = jpaQueryFactory.selectFrom(alarm)
                .where(alarm.alarmContent.eq("첫 알람입니다."))
                .fetchOne();

        alarm1.update(ReadStatus.UNREAD);

    }

//    여러개의 컬럼을 update할땐 set을 여러번 사용한다
    @Test
    public void updateMultipleTest(){


        List<Alarm> alarms = jpaQueryFactory.selectFrom(alarm)
                .where(alarm.readStatus.eq(ReadStatus.UNREAD))
                .fetch();

        alarms.stream().forEach(v->{v.update(ReadStatus.READ);});
    }

    @Test
    public void deleteTest(){
        Long count = jpaQueryFactory
                .delete(alarm)
                .where(alarm.alarmContent.eq("두번째수정"))
                .execute();
    }


}
