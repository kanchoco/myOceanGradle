package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.ChattingDTO;
import com.example.myoceanproject.domain.ChattingStatusDTO;
import com.example.myoceanproject.domain.QChattingDTO;
import com.example.myoceanproject.entity.*;
import com.example.myoceanproject.repository.*;
import com.example.myoceanproject.type.ReadStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private ChattingRepositoryImpl chattingRepositoryImple;
    @Autowired
    private ChattingStatusRepository chattingStatusRepository;


    @Test
    public void saveTest(){
//        시나리오 : 8번 그룹에서 16번 멤버가 메세지를 보내면 3번 그룹의 멤버들의 그룹멤버아이디로 readStatus가 unread로 저장된다.

        ChattingDTO chattingDTO = new ChattingDTO();

        chattingDTO.setChattingContent("안녕하세요 저는 김인영입니다.");
        Chatting chatting1 = chattingDTO.toEntity();
        chatting1.setSenderGroupMember(groupMemberRepository.findById(16L).get());
        chatting1.setGroup(groupMemberRepository.findById(16L).get().getGroup());

        List<ChattingStatus> chattingStatusList = new ArrayList<>();
        for (GroupMember groupMember:chattingRepositoryImple.findByGroupId(chatting1.getGroup().getGroupId())) {
            ChattingStatusDTO chattingStatusDTO = new ChattingStatusDTO();
            chattingStatusDTO.setReadStatus(ReadStatus.UNREAD);
            ChattingStatus chattingStatus1 = chattingStatusDTO.toEntity();
            chattingStatus1.setReceiverGroupMember(groupMember);
            chattingStatus1.setChatting(chatting1);
            chattingStatusList.add(chattingStatus1);
        }
        chattingRepository.save(chatting1);
        chattingStatusRepository.saveAll(chattingStatusList);
    }


    @Test
    public void findById(){
//        시나리오 : 특정 그룹의 채팅방에 들어가면 해당 그룹의 채팅 내용만 떠야한다.
//        그룹 아이디가 8인 채팅 내용만 조회
        List<ChattingDTO> chattingDTOs = jpaQueryFactory.select(new QChattingDTO(
                        chatting.senderGroupMember.user.userId,
                        chatting.senderGroupMember.user.userNickname,
                        chatting.senderGroupMember.user.userFileName,
                        chatting.senderGroupMember.user.userFilePath,
                        chatting.senderGroupMember.user.userFileUuid,
                        chatting.senderGroupMember.user.userFileSize,
                        chatting.group.groupId,
                        chatting.group.groupName,
                        chatting.group.groupFilePath,
                        chatting.group.groupFileName,
                        chatting.group.groupFileUuid,
                        chatting.group.groupFileSize,
                        chatting.senderGroupMember.groupMemberId,
                        chatting.chattingContent
                )).where(chatting.group.groupId.eq(8L))
                .from(chatting).fetch();

        chattingDTOs.stream().map(ChattingDTO::toString).forEach(log::info);

    }
//
//    @Test
//    public void updateTest(){
////        시나리오 : 특정 채팅방에 들어가면  readStatus가 READ로 바뀜
//
//        Chatting chatting1 = jpaQueryFactory.selectFrom(chatting)
//                .where(chatting.chattingId.eq(1L))
//                .fetchOne();
//
//        chatting1.update(ReadStatus.READ);
//
//    }
////
////    @Test
////    public void deleteTest(){
////        Long count = jpaQueryFactory
////                .delete(chatting)
////                .where(chatting.chattingId.eq(18L))
////                .execute();
////    }
}
