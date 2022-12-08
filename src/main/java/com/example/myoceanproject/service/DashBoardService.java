package com.example.myoceanproject.service;

import com.example.myoceanproject.domain.CommunityReplyDTO;
import com.example.myoceanproject.repository.community.reply.CommunityReplyRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashBoardService {
    private final CommunityReplyRepositoryImpl replyRepositoryImpl;

    public CommunityReplyDTO showReply() {
        return replyRepositoryImpl.findAllByDashboard();
    }

}
