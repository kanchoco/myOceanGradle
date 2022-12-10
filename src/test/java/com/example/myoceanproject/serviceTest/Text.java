package com.example.myoceanproject.serviceTest;

import com.example.myoceanproject.entity.QUser;
import com.example.myoceanproject.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class Text {
    @Autowired
    JPAQueryFactory j;

    @Test
    public void test1(){
        System.out.println("안녕 : "+j.selectFrom(QUser.user).where(QUser.user.userOauthId.eq("94vyCZE-yebR1W7a5tBAUDm5a5aPkaIqn_6znsAxr3Y")).fetchOne().getUserOauthId());
    }

}
