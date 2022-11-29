package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.AskDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.Ask;
import com.example.myoceanproject.entity.QAsk;
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
//      시나리오: 해당 유저(3L)가 문의하기 내용을 남긴다.


//      userRepository 인터페이스 구현체 hibernate의 findById메서드를 이용해서
//      다른 유저를 검색한다.
        Optional<User> user = userRepository.findById(2L);

//      화면에서 문의하기 작성내용을 입력받기 위해 AskDTO 객체 생성
        AskDTO askDTO = new AskDTO();

//      화면에서 문의하기 작성 내용을 입력받는다.
        askDTO.setAskCategory(AskCategory.QUESTINFO);
        askDTO.setAskStatus(AskStatus.WAITING);
        askDTO.setAskTitle("퀘스트 문의하기 제목");
        askDTO.setAskContent("퀘스트 문의하기 내용");

//      추가 저장을 위해 toentity메서드를 통해 Ask객체에 저장
        Ask ask1 = askDTO.toEntity();

//      userRepository 인터페이스 구현체 hibernate의 findbyid메서드로 유저를 검색후 추가
        ask1.changeUser(user.get());

//      문의하기 테이블에 해당 내용 저장
        askRepository.save(ask1);
    }

    @Test
    public void findAllTest(){
//      모든 문의사항을 검색한다.
        List<Ask> asks = jpaQueryFactory.selectFrom(ask)
                .join(ask.user)
                .fetchJoin()
                .fetch();
        asks.stream().map(Ask::toString).forEach(log::info);
    }

    @Test
    public void findAllById(){
//      사용자의 문의사항 전부를 가져온다.
        List<Ask> asks = jpaQueryFactory.selectFrom(ask)
                .join(ask.user)
                .where(ask.user.userId.eq(2L))
                .fetchJoin()
                .fetch();

        asks.stream().map(Ask::toString).forEach(log::info);

    }
//
//    @Test
//    public void updateTest(){
////        AskCategory askCategory=askRepository.findcategoryByuserId(2L,AskCategory.QUESTINFO);
//        Ask asks = jpaQueryFactory.selectFrom(ask)
//                .where(ask.user.userId.eq(2L))
//                .where(ask.askCategory.eq(AskCategory.QUESTINFO))
//                .fetchOne();
//
//        asks.update(AskStatus.COMPLETE,AskCategory.QUESTINFO);
//    }
//
//    @Test
//    public void deleteTest(){
//        Long count = jpaQueryFactory
//                .delete(ask)
//                .where(ask.askId.eq(15L))
//                .execute();
//    }
}
