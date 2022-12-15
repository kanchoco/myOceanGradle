package com.example.myoceanproject.aspect;

import com.example.myoceanproject.domain.AlarmDTO;
import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.CommunityReplyDTO;
import com.example.myoceanproject.domain.PointDTO;
import com.example.myoceanproject.entity.*;
import com.example.myoceanproject.repository.GroupRepository;
import com.example.myoceanproject.repository.PointRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.repository.ask.AskRepository;
import com.example.myoceanproject.repository.community.post.CommunityPostRepository;
import com.example.myoceanproject.repository.quest.QuestAchievementRepository;
import com.example.myoceanproject.repository.quest.QuestAchievementRepositoryImpl;
import com.example.myoceanproject.repository.quest.QuestRepository;
import com.example.myoceanproject.service.PointService;
import com.example.myoceanproject.service.alarm.AlarmService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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

    private final AskRepository askRepository;

    private final PointRepository pointRepository;
    private final QuestAchievementRepository questAchievementRepository;

    private final QuestAchievementRepositoryImpl achievementRepositoryImpl;
    private final QuestRepository questRepository;

    private final PointService pointService;
    private final GroupRepository groupRepository;



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

        if(replyDTO.getUserId() != replyDTO.getWriterId()){
            AlarmDTO alarmDTO = new AlarmDTO();
            alarmDTO.setAlarmCategory("COMMUNITY");
            alarmDTO.setAlarmContent("\"" + replyDTO.getCommunityPostTitle() + "\" 에 댓글이 달렸습니다 지금 확인해 보세요!");
            alarmDTO.setUserId(replyDTO.getWriterId());
            alarmDTO.setContentId(replyDTO.getCommunityPostId());

            log.info(alarmDTO.toString());
            alarmService.addAlarm(alarmDTO);
        }
    }

    @After("@annotation(com.example.myoceanproject.aspect.annotation.LikeAlarm)")
    public void afterAddLike(JoinPoint joinPoint){
        long userId = Long.parseLong(joinPoint.getArgs()[0].toString());
        long postId = Long.parseLong(joinPoint.getArgs()[1].toString());
        log.info("-------------------------------------------------");
        log.info("--" + userId);
        log.info("--" + postId);
        log.info("-------------------------------------------------");


        if(userId != postRepository.findById(postId).get().getUser().getUserId()){
            AlarmDTO alarmDTO = new AlarmDTO();
            alarmDTO.setAlarmCategory("COMMUNITY");
            alarmDTO.setAlarmContent("\"" + postRepository.findById(postId).get().getCommunityTitle() + "\" 이 좋아요를 받았습니다");
            alarmDTO.setUserId(userId);
            alarmDTO.setContentId(postId);
            alarmService.addAlarm(alarmDTO);
        }
    }

//    그룹 참여 알람
    @After("@annotation(com.example.myoceanproject.aspect.annotation.GroupJoinAlarm)")
    public void joinGroup(JoinPoint joinPoint){
        Long groupId = Long.valueOf(joinPoint.getArgs()[0].toString());
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[1];
        Long userId = (Long)request.getSession().getAttribute("userId");

        Group group = groupRepository.findById(groupId).get();

        AlarmDTO alarmDTO = new AlarmDTO();
        alarmDTO.setAlarmCategory("GROUP");
        alarmDTO.setAlarmContent("\"" + group.getGroupName() + "\" 에 참여하였습니다! 채팅방을 확인볼까요🙋‍♀️");
        alarmDTO.setUserId(userId);
        alarmDTO.setContentId(groupId);
        alarmService.addAlarm(alarmDTO);
    }

    //관리자 답변 알림
    @After("@annotation(com.example.myoceanproject.aspect.annotation.AskAlarm)")
    public void answer(JoinPoint joinPoint){
        ObjectNode objectNode = Arrays.stream(joinPoint.getArgs())
                .filter(ObjectNode.class::isInstance)
                .map(ObjectNode.class::cast)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User를 찾을 수 없습니다."));

        Long askId=objectNode.get("askId").asLong();
        String askContent=objectNode.get("askContent").asText();

        log.info("-------------------------------------------------");
        log.info("--" + askId);
        log.info("--" + askContent);
        log.info("-------------------------------------------------");
        Ask ask = askRepository.findById(askId).get() ;
        AlarmDTO alarmDTO = new AlarmDTO();
        alarmDTO.setAlarmCategory("ASK");
        alarmDTO.setAlarmContent("\"" + ask.getAskTitle() + "\" 에 답변이 달렸습니다.");
        alarmDTO.setUserId(ask.getUser().getUserId());
        alarmService.addAlarm(alarmDTO);
    }

