package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.AlarmDTO;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.AlarmRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.ReadStatus;
import com.example.myoceanproject.type.UserAccountStatus;
import com.example.myoceanproject.type.UserLoginMethod;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;

import static com.example.myoceanproject.entity.QAlarm.alarm;
import static com.example.myoceanproject.entity.QUser.user;
import static org.assertj.core.api.Assertions.*;

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
        Optional<UserDTO> userDTO = userRepository.findById(1L);
        log.info(userDTO.get().toEntity().toString());
        AlarmDTO alarmDTO = new AlarmDTO();
        alarmDTO.toEntity().changeUser(userDTO.get().toEntity());
        alarmDTO.setAlarmContent("두번째 알람입니다.");
        alarmDTO.setReadStatus(ReadStatus.UNREAD);

//        alarmDTO.setUser(alarm1.changeUser());
        alarmRepository.save(alarmDTO);


    }

//    @Test
//    public void findAllTest(){
//        List<Alarm> alarms = jpaQueryFactory.selectFrom(alarm)
//                .join(alarm.user)
//                .fetchJoin()
//                .fetch();
//        alarms.stream().map(Alarm::toString).forEach(log::info);
//
//
//    }

//    @Test
//    public void updateTest(){
//
//    }

}
