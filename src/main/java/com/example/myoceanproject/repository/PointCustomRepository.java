package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.PointDTO;

import java.util.List;

public interface PointCustomRepository {
    public List<PointDTO> findAllPointByUser(Long userId);
}
