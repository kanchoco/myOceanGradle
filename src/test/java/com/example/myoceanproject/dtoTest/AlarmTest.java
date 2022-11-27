package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.AlarmDTO;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.AlarmRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.ReadStatus;
import com.example.myoceanproject.type.UserAccountStatus;
import com.example.myoceanproject.type.UserLoginMethod;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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


    @Test
    public void saveAlarmTest(){
        User user = new User("rladlsdud", "강철멘탈3", "이메일", UserAccountStatus.ACTIVE, UserLoginMethod.KAKAO, 20);
        userRepository.save(user);
        alarmDTO.setUser(user);
        alarmDTO.setAlarmContent("첫 알람입니다.");
        alarmDTO.setReadStatus(ReadStatus.READ);
        alarmRepository.save(alarmDTO.toEntity());

//        alarmRepository.findAll().stream().map(alarmDTO+"").forEach(log::info);

        assertThat(alarmRepository.findAll().get(0));
    }

}
