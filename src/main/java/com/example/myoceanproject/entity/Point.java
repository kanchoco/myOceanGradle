package com.example.myoceanproject.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_POINT")
@Getter
@ToString(exclude = "user")
public class Point extends Period{
    @Id
    @GeneratedValue
    private Long pointId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user; //FK
    private String pointAmountHistory;

    public void create(Long pointId, User user, String pointAmountHistory) {
        this.pointId = pointId;
        this.user = user;
        this.pointAmountHistory = pointAmountHistory;
    }

    public void changeUser(User user){
        this.user = user;
    }
}
