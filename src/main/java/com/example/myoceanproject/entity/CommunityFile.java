package com.example.myoceanproject.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMUNITY_FILE")
@Getter
@ToString(exclude = "communityPost")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityFile extends Period{
    @Id
    @GeneratedValue
    private Long communityFileId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMUNITY_POST_ID")
    @NotNull
    private CommunityPost communityPost; //FK


    
//  양방향
    public void changeCommunityPost(CommunityPost communityPost){
        this.communityPost = communityPost;
        communityPost.getCommunityFiles().add(this);
    }


//  게시글의 내용이 수정될 수 있으므로 update
//  file의 경우 수정 시에도 삭제된 후 새롭게 insert를 하기 때문에 update에 포함되지 않는다.
    public void update(CommunityPost communityPost){
        this.communityPost = communityPost;
    }
}
