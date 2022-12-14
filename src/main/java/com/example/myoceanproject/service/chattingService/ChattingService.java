package com.example.myoceanproject.service.chattingService;
import com.example.myoceanproject.domain.ChattingDTO;
import com.example.myoceanproject.domain.ChattingStatusDTO;
import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.domain.GroupMemberDTO;
import com.example.myoceanproject.entity.Chatting;
import com.example.myoceanproject.entity.ChattingStatus;
import com.example.myoceanproject.repository.*;
import com.example.myoceanproject.repository.chatting.ChattingRepository;
import com.example.myoceanproject.repository.chatting.ChattingRepositoryImpl;
import com.example.myoceanproject.repository.chatting.ChattingStatusRepository;
import com.example.myoceanproject.repository.chatting.ChattingStatusRepositoryImpl;
import com.example.myoceanproject.type.ReadStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service @Qualifier("chatting") @Primary
@RequiredArgsConstructor
@Slf4j
public class ChattingService {

    private final ChattingRepository chattingRepository;
    private final ChattingStatusRepository chattingStatusRepository;
    private final ChattingRepositoryImpl chattingRepositoryImpl;
    private final ChattingStatusRepositoryImpl chattingStatusRepositoryImpl;

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

    private final UserRepository userRepository;

    //    user 정보를 이용해서 해당 유저가 소속된 그룹을 모두 찾아서 리스트로 반환함
    public List<GroupDTO> show(Long userId){
        return chattingRepositoryImpl.findByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<ChattingDTO> showChatting(Long userId, Long groupId){
        Long groupMemberId = chattingRepositoryImpl.findGroupMemberIdByUserIdAndGroupId(userId, groupId);
        chattingRepositoryImpl.updateChattingReadStatus(groupMemberId);
        return chattingRepositoryImpl.findChattingByUserId(groupId);}

//    public GroupDTO showGroup(Long groupMemberId){return chattingRepositoryImpl.findGroupByGroupMemberId(groupMemberId);}


    public void saveMessage(Long userId, Long groupId, ChattingDTO chattingDTO) {
        Long groupMemberId= chattingRepositoryImpl.findGroupMemberIdByUserIdAndGroupId(userId,groupId);
        log.info("=====================================groupMemberId=========================================");
        log.info(groupMemberId.toString());
        chattingDTO.setSenderGroupMemberId(groupMemberId);
        Chatting chatting = chattingDTO.toEntity();
        chatting.setGroup(groupRepository.findById(groupId).get());
        chatting.setSenderGroupMember(groupMemberRepository.findById(groupMemberId).get());
        log.info("====================================chatting entity=========================================");
        log.info(chatting.toString());
        List<ChattingStatus> chattingStatusList = new ArrayList<>();
        for (GroupMemberDTO groupMemberDTO : chattingRepositoryImpl.findByGroupId(chatting.getGroup().getGroupId())) {
            ChattingStatusDTO chattingStatusDTO = new ChattingStatusDTO();
            log.info("==========================saveMessage=================================");
            log.info("==========================saveMessage=================================");
            log.info("==========================saveMessage=================================");
            log.info(groupMemberDTO.getGroupMemberId().toString());
            log.info(chattingDTO.getSenderGroupMemberId().toString());
            if(groupMemberDTO.getGroupMemberId() == chattingDTO.getSenderGroupMemberId()){
                chattingStatusDTO.setReadStatus(ReadStatus.READ);
            }else {
                chattingStatusDTO.setReadStatus(ReadStatus.UNREAD);
            }
            ChattingStatus chattingStatus1 = chattingStatusDTO.toEntity();
            chattingStatus1.setReceiverGroupMember(groupMemberRepository.findById(groupMemberDTO.getGroupMemberId()).get());
            chattingStatus1.setChatting(chatting);
            chattingStatusList.add(chattingStatus1);
        }
        chattingRepository.save(chatting);
        chattingStatusRepository.saveAll(chattingStatusList);
        System.out.println(chatting);
    }

    public List<GroupDTO> showGroupAndUnreadChat(Long userId){

        List<GroupDTO> groupDTOList = chattingRepositoryImpl.findByUserId(userId);
        for (GroupDTO groupDTO : groupDTOList) {
            groupDTO.setUnreadMessage(chattingRepositoryImpl.findUnreadChattingByGroupMemberId(userId, groupDTO.getGroupId()));
        }
        return groupDTOList;
    }
}