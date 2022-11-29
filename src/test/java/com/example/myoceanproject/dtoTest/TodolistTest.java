package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.AlarmDTO;
import com.example.myoceanproject.domain.ToDoListDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.ToDoList;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.AlarmRepository;
import com.example.myoceanproject.repository.TodoListRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.ReadStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.myoceanproject.entity.QAlarm.alarm;
import static com.example.myoceanproject.entity.QToDoList.toDoList;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class TodolistTest {
    @Autowired
    private TodoListRepository todoListRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveTest(){
//        시나리오 : 유저가 투두리스트를 작성하면 작성한 유저의 아이디와 투두리스트 내용, 어떤 날짜에 해야하는 일인지가 db에 들어간다.
        
//        유저 정보 저장
        User user = userRepository.findById(1L).get();

//        ToDoListDTO 생성 후 값 세팅하기
        ToDoListDTO listDTO = new ToDoListDTO();
        listDTO.setToDoListContent("오늘할일 : 진짜 많음");
        listDTO.setToDoListSelectDate(LocalDateTime.of(2003, 12, 4, 0, 0));

//       ToDoList 엔티티화
        ToDoList list1 = listDTO.toEntity();
//       ToDoList 엔티티에 유저 set해줌
        list1.changeUser(user);

//      alarm 엔티티에 해당 값들을 모두 저장
        todoListRepository.save(list1);
    }

//    @Test
//    public void findAllTest(){
//        List<ToDoList> lists = jpaQueryFactory.selectFrom(toDoList)
//                .join(toDoList.user)
//                .fetchJoin()
//                .fetch();
//        lists.stream().map(ToDoList::toString).forEach(log::info);
//    }
//
//    @Test
//    public void findById(){
//        List<ToDoList> lists = jpaQueryFactory.selectFrom(toDoList)
//                .join(toDoList.user)
//                .where(toDoList.user.userId.eq(1L))
//                .fetchJoin()
//                .fetch();
//
//        lists.stream().map(ToDoList::toString).forEach(log::info);
//
//    }
//    @Test
//    public void updateTest(){
//
////        Long count = jpaQueryFactory.update(alarm).set(alarm.alarmContent, "수정").where(alarm.user.userId.eq(1L)).execute();
////        log.info(count.toString());
//        ToDoList list = jpaQueryFactory.selectFrom(toDoList)
//                .where(toDoList.toDoListId.eq(110L))
//                .fetchOne();
//
//        list.update("수정", LocalDateTime.of(2020, 12, 4, 0, 0));
//
//
//    }
//
//    @Test
//    public void deleteTest(){
//        Long count = jpaQueryFactory
//                .delete(toDoList)
//                .where(toDoList.toDoListId.eq(111L))
//                .execute();
//    }
}
