package com.example.myoceanproject.domain;

import com.example.myoceanproject.embeddable.GroupMemberLimit;
import com.example.myoceanproject.embeddable.GroupTime;
import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.entity.GroupSchedule;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.type.GroupLocationType;
import com.example.myoceanproject.type.GroupStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Data
@NoArgsConstructor
public class GroupDTO {

    private Long groupId;
    private Long userId;
    private String userNickName;
    private String groupName;
    private String groupCategory;
    private String groupContent;
    private int groupPoint;
    private String groupOverSea;
    private String groupLocationName;
    private String groupLocation;
    private String groupLocationDetail;
    private String groupParkingAvailable;
    private String groupMoreInformation;
    private GroupLocationType groupLocationType;
    private GroupStatus groupStatus;
    private String groupFilePath;
    private String groupFileName;

    private String groupFileUuid;
    private Long groupFileSize;

//    임베드 타입
    private Integer maxMember;
    private Integer minMember;


    @QueryProjection
    public GroupDTO(Long groupId, Long userId, String userNickName, String groupName, String groupCategory, String groupContent, int groupPoint, String groupOverSea, String groupLocationName, String groupLocation, String groupLocationDetail, String groupParkingAvailable, String groupMoreInformation, GroupLocationType groupLocationType, GroupStatus groupStatus, String groupFilePath, String groupFileName, String groupFileUuid, Long groupFileSize, Integer maxMember, Integer minMember) {
        this.groupId = groupId;
        this.userId = userId;
        this.userNickName = userNickName;
        this.groupName = groupName;
        this.groupCategory = groupCategory;
        this.groupContent = groupContent;
        this.groupPoint = groupPoint;
        this.groupOverSea = groupOverSea;
        this.groupLocationName = groupLocationName;
        this.groupLocation = groupLocation;
        this.groupLocationDetail = groupLocationDetail;
        this.groupParkingAvailable = groupParkingAvailable;
        this.groupMoreInformation = groupMoreInformation;
        this.groupLocationType = groupLocationType;
        this.groupStatus = groupStatus;
        this.groupFilePath = groupFilePath;
        this.groupFileName = groupFileName;
        this.groupFileUuid = groupFileUuid;
        this.groupFileSize = groupFileSize;
        this.maxMember = maxMember;
        this.minMember = minMember;
    }

    public Group toEntity(){
        GroupMemberLimit groupMemberLimit = new GroupMemberLimit();

        groupMemberLimit.setMaxMember(maxMember);
        groupMemberLimit.setMinMember(minMember);


        return Group.builder()
                .groupName(groupName)
                .groupCategory(groupCategory)
                .groupContent(groupContent)
                .groupPoint(groupPoint)
                .groupOverSea(groupOverSea)
                .groupLocationName(groupLocationName)
                .groupLocation(groupLocation)
                .groupLocationDetail(groupLocationDetail)
                .groupParkingAvailable(groupParkingAvailable)
                .groupMoreInformation(groupMoreInformation)
                .groupLocationType(groupLocationType)
                .groupStatus(GroupStatus.WAITING)
                .groupMemberLimit(groupMemberLimit)
                .groupName(groupName)
                .groupFileName(groupFileName)
                .groupFilePath(groupFilePath)
                .groupFileUuid(groupFileUuid)
                .groupFileSize(groupFileSize)
                .build();
    }
}