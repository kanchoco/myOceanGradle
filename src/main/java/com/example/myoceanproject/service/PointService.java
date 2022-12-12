package com.example.myoceanproject.service;

import com.example.myoceanproject.domain.PointDTO;
import com.example.myoceanproject.repository.PointRepositoryImpl;
import com.example.myoceanproject.type.PointType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepositoryImpl pointRepositoryImpl;

    public List<PointDTO> findAllPointByUser(Long userId){return pointRepositoryImpl.findAllPointByUser(userId);}
    public List<PointDTO> findAllPayPointByUser(Long userId,PointType pointType){return pointRepositoryImpl.findAllPayPoint(userId,pointType);}
    public List<PointDTO> findAllRefundPointByAllUser(PointType pointType){return pointRepositoryImpl.findAllRefundPoint(pointType);}
}
