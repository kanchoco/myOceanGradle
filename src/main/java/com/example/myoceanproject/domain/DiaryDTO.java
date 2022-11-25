package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Diary;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class DiaryDTO {

    private User user;
    private String diaryTitle;
    private String diaryContent;
    private User receiverUser;


    public Diary toEntity() {
        return Diary.builder()
                .diaryTitle(diaryTitle)
                .diaryContent(diaryContent)
                .receiverUser(receiverUser)
                .build();
    }
}
