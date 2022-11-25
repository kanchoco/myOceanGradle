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

    @Builder
    public Chatting(String chattingContent, ReadStatus readStatus) {

        this.chattingContent = chattingContent;
        this.readStatus = readStatus;
    }

    public void update(ReadStatus readStatus){
        this.readStatus = readStatus;
    }

}
