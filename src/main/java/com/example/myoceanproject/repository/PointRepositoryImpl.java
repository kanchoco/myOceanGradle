package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.PointDTO;
import com.example.myoceanproject.domain.QPointDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.example.myoceanproject.entity.QPoint.point;

@Repository
public class PointRepositoryImpl implements PointCustomRepository{

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PointDTO> findAllPointByUser(Long userId) {
        List<PointDTO> pointDTOS=jpaQueryFactory.select(new QPointDTO(
                point.pointId,
                point.groupName,
                point.pointAmountHistory,
                point.createDate,
                point.pointType,
                point.pointMerchantUid,
                point.pointImpUid,
                point.pointContent
        )).from(point).where(point.user.userId.eq(userId)).orderBy(point.pointId.desc()).fetch();
        return pointDTOS;
    }

}
