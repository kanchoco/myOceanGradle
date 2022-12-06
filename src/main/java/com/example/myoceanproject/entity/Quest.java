package com.example.myoceanproject.entity;

import com.example.myoceanproject.domain.QuestDTO;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_QUEST")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quest extends Period{
    @Id
    @GeneratedValue
    private long questId; //PK
    @NotNull
    private String questCategory;
    @NotNull
    private String questName;
    @NotNull
    private String questContent;
    @NotNull
    private String questPoint;
    private LocalDateTime questDeadLine;
    private String questFileName;
    private String questFilePath;
    private String questFileUuid;
    private Long questFileSize;

    @Builder
    public Quest(String questCategory, String questName, String questContent,int questPoint, LocalDateTime questDeadLine, String questFileName, String questFilePath, Long questFileSize, String questFileUuid) {
        this.questCategory = questCategory;
        this.questName = questName;
        this.questContent = questContent;
        this.questPoint = questPoint;
        this.questDeadLine = questDeadLine;
        this.questFileName = questFileName;
        this.questFilePath = questFilePath;
        this.questFileUuid = questFileUuid;
        this.questFileSize = questFileSize;
    }
    public void update(QuestDTO questDTO) {
        this.questCategory = questDTO.getQuestCategory();
        this.questName = questDTO.getQuestName();
        this.questContent = questDTO.getQuestContent();
        this.questDeadLine = LocalDateTime.parse(questDTO.getQuestDeadLine());
    }
}
