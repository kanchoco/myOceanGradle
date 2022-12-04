package com.example.myoceanproject.service;

import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.entity.Group;
import org.hibernate.Criteria;

import java.util.List;

public interface GroupBoardService {
    //  게시글 등록
    public void add(GroupDTO groupDTO);

    //  게시글 목록
    public List<GroupDTO> show();

    //  게시글 조회
    public GroupDTO find(Long GroupId);

    //  게시글 수정
    public void update(GroupDTO groupDTO);

    //  게시글 삭제
    public void delete(Long GroupId);
}