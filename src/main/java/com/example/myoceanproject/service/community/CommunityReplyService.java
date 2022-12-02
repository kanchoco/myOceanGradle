package com.example.myoceanproject.service.community;

import com.example.myoceanproject.domain.CommunityReplyDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.repository.community.reply.CommunityReplyRepository;
import com.example.myoceanproject.repository.community.reply.CommunityReplyRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommunityReplyService {
    @Autowired
    private CommunityReplyRepositoryImpl replyRepositoryImpl;
    @Autowired
    private CommunityReplyRepository replyRepository;

    public Page<CommunityReplyDTO> showReply(Pageable pageable, Criteria criteria){
        return criteria.getKeyword() == null ? replyRepositoryImpl.findAll(pageable) : replyRepositoryImpl.findAll(pageable,criteria);
    }
//    고민상담용
    public Page<CommunityReplyDTO> showReplyByCounseling(Pageable pageable, Criteria criteria){
        return criteria.getKeyword() == null ? replyRepositoryImpl.findAllByCategory(pageable) : replyRepositoryImpl.findAllByCategory(pageable,criteria);
    }

    public void remove(Long communityReplyId){
        replyRepository.deleteById(communityReplyId);
    }

}
