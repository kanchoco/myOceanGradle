package com.example.myoceanproject.entity;

import com.example.myoceanproject.type.ReadStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_CHATTING")
@Getter
@ToString(exclude = "group")
public class Chatting extends Period{
    @Id
    @GeneratedValue
    private Long chattingId;//PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    private Group group;//FK

    private String chattingContent;

    private ReadStatus readStatus;

//    public void create(Long chattingId, Group group, String chattingContent, ReadStatus readStatus) {
//        this.chattingId = chattingId;
//        this.group = group;
//        this.chattingContent = chattingContent;
//        this.readStatus = readStatus;
//    }
//
//    public void changeGroup(Group group){
//        this.group = group;
//    }

    @Builder
    public Chatting(Group group, String chattingContent, ReadStatus readStatus) {
        this.group = group;
        this.chattingContent = chattingContent;
        this.readStatus = readStatus;
    }
}
