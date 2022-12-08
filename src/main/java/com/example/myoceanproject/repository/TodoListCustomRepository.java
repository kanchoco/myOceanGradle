package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.ToDoListDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoListCustomRepository {

    public List<ToDoListDTO> findAllTodoList(Long userId);

    public List<ToDoListDTO> findAllByToday(Long userId);

    public List<ToDoListDTO> findAllByMonth(LocalDateTime toDoListSelectDate,Long userId);

    public ToDoListDTO findById(Long id);

}
