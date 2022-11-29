package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.GroupDTO;
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
//        시나리오 : 모임 작성자의 아이디를 세션에서 받아온다.(=>맞는지 확인 필요), 그 후 사용자가 화면에 입력한 그룹에 대한 정보를 groupDTO에 set해준다.
//        유저가 db에 없을 경우를 대비해서 유저 먼저 db에 저장해줬다. db에 유저가 있다면 findById를 사용하여 찾아오기만 하면 된다.

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
//        임베드 타입 set해줌
        groupDTO.setEndTime(LocalDateTime.now());
        groupDTO.setStartTime(LocalDateTime.now());
        groupDTO.setMaxMember(10);
        groupDTO.setMinMember(2);
//        DTO를 엔티티로 바꿔서 저장해줌
        Group group1 = groupDTO.toEntity();
        group1.changeUser(userRepository.findById(1L).get());
        groupRepository.save(group1);

    }



//    @Test
//    public void findAllTest(){
//        List<Group> groups = jpaQueryFactory.selectFrom(new QGroup(group))
//                .join(group.user)
//                .fetchJoin()
//                .fetch();
//        groups.stream().map(Group::toString).forEach(log::info);
//    }
//
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
