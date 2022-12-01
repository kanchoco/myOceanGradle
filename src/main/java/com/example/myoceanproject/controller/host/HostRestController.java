package com.example.myoceanproject.controller.host;

import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;



@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/host/*")
public class HostRestController {

    private final GroupService groupService;

    @PostMapping(value="/index", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> host(@RequestBody GroupDTO groupDTO) throws UnsupportedEncodingException {
        log.info("==============================");
        log.info("그룹명: " + groupDTO.getGroupName());
        log.info("그룹카테고리: " + groupDTO.getGroupCategory());
        log.info("그룹 최소인원: " + groupDTO.getMinMember());
        log.info("그룹 최대인원: " + groupDTO.getMaxMember());
        log.info("그룹 포인트: " + groupDTO.getGroupPoint());
        log.info("국내/해외 여부: " + groupDTO.getGroupOverSea());
        log.info("모임 타입: " + groupDTO.getGroupLocationType());
        log.info("모임 장소 이름: " + groupDTO.getGroupLocationName());
        log.info("모임 장소: " + groupDTO.getGroupLocation());
        log.info("모임 상세 주소: " + groupDTO.getGroupLocationDetail());
        log.info("주차 가능 여부: " + groupDTO.getGroupParkingAvailable());
        log.info("모임 주의사항: " + groupDTO.getGroupMoreInformation());
        log.info("상세 내용: " + groupDTO.getGroupContent());
        Group group = groupDTO.toEntity();
        groupService.add(group);
        return new ResponseEntity<>(new String("register success".getBytes(), "UTF-8"), HttpStatus.OK);
    }
}
