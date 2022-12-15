package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Diary;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@ToString
public class DiaryDTO {

    private Long userId;

    private String diaryTitle;
    private String diaryContent;
    private Long receiverUserId;

    private String createDate;
    private String updateDate;

//    private LocalDateTime localDateTime;

    private List<DiaryDTO> diaryList;

    private int endPage;

    @QueryProjection
    public DiaryDTO(Long userId, String diaryTitle, String diaryContent, Long receiverUserId, LocalDateTime createDate, LocalDateTime updateDate) {
        this.userId = userId;
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
        this.receiverUserId = receiverUserId;
        this.createDate=createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.updateDate=updateDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        this.localDateTime=localDateTime;
    }

    //  일기 작성 후 일기 제목, 내용, 수신인이 새롭게 저장된다.
    public Diary toEntity() {
        return Diary.builder()
                .diaryTitle(diaryTitle)
                .diaryContent(diaryContent)
//                .localDateTime(localDateTime)
                .build();
    }
}
