package com.example.myoceanproject.entity;

import com.example.myoceanproject.embeddable.File;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_QUEST")
@Getter
@ToString
public class Quest extends Period{
    @Id
    @GeneratedValue
    private long questId; //PK
    private String questCategory;
    private String questName;
    private String questContent;
    private LocalDateTime questDeadLine;
    @Embedded
    private File file;

    @Builder
    public Quest(String questCategory, String questName, String questContent, LocalDateTime questDeadLine, File file) {
        this.questCategory = questCategory;
        this.questName = questName;
        this.questContent = questContent;
        this.questDeadLine = questDeadLine;
        this.file = file;
    }
}
