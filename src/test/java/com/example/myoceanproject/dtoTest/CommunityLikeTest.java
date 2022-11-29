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
    public void saveCommunitylikeTest(){
        
//      시나리오: 커뮤니티 게시글(1L) 작성자에게 다른 유저(3L)가 좋아요를 누른다.

//      communityPostRepository 인터페이스 구현체 hibernate의 findById메서드를 이용해서
//      게시글 번호에 해당하는 게시글 내용 검색한다.
        Optional<CommunityPost> communityPost = communityPostRepository.findById(1L);

//      userRepository 인터페이스 구현체 hibernate의 findById메서드를 이용해서
//      다른 유저를 검색한다.
        Optional<User> user = userRepository.findById(3L);
        
//      화면에서 입력받는 내용은 없으며, 다른 유저의 요청에 의해 
//      커뮤니티 게시판에 출력되는 좋아요 값을 변경하기 위해 communityLike객체 선언
        CommunityLike communityLike = new CommunityLike();

//      userRepository 인터페이스 구현체 hibernate의 findbyid메서드로 유저를 검색후 추가
        communityLike.changeUser(user.get());
        
//      communityPostRepository 인터페이스 구현체 hibernate의 findbyid메서드로 게시글 검색후 추가
        communityLike.changeCommunityPost(communityPost.get());

//      커뮤니티 좋아요 테이블에 해당 내용 저장
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
