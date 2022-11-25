package com.example.myoceanproject.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_TODOLIST")
@Getter
@ToString(exclude = "user")
@NoArgsConstructor
public class ToDoList extends Period{
    @Id
    @GeneratedValue
    private Long toDoListId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user; //FK
    private String toDoListContent;
    private LocalDateTime toDoListSelectDate;

//    public void create(Long toDoListId, User user, String toDoListContent, LocalDateTime toDoListSelectDate) {
//        this.toDoListId = toDoListId;
//        this.user = user;
//        this.toDoListContent = toDoListContent;
//        this.toDoListSelectDate = toDoListSelectDate;
//    }
//
//    public void changeUser(User user){
//        this.user = user;
//        user.getToDoLists().add(this);
//    }


    @Builder
    public ToDoList(User user, String toDoListContent, LocalDateTime toDoListSelectDate) {
        this.user = user;
        this.toDoListContent = toDoListContent;
        this.toDoListSelectDate = toDoListSelectDate;
    }
}
