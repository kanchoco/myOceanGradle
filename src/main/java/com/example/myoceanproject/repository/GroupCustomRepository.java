package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.GroupDTO;

import java.util.List;

public interface GroupCustomRepository {
    GroupDTO findGroupByGroupId(Long groupId);

    List<GroupDTO> findAll();
}
