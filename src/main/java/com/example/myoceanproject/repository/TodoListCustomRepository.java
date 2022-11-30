package com.example.myoceanproject.repository;

import com.example.myoceanproject.domain.ToDoListDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TodoListCustomRepository {

    public List<ToDoListDTO> findAllTodoList();

    public List<ToDoListDTO> findAllByToday();

}
