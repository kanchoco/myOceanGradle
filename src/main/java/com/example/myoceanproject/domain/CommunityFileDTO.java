package com.example.myoceanproject.domain;

import com.example.myoceanproject.embeddable.File;
import com.example.myoceanproject.entity.CommunityFile;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.entity.Period;
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



    public CommunityFile toEntity(){
        return CommunityFile.builder()
                .communityPost(communityPost)
                .file(file)
                .build();
    }
}
