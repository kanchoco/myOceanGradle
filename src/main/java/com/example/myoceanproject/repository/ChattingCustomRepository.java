package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.entity.GroupMember;
import com.querydsl.core.Tuple;

import java.util.List;

public interface ChattingCustomRepository {

    List<GroupMember> findByGroupId(Long groupId);

    List<GroupDTO> findByUserId(Long userId);
}
