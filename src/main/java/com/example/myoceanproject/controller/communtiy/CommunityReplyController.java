package com.example.myoceanproject.controller.communtiy;

import com.example.myoceanproject.domain.CommunityReplyDTO;
import com.example.myoceanproject.service.community.CommunityPostService;
import com.example.myoceanproject.service.community.CommunityReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/reply/*")
public class CommunityReplyController {

    private final CommunityPostService communityPostService;
    private final CommunityReplyService communityReplyService;

//  댓글 목록 출력
    @GetMapping("/index")
    public String getReply(Long communityPostId, Model model){
        model.addAttribute("communityPostDTO", communityPostService.find(communityPostId));
        return "app/community/community_comment";
    }

}
