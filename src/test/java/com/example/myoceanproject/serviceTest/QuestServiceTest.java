package com.example.myoceanproject.serviceTest;

import com.example.myoceanproject.domain.QuestDTO;
import com.example.myoceanproject.entity.QuestAchievement;
import com.example.myoceanproject.repository.quest.QuestAchievementRepositoryImpl;
import com.example.myoceanproject.service.quest.QuestAchievementService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
@Slf4j
public class QuestServiceTest {
    @Autowired
    private QuestAchievementService questAchievementService;

    @Autowired
    private QuestAchievementRepositoryImpl questAchievementRepositoryImpl;

    @Test
    public void showTest(){
        List<QuestDTO> questDTOS = questAchievementService.showMyAchievement(2L);
        questDTOS.stream().map(QuestDTO::toString).forEach(log::info);
    }

    @Test
    public void showRepositoryTest(){
        List<QuestDTO> questDTOS = questAchievementRepositoryImpl.findQuestAchievementByUserId(2L);
        questDTOS.stream().map(QuestDTO::toString).forEach(log::info);
    }

}
