package com.example.myoceanproject.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_GROUP_MEMBER")
@Getter
@ToString(exclude = {"user","group"})
public class GroupMember extends Period{

    @Id
    @GeneratedValue
    private Long groupMemberId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    private Group group;

//    public void create(Long groupMemberId, User user, Group group) {
//        this.groupMemberId = groupMemberId;
//        this.user = user;
//        this.group = group;
//    }
//
//    public void changeUser(User user){
//        this.user = user;
//
//    }
//
//    public void changeGroup(Group group){
//        this.group = group;
//    }


    @Builder
    public GroupMember(User user, Group group) {
        this.user = user;
        this.group = group;
    }
}
