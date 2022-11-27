package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.CommunityReply;
import com.example.myoceanproject.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CommunityReplyDTO {
    private User user;
    private String communityReplyContent;

   public CommunityReply toEntity(){
       return CommunityReply.builder()
               .user(user)
               .communityReplyContent(communityReplyContent)
               .build();
   }
}
