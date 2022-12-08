package com.example.myoceanproject.service;

import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.repository.UserRepositoryImpl;
import com.example.myoceanproject.type.UserAccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private UserRepository userRepository;

    public Page<UserDTO> showAllUser(Pageable pageable, Criteria criteria) {
        return criteria.getKeyword().equals("null") ? userRepositoryImpl.findAll(pageable) : userRepositoryImpl.findAll(pageable, criteria);
    }
    public Page<UserDTO> showUserByStatus(Pageable pageable, UserAccountStatus userAccountStatus, Criteria criteria) {
        return criteria.getKeyword().equals("null") ? userRepositoryImpl.findAllByStatus(pageable, userAccountStatus) : userRepositoryImpl.findAllByStatus(pageable, criteria, userAccountStatus);
    }
    public void modify(UserDTO userDTO){
        userRepository.findById(userDTO.getUserId()).get().updateManager(UserAccountStatus.valueOf(userDTO.getUserAccountStatus()));
    }


}
