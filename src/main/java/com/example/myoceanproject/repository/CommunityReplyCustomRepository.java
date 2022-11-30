package com.example.myoceanproject.repository;

import com.example.myoceanproject.entity.CommunityLike;
import com.example.myoceanproject.entity.CommunityPost;

import java.util.List;

//사용자 지정 레파지토리 인터페이스
public interface CommunityReplyCustomRepository {

    //    전체 라이크 삭제하는 메소드
    public void deleteByCommunityPost(CommunityPost communityPost);
}
