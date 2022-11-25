package com.example.myoceanproject.entity;

import com.example.myoceanproject.type.AskCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ASK")
@Getter
@ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ask extends Period{
    @Id
    @GeneratedValue
    private Long askId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;//유저가 null일 경우 자주 묻는 질문
    private String askStatus;
    private String askTitle;
    private String askContent;
    private AskCategory askCategory;

    public void create(Long askId, User user, String askStatus, String askTitle, String askContent, AskCategory askCategory) {
        this.askId = askId;
        this.user = user;
        this.askStatus = askStatus;
        this.askTitle = askTitle;
        this.askContent = askContent;
        this.askCategory = askCategory;
    }

    public void changeUser(User user){

        this.user = user;
        user.getAsks().add(this);
    }
}