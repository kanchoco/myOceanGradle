package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.ToDoList;
import com.example.myoceanproject.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class ToDoListDTO {
    private User user;
    private String toDoListContent;
    private LocalDateTime toDoListSelectDate;


    public ToDoList toEntity(){
        return ToDoList.builder()
                .toDoListContent(toDoListContent)
                .toDoListSelectDate(toDoListSelectDate)
                .build();
    }
}
