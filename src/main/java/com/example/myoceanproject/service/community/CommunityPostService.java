package com.example.myoceanproject.service.community;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.repository.community.post.CommunityPostRepository;
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
    @Autowired
    private CommunityPostRepository postRepository;

    public Page<CommunityPostDTO> showCounseling(Pageable pageable, CommunityCategory communityCategory, Criteria criteria){
        return criteria.getKeyword() == null ? postRepositoryImpl.findAllByCategory(pageable, communityCategory) : postRepositoryImpl.findAllByCategory(pageable, communityCategory,criteria);
    }

    public Page<CommunityPostDTO> showPost(Pageable pageable, Criteria criteria){
        return criteria.getKeyword().equals("null") ? postRepositoryImpl.findAll(pageable) : postRepositoryImpl.findAll(pageable,criteria);
    }
    public void remove(Long communityPostId){
        CommunityPost post = postRepository.findById(communityPostId).get();
        postRepositoryImpl.deleteByPost(post);
        postRepository.delete(post);
    }

}
