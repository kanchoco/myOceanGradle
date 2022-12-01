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

import static com.example.myoceanproject.embeddable.QGroupTime.groupTime;

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
    private String groupFileName;

    private Long groupFileUuid;
    private Long groupFileSize;

//    임베드 타입 가져옴(이렇게 가져오는 것이 맞는지는 불확실함. 생성자와 toEntity에도 추가함)
    private Integer maxMember;
    private Integer minMember;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    @QueryProjection
    public GroupDTO(Long userId, String userNickName, String groupName, String groupCategory, String groupContent, int groupPoint, String groupLocation, GroupLocationType groupLocationType, GroupStatus groupStatus, String groupFilePath, String groupFileName, Long groupFileUuid, Long groupFileSize, Integer maxMember, Integer minMember, LocalDateTime startTime, LocalDateTime endTime) {
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
        this.groupFileName = groupFileName;
        this.groupFileUuid = groupFileUuid;
        this.groupFileSize = groupFileSize;
        this.maxMember = maxMember;
        this.minMember = minMember;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Group toEntity(){
        GroupMemberLimit groupMemberLimit = new GroupMemberLimit();
        GroupTime groupTime = new GroupTime();

        groupMemberLimit.setMaxMember(maxMember);
        groupMemberLimit.setMinMember(minMember);

        groupTime.setStartTime(startTime);
        groupTime.setEndTime(endTime);

        return Group.builder()
                .groupName(groupName)
                .groupCategory(groupCategory)
                .groupContent(groupContent)
                .groupPoint(groupPoint)
                .groupLocation(groupLocation)
                .groupLocationType(groupLocationType)
                .groupStatus(GroupStatus.WAITING)
                .groupMemberLimit(groupMemberLimit)
                .groupTime(groupTime)
                .groupName(groupName)
                .groupFileName(groupFileName)
                .groupFilePath(groupFilePath)
                .groupFileUuid(groupFileUuid)
                .groupFileSize(groupFileSize)
                .build();
    }
}