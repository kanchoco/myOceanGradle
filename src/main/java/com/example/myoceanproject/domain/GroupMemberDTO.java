package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.entity.GroupMember;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class GroupMemberDTO {

    private User user;
    private Group group;

    @QueryProjection
    public GroupMemberDTO(User user, Group group) {
        this.user = user;
        this.group = group;
    }
}
