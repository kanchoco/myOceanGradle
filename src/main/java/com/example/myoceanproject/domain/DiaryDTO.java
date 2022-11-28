package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Diary;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@ToString
public class DiaryDTO {

    private Long userId;

    private String diaryTitle;
    private String diaryContent;
    private Long receiverUserId;

    @QueryProjection
    public DiaryDTO(Long userId, String diaryTitle, String diaryContent, Long receiverUserId) {
        this.userId = userId;
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
        this.receiverUserId = receiverUserId;
    }

    //  일기 작성 후 일기 제목, 내용, 수신인이 새롭게 저장된다.
    public Diary toEntity() {
        return Diary.builder()
                .diaryTitle(diaryTitle)
                .diaryContent(diaryContent)
                .build();
    }
}
