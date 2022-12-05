package com.example.myoceanproject.service.chattingService;
import com.example.myoceanproject.domain.ChattingDTO;
import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.repository.*;
import com.example.myoceanproject.repository.chatting.ChattingRepository;
import com.example.myoceanproject.repository.chatting.ChattingRepositoryImpl;
import com.example.myoceanproject.repository.chatting.ChattingStatusRepository;
import com.example.myoceanproject.repository.chatting.ChattingStatusRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("chatting") @Primary
@RequiredArgsConstructor
public class ChattingService {

    private final ChattingRepository chattingRepository;
    private final ChattingStatusRepository chattingStatusRepository;
    private final ChattingRepositoryImpl chattingRepositoryImple;
    private final ChattingStatusRepositoryImpl chattingStatusRepositoryImple;

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

//    user 정보를 이용해서 해당 유저가 소속된 그룹을 모두 찾아서 리스트로 반환함
    public List<GroupDTO> show(Long userId){
        return chattingRepositoryImple.findByUserId(userId);
    }

    public List<ChattingDTO> showChatting(Long groupId){return chattingRepositoryImple.findChattingByUserId(groupId);}

//    public GroupDTO showGroup(Long groupMemberId){return chattingRepositoryImple.findGroupByGroupMemberId(groupMemberId);}


    public void saveMessage(Long userId, Long groupId, ChattingDTO chattingDTO) {
        chattingRepositoryImple.findGroupMemberIdByUserIdAndGroupId(userId,groupId);
        chattingRepository.save(chattingDTO);
    }
}
