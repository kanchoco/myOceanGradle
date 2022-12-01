package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.type.CommunityCategory;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Data
@NoArgsConstructor
public class CommunityPostDTO {
    private Long userId;
    private String userNickName;
    private String userFileName;
    private String userFilePath;
    private Long userFileSize;
    private Long userFileUuid;


    private CommunityCategory communityCategory;
    private String communityTitle;
    private String communityContent;
    private int communityViewNumber;

    private Long communityPostId;

    private int communityReplyCount;

    private String createDate;

    private String updatedDate;



    @QueryProjection
    public CommunityPostDTO(Long communityPostId, Long userId, String userNickName, String userFileName, String userFilePath, Long userFileSize, Long userFileUuid, CommunityCategory communityCategory, String communityTitle, String communityContent, int communityViewNumber, LocalDateTime createDate, LocalDateTime updatedDate) {
        this.communityPostId = communityPostId;
        this.userId = userId;
        this.userNickName = userNickName;
        this.userFileName = userFileName;
        this.userFilePath = userFilePath;
        this.userFileSize = userFileSize;
        this.userFileUuid = userFileUuid;
        this.communityCategory = communityCategory;
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.communityViewNumber = communityViewNumber;
        this.createDate = createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.updatedDate = updatedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }




    //  게시글 작성 시 게시글 제목, 카테고리, 내용이 처음으로 저장된다.
    public CommunityPost toEntity(){
        return CommunityPost.builder()
                .communityTitle(communityTitle)
                .communityCategory(communityCategory)
                .communityContent(communityContent)
//                .communityViewNumber(communityViewNumber)
                .build();
    }
}