package com.example.myoceanproject.service.community;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.repository.community.post.CommunityPostRepositoryImpl;
import com.example.myoceanproject.type.CommunityCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CommunityPostService {

    @Autowired
    private CommunityPostRepositoryImpl postRepositoryImpl;

    public Page<CommunityPostDTO> showPost(Pageable pageable, CommunityCategory communityCategory, Criteria criteria){
        return criteria.getKeyword() == null ? postRepositoryImpl.findAllByCategory(pageable, communityCategory) : postRepositoryImpl.findAllByCategory(pageable, communityCategory,criteria);
    }

}
