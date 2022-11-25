package com.example.myoceanproject.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_POINT")
@Getter
@ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point extends Period{
    @Id
    @GeneratedValue
    private Long pointId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user; //FK
    @NotNull
    private String pointAmountHistory;

//    public void create(Long pointId, User user, String pointAmountHistory) {
//        this.pointId = pointId;
//        this.user = user;
//        this.pointAmountHistory = pointAmountHistory;
//    }
//
    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public Point(String pointAmountHistory) {
        this.pointAmountHistory = pointAmountHistory;
    }
}
