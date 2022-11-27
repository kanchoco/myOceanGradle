package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.AskDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.Ask;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.AskRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.AskCategory;
import com.example.myoceanproject.type.AskStatus;
import com.example.myoceanproject.type.ReadStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.myoceanproject.entity.QAsk.ask;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class AskTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private AskRepository askRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveAskTest(){
        
//      1번 유저 불러오기
        Optional<User> user = userRepository.findById(1L);
        AskDTO askDTO = new AskDTO();
        
//      askDTO에 필요한 값 저장
        askDTO.setAskCategory(AskCategory.ACCOUNTINFO);
        askDTO.setAskStatus(AskStatus.COMPLETE);
        askDTO.setAskTitle("제목");
        askDTO.setAskContent("내용");
        
//      askDTO에 저장한 값을 entity로 변환
        Ask ask1 = askDTO.toEntity();

//      askDTO에 처음 조회했던 유저 정보 저장
//      changeUser 메소드로 askDTO에 저장된 User값을 ask1로 전달
        askDTO.setUser(user.get());
        ask1.changeUser(askDTO.getUser());

//      ask 엔티티에 해당 값 모두 저장
        askRepository.save(ask1);
    }

    @Test
    public void findAllTest(){
        List<Ask> asks = jpaQueryFactory.selectFrom(ask)
                .join(ask.user)
                .fetchJoin()
                .fetch();
        asks.stream().map(Ask::toString).forEach(log::info);
    }

    @Test
    public void findById(){
        List<Ask> asks = jpaQueryFactory.selectFrom(ask)
                .join(ask.user)
                .where(ask.user.userId.eq(1L))
                .fetchJoin()
                .fetch();

        asks.stream().map(Ask::toString).forEach(log::info);

    }

    @Test
    public void updateTest(){

        Ask ask1 = jpaQueryFactory.selectFrom(ask)
                .where(ask.askId.eq(16L))
                .fetchOne();

        ask1.update(AskStatus.WAITING, AskCategory.QUESTINFO);
    }

    @Test
    public void deleteTest(){
        Long count = jpaQueryFactory
                .delete(ask)
                .where(ask.askId.eq(15L))
                .execute();
    }
}
