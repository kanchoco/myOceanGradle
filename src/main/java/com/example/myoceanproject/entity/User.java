package com.example.myoceanproject.entity;

import com.example.myoceanproject.embeddable.File;
import com.example.myoceanproject.type.UserAccountStatus;
import com.example.myoceanproject.type.UserLoginMethod;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_USER")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Period {

    @Id
    @GeneratedValue
    private Long userId; //PK
    private String userEmail;
    private String userPassword;
    private String userNickname;
    private String userName;
    private UserAccountStatus userAccountStatus; //Enum으로 사용
    private UserLoginMethod userLoginMethod;
    @Embedded
    private File file;

    //    그룹 테이블 양방향
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Group> groups;

    //    투두리스트 테이블 양방향
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<ToDoList> toDoLists;

    //    다이어리 테이블 양방향
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Diary> diaries;

    //    문의사항 테이블 양방향(나의 질문)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Ask> asks;

    //    커뮤니티 포스트 양방향
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<CommunityPost> communityPosts;

    //    퀘스트 달성 테이블과 양방향
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<QuestAchievement> questAchievements;

    //    알람테이블과 양방향
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Alarm> alarms;

    public User(Long userId, String userEmail, String userPassword, String userNickname, String userName, UserAccountStatus userAccountStatus, UserLoginMethod userLoginMethod, File file) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userName = userName;
        this.userAccountStatus = userAccountStatus;
        this.userLoginMethod = userLoginMethod;
        this.file = file;
    }


}