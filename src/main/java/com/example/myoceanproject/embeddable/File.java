package com.example.myoceanproject.embeddable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class File {
    private String filePath;
    private String fileOriginName;

    public void create(String filePath, String fileOriginName) {
        this.filePath = filePath;
        this.fileOriginName = fileOriginName;
    }
}
