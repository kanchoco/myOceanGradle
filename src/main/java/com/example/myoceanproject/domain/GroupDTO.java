package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.type.GroupLocationType;
import com.example.myoceanproject.type.GroupStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class GroupDTO {

    private String groupName;
    private String groupCategory;
    private String groupContent;
    private int groupPoint;
    private String groupLocation;
    private GroupLocationType groupLocationType;
    private GroupStatus groupStatus;
    private String filePath;
    private String fileOriginName;


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