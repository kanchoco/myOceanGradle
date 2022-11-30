package com.example.myoceanproject.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMUNITY_LIKE")
@Getter
@ToString(exclude = {"communityPost"})
@NoArgsConstructor
//        (access = AccessLevel.PROTECTED)
public class CommunityLike extends Period{
    @Id
    @GeneratedValue
    private Long communityLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMUNITY_POST_ID")
    @NotNull
    private CommunityPost communityPost;

    @NotNull
    private Long userId;

    public void setUser(User user){
        this.userId=user.getUserId();
    }
    public void setCommunityPost(CommunityPost communityPost){
        this.communityPost=communityPost;
    }

}

