package com.example.myoceanproject.entity;

import com.example.myoceanproject.type.AskCategory;
import lombok.*;

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


//  양방향
    public void changeUser(User user){
        this.user = user;
        user.getAsks().add(this);
    }

    @Builder
    public Ask(String askTitle, String askContent, String askStatus, AskCategory askCategory) {
        this.askTitle = askTitle;
        this.askContent = askContent;
        this.askStatus = askStatus;
        this.askCategory = askCategory;
    }

//  관리자 답변이 발송되면 askStatus의 값이 변하므로 update
//  작성 후 질문 카테고리 변경이 가능하므로 update
    public void update(String askStatus, AskCategory askCategory){
        this.askStatus = askStatus;
        this.askCategory = askCategory;
    }
}