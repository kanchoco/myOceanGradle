package com.example.myoceanproject.domain;

import com.example.myoceanproject.type.UserAccountStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Criteria {
    private int page;
    private String keyword;
    private String status;

}






















