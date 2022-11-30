package com.example.myoceanproject.service;

import com.example.myoceanproject.entity.Group;
import org.hibernate.Criteria;

import java.util.List;

public interface GroupBoardService {
    //  게시글 등록
    public void add(Group group);

    //  게시글 목록
    public List<Group> show();

    //  게시글 조회
    public Group find(Long GroupId);

    //  게시글 수정
    public void update(Group group);

    //  게시글 삭제
    public void delete(Long GroupId);
}