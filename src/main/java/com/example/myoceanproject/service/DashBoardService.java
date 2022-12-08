package com.example.myoceanproject.service;

import com.example.myoceanproject.domain.AskDTO;
import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.CommunityReplyDTO;
import com.example.myoceanproject.repository.ask.AskRepositoryImpl;
import com.example.myoceanproject.repository.community.post.CommunityPostRepositoryImpl;
import com.example.myoceanproject.repository.community.reply.CommunityReplyRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashBoardService {
    private final CommunityReplyRepositoryImpl replyRepositoryImpl;
    private final CommunityPostRepositoryImpl postRepositoryImpl;

    private final AskRepositoryImpl askRepositoryImpl;

    public CommunityReplyDTO showReply() {
        return replyRepositoryImpl.findAllByDashboard();
    }
    public CommunityPostDTO showPost() {
        return postRepositoryImpl.findAllByDashboard();
    }

    public AskDTO showAsk() {
        return askRepositoryImpl.findAllByDashboard();
    }


}
