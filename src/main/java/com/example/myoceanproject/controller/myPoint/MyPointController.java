package com.example.myoceanproject.controller.myPoint;

import com.example.myoceanproject.domain.*;
import com.example.myoceanproject.entity.Point;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.PointRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.service.PointService;
import com.example.myoceanproject.service.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.example.myoceanproject.entity.QUser.user;

@Slf4j
@Controller
@RequestMapping("/myPoint/*")
public class MyPointController {

    @Autowired
    private PointService pointService;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    // 마이 포인트 페이지
    @GetMapping("/index")
    public String myPoint(Model model, HttpServletRequest request){
        HttpSession session=request.getSession();
        List<PointDTO> pointDTOList=pointService.findAllPointByUser((Long)session.getAttribute("userId"));
        log.info("pointList:"+pointDTOList);
        model.addAttribute("points", pointDTOList);
        return "app/myPoint/myPoint";
    }

    @RequestMapping("/savePay")
    @ResponseBody
    @Transactional
    @Modifying
    public String receivePayment(@RequestBody ObjectNode objectNode){
        PointDTO pointDTO=new PointDTO();
        UserDTO userDTO=new UserDTO();

        Long userPoint=objectNode.get("point").asLong();
        Long userId=objectNode.get("userId").asLong();
        String merchantUid=objectNode.get("merchantUid").asText();
        String impUid=objectNode.get("impUid").asText();
        String content=objectNode.get("content").asText();

        User user1=new User();
        user1.setUserId(userId);

        pointDTO.setPointAmountHistory(Integer.valueOf(String.valueOf(userPoint)));
        pointDTO.setPointType("결제");
        pointDTO.setPointMerchantUid(merchantUid);
        pointDTO.setPointImpUid(impUid);
        pointDTO.setPointContent(content);
        Point point=pointDTO.toEntity();
        point.changeUser(user1);

        log.info("pointDTO:"+pointDTO);
        log.info("user:"+user1);
        log.info("point:"+point);

        pointRepository.save(point);

        User selectuser=jpaQueryFactory.selectFrom(user).where(user.userId.eq(userId)).fetchOne();

        int updateTotalPoint=selectuser.getUserTotalPoint()+Integer.parseInt(String.valueOf(userPoint));
        userDTO.setUserTotalPoint(updateTotalPoint);
        selectuser.updateUserTotalPoint(userDTO);

        log.info("userDTO1:"+userDTO);
        log.info("selectuser:"+selectuser);
        return "sucess";
    }
}
