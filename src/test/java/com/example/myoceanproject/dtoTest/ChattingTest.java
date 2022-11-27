package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.AlarmDTO;
import com.example.myoceanproject.domain.ChattingDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.Chatting;
import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.ChattingRepository;
import com.example.myoceanproject.repository.GroupRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.ReadStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.myoceanproject.entity.QAlarm.alarm;
import static com.example.myoceanproject.entity.QChatting.chatting;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ChattingTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ChattingRepository chattingRepository;

    @Test
    public void saveTest(){
//      1번 유저 불러오기
        Optional<User> user = userRepository.findById(1L);
        ChattingDTO chattingDTO1 = new ChattingDTO();

//      DTO에 필요한 값 저장
        chattingDTO1.setChattingContent("첫번째 채팅입니다..");
        chattingDTO1.setReadStatus(ReadStatus.UNREAD);

//      chattingDTO에 저장한 값들을 entity로 변환
        Chatting chatting1 = chattingDTO1.toEntity();

//      chattingDTO에 처음 조회했던 유저 정보를 저장
//      changeUser 메소드로 chattingDTO에 저장된 User값을 chattingDTO1로 전달
        chattingDTO1.setUser(user.get());
        chatting1.changeUser(chattingDTO1.getUser());

//      chatting 엔티티에 해당 값들을 모두 저장
        chattingRepository.save(chatting1);
    }

    @Test
    public void findAllTest(){
        List<Chatting> chattings = jpaQueryFactory.selectFrom(chatting)
                .join(chatting.user)
                .fetchJoin()
                .fetch();

        chattings.stream().map(Chatting::toString).forEach(log::info);
    }

    @Test
    public void findById(){
        List<Chatting> chattings = jpaQueryFactory.selectFrom(chatting)
                .join(chatting.user)
                .where(chatting.user.userId.eq(1L))
                .fetchJoin()
                .fetch();

        chattings.stream().map(Chatting::toString).forEach(log::info);

    }

    @Test
    public void updateTest(){

        Chatting chatting1 = jpaQueryFactory.selectFrom(chatting)
                .where(chatting.chattingId.eq(1L))
                .fetchOne();

        chatting1.update(ReadStatus.READ);

    }

    @Test
    public void deleteTest(){
        Long count = jpaQueryFactory
                .delete(chatting)
                .where(chatting.chattingId.eq(18L))
                .execute();
    }
}
