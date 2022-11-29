package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.CommunityFileDTO;
import com.example.myoceanproject.domain.QCommunityFileDTO;
import com.example.myoceanproject.entity.CommunityFile;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.entity.QCommunityFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.myoceanproject.entity.QCommunityFile.communityFile;

@Repository
@RequiredArgsConstructor
public class CommunityFileRepositoryImpl implements CommunityFileCustomRepository{
//사용자 지정 레파지토리 Impl(구현)

    private final JPAQueryFactory queryFactory;

//포스트 객체를 사용하여 커뮤니티 파일을 리스트로 찾는 메소드
    @Override
    public List<CommunityFileDTO> findByCommunityPost(CommunityPost communityPost){
        return queryFactory.select(new QCommunityFileDTO(
                communityFile.communityPost.communityPostId,
                communityFile.communityFileName,
                communityFile.communityFilePath,
                communityFile.communityFileSize,
                communityFile.communityFileUuid
        )).from(communityFile).where(communityFile.communityPost.communityPostId.eq(communityPost.getCommunityPostId())).fetch();
    }

//    포스트 객체를 사용하여 커뮤니티 파일 전체를 삭제하는 메소드
    @Override
    public void deleteByCommunityPost(CommunityPost communityPost) {
        queryFactory.delete(communityFile)
                .where(communityFile.communityPost.communityPostId.eq(communityPost.getCommunityPostId()))
                .execute();
    }
}
