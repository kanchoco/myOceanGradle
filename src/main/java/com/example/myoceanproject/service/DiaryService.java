package com.example.myoceanproject.service;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.DiaryDTO;
import com.example.myoceanproject.repository.DiaryRepositoryImpl;
import com.example.myoceanproject.type.CommunityCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //  게시글 등록
    public Page<DiaryDTO> showYear(Pageable pageable, String year, Criteria criteria) {
        return criteria.getKeyword().equals("null") ? diaryRepositoryImpl.findAllByDiaryYear(pageable, year,criteria) : diaryRepositoryImpl.findAllByDiaryYear(pageable, year,criteria);
    }
    //  게시글 등록
    public Page<DiaryDTO> showYearMonth(Pageable pageable, String year,String month, Criteria criteria) {
        return criteria.getKeyword().equals("null") ? diaryRepositoryImpl.findAllByDiaryByYearMonth(pageable, year,month,criteria) : diaryRepositoryImpl.findAllByDiaryByYearMonth(pageable, year,month,criteria);
    }
    //  게시글 등록
    public Page<DiaryDTO> showYearMonthDay(Pageable pageable, String year,String month,String day, Criteria criteria) {
        return criteria.getKeyword().equals("null") ? diaryRepositoryImpl.findAllByDiaryYearMonthDay(pageable, year,month,day,criteria) : diaryRepositoryImpl.findAllByDiaryYearMonthDay(pageable, year,month,day,criteria);
    }
}
