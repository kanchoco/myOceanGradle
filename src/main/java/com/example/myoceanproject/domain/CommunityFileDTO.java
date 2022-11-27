package com.example.myoceanproject.domain;

import com.example.myoceanproject.embeddable.File;
import com.example.myoceanproject.entity.CommunityFile;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.entity.Period;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Embedded;

@Component
@Data
@NoArgsConstructor
public class CommunityFileDTO {

    private CommunityPost communityPost; //FK

    private File file;
    @QueryProjection
    public CommunityFileDTO(CommunityPost communityPost, File file) {
        this.communityPost = communityPost;
        this.file = file;
    }

//  게시글 작성 완료 시 처음으로 게시글 내용과 파일이 저장된다.
    public CommunityFile toEntity(){
        return CommunityFile.builder()
                .file(file)
                .build();
    }
}
