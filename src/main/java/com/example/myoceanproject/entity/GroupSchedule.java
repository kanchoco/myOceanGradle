package com.example.myoceanproject.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_GROUP_SCHEDULE")
@Getter
@ToString(exclude = "group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupSchedule extends Period{
    @Id
    @GeneratedValue
    private Long groupScheduleId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    @NotNull
    private Group group; //FK
    @NotNull
    private LocalDateTime groupScheduleDate;
    @NotNull
    private LocalDateTime groupScheduleStartTime;
    @NotNull
    private LocalDateTime groupScheduleEndTime;

//    public void create(Long groupScheduleId, Group group, LocalDateTime groupScheduleDate, LocalDateTime groupScheduleStartTime, LocalDateTime groupScheduleEndTime) {
//        this.groupScheduleId = groupScheduleId;
//        this.group = group;
//        this.groupScheduleDate = groupScheduleDate;
//        this.groupScheduleStartTime = groupScheduleStartTime;
//        this.groupScheduleEndTime = groupScheduleEndTime;
//    }
//
    public void changeGroup(Group group){
        this.group = group;
    }

    @Builder
    public GroupSchedule(LocalDateTime groupScheduleDate, LocalDateTime groupScheduleStartTime, LocalDateTime groupScheduleEndTime) {
        this.groupScheduleDate = groupScheduleDate;
        this.groupScheduleStartTime = groupScheduleStartTime;
        this.groupScheduleEndTime = groupScheduleEndTime;
    }

    public void update(LocalDateTime groupScheduleDate, LocalDateTime groupScheduleStartTime, LocalDateTime groupScheduleEndTime){
        this.groupScheduleDate = groupScheduleDate;
        this.groupScheduleStartTime = groupScheduleStartTime;
        this.groupScheduleEndTime = groupScheduleEndTime;
    }
}
