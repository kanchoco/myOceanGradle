package com.example.myoceanproject.embeddable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class GroupTime {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
