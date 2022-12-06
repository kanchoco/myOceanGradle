package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.myoceanproject.entity.QDiary.diary;

@Repository
public class DiaryRepositoryImpl implements DiaryCustomRepository{

    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<DiaryDTO> findAllByUserId(Pageable pageable, Long userId) {
        List<DiaryDTO> posts = jpaQueryFactory.select(new QDiaryDTO(
                diary.user.userId,
                diary.diaryTitle,
                diary.diaryContent,
                diary.receiverUser.userId
//                ,
//                diary.createDate,
//                diary.updatedDate
        ))
                .from(diary)
                .where(diary.user.userId.eq(userId).and(diary.receiverUser.userId.isNull()))
                .orderBy(diary.diaryId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();


        long total = jpaQueryFactory.selectFrom(diary)
                .where(diary.user.userId.eq(userId))
                .fetch().size();

        return new PageImpl<>(posts, pageable, total);
    }

    @Override
    public Page<DiaryDTO> findAllByUserId(Pageable pageable, Long userId, Criteria criteria) {
        List<DiaryDTO> diarys = jpaQueryFactory.select(new QDiaryDTO(
                diary.user.userId,
                diary.diaryTitle,
                diary.diaryContent,
                diary.receiverUser.userId
//                ,
//                diary.createDate,
//                diary.updatedDate
        ))
                .from(diary)
                .where(diary.user.userId.eq(userId).and(diary.receiverUser.userId.isNull()))
                .orderBy(diary.diaryId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();


        long total = jpaQueryFactory.selectFrom(diary)
                .where(diary.user.userId.eq(userId))
                .fetch().size();

        return new PageImpl<>(diarys, pageable, total);
    }

    @Override
    public Page<DiaryDTO> findAllByUserIdExchange(Pageable pageable, Long userId) {
        List<DiaryDTO> posts = jpaQueryFactory.select(new QDiaryDTO(
                diary.user.userId,
                diary.diaryTitle,
                diary.diaryContent,
                diary.receiverUser.userId
//                ,
//                diary.createDate,
//                diary.updatedDate
        ))
                .from(diary)
                .where(diary.user.userId.eq(userId).and(diary.receiverUser.userId.isNotNull()))
                .orderBy(diary.diaryId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();


        long total = jpaQueryFactory.selectFrom(diary)
                .where(diary.user.userId.eq(userId))
                .fetch().size();

        return new PageImpl<>(posts, pageable, total);
    }

    @Override
    public Page<DiaryDTO> findAllByUserIdExchange(Pageable pageable, Long userId, Criteria criteria) {
        List<DiaryDTO> diarys = jpaQueryFactory.select(new QDiaryDTO(
                diary.user.userId,
                diary.diaryTitle,
                diary.diaryContent,
                diary.receiverUser.userId
//                ,
//                diary.createDate,
//                diary.updatedDate
        ))
                .from(diary)
                .where(diary.user.userId.eq(userId).and(diary.receiverUser.userId.isNotNull()))
                .orderBy(diary.diaryId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();


        long total = jpaQueryFactory.selectFrom(diary)
                .where(diary.user.userId.eq(userId))
                .fetch().size();

        return new PageImpl<>(diarys, pageable, total);
    }
}
