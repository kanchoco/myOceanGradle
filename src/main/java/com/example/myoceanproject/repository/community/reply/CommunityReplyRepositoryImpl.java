package com.example.myoceanproject.repository.community;

import com.example.myoceanproject.entity.CommunityPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.example.myoceanproject.entity.QCommunityReply.communityReply;

@Repository
@RequiredArgsConstructor
public class CommunityReplyRepositoryImpl implements CommunityReplyCustomRepository{
//사용자 지정 레파지토리 Impl(구현)

    private final JPAQueryFactory queryFactory;



//    해당포스트의 전체 댓글 삭제
    @Override
    public void deleteByCommunityPost(CommunityPost communityPost) {
        queryFactory.delete(communityReply)
                .where(communityReply.communityPost.communityPostId.eq(communityPost.getCommunityPostId()))
                .execute();
    }

    @Override
    public Integer CountReplyByCommunityPost(Long communityPostId){
        return Math.toIntExact(queryFactory.select(communityReply.communityReplyId.count())
                .from(communityReply)
                .where(communityReply.communityPost.communityPostId.eq(communityPostId))
                .fetchFirst());
    }

}
