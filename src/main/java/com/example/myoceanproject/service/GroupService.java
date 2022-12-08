package com.example.myoceanproject.service;

import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.repository.GroupRepository;
import com.example.myoceanproject.repository.GroupRepositoryImpl;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.GroupStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Qualifier("group") @Primary
@RequiredArgsConstructor
public class GroupService implements GroupBoardService {
    private final GroupRepository groupRepository;
    private final GroupRepositoryImpl groupRepositoryImpl;

    @Autowired
    private UserRepository userRepository;

//  게시글 등록
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(GroupDTO groupDTO){
        Group group = groupDTO.toEntity();
        group.setUser(userRepository.findById(groupDTO.getUserId()).get());
        groupRepository.save(group);
    }

//  게시글 목록 보기
    @Override
    public List<GroupDTO> show() {
        return groupRepositoryImpl.findAll();
    }

    @Override
    public GroupDTO find(Long groupId) {
        GroupDTO groupDTO = groupRepositoryImpl.findGroupByGroupId(groupId);
        return groupDTO;
    }

    @Override
    public List<GroupDTO> findTop5BygroupId(Long groupId){
        return groupRepositoryImpl.findGroupTop5ByGroupId(groupId);
    }

    @Override
    public void update(GroupDTO groupDTO) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    public Page<GroupDTO> findAllManage(Pageable pageable, Criteria criteria){
        return criteria.getKeyword().equals("null") ? groupRepositoryImpl.findAllManage(pageable) : groupRepositoryImpl.findAllManage(pageable,criteria);
    }
    public Page<GroupDTO> findAllByStatus(Pageable pageable, GroupStatus groupStatus, Criteria criteria){
        return criteria.getKeyword().equals("null") ? groupRepositoryImpl.findAllByStatus(pageable, groupStatus) : groupRepositoryImpl.findAllByStatus(pageable,groupStatus, criteria);
    }
}
