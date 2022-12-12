package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.PointDTO;
import com.example.myoceanproject.type.PointType;

import java.util.List;

public interface PointCustomRepository {
    public List<PointDTO> findAllPointByUser(Long userId);
    public List<PointDTO> findAllPayPoint(Long userId,PointType pointType);
    public List<PointDTO> findAllRefundPoint(PointType pointType);
}
