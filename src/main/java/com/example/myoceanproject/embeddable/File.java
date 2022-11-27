package com.example.myoceanproject.embeddable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Setter
@NoArgsConstructor
public class File {
    private String filePath;
    private String fileOriginName;

    public void create(String filePath, String fileOriginName) {
        this.filePath = filePath;
        this.fileOriginName = fileOriginName;
    }

//    @Builder
//    public File(String filePath, String fileOriginName){
//        this.filePath = filePath;
//        this.fileOriginName = fileOriginName;
//    }
//
//    public void setFile(File file){
//        file.setFilePath(file.filePath);
//        file.setFileOriginName(file.fileOriginName);
//    }




}
