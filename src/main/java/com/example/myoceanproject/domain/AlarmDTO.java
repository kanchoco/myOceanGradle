package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.type.ReadStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
@ToString
public class AlarmDTO {

    private User user; //FK
    private String alarmContent;

    private ReadStatus readStatus;


    @QueryProjection
    public AlarmDTO(User user, String alarmContent, ReadStatus readStatus) {
        this.user = user;
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
