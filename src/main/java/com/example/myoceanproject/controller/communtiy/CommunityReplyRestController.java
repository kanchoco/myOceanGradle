package com.example.myoceanproject.controller.communtiy;

import com.example.myoceanproject.domain.CommunityReplyDTO;
import com.example.myoceanproject.entity.CommunityReply;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.repository.community.post.CommunityPostRepository;
import com.example.myoceanproject.repository.community.reply.CommunityReplyRepository;
import com.example.myoceanproject.service.community.CommunityPostService;
import com.example.myoceanproject.service.community.CommunityReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reply/*")
public class CommunityReplyRestController {

    private final CommunityReplyService communityReplyService;
    private final CommunityPostService communityPostService;
    private final UserRepository userRepository;
    private final CommunityPostRepository communityPostRepository;
    private final CommunityReplyRepository communityReplyRepository;


    //  댓글 목록 출력
    @GetMapping("/list/{bno}")
    public List<CommunityReplyDTO> getReplyList(@PathVariable("bno") Long communityPostId){
        log.info("컨트롤러까지는 옴");
        List<CommunityReplyDTO> communityReplyDTOList = communityReplyService.findAllByCommunityId(communityPostId);
        return communityReplyDTOList;
    }


    //  댓글 등록
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/add", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> add(@RequestBody CommunityReplyDTO communityReplyDTO, HttpServletRequest request, Model model) throws UnsupportedEncodingException{
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        model.addAttribute("userId", userId);


        CommunityReply communityReply = communityReplyDTO.toEntity();
        communityReply.setUser(userRepository.findById(userId).get());
        communityReply.setCommunityPost(communityPostRepository.findById(communityReplyDTO.getCommunityPostId()).get());
        communityReplyService.add(communityReplyDTO);

        return new ResponseEntity<>(new String("register success".getBytes(), "UTF-8"), HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/delete-reply/{communityReplyId}")
    public String remove(@PathVariable("communityReplyId") Long communityReplyId){
        log.info("들어옴");
        communityReplyService.delete(communityReplyId);
        return "delete success";
    }
}
