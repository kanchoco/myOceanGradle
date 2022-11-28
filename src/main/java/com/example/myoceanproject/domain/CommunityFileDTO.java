package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.CommunityFile;
import com.example.myoceanproject.entity.CommunityPost;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CommunityFileDTO {

    private Long communityPostId;
    private String communityFileName;
    private String communityFilePath;
    private Long communityFileSize;
    private Long communityFileUuid;

    @QueryProjection
    public CommunityFileDTO(Long communityPostId, String communityFileName, String communityFilePath, Long communityFileSize, Long communityFileUuid) {
        this.communityPostId = communityPostId;
        this.communityFileName = communityFileName;
        this.communityFilePath = communityFilePath;
        this.communityFileSize = communityFileSize;
        this.communityFileUuid = communityFileUuid;
    }




//  게시글 작성 완료 시 처음으로 게시글 내용과 파일이 저장된다.
    public CommunityFile toEntity(){
        return CommunityFile.builder()
                .communityFileName(communityFileName)
                .communityFileSize(communityFileSize)
                .communityFilePath(communityFilePath)
                .communityFileUuid(communityFileUuid)
                .build();
    }
}
