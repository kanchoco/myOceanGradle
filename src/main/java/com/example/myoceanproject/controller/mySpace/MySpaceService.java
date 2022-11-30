package com.example.myoceanproject.controller.mySpace;

import com.example.myoceanproject.domain.ToDoListDTO;
import com.example.myoceanproject.entity.ToDoList;
import com.example.myoceanproject.repository.TodoListCustomRepository;
import com.example.myoceanproject.repository.TodoListRepository;
import com.example.myoceanproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MySpaceService {
    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;
    private final TodoListCustomRepository todoListCustomRepository;

    //투두 리스트 작성
    public void post(String toDoListContent, String toDoListSelectDate){
        ToDoListDTO toDoListDTO = new ToDoListDTO();

        //회원가입된 유저 찾기
//        HttpSession session = (HttpSession)request.getSession();
//        session.getAttribute("loginUser");

        //String 날짜를 LocalDateTime타입의 날짜로 바꾸는 과정
        // 패턴을 정해준다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //LocalDateTime에 String 날짜와 패턴을 넘겨준다.
        LocalDateTime time = LocalDate.parse(toDoListSelectDate,formatter).atStartOfDay();
        toDoListDTO.setToDoListContent(toDoListContent);
        toDoListDTO.setToDoListSelectDate(time);


        ToDoList toDoList = toDoListDTO.toEntity();
        toDoList.setUser(userRepository.findById(1l).get());

        todoListRepository.save(toDoList);

    }

    public String returnToday(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(LocalDateTime.now());
    }


//    전체날짜 출력
    public List<ToDoListDTO> showAll(){
        return todoListCustomRepository.findAllTodoList();
    }

//    오늘 날짜로 출력
    public List<ToDoListDTO> showAllByToday(){
        return todoListCustomRepository.findAllByToday();
    }

    public void remove(){

    }

}
