package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.type.ReadStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class AlarmDTO {

    private User user; //FK
    private String alarmContent;
    private LocalDateTime alarmDate;


    public Alarm toEntity(){
        return Alarm.builder()
                .user(user)
                .alarmContent(alarmContent)
                .alarmDate(alarmDate)
                .build();
    }

}
