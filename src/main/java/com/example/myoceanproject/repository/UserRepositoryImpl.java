package com.example.myoceanproject.repository;

import com.example.myoceanproject.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserCustomRepository{
    private final JPAQueryFactory queryFactory;


    @Override
    public int findCountByemail(String email) {
//        @Query("select count(u.userEmail) from User u where ")
        return 0;
    }
}
