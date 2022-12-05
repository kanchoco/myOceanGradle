package com.example.myoceanproject.repository.chatting;


import com.example.myoceanproject.domain.ChattingDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepository  extends JpaRepository<ChattingDTO, Long> {
}
