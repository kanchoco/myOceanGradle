package com.example.myoceanproject.controller.myAlarm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myAlarm/*")
public class MyAlarm {
    // 내가 받은 알람
    @GetMapping("/myAlarm")
    public String myAlarm(){
        return "app/myAlarm/myAlarm";
    }

}
