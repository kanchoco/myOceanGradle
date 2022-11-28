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

    private Long userId;
    private String userNickName;

    private String groupName;
    private String groupCategory;
    private String groupContent;
    private int groupPoint;
    private String groupLocation;
    private GroupLocationType groupLocationType;
    private GroupStatus groupStatus;
    private String groupFilePath;
    private String groupFileOriginName;

    private String groupFileUuid;
    private Long groupFileSize;

    @QueryProjection
    public GroupDTO(Long userId, String userNickName, String groupName, String groupCategory, String groupContent, int groupPoint, String groupLocation, GroupLocationType groupLocationType, GroupStatus groupStatus, String groupFilePath, String groupFileOriginName, String groupFileUuid, Long groupFileSize) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.groupName = groupName;
        this.groupCategory = groupCategory;
        this.groupContent = groupContent;
        this.groupPoint = groupPoint;
        this.groupLocation = groupLocation;
        this.groupLocationType = groupLocationType;
        this.groupStatus = groupStatus;
        this.groupFilePath = groupFilePath;
        this.groupFileOriginName = groupFileOriginName;
        this.groupFileUuid = groupFileUuid;
        this.groupFileSize = groupFileSize;
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