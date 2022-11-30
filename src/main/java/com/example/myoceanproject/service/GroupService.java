package com.example.myoceanproject.service;

import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Qualifier("group") @Primary
@RequiredArgsConstructor
public class GroupService implements GroupBoardService {
    private final GroupRepository groupRepository;


//  게시글 등록
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Group group){
        groupRepository.save(group);
    }

//  게시글 목록 보기
    @Override
    public List<Group> show() {
        return groupRepository.findAll();
    }

    @Override
    public Group find(Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        return group;
    }

    @Override
    public void update(Group group) {

    }

    @Override
    public void delete(Long GroupId) {

    }
}
