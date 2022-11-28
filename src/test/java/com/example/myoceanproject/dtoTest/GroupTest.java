package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.embeddable.GroupTime;
import com.example.myoceanproject.entity.Ask;
import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.entity.QGroup;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.GroupRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.AskCategory;
import com.example.myoceanproject.type.AskStatus;
import com.example.myoceanproject.type.GroupLocationType;
import com.example.myoceanproject.type.GroupStatus;
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
//      1번 유저 불러오기
        Optional<User> user = userRepository.findById(1L);
        GroupDTO groupDTO = new GroupDTO();

//      모임 정보 저장
        groupDTO.setGroupCategory("운동");
        groupDTO.setGroupContent("놀아요");
        groupDTO.setGroupLocation("장소");
        groupDTO.setGroupName("모임 이름");
        groupDTO.setGroupPoint(200);
        groupDTO.setGroupStatus(GroupStatus.WAITING);
        groupDTO.setGroupLocationType(GroupLocationType.ONLINE);
//        groupDTO.setGroupLimit


//      entity 변환
        Group group1 = groupDTO.toEntity();

//      유저 정보 저장
        groupDTO.setUser(user.get());
        group1.changeUser(groupDTO.getUser());

//      group 엔티티에 정보 저장
        groupRepository.save(group1);

    }



    @Test
    public void findAllTest(){
        List<Group> groups = jpaQueryFactory.selectFrom(new QGroup(group))
                .join(group.user)
                .fetchJoin()
                .fetch();
        groups.stream().map(Group::toString).forEach(log::info);
    }

    @Test
    public void findById(){
        List<Group> groups = jpaQueryFactory.selectFrom(group)
                .join(group.user)
                .where(group.user.userId.eq(1L))
                .fetchJoin()
                .fetch();

        groups.stream().map(Group::toString).forEach(log::info);

    }

    @Test
    public void updateTest(){

        Group group1 = jpaQueryFactory.selectFrom(group)
                .where(group.groupId.eq(2L))
                .fetchOne();
        group1.update("수정이름","카테고리수정", "내용수정",500,"장소수정", GroupLocationType.OFFLINE, GroupStatus.APPROVED, group1.getGroupMemberLimit(), group1.getGroupTime());
    }

    @Test
    public void deleteTest(){
        Long count = jpaQueryFactory
                .delete(group)
                .where(group.groupId.eq(1L))
                .execute();
    }

}
