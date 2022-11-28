package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.ReadStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
@ToString
public class AlarmDTO {

    private Long userId;

    private String userNickName;

    private String alarmContent;

    private ReadStatus readStatus;




    @QueryProjection
    public AlarmDTO(Long userId, String userNickName, String alarmContent, ReadStatus readStatus) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.alarmContent = alarmContent;
        this.readStatus = readStatus;
    }

    public Alarm toEntity(){


        return Alarm.builder()
                .readStatus(readStatus)
                .alarmContent(alarmContent)
                .build();
    }



}
