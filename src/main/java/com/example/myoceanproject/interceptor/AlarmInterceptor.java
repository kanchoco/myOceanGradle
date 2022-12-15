package com.example.myoceanproject.interceptor;

import com.example.myoceanproject.controller.myAlarm.MyAlarmRestController;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.service.alarm.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlarmInterceptor implements HandlerInterceptor {

    private AlarmService alarmService;
    private MyAlarmRestController alarmRestController;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            Long userId = ((Long)request.getSession().getAttribute("userId"));
            if(userId != null){
                log.info("-=----------------------------------------");
                log.info("preHandler");
                log.info("userId" + userId);
                log.info("readCheck" + alarmService.checkStatus(userId));
                log.info("-=----------------------------------------");
//                request.getSession().setAttribute("readCheck", alarmService.checkStatus(userDTO.getUserId()));
    //            alarmRestController.checkStatus(request);
            }
        }catch (NullPointerException e){
            log.info("NPE");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
