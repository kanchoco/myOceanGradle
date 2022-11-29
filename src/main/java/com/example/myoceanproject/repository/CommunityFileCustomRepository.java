package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.CommunityFileDTO;
import com.example.myoceanproject.entity.CommunityPost;

import java.util.List;

//사용자 지정 레파지토리 인터페이스
public interface CommunityFileCustomRepository {
//    포스트 객체로 포스트 파일을 찾아내서 리스트로 반환하는 메소드
    public List<CommunityFileDTO> findByCommunityPost(CommunityPost communityPost);
//    포스트 객체로 포스트 파일을 찾아내서 전체 삭제하는 메소드
    public void deleteByCommunityPost(CommunityPost communityPost);
}
