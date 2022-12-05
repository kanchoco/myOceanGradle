package com.example.myoceanproject.service;

import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.repository.UserRepositoryImpl;
import com.example.myoceanproject.type.UserAccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    public Page<UserDTO> showAllUser(Pageable pageable, Criteria criteria) {
        return criteria.getKeyword() == null ? userRepositoryImpl.findAll(pageable) : userRepositoryImpl.findAll(pageable, criteria);
    }
    public Page<UserDTO> showUserByStatus(Pageable pageable, UserAccountStatus userAccountStatus, Criteria criteria) {
        return criteria.getKeyword() == null ? userRepositoryImpl.findAllByStatus(pageable, userAccountStatus) : userRepositoryImpl.findAllByStatus(pageable, criteria, userAccountStatus);
    }
}
