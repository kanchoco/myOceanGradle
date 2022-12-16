package com.example.myoceanproject.service;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.DiaryDTO;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.DiaryRepositoryImpl;
import com.example.myoceanproject.type.CommunityCategory;
import com.example.myoceanproject.type.DiaryCategory;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public Page<DiaryDTO> showSelectDiarys(Pageable pageable, List<String> dateData, Long userId,Criteria criteria) {
        return criteria.getKeyword().equals("null") ? diaryRepositoryImpl.findAllByDiaryDuration(pageable,dateData,userId) : diaryRepositoryImpl.findAllByDiaryDuration(pageable,dateData,userId,criteria);
    }
    //  게시글 등록
    public Page<DiaryDTO> showFirstDiarys(Pageable pageable,Long userId,Criteria criteria) {
        return criteria.getKeyword().equals("null") ? diaryRepositoryImpl.findAllByDiaryByUser(pageable,userId) : diaryRepositoryImpl.findAllByDiaryByUser(pageable,userId,criteria);
    }
    //  게시글 등록
    public int registerShareDiary(Long userId,DiaryCategory diaryCategory) {
        return diaryRepositoryImpl.registerReceiverByUser(userId,diaryCategory);
    }

    public DiaryDTO findNullReceiver(DiaryCategory diaryCategory){
        return diaryRepositoryImpl.findBeforeShareWriter(diaryCategory);
    }

    public UserDTO findReceiverNullUser(Long userId){
        return diaryRepositoryImpl.findByUserId(userId);
    }

    public int checkSameUser(Long userId){
        return diaryRepositoryImpl.checkSameUser(userId);
    }
    public int searchMyDiaryCount(Long userId,DiaryCategory diaryCategory){
        return diaryRepositoryImpl.checkSameUser(userId);
    }
}
