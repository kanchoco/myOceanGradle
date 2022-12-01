package com.example.myoceanproject.repository.community;

import com.example.myoceanproject.domain.CommunityFileDTO;
import com.example.myoceanproject.entity.CommunityFile;
import com.example.myoceanproject.entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommunityFileRepository extends JpaRepository<CommunityFile, Long> {
}
