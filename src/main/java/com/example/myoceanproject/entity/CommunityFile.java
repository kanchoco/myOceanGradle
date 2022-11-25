package com.example.myoceanproject.entity;

import com.example.myoceanproject.embeddable.File;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMUNITY_FILE")
@Getter
@ToString(exclude = "communityPost")
public class CommunityFile extends Period{
    @Id
    @GeneratedValue
    private Long communityFileId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMUNITY_POST_ID")
    private CommunityPost communityPost; //FK
    @Embedded
    private File file;

//    public void create(Long communityFileId, CommunityPost communityPost, File file) {
//        this.communityFileId = communityFileId;
//        this.communityPost = communityPost;
//        this.file = file;
//    }
//    public void changeCommunityPost(CommunityPost communityPost){
//        this.communityPost = communityPost;
//        communityPost.getCommunityFiles().add(this);
//    }

    @Builder
    public CommunityFile(CommunityPost communityPost, File file) {
        this.communityPost = communityPost;
        this.file = file;
    }
}
