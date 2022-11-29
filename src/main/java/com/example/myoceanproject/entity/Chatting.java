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
    @Enumerated(EnumType.STRING)
    private ReadStatus readStatus;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user;

    public void setGroup(Group group){
        this.group = group;
    }
//    양방향이 아니기 떄문에 add 없음
    public void setUser(User user){
        this.user = user;
    }

//  group이 생성되고 chatting이 생성되기 때문에 Builder에는 group을 넣지 않는다.
    @Builder
    public Chatting(String chattingContent, ReadStatus readStatus) {
        this.chattingContent = chattingContent;
        this.readStatus = readStatus;
    }

//  채팅을 읽는 순간 READ로 값이 변경되므로 update
    public void update(ReadStatus readStatus){
        this.readStatus = readStatus;
    }

}
