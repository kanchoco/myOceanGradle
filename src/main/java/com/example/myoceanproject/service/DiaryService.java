package com.example.myoceanproject.service;

import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.DiaryDTO;
import com.example.myoceanproject.repository.DiaryRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepositoryImpl diaryRepositoryImpl;

    public Page<DiaryDTO> showDiary(Pageable pageable, Long userId, Criteria criteria){
        return criteria.getKeyword() == null ? diaryRepositoryImpl.findAllByUserId(pageable, userId) : diaryRepositoryImpl.findAllByUserId(pageable, userId,criteria);
    }
    public Page<DiaryDTO> showExchangeDiary(Pageable pageable, Long userId, Criteria criteria){
        return criteria.getKeyword() == null ? diaryRepositoryImpl.findAllByUserIdExchange(pageable, userId) : diaryRepositoryImpl.findAllByUserIdExchange(pageable, userId,criteria);
    }
}
