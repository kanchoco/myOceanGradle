package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Ask;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.type.AskCategory;
import com.example.myoceanproject.type.AskStatus;
import com.example.myoceanproject.type.ReadStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Data
@NoArgsConstructor
public class AskDTO {
    private Long userId;//유저가 null일 경우 자주 묻는 질문


    private AskStatus askStatus;
    private String askTitle;
    private String askContent;
    private AskCategory askCategory;

    private String createDate;
    private String updateDate;

    @QueryProjection
    public AskDTO(Long userId, AskStatus askStatus, String askTitle, String askContent, AskCategory askCategory,LocalDateTime createDate, LocalDateTime updateDate) {
        this.userId=userId;
        this.askStatus = askStatus;
        this.askTitle = askTitle;
        this.askContent = askContent;
        this.askCategory = askCategory;
        this.createDate=createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.updateDate=updateDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    //  문의사항 작성 시 작성한 유저의 정보, 문의글 제목, 내용, 질문 카테고리가 처음으로 저장된다.
    //  askCategory는 변경될 수 있기 때문에 update에 포함되지만, 현재 화면에서도 처음으로 저장되기 때문에 toEntity에도 포함된다.
    public Ask toEntity(){
        return Ask.builder()
                .askStatus(askStatus)
                .askTitle(askTitle)
                .askContent(askContent)
                .askCategory(askCategory)
                .build();
    }
}
