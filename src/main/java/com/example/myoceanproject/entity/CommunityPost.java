package com.example.myoceanproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_COMMUNITY_POST")
@Getter
@ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityPost extends Period{
    @Id
    @GeneratedValue
    private Long communityPostId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user; //FK
    private String communityCategory;
    private String communityTitle;
    private String communityContent;
    private String communityViewNumber;

//    커뮤니티 파일 테이블 양방향
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "communityPost")
    private List<CommunityFile> communityFiles;

//    public void create(Long communityPostId, User user, String communityCategory, String communityTitle, String communityContent, String communityViewNumber) {
//        this.communityPostId = communityPostId;
//        this.user = user;
//        this.communityCategory = communityCategory;
//        this.communityTitle = communityTitle;
//        this.communityContent = communityContent;
//        this.communityViewNumber = communityViewNumber;
//    }
//
//  일대다 양방향
    public void changeUser(User user){
        this.user = user;
        user.getCommunityPosts().add(this);
    }

    @Builder
    public CommunityPost(String communityCategory, String communityTitle, String communityContent, String communityViewNumber, User user) {
        this.communityCategory = communityCategory;
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.communityViewNumber = communityViewNumber;
        this.user = user;
    }

//  커뮤니티 게시글은 카테고리와 제목, 내용이 수정 가능하고
//  조회수는 유저가 조회할 경우 계속 업데이트가 된다.
    public void update(String communityCategory, String communityTitle, String communityContent, String communityViewNumber){
        this.communityCategory = communityCategory;
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.communityViewNumber = communityViewNumber;
    }
}
