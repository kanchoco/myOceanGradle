package com.example.myoceanproject.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMUNITY_LIKE")
@Getter
@ToString(exclude = {"communityPost", "user"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityLike extends Period{
    @Id
    @GeneratedValue
    private Long communityLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMUNITY_POST_ID")
    @NotNull
    private CommunityPost communityPost;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user;

//    public void create(Long communityLikeNumber, CommunityPost communityPost, User user) {
//        this.communityLikeNumber = communityLikeNumber;
//        this.communityPost = communityPost;
//        this.user = user;
//    }

    public void changeCommunityPost(CommunityPost communityPost){
        this.communityPost = communityPost;
    }

    public void changeUser(User user){
        this.user = user;
    }


    @Builder
    public CommunityLike(CommunityPost communityPost, User user) {
        this.communityPost = communityPost;
        this.user = user;
    }

}

