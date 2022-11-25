package com.example.myoceanproject.entity;

import com.example.myoceanproject.embeddable.File;
import com.example.myoceanproject.type.GroupLocationType;
import com.example.myoceanproject.type.GroupStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_GROUP")
@Getter
@ToString(exclude = "user")
public class Group extends Period{

    @Id
    @GeneratedValue
    private Long groupId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user; //FK
    private String groupName;
    private String groupCategory; //직접 입력임. 선택x
    private String groupContent;
    private int groupPoint;
    private String groupLocation;
    private GroupLocationType groupLocationType; //Enum
    private GroupStatus groupStatus; // Enum
    @Embedded
    private File file; //썸네일



//    public void create(Long groupId, User user, String groupName, String groupCategory, String groupContent, int groupPoint, String groupLocation, GroupLocationType groupLocationType, GroupStatus groupStatus, File file) {
//        this.groupId = groupId;
//        this.user = user;
//        this.groupName = groupName;
//        this.groupCategory = groupCategory;
//        this.groupContent = groupContent;
//        this.groupPoint = groupPoint;
//        this.groupLocation = groupLocation;
//        this.groupLocationType = groupLocationType;
//        this.groupStatus = groupStatus;
//        this.file = file;
//    }
//    public void changeUser(User user){
//        this.user = user;
//        user.getGroups().add(this);
//    }

    @Builder
    public Group(User user, String groupName, String groupCategory, String groupContent, int groupPoint, String groupLocation, GroupLocationType groupLocationType, GroupStatus groupStatus, File file) {
        this.user = user;
        this.groupName = groupName;
        this.groupCategory = groupCategory;
        this.groupContent = groupContent;
        this.groupPoint = groupPoint;
        this.groupLocation = groupLocation;
        this.groupLocationType = groupLocationType;
        this.groupStatus = groupStatus;
        this.file = file;
    }
}
