package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.entity.Group;

import java.util.List;

public interface GroupCustomRepository {
    GroupDTO findGroupByGroupId(Long groupId);

    List<GroupDTO> findAll();

    List<GroupDTO> findGroupTop5ByGroupId(Long groupId);


}
