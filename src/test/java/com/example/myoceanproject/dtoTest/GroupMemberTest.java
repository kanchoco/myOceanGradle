package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.entity.GroupMember;
import com.example.myoceanproject.entity.QuestAchievement;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.GroupMemberRepository;
import com.example.myoceanproject.repository.GroupRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.GroupLocationType;
import com.example.myoceanproject.type.GroupStatus;
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

import static com.example.myoceanproject.entity.QGroupMember.groupMember;
import static com.example.myoceanproject.entity.QQuestAchievement.questAchievement;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class GroupMemberTest {

    @Autowired
    JPAQueryFactory jpaQueryFactory;
    @Autowired
    GroupMemberRepository groupMemberRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void saveTest(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmail("akjhdgaiafd");
        userDTO.setUserNickname("dlsdud");
        userDTO.setUserPassword("qakjghlig");
        userDTO.setUserLoginMethod(UserLoginMethod.GENERAL);
        userDTO.setUserAccountStatus(UserAccountStatus.ACTIVE);
        userRepository.save(userDTO.toEntity());

//      모임 정보 저장
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupCategory("운동");
        groupDTO.setGroupContent("놀아요");
        groupDTO.setGroupLocation("장소");
        groupDTO.setGroupName("모임 이름");
        groupDTO.setGroupPoint(200);
        groupDTO.setGroupStatus(GroupStatus.WAITING);
        groupDTO.setGroupLocationType(GroupLocationType.ONLINE);
        Group group1 = groupDTO.toEntity();
        group1.changeUser(userRepository.findById(1L).get());
        groupRepository.save(group1);
        GroupMember groupMember = new GroupMember();


        groupMember.changeUser(userRepository.findById(1L).get());
        groupMember.changeGroup(groupRepository.findById(12L).get());

        groupMemberRepository.save(groupMember);
    }

//    @Test
//    public void findAllTest(){
//        List<GroupMember> groupMembers = jpaQueryFactory.selectFrom(groupMember)
//                .join(groupMember.user)
//                .join(groupMember.group)
//                .fetchJoin()
//                .fetch();
//
//        groupMembers.stream().map(GroupMember::toString).forEach(log::info);
//    }
//
//    @Test
//    public void findByIdTest(){
//        List<GroupMember> groupMembers = jpaQueryFactory.selectFrom(groupMember)
//                .join(groupMember.user)
//                .join(groupMember.group)
//                .where(groupMember.user.userId.eq(1L))
//                .fetchJoin()
//                .fetch();
//        groupMembers.stream().map(GroupMember::toString).forEach(log::info);
//    }
//
//    @Test
//    public void deleteTest(){
//        Long count = jpaQueryFactory
//                .delete(groupMember)
//                .where(groupMember.groupMemberId.eq(4L))
//                .execute();
//    }
}
