package com.example.myoceanproject.controller.mySpace;

import com.example.myoceanproject.domain.ToDoListDTO;
import com.example.myoceanproject.repository.TodoListCustomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

//viewResolver가 관여하지 않는다.
@RestController
@RequestMapping("/month/*")
@Slf4j
@RequiredArgsConstructor
public class MySpaceMonthController {
    private final MySpaceService mySpaceService;

    //    /month/?
    @GetMapping("{month}")
    public List<Object> getMonth(@PathVariable String month){
//        String을 LocalDateTime으로 형변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        화면에서 받아온 값(String) month를 LocalDateTime 타입의 time으로 변환.

        LocalDateTime time = LocalDate.parse(month,formatter).atStartOfDay();
        List<ToDoListDTO> list = mySpaceService.showAllByMonth(time);
        List<LocalDateTime> selectTime = list.stream().map(ToDoListDTO::getToDoListSelectDate).collect(Collectors.toList());
        Set<LocalDateTime> set = new HashSet<>(selectTime);
        List<LocalDateTime> finalTime = set.stream().collect(Collectors.toList());
        Collections.sort(finalTime, Collections.reverseOrder());


        List<Object> onepiece = List.of(list,finalTime);

        return onepiece;

    }


    @PostMapping("{bno}")
    public ToDoListDTO update(@PathVariable Long bno,ToDoListDTO toDoListDTO){
        return mySpaceService.update(toDoListDTO);
    }

    @PutMapping("{now}")
    public List<ToDoListDTO> getNow(){
        return mySpaceService.showAllByToday();
    }

    @DeleteMapping("{bno}")
    public String delete(@PathVariable Long bno){

        mySpaceService.delete(bno);
        return "success";
    }


}