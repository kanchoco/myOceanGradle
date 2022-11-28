package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.AlarmDTO;
import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.CommunityReplyDTO;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.entity.QAlarm;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.AlarmRepository;
import com.example.myoceanproject.repository.CommunityPostRepository;
import com.example.myoceanproject.repository.CommunityReplyRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.CommunityCategory;
import com.example.myoceanproject.type.ReadStatus;
import com.example.myoceanproject.type.UserAccountStatus;
import com.example.myoceanproject.type.UserLoginMethod;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;

import static com.example.myoceanproject.entity.QAlarm.alarm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class AlarmTest {
    @Autowired
    private AlarmDTO alarmDTO;
    @Autowired
    private AlarmRepository alarmRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private CommunityPostRepository communityPostRepository;

    @Autowired
    private CommunityReplyRepository communityReplyRepository;


    @Test
    public void saveAlarmTest(){

//      댓글 작성 시 알람이 게시글 작성자에게 오도록 함
//      replyDTO를 선언하고 여기에 댓글 내용이 추가되면,
//      post 작성자에게 알람 메세지가 오도록 함
//      따라서 postRepository의 findById를 해서 알람이 오도록 진행
        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmail("akjhdgaiafd");
        userDTO.setUserNickname("dlsdud");
        userDTO.setUserPassword("qakjghlig");
        userDTO.setUserLoginMethod(UserLoginMethod.GENERAL);
        userDTO.setUserAccountStatus(UserAccountStatus.ACTIVE);
        userRepository.save(userDTO.toEntity());
        CommunityPostDTO communityPostDTO = new CommunityPostDTO();

        communityPostDTO.setCommunityCategory(CommunityCategory.BOOK);
        communityPostDTO.setCommunityTitle("안녕");
        communityPostDTO.setCommunityContent("ahffkahffk");
        CommunityPost communityPost = communityPostDTO.toEntity();
        communityPost.changeUser(userRepository.findById(1L).get());
        communityPostRepository.save(communityPost);


        CommunityReplyDTO communityReplyDTO = new CommunityReplyDTO();
        communityReplyDTO.setCommunityReplyContent("댓글을 달았습니다.");
        communityReplyDTO.setUserId(2L);
//      postId 받아오기
        communityReplyDTO.setCommunityPostId(1L);
        communityReplyRepository.save(communityReplyDTO.toEntity());


//      1번 유저 불러오기
        User user = communityPostRepository.findById(2L).get().getUser();

        Alarm alarm1 = new Alarm();
        alarm1.setAlarmContent("알ㄹ마알망ㄹ");
        alarm1.setUser(user);
        alarm1.setReadStatus(ReadStatus.UNREAD);

        alarmRepository.save(alarm1);



    }

//    @Test
//    public void findAllTest(){
//        List<Alarm> alarms = jpaQueryFactory.selectFrom(new QAlarm(alarm))
//                .join(alarm.user)
//                .fetchJoin()
//                .fetch();
//        alarms.stream().map(Alarm::toString).forEach(log::info);
//    }
//
//    @Test
//    public void findById(){
//        List<Alarm> alarms = jpaQueryFactory.selectFrom(alarm)
//                .join(alarm.user)
//                .where(alarm.user.userId.eq(1L))
//                .fetchJoin()
//                .fetch();
//
//        alarms.stream().map(Alarm::toString).forEach(log::info);
//
//    }
//
//    @Test
//    public void updateTest(){
//
//        Alarm alarm1 = jpaQueryFactory.selectFrom(alarm)
//                .where(alarm.alarmContent.eq("첫 알람입니다."))
//                .fetchOne();
//
//        alarm1.update(ReadStatus.UNREAD);
//
//    }
//
//    //    여러개의 컬럼을 update할땐 set을 여러번 사용한다
//    @Test
//    public void updateMultipleTest(){
//
//
//        List<Alarm> alarms = jpaQueryFactory.selectFrom(alarm)
//                .where(alarm.readStatus.eq(ReadStatus.UNREAD))
//                .fetch();
//
//        alarms.stream().forEach(v->{v.update(ReadStatus.READ);});
//    }
//
//    @Test
//    public void deleteTest(){
//        Long count = jpaQueryFactory
//                .delete(alarm)
//                .where(alarm.alarmContent.eq("두번째수정"))
//                .execute();
//    }


}