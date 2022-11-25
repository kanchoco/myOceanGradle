package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.CommunityLike;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Component
@Data
@NoArgsConstructor
public class CommunityLikeDTO {

    private CommunityPost communityPost;

    private User user;


    public CommunityLike toEntity(){
        return CommunityLike.builder()
                .communityPost(communityPost)
                .user(user)
                .build();
    }
}
