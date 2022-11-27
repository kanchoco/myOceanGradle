package com.example.myoceanproject.repository;


import com.example.myoceanproject.entity.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepository  extends JpaRepository<Chatting, Long> {
}
