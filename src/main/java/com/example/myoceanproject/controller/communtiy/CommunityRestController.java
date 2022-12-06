package com.example.myoceanproject.controller.communtiy;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.service.community.CommunityPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/community/*")
public class CommunityRestController {

    private final CommunityPostService communityPostService;

    // 게시글 작성 후 커뮤니티 페이지로 이동
    @PostMapping(value="/index", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> communityWriting(@RequestBody CommunityPostDTO communityPostDTO, HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();

        Long userId = (Long) session.getAttribute("userId");
        communityPostDTO.setUserId(userId);

        communityPostService.add(communityPostDTO);

        return new ResponseEntity<>(new String("register success".getBytes(), "UTF-8"), HttpStatus.OK);
    }
}
