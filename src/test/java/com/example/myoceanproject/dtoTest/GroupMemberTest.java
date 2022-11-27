package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.entity.GroupMember;
import com.example.myoceanproject.entity.QuestAchievement;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.GroupMemberRepository;
import com.example.myoceanproject.repository.GroupRepository;
import com.example.myoceanproject.repository.UserRepository;
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
        Optional<User> user = userRepository.findById(1L);
        Optional<Group> group = groupRepository.findById(1L);
        GroupMember groupMember = new GroupMember();

        groupMember.changeUser(user.get());
        groupMember.changeGroup(group.get());

        groupMemberRepository.save(groupMember);
    }

    @Test
    public void findAllTest(){
        List<GroupMember> groupMembers = jpaQueryFactory.selectFrom(groupMember)
                .join(groupMember.user)
                .join(groupMember.group)
                .fetchJoin()
                .fetch();

        groupMembers.stream().map(GroupMember::toString).forEach(log::info);
    }

    @Test
    public void findByIdTest(){
        List<GroupMember> groupMembers = jpaQueryFactory.selectFrom(groupMember)
                .join(groupMember.user)
                .join(groupMember.group)
                .where(groupMember.user.userId.eq(1L))
                .fetchJoin()
                .fetch();
        groupMembers.stream().map(GroupMember::toString).forEach(log::info);
    }

    @Test
    public void deleteTest(){
        Long count = jpaQueryFactory
                .delete(groupMember)
                .where(groupMember.groupMemberId.eq(4L))
                .execute();
    }
}
