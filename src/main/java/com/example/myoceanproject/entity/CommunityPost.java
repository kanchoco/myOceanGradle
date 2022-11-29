package com.example.myoceanproject.entity;

import com.example.myoceanproject.type.CommunityCategory;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "TBL_COMMUNITY_POST")
@Getter
@ToString(exclude = "user")
@NoArgsConstructor
//        (access = AccessLevel.PROTECTED)
public class CommunityPost extends Period{
    @Id
    @GeneratedValue
    private Long communityPostId; //PK
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    private User user; //FK
    @NotNull
    @Enumerated(EnumType.STRING)
    private CommunityCategory communityCategory;
    private String communityTitle;
    private String communityContent;
    private int communityViewNumber;

//  커뮤니티 파일 테이블 양방향
    @OneToMany(fetch = LAZY, mappedBy = "communityPost")
    private List<CommunityFile> communityFiles;

    @OneToMany(fetch = LAZY, mappedBy = "communityPost")
    private List<CommunityLike> communityLikes;

//  양방향
    public void changeUser(User user){
        this.user = user;
    }

    @Builder
    public CommunityPost(CommunityCategory communityCategory, String communityTitle, String communityContent, int communityViewNumber) {
        this.communityCategory = communityCategory;
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.communityViewNumber = communityViewNumber;
    }

//  커뮤니티 게시글은 카테고리와 제목, 내용이 수정 가능하고
//  조회수는 유저가 조회할 경우 계속 업데이트가 된다.
    public void update(CommunityCategory communityCategory, String communityTitle, String communityContent, int communityViewNumber){
        this.communityCategory = communityCategory;
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.communityViewNumber = communityViewNumber;
    }
}
