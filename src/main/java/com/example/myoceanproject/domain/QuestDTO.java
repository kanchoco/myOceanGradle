package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Quest;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class QuestDTO {

    private String questCategory;
    private String questName;
    private String questContent;
    private LocalDateTime questDeadLine;

    private String questFilePath;
    private String questFileName;

    private String questFileUuid;
    private Long questFileSize;

    @QueryProjection
    public QuestDTO(String questCategory, String questName, String questContent, LocalDateTime questDeadLine, String questFilePath, String questFileName, String questFileUuid, Long questFileSize) {
        this.questCategory = questCategory;
        this.questName = questName;
        this.questContent = questContent;
        this.questDeadLine = questDeadLine;
        this.questFilePath = questFilePath;
        this.questFileName = questFileName;
        this.questFileUuid = questFileUuid;
        this.questFileSize = questFileSize;
    }

    public Quest toEntity() {
        return Quest.builder()
                .questCategory(questCategory)
                .questName(questName)
                .questDeadLine(questDeadLine)
                .questContent(questContent)
                .questFilePath(questFilePath)
                .questFileName(questFileName)
                .questFileUuid(questFileUuid)
                .questFileSize(questFileSize)
                .build();
    }
}
