package com.example.myoceanproject.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMUNITY_REPLY")
@Getter
@ToString(exclude = {"communityPost", "user"})
public class CommunityReply extends Period{

    @Id
    @GeneratedValue
    private Long communityReplyId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMUNITY_POST_ID")
    private CommunityPost communityPost; //FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user; //FK
    private String communityReplyContent;

    public CommunityReply(Long communityReplyId, CommunityPost communityPost, User user, String communityReplyContent) {
        this.communityReplyId = communityReplyId;
        this.communityPost = communityPost;
        this.user = user;
        this.communityReplyContent = communityReplyContent;
    }

    public void changeCommunityPost(CommunityPost communityPost){
        this.communityPost = communityPost;
    }


}
