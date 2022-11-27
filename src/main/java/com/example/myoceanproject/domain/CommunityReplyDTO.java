package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.entity.CommunityReply;
import com.example.myoceanproject.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CommunityReplyDTO {
    private User user;

    private CommunityPost communityPost;
    private String communityReplyContent;

    @QueryProjection
    public CommunityReplyDTO(User user, CommunityPost communityPost, String communityReplyContent) {
        this.user = user;
        this.communityPost = communityPost;
        this.communityReplyContent = communityReplyContent;
    }

    public CommunityReply toEntity(){
       return CommunityReply.builder()
               .communityReplyContent(communityReplyContent)
               .build();
   }
}
