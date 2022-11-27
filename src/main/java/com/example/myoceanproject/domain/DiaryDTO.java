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

    private User user;
    private String diaryTitle;
    private String diaryContent;
    private User receiverUser;

    @QueryProjection
    public DiaryDTO(User user, String diaryTitle, String diaryContent, User receiverUser) {
        this.user = user;
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
        this.receiverUser = receiverUser;
    }

    //  일기 작성 후 일기 제목, 내용, 수신인이 새롭게 저장된다.
    public Diary toEntity() {
        return Diary.builder()
                .diaryTitle(diaryTitle)
                .diaryContent(diaryContent)
                .receiverUser(receiverUser)
                .build();
    }
}
