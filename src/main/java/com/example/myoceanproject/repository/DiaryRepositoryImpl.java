package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.*;
import com.example.myoceanproject.type.CommunityCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.myoceanproject.entity.QCommunityPost.communityPost;
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
                diary.receiverUser.userId,
                diary.createDate,
                diary.updatedDate,
                diary.localDateTime
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
                diary.receiverUser.userId,
                diary.createDate,
                diary.updatedDate,
                diary.localDateTime
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
                diary.receiverUser.userId,
                diary.createDate,
                diary.updatedDate,
                diary.localDateTime
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
                diary.receiverUser.userId,
                diary.createDate,
                diary.updatedDate,
                diary.localDateTime
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

//        List<DiaryDTO> posts = jpaQueryFactory.select(new QCommunityPostDTO(
//                diary.user.userId,
//                diary.diaryTitle,
//                diary.diaryContent,
//                diary.receiverUser.userId,
//                diary.createDate,
//                diary.updatedDate
//        ))
//                .from(diary)
//                .where(diary.createDate.between())
//                .orderBy(diary.diaryId.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize()).fetch();
//
//        posts.stream().forEach(communityPostDTO -> {
//            communityPostDTO.setCommunityReplyCount(replyRepositoryImpl.countReplyByCommunityPost(communityPostDTO.getCommunityPostId()));
//        });
//        long total = jpaQueryFactory.selectFrom(communityPost)
//                .where(communityPost.communityCategory.eq(communityCategory).and(communityPost.communityTitle.contains(criteria.getKeyword())))
//                .fetch().size();
//
//        return new PageImpl<>(posts, pageable, total);

    @Override
    public Page<DiaryDTO> findAllByDiaryYear(Pageable pageable, String year, Criteria criteria) {
//                List<DiaryDTO> posts = jpaQueryFactory.select(new QDiaryDTO(
//                diary.user.userId,
//                diary.diaryTitle,
//                diary.diaryContent,
//                diary.receiverUser.userId,
//                diary.createDate,
//                diary.updatedDate
//        ))
//                .from(diary)
//                .where(diary.createDate.between())
//                .orderBy(diary.diaryId.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize()).fetch();
//
//        long total = jpaQueryFactory.selectFrom(communityPost)
//                .where(communityPost.communityCategory.eq(communityCategory).and(communityPost.communityTitle.contains(criteria.getKeyword())))
//                .fetch().size();
//
//        return new PageImpl<>(posts, pageable, total);
        return null;
    }

    @Override
    public Page<DiaryDTO> findAllByDiaryByYearMonth(Pageable pageable, String year,String month, Criteria criteria) {
        return null;
    }

    @Override
    public Page<DiaryDTO> findAllByDiaryYearMonthDay(Pageable pageable, String year,String month,String day, Criteria criteria) {
        return null;
    }

}
