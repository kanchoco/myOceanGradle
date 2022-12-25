package com.example.myoceanproject.service.alarm;

import com.example.myoceanproject.domain.AlarmDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.QuestDTO;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.repository.UserRepositoryImpl;
import com.example.myoceanproject.repository.alarm.AlarmRepository;
import com.example.myoceanproject.repository.alarm.AlarmRepositoryImpl;
import com.example.myoceanproject.repository.community.post.CommunityPostRepository;
import com.example.myoceanproject.type.AlarmCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class AlarmService {

    private final JPAQueryFactory queryFactory;
    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;
    private final UserRepositoryImpl userRepositoryImpl;
    private final AlarmRepositoryImpl alarmRepositoryImpl;

    public void addAlarm(AlarmDTO alarmDTO){
        if(!alarmDTO.getAlarmCategory().equals(AlarmCategory.TODAY.name())){
            Alarm alarm = alarmDTO.toEntity();
            alarm.setUser(userRepository.findById(alarmDTO.getUserId()).get());
            alarmRepository.save(alarm);
        }else{
            List<UserDTO> users = userRepositoryImpl.findAllByActive();
            for(UserDTO user:users){
                Alarm alarm = alarmDTO.toEntity();
                alarm.setUser(userRepository.findById(user.getUserId()).get());
                alarmRepository.save(alarm);
            }
        }
    }



    public Page<AlarmDTO> showAlarm(Pageable pageable, Long userId){
        return alarmRepositoryImpl.findAllByUserId(pageable, userId);
    }

    public void modify(Long alarmId){
        alarmRepository.findById(alarmId).get().updateStatus();
    }

}
