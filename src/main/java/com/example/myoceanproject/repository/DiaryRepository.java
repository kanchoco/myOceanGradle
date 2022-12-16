package com.example.myoceanproject.repository;

import com.example.myoceanproject.entity.Diary;
import com.example.myoceanproject.type.DiaryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary,Long> {
    //    public Diary findByUserOrderByDiaryIdDesc(Long userId);
//    public Long countAllByReceiverUser();
    public Optional<Diary> findByUser_UserIdAndDiaryCategoryAndReceiverUserIsNull(Long userId, DiaryCategory diaryCategory);

}
