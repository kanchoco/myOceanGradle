package com.example.myoceanproject.domain;

import com.example.myoceanproject.embeddable.File;
import com.example.myoceanproject.entity.Period;
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

//    private String filePath;
//    private String fileOriginName;

    @Embedded
    private File file;

    @QueryProjection
    public QuestDTO(String questCategory, String questName, String questContent, LocalDateTime questDeadLine, String filePath, String fileOriginName) {
        this.questCategory = questCategory;
        this.questName = questName;
        this.questContent = questContent;
        this.questDeadLine = questDeadLine;
//        this.file.setFilePath(filePath);
//        this.file.setFileOriginName(fileOriginName);
        this.file = file;
    }

    public Quest toEntity() {
        return Quest.builder()
                .questCategory(questCategory)
                .questName(questName)
                .questDeadLine(questDeadLine)
                .questContent(questContent)
//                .fileOriginName(file.getFileOriginName())
//                .filePath(file.getFilePath())
                .file(file)
                .build();
    }



}
