package com.example.myoceanproject.repository;

import com.example.myoceanproject.entity.GroupMember;

import java.util.List;

public interface ChattingCustomRepository {

    List<GroupMember> findByGroupId(Long groupId);
}
