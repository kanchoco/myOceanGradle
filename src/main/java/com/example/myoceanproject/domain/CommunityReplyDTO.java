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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Data
@NoArgsConstructor
public class CommunityReplyDTO {
    private Long userId;
    private String userNickName;
    private String userFileName;
    private String userFilePath;
    private Long userFileSize;
    private String userFileUuid;

    private Long communityPostId;

    private String communityPostTitle;

    private Long communityReplyId;
    private String communityReplyContent;

    private String createDate;

    private String updatedDate;


    @QueryProjection
    public CommunityReplyDTO(Long userId, String userNickName, String userFileName, String userFilePath, Long userFileSize, String userFileUuid, Long communityPostId, String communityPostTitle, Long communityReplyId ,String communityReplyContent, LocalDateTime createDate, LocalDateTime updatedDate) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userFileName = userFileName;
        this.userFilePath = userFilePath;
        this.userFileSize = userFileSize;
        this.userFileUuid = userFileUuid;
        this.communityPostId = communityPostId;
        this.communityPostTitle = communityPostTitle;
        this.communityReplyId = communityReplyId;
        this.communityReplyContent = communityReplyContent;
        this.createDate = createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.updatedDate = updatedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public CommunityReply toEntity(){

       return CommunityReply.builder()
               .communityReplyContent(communityReplyContent)
               .build();
   }
}
