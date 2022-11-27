package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.type.GroupLocationType;
import com.example.myoceanproject.type.GroupStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class GroupDTO {

    private User user;
    private String groupName;
    private String groupCategory;
    private String groupContent;
    private int groupPoint;
    private String groupLocation;
    private GroupLocationType groupLocationType;
    private GroupStatus groupStatus;
    private String filePath;
    private String fileOriginName;

    @QueryProjection
    public GroupDTO(User user, String groupName, String groupCategory, String groupContent, int groupPoint, String groupLocation, GroupLocationType groupLocationType, GroupStatus groupStatus, String filePath, String fileOriginName) {
        this.user = user;
        this.groupName = groupName;
        this.groupCategory = groupCategory;
        this.groupContent = groupContent;
        this.groupPoint = groupPoint;
        this.groupLocation = groupLocation;
        this.groupLocationType = groupLocationType;
        this.groupStatus = groupStatus;
        this.filePath = filePath;
        this.fileOriginName = fileOriginName;
    }



    public Group toEntity(){
        return Group.builder()
                .groupName(groupName)
                .groupCategory(groupCategory)
                .groupContent(groupContent)
                .groupPoint(groupPoint)
                .groupLocation(groupLocation)
                .groupLocationType(groupLocationType)
                .build();
    }
}