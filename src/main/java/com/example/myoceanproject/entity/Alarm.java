package com.example.myoceanproject.entity;

import com.example.myoceanproject.type.ReadStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Alarm")
@Table(name = "TBL_ALARM")
@Getter
@ToString(exclude = "user")
@NoArgsConstructor
//        (access = AccessLevel.PROTECTED)
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
//    @NotNull
//    private LocalDateTime alarmDate;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ReadStatus readStatus; //Enum

//  양방향
    public void changeUser(User user){
        this.user = user;
        user.getAlarms().add(this);
    }

    @Builder
    public Alarm(String alarmContent, ReadStatus readStatus) {
        this.alarmContent = alarmContent;
        this.readStatus = readStatus;
    }

//  ReadStatus는 유저가 알람을 보는 순간 READ로 업데이트가 되어야 한다.
    public void update(ReadStatus readStatus){
        this.readStatus = readStatus;
    }

}
