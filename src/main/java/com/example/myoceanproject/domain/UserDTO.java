package com.example.myoceanproject.domain;

import com.example.myoceanproject.entity.Period;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.type.UserAccountStatus;
import com.example.myoceanproject.type.UserLoginMethod;
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


    public User toEntity(){
        return User.builder()
                .userPassword(userPassword)
                .userNickname(userNickname)
                .userEmail(userEmail)
                .userLoginMethod(userLoginMethod)
                .build();
    }


}
