package com.example.myoceanproject.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_COMMUNITY_POST")
@Getter
@ToString(exclude = "user")
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

    public void create(Long communityPostId, User user, String communityCategory, String communityTitle, String communityContent, String communityViewNumber) {
        this.communityPostId = communityPostId;
        this.user = user;
        this.communityCategory = communityCategory;
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.communityViewNumber = communityViewNumber;
    }

    public void changeUser(User user){

        this.user = user;
        user.getCommunityPosts().add(this);
    }

}
