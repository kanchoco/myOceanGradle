//package com.example.myoceanproject.repository;
//
//import com.example.myoceanproject.domain.AskDTO;
//import com.example.myoceanproject.domain.Criteria;
//import com.example.myoceanproject.type.CommunityCategory;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//public interface MyAskCustomRepository {
//    public Page<AskDTO> findAll(Pageable pageable, Long userId);
//    public Page<AskDTO> findAll(Pageable pageable, Criteria criteria, Long userId);
//    public Page<AskDTO> findAllByCategory(Pageable pageable, CommunityCategory communityCategory, Long userId);
//    public Page<AskDTO> findAllByCategory(Pageable pageable, CommunityCategory communityCategory, Criteria criteria,Long userId);
//}
