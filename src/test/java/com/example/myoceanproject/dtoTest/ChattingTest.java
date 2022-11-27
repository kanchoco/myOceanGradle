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

import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ChattingTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ChattingRepository chattingRepository;
    @Autowired
    private ChattingDTO chattingDTO;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Test
    public void saveTest(){
        Optional<User> user = userRepository.findById(1L);
        ChattingDTO chattingDTO1 = new ChattingDTO();

        chattingDTO1.setChattingContent("첫번째 채팅입니다..");

        Chatting chatting1 = chattingDTO1.toEntity();
        chattingDTO1.setUser(user.get());
        chatting1.changeUser(chattingDTO1.getUser());

        chattingRepository.save(chatting1);


    }

//    @Test
//    public void findAllTest(){
//
//    }
}
