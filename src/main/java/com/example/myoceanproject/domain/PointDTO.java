package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.Point;
import com.example.myoceanproject.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class PointDTO {

    private Long userId;
    private String groupName;
    private int pointAmountHistory;

    private LocalDateTime createDate;

    @QueryProjection
    public PointDTO(Long userId, String groupName, int pointAmountHistory, LocalDateTime createDate) {
        this.userId = userId;
        this.groupName = groupName;
        this.pointAmountHistory = pointAmountHistory;
        this.createDate = createDate;
    }

    //  포인트의 변화 추이가 계속 새롭게 저장이 된다.
    public Point toEntity(){
        return Point.builder()
                .pointAmountHistory(pointAmountHistory)
                .build();
    }
}
