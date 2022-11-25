package com.example.myoceanproject.entity;

import com.example.myoceanproject.embeddable.File;
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
    @Embedded
    @NotNull
    private File file;

//    public void create(Long communityFileId, CommunityPost communityPost, File file) {
//        this.communityFileId = communityFileId;
//        this.communityPost = communityPost;
//        this.file = file;
//    }
    public void changeCommunityPost(CommunityPost communityPost){
        this.communityPost = communityPost;
        communityPost.getCommunityFiles().add(this);
    }

    @Builder
    public CommunityFile(CommunityPost communityPost, File file) {

        this.communityPost = communityPost;
        this.file = file;



    }

    public void update(CommunityPost communityPost){
        this.communityPost = communityPost;
    }
}
