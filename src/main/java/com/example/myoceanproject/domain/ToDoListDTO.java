package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.ToDoList;
import com.example.myoceanproject.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class ToDoListDTO {
    private Long userId;
    private String toDoListContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime toDoListSelectDate;

    @QueryProjection
    public ToDoListDTO( Long userId, String toDoListContent, LocalDateTime toDoListSelectDate) {
        this.userId = userId;
        this.toDoListContent = toDoListContent;
        this.toDoListSelectDate = toDoListSelectDate;
    }

    public ToDoList toEntity(){
        return ToDoList.builder()
                .toDoListContent(toDoListContent)
                .toDoListSelectDate(toDoListSelectDate)
                .build();
    }
}
