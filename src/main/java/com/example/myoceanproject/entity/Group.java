package com.example.myoceanproject.entity;

import com.example.myoceanproject.embeddable.GroupMemberLimit;
import com.example.myoceanproject.embeddable.GroupTime;
import com.example.myoceanproject.type.GroupLocationType;
import com.example.myoceanproject.type.GroupStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_GROUP")
@Getter
@ToString(exclude = "user")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class Group extends Period{

    @Id
    @GeneratedValue
    private Long groupId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user; //FK
    @NotNull
    private String groupName;
    @NotNull//직접 입력임. 선택x
    private String groupContent;
    @NotNull
    private String groupCategory;
    @NotNull
    private int groupPoint;
    private String groupLocation;
    @NotNull
    @Enumerated(EnumType.STRING)
    private GroupLocationType groupLocationType; //Enum
    @NotNull
    @Enumerated(EnumType.STRING)
    private GroupStatus groupStatus; // Enum


    @Embedded
    private GroupMemberLimit groupMemberLimit;

    @Embedded
    private GroupTime groupTime;

    
//  양방향
    public void changeUser(User user){
        this.user = user;
        user.getGroups().add(this);
    }

    @Builder
    public Group(String groupName, String groupCategory, String groupContent, int groupPoint, String groupLocation, GroupLocationType groupLocationType, GroupStatus groupStatus, GroupMemberLimit groupMemberLimit, GroupTime groupTime) {
        this.groupName = groupName;
        this.groupCategory = groupCategory;
        this.groupContent = groupContent;
        this.groupPoint = groupPoint;
        this.groupLocation = groupLocation;
        this.groupLocationType = groupLocationType;
        this.groupStatus = groupStatus;
        this.groupMemberLimit = groupMemberLimit;
        this.groupTime = groupTime;
    }

//  모임의 경우 모든 내용을 수정할 수 있기 때문에 Builder와 동일하게 update가 들어간다.
//  파일의 경우, 삭제 후 추가기 때문에 update에 포함되지 않는다.
    public void update(String groupName, String groupCategory, String groupContent, int groupPoint, String groupLocation, GroupLocationType groupLocationType, GroupStatus groupStatus, GroupMemberLimit groupMemberLimit, GroupTime groupTime) {
        this.groupName = groupName;
        this.groupCategory = groupCategory;
        this.groupContent = groupContent;
        this.groupPoint = groupPoint;
        this.groupLocation = groupLocation;
        this.groupLocationType = groupLocationType;
        this.groupStatus = groupStatus;
        this.groupMemberLimit = groupMemberLimit;
        this.groupTime = groupTime;
    }
}
