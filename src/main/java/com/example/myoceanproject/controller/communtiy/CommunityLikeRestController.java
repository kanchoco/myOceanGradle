package com.example.myoceanproject.controller.communtiy;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.repository.community.post.CommunityPostRepository;
import com.example.myoceanproject.service.community.CommunityLikeService;
import com.example.myoceanproject.service.community.CommunityPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/like/*")
public class CommunityLikeRestController {
    private final CommunityLikeService communityLikeService;
    private final CommunityPostRepository communityPostRepository;


//  좋아요 눌렀는 지 확인
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value="/checkLike/{communityPostId}")
    public Boolean checkLike(@PathVariable("communityPostId") Long communityPostId, HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId"); //게시글 작성자가 아닌 사용자의 아이디

        Boolean checkLike = communityLikeService.checkLike(userId, communityPostId);
        return checkLike;
    }


//  좋아요 누를 때
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value="/addLike/{communityPostId}")
    public ResponseEntity<String> add(@PathVariable("communityPostId") Long communityPostId, HttpServletRequest request) throws UnsupportedEncodingException{
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId"); //게시글 작성자가 아닌 사용자의 아이디
        
        
        // CommunityPost 업데이트
        CommunityPost communityPost = communityPostRepository.findById(communityPostId).get();
        communityPost.updateLikePlusCount();

        // Like 테이블에 저장
        communityLikeService.addLike(userId, communityPostId);

        return new ResponseEntity<>(new String("like success".getBytes(), "UTF-8"), HttpStatus.OK);
    }

//  좋아요 취소할 때
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value="/minusLike/{communityPostId}")
    public ResponseEntity<String> minus(@PathVariable("communityPostId") Long communityPostId, HttpServletRequest request) throws UnsupportedEncodingException{
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId"); //게시글 작성자가 아닌 사용자의 아이디

        // CommunityPost 업데이트
        CommunityPost communityPost = communityPostRepository.findById(communityPostId).get();
        communityPost.updateLikeMinusCount();

        // Like 테이블에서 해당 내용 삭제
        communityLikeService.minusLike(userId, communityPostId);

        return new ResponseEntity<>(new String("like success".getBytes(), "UTF-8"), HttpStatus.OK);
    }
}
