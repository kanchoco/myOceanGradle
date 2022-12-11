package com.example.myoceanproject.service;

import com.example.myoceanproject.domain.PointDTO;
import com.example.myoceanproject.repository.PointRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepositoryImpl pointRepositoryImpl;

    public List<PointDTO> findAllPointByUser(Long userId){return pointRepositoryImpl.findAllPointByUser(userId);}
}
