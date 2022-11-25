package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Ask;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.type.AskCategory;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class AskDTO {
    private User user;//유저가 null일 경우 자주 묻는 질문
    private String askTitle;
    private String askContent;
    private AskCategory askCategory;

//  문의사항 작성 시 작성한 유저의 정보, 문의글 제목, 내용, 질문 카테고리가 처음으로 저장된다.
//  askStatus는 디폴트로 값이 주어지기 때문에, 처음으로 저장되지 않고 update만 진행된다.
//  askCategory는 변경될 수 있기 때문에 update에 포함되지만, 현재 화면에서도 처음으로 저장되기 때문에 toEntity에도 포함된다.
    public Ask toEntity(){
        return Ask.builder()
                .user(user)
                .askTitle(askTitle)
                .askContent(askContent)
                .askCategory(askCategory)
                .build();
    }
}
