package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.domain.QGroupDTO;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.embeddable.GroupTime;
import com.example.myoceanproject.entity.*;
import com.example.myoceanproject.repository.GroupRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.myoceanproject.entity.QAsk.ask;
import static com.example.myoceanproject.entity.QGroup.group;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class GroupTest {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void saveGroupTest(){
//      유저 db에 저장
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
        groupDTO.setGroupFileSize(10L);
        groupDTO.setGroupFileUuid("Uuid");
        groupDTO.setGroupFilePath("filePath");
        groupDTO.setGroupFileName("groupName");

//        임베드 타입 set해줌
        groupDTO.setEndTime(LocalDateTime.now());
        groupDTO.setStartTime(LocalDateTime.now());
        groupDTO.setMaxMember(10);
        groupDTO.setMinMember(2);
//        DTO를 엔티티로 바꿔서 저장해줌
        Group group1 = groupDTO.toEntity();
        group1.setUser(userRepository.findById(1L).get());
        groupRepository.save(group1);

    }



    @Test
    public void findAllTest(){
        List<GroupDTO> groups = jpaQueryFactory.select(new QGroupDTO(
                group.user.userId,
                group.user.userNickname,
                group.groupName,
                group.groupCategory,
                group.groupContent,
                group.groupPoint,
                group.groupLocation,
                group.groupLocationType,
                group.groupStatus,
                group.groupFilePath,
                group.groupFileName,
                group.groupFileUuid,
                group.groupFileSize,
                group.groupMemberLimit.maxMember,
                group.groupMemberLimit.minMember,
                group.groupTime.startTime,
                group.groupTime.endTime)).from(group).fetch();
        log.info("------------------------------------------------------------");
        groups.stream().map(GroupDTO::toString).forEach(log::info);
        log.info("------------------------------------------------------------");
    }

//    @Test
//    public void findById(){
//        List<Group> groups = jpaQueryFactory.selectFrom(group)
//                .join(group.user)
//                .where(group.user.userId.eq(1L))
//                .fetchJoin()
//                .fetch();
//
//        groups.stream().map(Group::toString).forEach(log::info);
//
//    }
//
//    @Test
//    public void updateTest(){
//
//        Group group1 = jpaQueryFactory.selectFrom(group)
//                .where(group.groupId.eq(2L))
//                .fetchOne();
//        group1.update("수정이름","카테고리수정", "내용수정",500,"장소수정", GroupLocationType.OFFLINE, GroupStatus.APPROVED, group1.getGroupMemberLimit(), group1.getGroupTime());
//    }
//
//    @Test
//    public void deleteTest(){
//        Long count = jpaQueryFactory
//                .delete(group)
//                .where(group.groupId.eq(1L))
//                .execute();
//    }

}
