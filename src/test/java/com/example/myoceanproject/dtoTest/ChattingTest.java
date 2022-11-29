package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.AlarmDTO;
import com.example.myoceanproject.domain.ChattingDTO;
import com.example.myoceanproject.domain.QChattingDTO;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.Chatting;
import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.ChattingRepository;
import com.example.myoceanproject.repository.GroupMemberRepository;
import com.example.myoceanproject.repository.GroupRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.ReadStatus;
import com.example.myoceanproject.type.UserAccountStatus;
import com.example.myoceanproject.type.UserLoginMethod;
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

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Test
    public void saveTest(){

//      유저 정보 db에 저장
        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmail("akjhdgaiafd");
        userDTO.setUserNickname("dlsdud");
        userDTO.setUserPassword("qakjghlig");
        userDTO.setUserLoginMethod(UserLoginMethod.GENERAL);
        userDTO.setUserAccountStatus(UserAccountStatus.ACTIVE);
        userDTO.setUserFileName("유저 프로필사진");
        userDTO.setUserFilePath("aljkgdskjfh");
        userDTO.setUserFileSize(72347L);
        userDTO.setUserFileUuid(3298857L);
        userRepository.save(userDTO.toEntity());

//      DTO에 필요한 값 저장
        ChattingDTO chattingDTO1 = new ChattingDTO();
        chattingDTO1.setChattingContent("두번째 채팅입니다..");
        chattingDTO1.setReadStatus(ReadStatus.UNREAD);

//      chattingDTO에 저장한 값들을 entity로 변환
        Chatting chatting1 = chattingDTO1.toEntity();

//      changeUser 메소드로 user를 엔티티에 set해줌
        chatting1.setUser(userRepository.findById(3L).get());
        chatting1.setGroup(groupRepository.findById(11L).get());

//      chatting 엔티티에 해당 값들을 모두 저장
        chattingRepository.save(chatting1);
    }

    @Test
    public void findAllTest(){
//        전체조회
        List<ChattingDTO> chattingDTOs = jpaQueryFactory.select(new QChattingDTO(
                chatting.user.userId,
                chatting.user.userNickname,
                chatting.user.userFileName,
                chatting.user.userFilePath,
                chatting.user.userFileUuid,
                chatting.user.userFileSize,
                chatting.group.groupId,
                chatting.group.groupName,
                chatting.group.groupFilePath,
                chatting.group.groupFileName,
                chatting.group.groupFileUuid,
                chatting.group.groupFileSize,
                chatting.chattingContent,
                chatting.readStatus
                        ))
                .from(chatting).fetch();

        chattingDTOs.stream().map(ChattingDTO::toString).forEach(log::info);
    }

    @Test
    public void findById(){
//        시나리오 : 특정 그룹의 채팅방에 들어가면 해당 그룹의 채팅 내용만 떠야한다.
//        그룹 아이디가 4인 채팅 내용만 조회
        List<ChattingDTO> chattingDTOs = jpaQueryFactory.select(new QChattingDTO(
                        chatting.user.userId,
                        chatting.user.userNickname,
                        chatting.user.userFileName,
                        chatting.user.userFilePath,
                        chatting.user.userFileUuid,
                        chatting.user.userFileSize,
                        chatting.group.groupId,
                        chatting.group.groupName,
                        chatting.group.groupFilePath,
                        chatting.group.groupFileName,
                        chatting.group.groupFileUuid,
                        chatting.group.groupFileSize,
                        chatting.chattingContent,
                        chatting.readStatus
                )).where(chatting.group.groupId.eq(4L))
                .from(chatting).fetch();

        chattingDTOs.stream().map(ChattingDTO::toString).forEach(log::info);

    }

    @Test
    public void updateTest(){
//        시나리오 : 특정 채팅방에 들어가면  readStatus가 READ로 바뀜

        Chatting chatting1 = jpaQueryFactory.selectFrom(chatting)
                .where(chatting.chattingId.eq(1L))
                .fetchOne();

        chatting1.update(ReadStatus.READ);

    }
//
//    @Test
//    public void deleteTest(){
//        Long count = jpaQueryFactory
//                .delete(chatting)
//                .where(chatting.chattingId.eq(18L))
//                .execute();
//    }
}
