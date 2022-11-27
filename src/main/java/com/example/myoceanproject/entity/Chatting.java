package com.example.myoceanproject.entity;

import com.example.myoceanproject.type.ReadStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_CHATTING")
@Getter
@ToString(exclude = "group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatting extends Period{
    @Id
    @GeneratedValue
    private Long chattingId;//PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    @NotNull
    private Group group;//FK

    @NotNull
    private String chattingContent;

    @NotNull
    private ReadStatus readStatus;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user;

//    public void setUser(User user) {
//        this.user = user;
//    }

    //    public void create(Long chattingId, Group group, String chattingContent, ReadStatus readStatus) {
//        this.chattingId = chattingId;
//        this.group = group;
//        this.chattingContent = chattingContent;
//        this.readStatus = readStatus;
//    }
//
    public void changeGroup(Group group){
        this.group = group;
    }
    public void changeUser(User user){
        this.user = user;
//        user.getGroup.add(this);
    }

//  group이 생성되고 chatting이 생성되기 때문에 Builder에는 group을 넣지 않는다.
    @Builder
    public Chatting(String chattingContent, ReadStatus readStatus, Group group, User user) {
        this.chattingContent = chattingContent;
        this.readStatus = readStatus;
        this.group = group;
        this.user = user;
    }

//  채팅을 읽는 순간 READ로 값이 변경되므로 update
    public void update(ReadStatus readStatus){
        this.readStatus = readStatus;
    }

}
