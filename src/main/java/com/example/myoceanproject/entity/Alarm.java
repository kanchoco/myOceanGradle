package com.example.myoceanproject.entity;

import com.example.myoceanproject.type.ReadStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_ALARM")
@Getter
@ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm extends Period{

    @Id
    @GeneratedValue
    private Long alarmId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user; //FK
    @NotNull
    private String alarmContent;
    @NotNull
    private LocalDateTime alarmDate;
    @NotNull
    private ReadStatus readStatus; //Enum

//    public void create(Long alarmId, User user, String alarmContent, LocalDateTime alarmDate, ReadStatus readStatus) {
//        this.alarmId = alarmId;
//        this.user = user;
//        this.alarmContent = alarmContent;
//        this.alarmDate = alarmDate;
//        this.readStatus = readStatus;
//    }
//
    public void changeUser(User user){
        this.user = user;
        user.getAlarms().add(this);
    }

    @Builder
    public Alarm(User user, String alarmContent, LocalDateTime alarmDate, ReadStatus readStatus) {
        this.user = user;
        this.alarmContent = alarmContent;
        this.alarmDate = alarmDate;
        this.readStatus = readStatus;
    }

    public void update(ReadStatus readStatus){
        this.readStatus = readStatus;
    }

}
