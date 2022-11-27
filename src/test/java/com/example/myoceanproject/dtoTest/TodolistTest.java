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
        User user = userRepository.findById(1L).get();

        ToDoListDTO listDTO = new ToDoListDTO();

        listDTO.setToDoListContent("내용2");
        listDTO.setToDoListSelectDate(LocalDateTime.of(2003, 12, 4, 0, 0));

        ToDoList list1 = listDTO.toEntity();

//      alarmDTO에 처음에 조회했던 유저 정보를 저장(optional이기 때문에 get 사용)
//      changeUser 메소드로 alarmDTO에 저장된 User값을 alarm1로 전달
        listDTO.setUser(user);
        list1.changeUser(listDTO.getUser());

//      alarm 엔티티에 해당 값들을 모두 저장
        todoListRepository.save(list1);
    }

    @Test
    public void findAllTest(){
        List<ToDoList> lists = jpaQueryFactory.selectFrom(toDoList)
                .join(toDoList.user)
                .fetchJoin()
                .fetch();
        lists.stream().map(ToDoList::toString).forEach(log::info);
    }

    @Test
    public void findById(){
        List<ToDoList> lists = jpaQueryFactory.selectFrom(toDoList)
                .join(toDoList.user)
                .where(toDoList.user.userId.eq(1L))
                .fetchJoin()
                .fetch();

        lists.stream().map(ToDoList::toString).forEach(log::info);

    }
    @Test
    public void updateTest(){

//        Long count = jpaQueryFactory.update(alarm).set(alarm.alarmContent, "수정").where(alarm.user.userId.eq(1L)).execute();
//        log.info(count.toString());
        ToDoList list = jpaQueryFactory.selectFrom(toDoList)
                .where(toDoList.toDoListId.eq(110L))
                .fetchOne();

        list.update("수정", LocalDateTime.of(2020, 12, 4, 0, 0));


    }

    @Test
    public void deleteTest(){
        Long count = jpaQueryFactory
                .delete(toDoList)
                .where(toDoList.toDoListId.eq(111L))
                .execute();
    }
}
