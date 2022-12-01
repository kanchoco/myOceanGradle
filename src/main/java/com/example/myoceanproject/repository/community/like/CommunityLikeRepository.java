package com.example.myoceanproject.repository.community;

import com.example.myoceanproject.entity.CommunityLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Long> {
    
}
