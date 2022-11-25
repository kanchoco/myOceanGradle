package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.entity.GroupSchedule;
import com.example.myoceanproject.entity.Period;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class GroupScheduleDTO {

    private Group group;
    private LocalDateTime groupScheduleDate;
    private LocalDateTime groupScheduleStartTime;
    private LocalDateTime groupScheduleEndTime;


    public GroupSchedule toEntity(){
        return GroupSchedule.builder()
//                .group(group)
                .groupScheduleDate(groupScheduleDate)
                .groupScheduleStartTime(groupScheduleStartTime)
                .groupScheduleEndTime(groupScheduleEndTime)
                .build();
    }
}