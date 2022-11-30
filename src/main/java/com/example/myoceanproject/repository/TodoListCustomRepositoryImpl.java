package com.example.myoceanproject.repository;


import com.example.myoceanproject.domain.QToDoListDTO;
import com.example.myoceanproject.domain.ToDoListDTO;
import com.example.myoceanproject.entity.QToDoList;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoListCustomRepositoryImpl implements TodoListCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<ToDoListDTO> findAllTodoList() {
        return jpaQueryFactory.select(new QToDoListDTO(QToDoList.toDoList.toDoListId,
                QToDoList.toDoList.toDoListContent,
                QToDoList.toDoList.toDoListSelectDate)).from(QToDoList.toDoList).fetch();
    }

    @Override
    public List<ToDoListDTO> findAllByToday() {
        return jpaQueryFactory.select(new QToDoListDTO(QToDoList.toDoList.toDoListId,
                QToDoList.toDoList.toDoListContent,
                QToDoList.toDoList.toDoListSelectDate)).from(QToDoList.toDoList).
                where(QToDoList.toDoList.toDoListSelectDate.between(LocalDate.now().atStartOfDay()
                        ,LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59))))
                .fetch();
    }
}
