package com.example.myoceanproject.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMUNITY_LIKE")
@Getter
@ToString(exclude = {"communityPost", "user"})
public class CommunityLike extends Period{
    @Id
    @GeneratedValue
    private Long communityLikeNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMUNITY_POST_ID")
    private CommunityPost communityPost;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public void create(Long communityLikeNumber, CommunityPost communityPost, User user) {
        this.communityLikeNumber = communityLikeNumber;
        this.communityPost = communityPost;
        this.user = user;
    }

    public void changeCommunityPost(CommunityPost communityPost){
        this.communityPost = communityPost;
    }

    public void changeUser(User user){
        this.user = user;
    }
}

