package com.example.myoceanproject.repository;

import com.example.myoceanproject.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary,Long> {
    //    public Diary findByUserOrderByDiaryIdDesc(Long userId);
//    public Long countAllByReceiverUser();
    public Diary findByUser_UserId(Long userId);

}
