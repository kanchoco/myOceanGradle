package com.example.myoceanproject.repository;

import com.example.myoceanproject.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends JpaRepository<ToDoList, Long> {
    
}
