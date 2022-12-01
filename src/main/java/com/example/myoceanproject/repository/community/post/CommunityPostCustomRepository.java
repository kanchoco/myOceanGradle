package com.example.myoceanproject.repository.community.post;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.type.CommunityCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommunityPostCustomRepository {
    public Page<CommunityPostDTO> findAllByCategory(Pageable pageable, CommunityCategory communityCategory);
}
