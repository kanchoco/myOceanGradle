package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.*;
import com.example.myoceanproject.entity.*;
import com.example.myoceanproject.repository.AlarmRepository;
import com.example.myoceanproject.repository.CommunityPostRepository;
import com.example.myoceanproject.repository.CommunityReplyRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;

import static com.example.myoceanproject.entity.QAlarm.alarm;
import static com.example.myoceanproject.entity.QGroup.group;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        communityPost.setUser(userRepository.findById(1L).get());
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

    @Test
    public void findAllTest(){
        List<AlarmDTO> alarms = jpaQueryFactory.select(new QAlarmDTO(
                alarm.user.userId,
                alarm.user.userNickname,
                alarm.alarmContent,
                alarm.readStatus)).from(alarm).fetch();
        alarms.stream().map(AlarmDTO::toString).forEach(log::info);
    }

    @Test
    public void findById(){
//        시나리오 : 유저의 아이디가 1인 알람만 select
        List<AlarmDTO> alarms = jpaQueryFactory.select(new QAlarmDTO(
                alarm.user.userId,
                alarm.user.userNickname,
                alarm.alarmContent,
                alarm.readStatus)).where(alarm.user.userId.eq(1L)).from(alarm).fetch();
        alarms.stream().map(AlarmDTO::toString).forEach(log::info);

    }

    @Test
    public void updateTest(){
//        상태를 read로 바꿔줌

        AlarmDTO alarmDTO = new AlarmDTO();
        alarmDTO.setUserId(1L);
        alarmDTO.setReadStatus(ReadStatus.READ);

//      외부에서 넘겨온 모임 게시글 번호로 영속성 컨텍스트가 관리하는 개체를 가져온다.
        Alarm alarm1 = jpaQueryFactory.selectFrom(alarm).where(alarm.alarmId.eq(4L)).fetchOne();

//      entity로 변환하며 수정이 불가한 내용들은 지워지고 update 메소드에 포함된 내용만 저장이 된다.
        alarm1.setUser(userRepository.findById(alarmDTO.getUserId()).get());

        alarm1.update();

    }

    //    여러개의 컬럼을 update할땐 set을 여러번 사용한다
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