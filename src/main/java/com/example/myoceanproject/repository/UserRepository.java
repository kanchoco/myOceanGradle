package com.example.myoceanproject.repository;

import com.example.myoceanproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
