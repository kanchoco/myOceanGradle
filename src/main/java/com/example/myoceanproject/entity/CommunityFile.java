package com.example.myoceanproject.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.security.cert.CertPathBuilder;

@Entity
@Table(name = "TBL_COMMUNITY_FILE")
@Getter
@ToString(exclude = "communityPost")
@NoArgsConstructor
//        (access = AccessLevel.PROTECTED)
public class CommunityFile extends Period{
    @Id
    @GeneratedValue
    private Long communityFileId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMUNITY_POST_ID")
    @NotNull
    private CommunityPost communityPost; //FK
    @NotNull
    private String communityFileName;
    @NotNull
    private String communityFilePath;
    @NotNull
    private Long communityFileSize;
    @NotNull
    private Long communityFileUuid;





    //  양방향
    public void setCommunityPost(CommunityPost communityPost){
        this.communityPost = communityPost;
    }

    @Builder
    public CommunityFile(Long communityFileId, CommunityPost communityPost, String communityFileName, String communityFilePath, Long communityFileSize, Long communityFileUuid) {
        this.communityFileId = communityFileId;
        this.communityPost = communityPost;
        this.communityFileName = communityFileName;
        this.communityFilePath = communityFilePath;
        this.communityFileSize = communityFileSize;
        this.communityFileUuid = communityFileUuid;
    }






//  게시글의 내용이 수정될 수 있으므로 update
//  file의 경우 수정 시에도 삭제된 후 새롭게 insert를 하기 때문에 update에 포함되지 않는다.
    public void update(CommunityPost communityPost){
        this.communityPost = communityPost;
    }
}
