package com.example.myoceanproject.domain;

import com.example.myoceanproject.embeddable.File;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.Quest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class QuestDTO {

    private String questCategory;
    private String questName;
    private String questContent;
    private LocalDateTime questDeadLine;

    private File file;


    public Quest toEntity() {
        return Quest.builder()
                .questCategory(questCategory)
                .questName(questName)
                .questDeadLine(questDeadLine)
                .questContent(questContent)
                .file(file)
                .build();
    }
}
