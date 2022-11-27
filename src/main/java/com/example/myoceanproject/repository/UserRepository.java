package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
