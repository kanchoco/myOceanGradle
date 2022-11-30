//package com.example.myoceanproject.repository.dtoTest;
//
//import com.example.myoceanproject.domain.ChattingDTO;
//import com.example.myoceanproject.entity.*;
//import com.example.myoceanproject.repository.*;
//import com.example.myoceanproject.type.ReadStatus;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//
//@SpringBootTest
//@Slf4j
//@Transactional
//@Rollback(false)
//public class ChattingTest {
//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private GroupRepository groupRepository;
//    @Autowired
//    private ChattingRepository chattingRepository;
//
//    @Autowired
//    private GroupMemberRepository groupMemberRepository;
//
//    @Autowired
//    private ChattingRepositoryImpl chattingRepositoryImple;
//
//    @Test
//    public void saveTest(){
////        시나리오 : 3번 그룹에서 12번 멤버가 메세지를 보내면 3번 그룹의 멤버인 12, 15, 18번에게 메세지가 저장된다.
////        처음 저장될때는 읽지 않은 상태기 때문에 status는 unread로 저장한다
////        그룹멤버 테이블의 경우 fk만 두개이기 때문에 DTO가 없어 entity에 바로 set 해준다.
//
////        그룹 멤버 번호 12번의 정보를 저장한다
//        GroupMember groupMember = groupMemberRepository.findById(12L).get();
//        List<GroupMember> groupMemberList = chattingRepositoryImple.findByGroupId(groupMember.getGroup().getGroupId());
//
//        for (int i = 0; i<groupMemberList.size(); i++) {
//
//            ChattingDTO chattingDTO = new ChattingDTO();
//            chattingDTO.setChattingContent("안녕 나는 김인영 공주야");
//            chattingDTO.setReadStatus(ReadStatus.UNREAD);
//            Chatting chatting1 = chattingDTO.toEntity();
//            chatting1.setGroup(groupMember.getGroup());
//            chatting1.setSenderGroupMember(groupMember);
//            chatting1.setReceiverGroupMember(groupMemberList.get(i));
//            chattingRepository.save(chatting1);
//
//
//        }
//
//
//
//    }
//
////    @Test
////    public void findAllTest(){
//////        전체조회
////        List<ChattingDTO> chattingDTOs = jpaQueryFactory.select(new QChattingDTO(
////                chatting.user.userId,
////                chatting.user.userNickname,
////                chatting.user.userFileName,
////                chatting.user.userFilePath,
////                chatting.user.userFileUuid,
////                chatting.user.userFileSize,
////                chatting.group.groupId,
////                chatting.group.groupName,
////                chatting.group.groupFilePath,
////                chatting.group.groupFileName,
////                chatting.group.groupFileUuid,
////                chatting.group.groupFileSize,
////                chatting.chattingContent,
////                chatting.readStatus
////                        ))
////                .from(chatting).fetch();
////
////        chattingDTOs.stream().map(ChattingDTO::toString).forEach(log::info);
////    }
////
////    @Test
////    public void findById(){
//////        시나리오 : 특정 그룹의 채팅방에 들어가면 해당 그룹의 채팅 내용만 떠야한다.
//////        그룹 아이디가 4인 채팅 내용만 조회
////        List<ChattingDTO> chattingDTOs = jpaQueryFactory.select(new QChattingDTO(
////                        chatting.user.userId,
////                        chatting.user.userNickname,
////                        chatting.user.userFileName,
////                        chatting.user.userFilePath,
////                        chatting.user.userFileUuid,
////                        chatting.user.userFileSize,
////                        chatting.group.groupId,
////                        chatting.group.groupName,
////                        chatting.group.groupFilePath,
////                        chatting.group.groupFileName,
////                        chatting.group.groupFileUuid,
////                        chatting.group.groupFileSize,
////                        chatting.chattingContent,
////                        chatting.readStatus
////                )).where(chatting.group.groupId.eq(4L))
////                .from(chatting).fetch();
////
////        chattingDTOs.stream().map(ChattingDTO::toString).forEach(log::info);
////
////    }
////
////    @Test
////    public void updateTest(){
//////        시나리오 : 특정 채팅방에 들어가면  readStatus가 READ로 바뀜
////
////        Chatting chatting1 = jpaQueryFactory.selectFrom(chatting)
////                .where(chatting.chattingId.eq(1L))
////                .fetchOne();
////
////        chatting1.update(ReadStatus.READ);
////
////    }
//////
//////    @Test
//////    public void deleteTest(){
//////        Long count = jpaQueryFactory
//////                .delete(chatting)
//////                .where(chatting.chattingId.eq(18L))
//////                .execute();
//////    }
//}
