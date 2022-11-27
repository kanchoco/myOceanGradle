package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.UserAccountStatus;
import com.example.myoceanproject.type.UserLoginMethod;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.myoceanproject.entity.QUser.user;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class UserTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void saveUserTest(){
        UserDTO userDTO = new UserDTO();
        
//      유저 값 저장
        userDTO.setUserNickname("준성이");
        userDTO.setUserEmail("junsungh@naver.com");
        userDTO.setUserPassword("1234");
        userDTO.setUserAccountStatus(UserAccountStatus.ACTIVE);
        userDTO.setUserLoginMethod(UserLoginMethod.GOOGLE);
        userDTO.setUserTotalPoint(100);

//      userDTO 저장 값을 entity로 변환
        User user = userDTO.toEntity();

//      user 엔티티에 정보 저장
        userRepository.save(user);
    }


    @Test
    public void findAllTest(){
        List<User> users = jpaQueryFactory.selectFrom(user)
                .fetch();
        users.stream().map(User::toString).forEach(log::info);
    }

    @Test
    public void findById(){
        List<User> users = jpaQueryFactory.selectFrom(user)
                .where(user.userId.eq(1L))
                .fetch();
        users.stream().map(User::toString).forEach(log::info);
    }

    @Test
    public void updateTest(){
        User user1 = jpaQueryFactory.selectFrom(user)
                .where(user.userId.eq(11L))
                .fetchOne();

        user1.update("12345", "준준성", 20, UserAccountStatus.ACTIVE);
    }

    @Test
    public void deleteTest(){
        Long count = jpaQueryFactory
                .delete(user)
                .where(user.userId.eq(11L))
                .execute();
    }
}