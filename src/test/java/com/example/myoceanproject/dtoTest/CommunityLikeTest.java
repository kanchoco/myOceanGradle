package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.entity.CommunityLike;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.CommunityLikeRepository;
import com.example.myoceanproject.repository.CommunityPostRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.myoceanproject.entity.QCommunityLike.communityLike;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CommunityLikeTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommunityPostRepository communityPostRepository;
    @Autowired
    CommunityLikeRepository communityLikeRepository;


    @Test
    public void saveCommunityFileTest(){
        
//      커뮤니티 게시글 3번 불러오기
        Optional<CommunityPost> communityPost = communityPostRepository.findById(3L);
        Optional<User> user = userRepository.findById(1L);
        CommunityLike communityLike = new CommunityLike();


        communityLikeRepository.save(communityLike);

    }

    @Test
    public void findAllTest(){
        List<CommunityLike> communityLikes = jpaQueryFactory.selectFrom(communityLike)
                .join(communityLike.communityPost)
                .fetchJoin()
                .fetch();
        communityLikes.stream().map(CommunityLike::toString).forEach(log::info);
    }

    @Test
    public void findById(){
        List<CommunityLike> communityLikes = jpaQueryFactory.selectFrom(communityLike)
                .join(communityLike.communityPost)
                .where(communityLike.communityPost.communityPostId.eq(3L))
                .fetchJoin()
                .fetch();

        communityLikes.stream().map(CommunityLike::toString).forEach(log::info);
    }


    @Test
    public void deleteTest(){
        Long count = jpaQueryFactory
                .delete(communityLike)
                .where(communityLike.communityLikeId.eq(4L))
                .execute();
    }
}
