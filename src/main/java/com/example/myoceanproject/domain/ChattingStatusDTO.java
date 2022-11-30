package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.GroupMember;
import com.example.myoceanproject.type.ReadStatus;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Data
@NoArgsConstructor
public class ChattingStatusDTO {

    private Long ChattingStatusId;
    private GroupMember receiverGroupMember;
    private ReadStatus readStatus;
}
