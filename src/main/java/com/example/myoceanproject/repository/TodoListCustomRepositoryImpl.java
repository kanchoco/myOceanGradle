package com.example.myoceanproject.repository;


import com.example.myoceanproject.domain.QToDoListDTO;
import com.example.myoceanproject.domain.ToDoListDTO;
import com.example.myoceanproject.entity.QToDoList;
import com.example.myoceanproject.entity.ToDoList;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.example.myoceanproject.entity.QToDoList.toDoList;

@Repository
@RequiredArgsConstructor
public class TodoListCustomRepositoryImpl implements TodoListCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<ToDoListDTO> findAllTodoList() {
        return jpaQueryFactory.select(new QToDoListDTO(toDoList.toDoListId, toDoList.user.userId,
                QToDoList.toDoList.toDoListContent,
                QToDoList.toDoList.toDoListSelectDate)).from(QToDoList.toDoList).fetch();
    }

    @Override
    public List<ToDoListDTO> findAllByToday() {
        return jpaQueryFactory.select(new QToDoListDTO(toDoList.toDoListId,toDoList.user.userId,
                QToDoList.toDoList.toDoListContent,
                QToDoList.toDoList.toDoListSelectDate)).from(QToDoList.toDoList).
                join(toDoList.user).
                where(QToDoList.toDoList.toDoListSelectDate.between(LocalDate.now().atStartOfDay()
                        ,LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59))))
                .orderBy(toDoList.toDoListId.desc())
                .fetch();
    }

    @Override
    public List<ToDoListDTO> findAllByMonth(LocalDateTime toDoListSelectDate) {
        int lastDay = toDoListSelectDate.toLocalDate().lengthOfMonth();

//        return jpaQueryFactory.select(new QToDoListDTO(QToDoList.toDoList.toDoListId,
//                QToDoList.toDoList.toDoListContent,
//                QToDoList.toDoList.toDoListSelectDate)).from(QToDoList.toDoList).
//                where(QToDoList.toDoList.toDoListSelectDate.between()).fetch();
//        return jpaQueryFactory.select(Projections.fields(QToDoList.toDoList.toDoListId,
//                QToDoList.toDoList.toDoListContent,
//                QToDoList.toDoList.toDoListSelectDate)).from(QToDoList.toDoList).
//                groupBy(formmattedDate).fetch();

        return jpaQueryFactory.select(new QToDoListDTO(toDoList.toDoListId,toDoList.user.userId,toDoList.toDoListContent,toDoList.toDoListSelectDate))
                .from(toDoList)
                .join(toDoList.user)
                .where(toDoList.toDoListSelectDate.between(toDoListSelectDate.withDayOfMonth(1)
                        ,LocalDateTime.of(toDoListSelectDate.withDayOfMonth(lastDay).toLocalDate(),LocalTime.of(23,59,59))))
                .orderBy(toDoList.toDoListSelectDate.desc())
                .fetch();
    }

    @Override
    public ToDoListDTO findById(Long id) {
        return jpaQueryFactory.select(new QToDoListDTO(toDoList.toDoListId,toDoList.user.userId,toDoList.toDoListContent,toDoList.toDoListSelectDate))
                .from(toDoList)
                .where(toDoList.toDoListId.eq(id))
                .fetchOne();
    }

}
