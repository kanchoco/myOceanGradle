package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.Point;
import com.example.myoceanproject.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class PointDTO {

    private User user;
    private String pointAmountHistory;


    public Point toEntity(){
        return Point.builder()
                .pointAmountHistory(pointAmountHistory)
                .build();
    }
}
