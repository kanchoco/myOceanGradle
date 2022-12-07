//package com.example.myoceanproject.service;
//
//import com.example.myoceanproject.domain.AskDTO;
//import com.example.myoceanproject.domain.CommunityPostDTO;
//import com.example.myoceanproject.domain.Criteria;
//import com.example.myoceanproject.repository.MyAskRepository;
//import com.example.myoceanproject.repository.MyAskRepositoryImpl;
//import com.example.myoceanproject.repository.MyPostRepository;
//import com.example.myoceanproject.repository.MyPostRepositoryImpl;
//import com.example.myoceanproject.type.CommunityCategory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//public class AskService {
//
//    @Autowired
//    private MyAskRepositoryImpl myAskRepositoryImpl;
//    @Autowired
//    private MyAskRepository myAskRepository;
//
//    public Page<AskDTO> showCounseling(Pageable pageable, CommunityCategory communityCategory, Criteria criteria, Long userId){
//        return criteria.getKeyword() == null ? myAskRepositoryImpl.findAllByCategory(pageable, communityCategory,userId) : myAskRepositoryImpl.findAllByCategory(pageable, communityCategory,criteria,userId);
//    }
//
//    public Page<AskDTO> showPost(Pageable pageable, Criteria criteria, Long userId){
//        return criteria.getKeyword() == null ? myAskRepositoryImpl.findAll(pageable,userId) : myAskRepositoryImpl.findAll(pageable,criteria,userId);
//    }
//}
