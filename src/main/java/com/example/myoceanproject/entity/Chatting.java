package com.example.myoceanproject.entity;

import com.example.myoceanproject.type.ReadStatus;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.mapping.List;

import javax.persistence.*;

@Entity
@Table(name = "TBL_CHATTING")
@Getter
@ToString(exclude = {"group", "senderGroupMember"})
@NoArgsConstructor
//        (access = AccessLevel.PROTECTED)
public class Chatting extends Period{
    @Id
    @GeneratedValue
    private Long chattingId;//PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    @NotNull
    private Group group;
    //    받는 사람
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SENDER_GROUP_MEMBER_ID")
    @NotNull
    private GroupMember senderGroupMember;

    @NotNull
    private String chattingContent;



    //    양방향이 아니기 떄문에 add 없음
    public void setGroup(Group group){
        this.group = group;
    }
    public void setSenderGroupMember(GroupMember senderGroupMember){
        this.senderGroupMember = senderGroupMember;
    }


    //  group이 생성되고 chatting이 생성되기 때문에 Builder에는 group을 넣지 않는다.
    @Builder
    public Chatting(String chattingContent) {
        this.chattingContent = chattingContent;
    }



}