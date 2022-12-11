package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.AlarmDTO;
import com.example.myoceanproject.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    
}
