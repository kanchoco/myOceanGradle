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

//    public void create(Long alarmId, User user, String alarmContent, LocalDateTime alarmDate, ReadStatus readStatus) {
//        this.alarmId = alarmId;
//        this.user = user;
//        this.alarmContent = alarmContent;
//        this.alarmDate = alarmDate;
//        this.readStatus = readStatus;
//    }
//
//  양방향
//    user와 alarm은 바뀔 일이 없기 떄문에 편의 메소드가 사용될 필요가 없다고 판단함
    public void changeUser(User user){
        this.user = user;
        user.getAlarms().add(this);
    }



    @Builder
    public Alarm(String alarmContent, ReadStatus readStatus) {
        this.user = user;
        this.alarmContent = alarmContent;
//        this.alarmDate = alarmDate;
        this.readStatus = readStatus;
    }

//  ReadStatus는 유저가 알람을 보는 순간 READ로 업데이트가 되어야 한다.
    public void update(ReadStatus readStatus){
        this.readStatus = readStatus;
    }

}
