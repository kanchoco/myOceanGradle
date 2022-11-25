package com.example.myoceanproject.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_GROUP_SCHEDULE")
@Getter
@ToString(exclude = "group")
public class GroupSchedule extends Period{
    @Id
    @GeneratedValue
    private Long groupScheduleId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    private Group group; //FK
    private LocalDateTime groupScheduleDate;
    private LocalDateTime groupScheduleStartTime;
    private LocalDateTime groupScheduleEndTime;

//    public void create(Long groupScheduleId, Group group, LocalDateTime groupScheduleDate, LocalDateTime groupScheduleStartTime, LocalDateTime groupScheduleEndTime) {
//        this.groupScheduleId = groupScheduleId;
//        this.group = group;
//        this.groupScheduleDate = groupScheduleDate;
//        this.groupScheduleStartTime = groupScheduleStartTime;
//        this.groupScheduleEndTime = groupScheduleEndTime;
//    }
//
//    public void changeGroup(Group group){
//        this.group = group;
//    }

    @Builder
    public GroupSchedule(Group group, LocalDateTime groupScheduleDate, LocalDateTime groupScheduleStartTime, LocalDateTime groupScheduleEndTime) {
        this.group = group;
        this.groupScheduleDate = groupScheduleDate;
        this.groupScheduleStartTime = groupScheduleStartTime;
        this.groupScheduleEndTime = groupScheduleEndTime;
    }
}
