package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.DiaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiaryCustomRepository {
    public Page<DiaryDTO> findAllByUserId(Pageable pageable, Long userId);
    public Page<DiaryDTO> findAllByUserId(Pageable pageable, Long userId, Criteria criteria);
    public Page<DiaryDTO> findAllByUserIdExchange(Pageable pageable, Long userId);
    public Page<DiaryDTO> findAllByUserIdExchange(Pageable pageable, Long userId, Criteria criteria);
}
