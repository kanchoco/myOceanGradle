package com.example.myoceanproject.repository.quest;

import com.example.myoceanproject.domain.QuestDTO;
import com.example.myoceanproject.entity.CommunityLike;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.entity.Quest;
import com.example.myoceanproject.repository.community.like.CommunityLikeCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.myoceanproject.entity.QCommunityLike.communityLike;

@Repository
@RequiredArgsConstructor
public class QuestRepositoryImpl implements QuestCustomRepository {
//사용자 지정 레파지토리 Impl(구현)

    private final JPAQueryFactory queryFactory;

    @Override
    public Quest saveQuest(QuestDTO questDTO){
        Quest quest = questDTO.toEntity();
        quest.update(questDTO);
        return quest;
    }

}
