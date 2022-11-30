package com.example.myoceanproject.repository;

import com.example.myoceanproject.entity.GroupMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.myoceanproject.entity.QGroupMember.groupMember;

@Repository
@RequiredArgsConstructor
public class ChattingRepositoryImpl implements ChattingCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GroupMember> findByGroupId(Long groupId) {
        return queryFactory.select(groupMember)
                .from(groupMember)
                .where(groupMember.group.groupId.eq(groupId))
                .fetch();
    }


}
