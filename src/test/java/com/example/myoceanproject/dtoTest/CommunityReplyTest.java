package com.example.myoceanproject.dtoTest;


import com.example.myoceanproject.domain.CommunityReplyDTO;
import com.example.myoceanproject.entity.Ask;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.entity.CommunityReply;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.CommunityPostRepository;
import com.example.myoceanproject.repository.CommunityReplyRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.AskCategory;
import com.example.myoceanproject.type.AskStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.myoceanproject.entity.QAsk.ask;
import static com.example.myoceanproject.entity.QCommunityReply.communityReply;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CommunityReplyTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommunityPostRepository communityPostRepository;
    @Autowired
    private CommunityReplyRepository communityReplyRepository;


    @Test
    public void saveCommunityReplyTest(){

//      시나리오: 커뮤니티 게시글(1L) 작성자에게 다른 유저(3L)가 댓글을 추가한다.
        
//      userRepository 인터페이스 구현체 hibernate의 findById메서드를 이용해서
//      다른 유저를 검색한다.
        Optional<User> user = userRepository.findById(3L);
        
//      communityPostRepository 인터페이스 구현체 hibernate의 findById메서드를 이용해서
//      게시글 번호에 해당하는 게시글 내용 검색한다.
        Optional<CommunityPost> communityPost = communityPostRepository.findById(1L);
        
//      화면에서 댓글내용을 입력받기 위해 댓글 DTO 객체 생성
        CommunityReplyDTO communityReplyDTO = new CommunityReplyDTO();

//      댓글 내용을 화면에서 입력받는다.
        communityReplyDTO.setCommunityReplyContent("새로운 작성자 댓글 추가");

//      추가 저장을 위해 toentity메서드를 통해 communityReply객체에 저장
        CommunityReply communityReply = communityReplyDTO.toEntity();

//      userRepository 인터페이스 구현체 hibernate의 findbyid메서드로 유저를 검색후 추가
        communityReply.changeUser(user.get());

//      communityPostRepository 인터페이스 구현체 hibernate의 findbyid메서드로 게시글 검색후 추가
        communityReply.changeCommunityPost(communityPost.get());

//      커뮤니티 댓글 테이블에 해당 내용 저장
        communityReplyRepository.save(communityReply);
    }

    @Test
    public void findAllTest(){
        List<CommunityReply> communityReplies = jpaQueryFactory.selectFrom(communityReply)
                .join(communityReply.user)
                .join(communityReply.communityPost)
                .where(communityReply.communityPost.communityPostId.eq(1L))
                .where(communityReply.user.userId.eq(2L))
                .groupBy(communityReply.communityReplyId)
//                .orderBy(communityReply.updatedDate)
                .fetchJoin()
                .fetch();
        communityReplies.stream().map(CommunityReply::toString).forEach(log::info);
    }
//
//    @Test
//    public void findById(){
//        List<CommunityReply> communityReplies = jpaQueryFactory.selectFrom(communityReply)
//                .join(communityReply.user)
//                .join(communityReply.communityPost)
//                .where(communityReply.user.userId.eq(1L))
//                .fetchJoin()
//                .fetch();
//
//        communityReplies.stream().map(CommunityReply::toString).forEach(log::info);
//
//    }
//
//    @Test
//    public void updateTest(){
//
//        CommunityReply communityReply = jpaQueryFactory.selectFrom(communityReply)
//                .where(communityReply.communityReplyId.eq(5L))
//                .fetchOne();
//
//        communityReply.update("수정된 댓글 내용");
//    }
//
//    @Test
//    public void deleteTest(){
//        Long count = jpaQueryFactory
//                .delete(communityReply)
//                .where(communityReply.communityReplyId.eq(5L))
//                .execute();
//    }

}
