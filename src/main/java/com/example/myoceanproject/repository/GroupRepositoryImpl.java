package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.domain.QGroupDTO;
import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.example.myoceanproject.entity.QGroup.group;

@Repository
@RequiredArgsConstructor
public class GroupRepositoryImpl implements GroupCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GroupDTO> findAll(){
        return queryFactory.select(new QGroupDTO(
                group.groupId,
                group.user.userId,
                group.user.userNickname,
                group.groupName,
                group.groupCategory,
                group.groupContent,
                group.groupPoint,
                group.groupOverSea,
                group.groupLocationName,
                group.groupLocation,
                group.groupLocationDetail,
                group.groupParkingAvailable,
                group.groupMoreInformation,
                group.groupLocationType,
                group.groupStatus,
                group.groupFilePath,
                group.groupFileName,
                group.groupFileUuid,
                group.groupFileSize,
                group.groupMemberLimit.maxMember,
                group.groupMemberLimit.minMember
        )).from(group).orderBy(group.groupId.desc()).fetch();
    }

    @Override
    public List<GroupDTO> findGroupTop5ByGroupId(Long groupId) {
        return queryFactory.select(new QGroupDTO(
                group.groupId,
                group.user.userId,
                group.user.userNickname,
                group.groupName,
                group.groupCategory,
                group.groupContent,
                group.groupPoint,
                group.groupOverSea,
                group.groupLocationName,
                group.groupLocation,
                group.groupLocationDetail,
                group.groupParkingAvailable,
                group.groupMoreInformation,
                group.groupLocationType,
                group.groupStatus,
                group.groupFilePath,
                group.groupFileName,
                group.groupFileUuid,
                group.groupFileSize,
                group.groupMemberLimit.maxMember,
                group.groupMemberLimit.minMember
        )).from(group).orderBy(group.groupId.desc()).limit(5L).fetch();
    }

    @Override
    public GroupDTO findGroupByGroupId(Long groupId) {
        return queryFactory.select(new QGroupDTO(
                group.groupId,
                group.user.userId,
                group.user.userNickname,
                group.groupName,
                group.groupCategory,
                group.groupContent,
                group.groupPoint,
                group.groupOverSea,
                group.groupLocationName,
                group.groupLocation,
                group.groupLocationDetail,
                group.groupParkingAvailable,
                group.groupMoreInformation,
                group.groupLocationType,
                group.groupStatus,
                group.groupFilePath,
                group.groupFileName,
                group.groupFileUuid,
                group.groupFileSize,
                group.groupMemberLimit.maxMember,
                group.groupMemberLimit.minMember
        )).from(group).where(group.groupId.eq(groupId)).fetchOne();
    }
}
