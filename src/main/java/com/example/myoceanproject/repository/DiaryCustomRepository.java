package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.DiaryDTO;
import com.example.myoceanproject.type.CommunityCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface DiaryCustomRepository {
    public Page<DiaryDTO> findAllByUserId(Pageable pageable, Long userId);
    public Page<DiaryDTO> findAllByUserId(Pageable pageable, Long userId, Criteria criteria);
    public Page<DiaryDTO> findAllByUserIdExchange(Pageable pageable, Long userId);
    public Page<DiaryDTO> findAllByUserIdExchange(Pageable pageable, Long userId, Criteria criteria);
    public Page<DiaryDTO> findAllByDiaryDuration(Pageable pageable, List<String> dateData, Long userId);
    public Page<DiaryDTO> findAllByDiaryDuration(Pageable pageable, List<String> dateData, Long userId,Criteria criteria);
    public Page<DiaryDTO> findAllByDiaryByUser(Pageable pageable, Long userId);
    public Page<DiaryDTO> findAllByDiaryByUser(Pageable pageable, Long userId,Criteria criteria);

}
