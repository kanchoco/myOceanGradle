package com.example.myoceanproject.repository;

import com.example.myoceanproject.entity.CommunityFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityFileRepository extends JpaRepository<CommunityFile, Long> {
    
}
