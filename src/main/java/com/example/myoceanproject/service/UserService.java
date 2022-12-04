package com.example.myoceanproject.service;

import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.repository.UserRepositoryImpl;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    public Page<UserDTO> showUser(Pageable pageable, Criteria criteria) {
        return criteria.getKeyword() == null ? userRepositoryImpl.findAll(pageable) : userRepositoryImpl.findAll(pageable, criteria);
    }
}
