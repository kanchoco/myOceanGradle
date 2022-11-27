package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.PointDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.Point;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.PointRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.myoceanproject.entity.QPoint.point;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class PointTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void savePointTest(){
        Optional<User> user = userRepository.findById(1L);
        PointDTO pointDTO = new PointDTO();

//      pointDTO에 필요한 값 저장
        pointDTO.setPointAmountHistory(200);

//      pointDTO에 저장한 값들을 entity로 변환
        Point point1 = pointDTO.toEntity();

//      changeUser 메소드로 pointDTO에 저장된 User값을 point1으로 전달
        pointDTO.setUser(user.get());
        point1.changeUser(pointDTO.getUser());

//      point 엔티티에 해당 값 모두 저장
        pointRepository.save(point1);
    }

    @Test
    public void findAllTest(){
        List<Point> points = jpaQueryFactory.selectFrom(point)
                .join(point.user)
                .fetchJoin()
                .fetch();
        points.stream().map(Point::toString).forEach(log::info);
    }

    @Test
    public void findById(){
        List<Point> points = jpaQueryFactory.selectFrom(point)
                .join(point.user)
                .where(point.user.userId.eq(1L))
                .fetchJoin()
                .fetch();

        points.stream().map(Point::toString).forEach(log::info);

    }

    @Test
    public void deleteTest(){
        Long count = jpaQueryFactory
                .delete(point)
                .where(point.pointId.eq(13L))
                .execute();
    }
}