//포인트 충전 알람
    @After("@annotation(com.example.myoceanproject.aspect.annotation.PointAlarm)")
    public void point(JoinPoint joinPoint){
        ObjectNode objectNode = Arrays.stream(joinPoint.getArgs())
                .filter(ObjectNode.class::isInstance)
                .map(ObjectNode.class::cast)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User를 찾을 수 없습니다."));

        AlarmDTO alarmDTO = new AlarmDTO();

    try{
        if(!objectNode.get("userId").isNull()){
            log.info("포인트 충전");
            Long userPoint=objectNode.get("point").asLong();
            Long userId=objectNode.get("userId").asLong();
            String merchantUid=objectNode.get("merchantUid").asText();
            String impUid=objectNode.get("impUid").asText();
            String content=objectNode.get("content").asText();

            alarmDTO.setAlarmCategory("POINT");
            alarmDTO.setAlarmContent(userPoint + "POINT 충전이 완료되었습니다!");
            alarmDTO.setUserId(userId);

//100000포인트 결제 고객에게 리워드 지급(받은적이 없다면)
            if(userPoint >= 100000L && achievementRepositoryImpl.checkDuplicatedById(userId, 252L)) {
                User user = userRepository.findById(userId).get();
                QuestAchievement questAchievement = new QuestAchievement();
                questAchievement.setQuest(questRepository.findById(252L).get());
                questAchievement.setUser(user);
                questAchievementRepository.save(questAchievement);

                PointDTO pointDTO = new PointDTO();
                pointDTO.setPointAmountHistory(5000);
                pointDTO.setUserId(userId);

                pointService.questReward(pointDTO);

            }else if(achievementRepositoryImpl.checkDuplicatedById(userId, 320L)){
//                첫 결제 && 이전 결제 보상이 없던 유저도 포함 보상
                User user = userRepository.findById(userId).get();
                QuestAchievement questAchievement = new QuestAchievement();
                questAchievement.setQuest(questRepository.findById(320L).get());
                questAchievement.setUser(user);
                questAchievementRepository.save(questAchievement);

                PointDTO pointDTO = new PointDTO();
                pointDTO.setPointAmountHistory(2000);
                pointDTO.setUserId(userId);

                pointService.questReward(pointDTO);
            }

                AlarmDTO questAlarm = new AlarmDTO();

                questAlarm.setUserId(userId);
                alarmService.questAlarm(questAlarm);
        }
    }catch(NullPointerException e){
        log.info("관리자 환불페이지");
        //        환불
        Long requestRefundUser=objectNode.get("requestRefundUser").asLong();
        Long requestRefundPointId=objectNode.get("requestRefundPointId").asLong();

        log.info(requestRefundUser + "requestRefundUser");
        log.info(requestRefundPointId + "requestRefundPointId");

        alarmDTO.setAlarmCategory("POINT");
        alarmDTO.setAlarmContent(pointRepository.findById(requestRefundPointId).get().getPointAmountHistory() + "POINT 에 대한 환불이 완료되었습니다.");
        alarmDTO.setUserId(requestRefundUser);

        }
        log.info("-------------------------------------------------");
        log.info(alarmDTO.toString());
        log.info("-------------------------------------------------");
            alarmService.addAlarm(alarmDTO);
    }

    @After("@annotation(com.example.myoceanproject.aspect.annotation.RefundAlarm)")
    public void refund(JoinPoint joinPoint){
        Long pointId = Long.valueOf(joinPoint.getArgs()[0].toString());
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[1];
        Long userId = (Long)request.getSession().getAttribute("userId");
        Point point = pointRepository.findById(pointId).get();


        AlarmDTO alarmDTO = new AlarmDTO();
        alarmDTO.setAlarmCategory("POINT");
        alarmDTO.setAlarmContent(point.getPointAmountHistory() + "POINT 에 대한 환불신청이 완료되었습니다.");
        alarmDTO.setUserId(userId);
        alarmService.addAlarm(alarmDTO);
        log.info("-------------------------------------------------");
        log.info(alarmDTO.toString());
        log.info("-------------------------------------------------");
    }

    @After("@annotation(com.example.myoceanproject.aspect.annotation.GroupAlarm)")
    public void requestGroup(JoinPoint joinPoint){
        Long groupId = Long.valueOf(joinPoint.getArgs()[0].toString());
        String status = joinPoint.getArgs()[1].toString();
        Group group = groupRepository.findById(groupId).get();

        AlarmDTO alarmDTO = new AlarmDTO();
        alarmDTO.setAlarmCategory("GROUP");
        if(status.equals("disapprove")){
            alarmDTO.setAlarmContent("\""+ group.getGroupName() + "\" 모임이 거절되었습니다");
            alarmDTO.setUserId(group.getUser().getUserId());
            alarmDTO.setContentId(group.getGroupId());
        }else{
            alarmDTO.setAlarmContent("\""+ group.getGroupName() + "\" 모임이 승인되었습니다✨🎉");
            alarmDTO.setUserId(group.getUser().getUserId());
            alarmDTO.setContentId(group.getGroupId());
        }
            alarmService.addAlarm(alarmDTO);
    }

    @AfterReturning("@annotation(com.example.myoceanproject.aspect.annotation.PostAlarm)")
    public void postAlarm(JoinPoint joinPoint){
        log.info("---------------------------------------------------");
        CommunityPostDTO postDTO = (CommunityPostDTO) joinPoint.getArgs()[0];
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[1];
        Long userId = (Long)request.getSession().getAttribute("userId");
        User user = userRepository.findById(userId).get();
        String[] category = {"독서", "운동", "요리", "영화", "고민"};
        int k = 0;

        for(int i = 334; i <= 338; i++){
        log.info("---------------------------------------------------");
            if(postDTO.getCommunityCategory().equals(category[k]) && achievementRepositoryImpl.checkDuplicatedById(userId, (long) i)){
                QuestAchievement questAchievement = new QuestAchievement();
                questAchievement.setQuest(questRepository.findById((long)i).get());
                questAchievement.setUser(user);
                questAchievementRepository.save(questAchievement);

                PointDTO pointDTO = new PointDTO();
                pointDTO.setPointAmountHistory(500);
                pointDTO.setUserId(userId);

                pointService.questReward(pointDTO);

                AlarmDTO questAlarm = new AlarmDTO();

                questAlarm.setUserId(userId);
        log.info("---------------------------------------------------");
        log.info("알람으로");
        log.info(questAlarm.toString());
                alarmService.questAlarm(questAlarm);
            }
            k++;
        }
//
//        if(achievementRepositoryImpl.checkDuplicatedById(userId, 320L)){
//            QuestAchievement questAchievement = new QuestAchievement();
//            questAchievement.setQuest(questRepository.findById(320L).get());
//            questAchievement.setUser(user);
//            questAchievementRepository.save(questAchievement);
//
//            PointDTO pointDTO = new PointDTO();
//            pointDTO.setPointAmountHistory(500);
//            pointDTO.setUserId(userId);
//
//            pointService.questReward(pointDTO);
//        }else if(achievementRepositoryImpl.checkDuplicatedById(userId, 320L)){
//            QuestAchievement questAchievement = new QuestAchievement();
//            questAchievement.setQuest(questRepository.findById(320L).get());
//            questAchievement.setUser(user);
//            questAchievementRepository.save(questAchievement);
//
//            PointDTO pointDTO = new PointDTO();
//            pointDTO.setPointAmountHistory(500);
//            pointDTO.setUserId(userId);
//
//            pointService.questReward(pointDTO);
//        }


    }
//    @After("@annotation(com.example.myoceanproject.aspect.annotation.JoinAlarm)")
//    public void joinAlarm(JoinPoint joinPoint){
////        long userId = Long.parseLong(joinPoint.getArgs()[3].toString());
//        log.info("---------------------------------------------------");
////        log.info("---" + userId);
//        Arrays.stream(joinPoint.getArgs()).forEach(v -> v.toString());
//        log.info("---------------------------------------------------");
//        AlarmDTO alarmDTO = new AlarmDTO();
//    }



}
