package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.*;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.type.CommunityCategory;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.myoceanproject.entity.QCommunityPost.communityPost;
import static com.example.myoceanproject.entity.QDiary.diary;

@Repository
@Slf4j
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
                diary.updatedDate
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
                diary.updatedDate
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
                diary.updatedDate
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
                diary.updatedDate
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
    public Page<DiaryDTO> findAllByDiaryDuration(Pageable pageable,List<String> dateData, Long userId) {
        List<DiaryDTO> diaries = jpaQueryFactory.select(new QDiaryDTO(
                diary.user.userId,
                diary.diaryTitle,
                diary.diaryContent,
                diary.receiverUser.userId,
                diary.createDate,
                diary.updatedDate
        ))
                .from(diary)
                .where(dateFilter(dateData).and(diary.user.userId.eq(userId)))
                .orderBy(diary.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();

        long total = jpaQueryFactory.selectFrom(diary)
                .where(diary.user.userId.eq(userId))
                .fetch().size();

        return new PageImpl<>(diaries, pageable, total);
    }

    @Override
    public Page<DiaryDTO> findAllByDiaryDuration(Pageable pageable, List<String> dateData, Long userId, Criteria criteria) {
               List<DiaryDTO> diaries = jpaQueryFactory.select(new QDiaryDTO(
                       diary.user.userId,
                       diary.diaryTitle,
                       diary.diaryContent,
                       diary.receiverUser.userId,
                       diary.createDate,
                       diary.updatedDate
        ))
                .from(diary)
                .where(dateFilter(dateData).and(diary.user.userId.eq(userId)))
                .orderBy(diary.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();
//
        long total = jpaQueryFactory.selectFrom(diary)
                .where(diary.user.userId.eq(userId))
                .fetch().size();
//
        return new PageImpl<>(diaries, pageable, total);
    }

    @Override
    public Page<DiaryDTO> findAllByDiaryByUser(Pageable pageable, Long userId) {
        List<DiaryDTO> diaries = jpaQueryFactory.select(new QDiaryDTO(
                diary.user.userId,
                diary.diaryTitle,
                diary.diaryContent,
                diary.receiverUser.userId,
                diary.createDate,
                diary.updatedDate
        ))
                .from(diary)
                .where(diary.user.userId.eq(userId))
                .orderBy(diary.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();
//
        long total = jpaQueryFactory.selectFrom(diary)
                .where(diary.user.userId.eq(userId))
                .fetch().size();

        log.info("diaries:"+diaries);
        log.info("total:"+total);

        return new PageImpl<>(diaries, pageable, total);
    }

    @Override
    public Page<DiaryDTO> findAllByDiaryByUser(Pageable pageable, Long userId, Criteria criteria) {
        List<DiaryDTO> diaries = jpaQueryFactory.select(new QDiaryDTO(
                diary.user.userId,
                diary.diaryTitle,
                diary.diaryContent,
                diary.receiverUser.userId,
                diary.createDate,
                diary.updatedDate
        ))
                .from(diary)
                .where(diary.user.userId.eq(userId))
                .orderBy(diary.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();
//
        long total = jpaQueryFactory.selectFrom(diary)
                .where(diary.user.userId.eq(userId))
                .fetch().size();
//
        log.info("diaries:"+diaries);
        log.info("total:"+total);
        return new PageImpl<>(diaries, pageable, total);
    }

    private BooleanBuilder dateFilter(List<String> dateData){
        log.info("dateData:"+dateData);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        LocalDateTime localDateTime1=LocalDateTime.of(2022,1,1,0,0,0);
        LocalDateTime localDateTime2=LocalDateTime.of(2022,12, 31,23,59,59);

        for(String dateNumber:dateData){
            if(dateNumber.contains("년")) {
                localDateTime1 = localDateTime1.withYear(Integer.parseInt(dateNumber.substring(0, 4)));
                localDateTime2 = localDateTime2.withYear(Integer.parseInt(dateNumber.substring(0, 4)));
                log.info("dateNumber:"+dateNumber);
                log.info("localDateTime1:"+localDateTime1);
                log.info("localDateTime2:"+localDateTime2);

                booleanBuilder.and(diary.createDate.between(localDateTime1, localDateTime2));
            }else {
                if (dateNumber.contains("월") && dateNumber.length()!=1) {
                    log.info("dateNumber:"+dateNumber);
                    log.info("localDateTime1:"+localDateTime1);
                    log.info("localDateTime2:"+localDateTime2);
                    localDateTime1 = localDateTime1.withMonth(Integer.parseInt(dateNumber.substring(0, 2)));
                    localDateTime2 = localDateTime2.withMonth(Integer.parseInt(dateNumber.substring(0, 2)));
                    booleanBuilder.and(diary.createDate.between(localDateTime1, localDateTime2));
                }
                else{
                    if (dateNumber.contains("일") && dateNumber.length()!=1) {
                        log.info("dateNumber:"+dateNumber);
                        log.info("localDateTime1:"+localDateTime1);
                        log.info("localDateTime2:"+localDateTime2);
                        localDateTime1 = localDateTime1.withDayOfMonth(Integer.parseInt(dateNumber.substring(0, 2)));
                        localDateTime2 = localDateTime2.withDayOfMonth(Integer.parseInt(dateNumber.substring(0, 2)));
                        booleanBuilder.and(diary.createDate.between(localDateTime1, localDateTime2));
                    }else{break;}
                }
            }
        }

        return booleanBuilder;
    }

}
