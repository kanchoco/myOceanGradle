package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.entity.CommunityReply;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.UserRepository;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CommunityReplyDTO {
    private Long userId;
    private String userNickName;
    private String userFileName;
    private String userFilePath;
    private Long userFileSize;
    private Long userFileUuid;

    private CommunityPost communityPost;
    private String communityReplyContent;




    @QueryProjection
    public CommunityReplyDTO(Long userId, String userNickName, CommunityPost communityPost, String communityReplyContent, String userFileName, String userFilePath, Long userFileSize, Long userFileUuid) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.communityPost = communityPost;
        this.communityReplyContent = communityReplyContent;
        this.userFileName = userFileName;
        this.userFilePath = userFilePath;
        this.userFileSize = userFileSize;
        this.userFileUuid = userFileUuid;
    }


    public CommunityReply toEntity(){

       return CommunityReply.builder()
               .communityReplyContent(communityReplyContent)
               .build();
   }
}
