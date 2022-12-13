package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.DiaryDTO;
import com.example.myoceanproject.type.CommunityCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiaryCustomRepository {
    public Page<DiaryDTO> findAllByUserId(Pageable pageable, Long userId);
    public Page<DiaryDTO> findAllByUserId(Pageable pageable, Long userId, Criteria criteria);
    public Page<DiaryDTO> findAllByUserIdExchange(Pageable pageable, Long userId);
    public Page<DiaryDTO> findAllByUserIdExchange(Pageable pageable, Long userId, Criteria criteria);
    public Page<DiaryDTO> findAllByDiaryYear(Pageable pageable, String year, Criteria criteria);
    public Page<DiaryDTO> findAllByDiaryByYearMonth(Pageable pageable, String year,String month, Criteria criteria);
    public Page<DiaryDTO> findAllByDiaryYearMonthDay(Pageable pageable, String year,String month,String day, Criteria criteria);

}
