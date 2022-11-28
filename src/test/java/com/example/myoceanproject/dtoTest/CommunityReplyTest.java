//package com.example.myoceanproject.dtoTest;
//
//
//import com.example.myoceanproject.domain.CommunityReplyDTO;
//import com.example.myoceanproject.entity.Ask;
//import com.example.myoceanproject.entity.CommunityPost;
//import com.example.myoceanproject.entity.CommunityReply;
//import com.example.myoceanproject.entity.User;
//import com.example.myoceanproject.repository.CommunityPostRepository;
//import com.example.myoceanproject.repository.CommunityReplyRepository;
//import com.example.myoceanproject.repository.UserRepository;
//import com.example.myoceanproject.type.AskCategory;
//import com.example.myoceanproject.type.AskStatus;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//import static com.example.myoceanproject.entity.QAsk.ask;
//import static com.example.myoceanproject.entity.QCommunityReply.communityReply;
//
//@SpringBootTest
//@Slf4j
//@Transactional
//@Rollback(false)
//public class CommunityReplyTest {
//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private CommunityPostRepository communityPostRepository;
//    @Autowired
//    private CommunityReplyRepository communityReplyRepository;
//
//    @Test
//    public void saveCommunityReplyTest(){
//        Optional<User> user = userRepository.findById(1L);
//        Optional<CommunityPost> communityPost = communityPostRepository.findById(1L);
//        CommunityReplyDTO communityReplyDTO = new CommunityReplyDTO();
//
////      댓글 값 저장
//        communityReplyDTO.setCommunityReplyContent("댓글 내용");
//
////      저장 값을 entity로 변환
//        CommunityReply communityReply1 = communityReplyDTO.toEntity();
//
////      유저 정보 저장
//        communityReplyDTO.setUser(user.get());
//        communityReply1.changeUser(communityReplyDTO.getUser());
//
////      게시글 정보 저장
//        communityReplyDTO.setCommunityPost(communityPost.get());
//        communityReply1.changeCommunityPost(communityReplyDTO.getCommunityPost());
//
////      엔티티에 정보 저장
//        communityReplyRepository.save(communityReply1);
//    }
//
//    @Test
//    public void findAllTest(){
//        List<CommunityReply> communityReplies = jpaQueryFactory.selectFrom(communityReply)
//                .join(communityReply.user)
//                .join(communityReply.communityPost)
//                .fetchJoin()
//                .fetch();
//        communityReplies.stream().map(CommunityReply::toString).forEach(log::info);
//    }
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
//        CommunityReply communityReply1 = jpaQueryFactory.selectFrom(communityReply)
//                .where(communityReply.communityReplyId.eq(5L))
//                .fetchOne();
//
//        communityReply1.update("수정된 댓글 내용");
//    }
//
//    @Test
//    public void deleteTest(){
//        Long count = jpaQueryFactory
//                .delete(communityReply)
//                .where(communityReply.communityReplyId.eq(5L))
//                .execute();
//    }
//
//}
