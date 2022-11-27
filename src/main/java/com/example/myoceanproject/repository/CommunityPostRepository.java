package com.example.myoceanproject.repository;

import com.example.myoceanproject.entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {
    
}
