package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.type.UserAccountStatus;
import com.example.myoceanproject.type.UserLoginMethod;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@NoArgsConstructor
public class UserDTO {

    private String userEmail;
    private String userPassword;
    private String userNickname;

    private UserAccountStatus userAccountStatus;

    private UserLoginMethod userLoginMethod;
    private int totalPoint;
    private int userTotalPoint;
    private String userFileName;
    private String userFilePath;
    private Long userFileSize;
    private Long userFileUuid;

    @QueryProjection
    public UserDTO(String userEmail, String userPassword, String userNickname, UserAccountStatus userAccountStatus, UserLoginMethod userLoginMethod, int userTotalPoint) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userAccountStatus = userAccountStatus;
        this.userLoginMethod = userLoginMethod;
        this.userTotalPoint = userTotalPoint;
    }

    public User toEntity(){
        return User.builder()
                .userPassword(userPassword)
                .userNickname(userNickname)
                .userEmail(userEmail)
                .userLoginMethod(userLoginMethod)
                .userAccountStatus(userAccountStatus)
                .userTotalPoint(userTotalPoint)
                .build();
    }


}
