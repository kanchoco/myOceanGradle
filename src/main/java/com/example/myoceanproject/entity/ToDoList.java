package com.example.myoceanproject.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_TODOLIST")
@Getter
@ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDoList extends Period{
    @Id
    @GeneratedValue
    private Long toDoListId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user; //FK
    @NotNull
    private String toDoListContent;
    @NotNull
    private LocalDateTime toDoListSelectDate;

//    public void create(Long toDoListId, User user, String toDoListContent, LocalDateTime toDoListSelectDate) {
//        this.toDoListId = toDoListId;
//        this.user = user;
//        this.toDoListContent = toDoListContent;
//        this.toDoListSelectDate = toDoListSelectDate;
//    }
//
    public void changeUser(User user){
        this.user = user;
        user.getToDoLists().add(this);
    }


    @Builder
    public ToDoList(String toDoListContent, LocalDateTime toDoListSelectDate) {
        this.toDoListContent = toDoListContent;
        this.toDoListSelectDate = toDoListSelectDate;
    }

    public void update(String toDoListContent, LocalDateTime toDoListSelectDate) {
        this.toDoListContent = toDoListContent;
        this.toDoListSelectDate = toDoListSelectDate;
    }
}
