package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Chatting;
import com.example.myoceanproject.entity.ChattingStatus;
import com.example.myoceanproject.entity.GroupMember;
import com.example.myoceanproject.type.ReadStatus;
import com.querydsl.core.annotations.QueryProjection;
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

    @QueryProjection
    public ChattingStatusDTO(Long chattingStatusId, GroupMember receiverGroupMember, ReadStatus readStatus) {
        ChattingStatusId = chattingStatusId;
        this.receiverGroupMember = receiverGroupMember;
        this.readStatus = readStatus;
    }

    public ChattingStatus toEntity(){
        return ChattingStatus.builder()
                .readStatus(readStatus)
                .build();
    }
}
