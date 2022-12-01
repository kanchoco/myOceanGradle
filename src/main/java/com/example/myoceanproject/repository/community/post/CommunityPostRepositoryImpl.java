package com.example.myoceanproject.repository.community;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.QCommunityPostDTO;
import com.example.myoceanproject.type.CommunityCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.myoceanproject.entity.QCommunityPost.communityPost;

@Repository
@RequiredArgsConstructor
public class CommunityPostRepositoryImpl implements CommunityPostCustomRepository{
    @Autowired
    private CommunityReplyRepositoryImpl replyRepositoryImpl ;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public Page<CommunityPostDTO> findAllByCategory(Pageable pageable, CommunityCategory communityCategory){
        List<CommunityPostDTO> posts = jpaQueryFactory.select(new QCommunityPostDTO(
                        communityPost.communityPostId,
                        communityPost.user.userId,
                        communityPost.user.userNickname,
                        communityPost.user.userFileName,
                        communityPost.user.userFilePath,
                        communityPost.user.userFileSize,
                        communityPost.user.userFileUuid,
                        communityPost.communityCategory,
                        communityPost.communityTitle,
                        communityPost.communityContent,
                        communityPost.communityViewNumber,
                        communityPost.createDate,
                        communityPost.updatedDate
                ))
                .from(communityPost)
                .where(communityPost.communityCategory.eq(communityCategory))
                .orderBy(communityPost.communityPostId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();

        posts.stream().forEach(communityPostDTO -> {
            communityPostDTO.setCommunityReplyCount(replyRepositoryImpl.CountReplyByCommunityPost(communityPostDTO.getCommunityPostId()));
        });
        long total = jpaQueryFactory.selectFrom(communityPost)
                .where(communityPost.communityCategory.eq(communityCategory))
                .fetch().size();

        return new PageImpl<>(posts, pageable, total);
    }


}
