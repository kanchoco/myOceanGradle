package com.example.myoceanproject.repository.community.post;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.type.CommunityCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommunityPostCustomRepository {
    public Page<CommunityPostDTO> findAll(Pageable pageable);
    public Page<CommunityPostDTO> findAll(Pageable pageable, Criteria criteria);
    public Page<CommunityPostDTO> findAllByCategory(Pageable pageable, CommunityCategory communityCategory);
    public Page<CommunityPostDTO> findAllByCategory(Pageable pageable, CommunityCategory communityCategory, Criteria criteria);
    public void deleteByPost(CommunityPost post);

    public Integer countPostByUser(Long userId);

    public List<CommunityPostDTO> findAllByList(Long userId);

    public CommunityPostDTO findAllByDashboard();
}
