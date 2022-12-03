package com.example.myoceanproject.controller.manager;


import com.example.myoceanproject.service.community.CommunityPostService;
import com.example.myoceanproject.service.community.CommunityReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/post/*")
public class PostController {
    @Autowired
    private CommunityPostService postService;
//    브라우저에서 JSON 타입으로 데이터를 전송하고 서버에서는 댓글의 처리 결과에 따라 문자열로 결과를 리턴한다.
//    consumes : 전달받은 데이터의 타입
//    produces : 콜백함수로 결과를 전달할 때의 타입
//    @RequestBody : 전달받은 데이터를 알맞는 매개변수로 주입
//    ResponseEntity : 서버의 상태코드, 응답 메세지 등을 담을 수 있는 타입

    @Transactional
    @DeleteMapping("/{postNumber}")
    public String deletePost(@PathVariable Long postNumber){
        postService.remove(postNumber);
        return "delete success";
    }

}
