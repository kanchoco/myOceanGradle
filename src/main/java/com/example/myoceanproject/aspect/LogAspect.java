package com.example.myoceanproject.aspect;

import com.example.myoceanproject.domain.AlarmDTO;
import com.example.myoceanproject.domain.CommunityReplyDTO;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.repository.community.post.CommunityPostRepository;
import com.example.myoceanproject.service.alarm.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Configuration
@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class LogAspect {

    private final AlarmService alarmService;

    private final CommunityPostRepository postRepository;

    private final UserRepository userRepository;

    @After("@annotation(com.example.myoceanproject.aspect.annotation.ReplyAlarm)")
    public void afterAddReply(JoinPoint joinPoint){
        CommunityReplyDTO replyDTO = Arrays.stream(joinPoint.getArgs())
                .filter(CommunityReplyDTO.class::isInstance)
                .map(CommunityReplyDTO.class::cast)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User를 찾을 수 없습니다."));

        log.info("-------------------------------------------------");
        log.info("--" + replyDTO.getCommunityPostId());
        log.info("-------------------------------------------------");
        AlarmDTO alarmDTO = new AlarmDTO();
        alarmDTO.setAlarmCategory("COMMUNITY");
        alarmDTO.setAlarmContent(replyDTO.getCommunityPostTitle() + " 에 댓글이 달렸습니다 지금 확인해 보세요!");
        alarmDTO.setUserId(replyDTO.getWriterId());
        alarmDTO.setContentId(replyDTO.getCommunityPostId());

        log.info(alarmDTO.toString());
        alarmService.addAlarm(alarmDTO);
    }

    @After("@annotation(com.example.myoceanproject.aspect.annotation.LikeAlarm)")
    public void afterAddLike(JoinPoint joinPoint){
        long userId = Long.parseLong(joinPoint.getArgs()[0].toString());
        long postId = Long.parseLong(joinPoint.getArgs()[1].toString());
        log.info("-------------------------------------------------");
        log.info("--" + userId);
        log.info("--" + postId);
        log.info("-------------------------------------------------");

        AlarmDTO alarmDTO = new AlarmDTO();
        alarmDTO.setAlarmCategory("COMMUNITY");
        alarmDTO.setAlarmContent("|" + postRepository.findById(postId).get().getCommunityTitle() + "| 이 좋아요를 받았습니다");
        alarmDTO.setUserId(userId);
        alarmDTO.setContentId(postId);
        alarmService.addAlarm(alarmDTO);
    }

    



}
