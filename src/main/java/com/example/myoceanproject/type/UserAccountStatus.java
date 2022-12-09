package com.example.myoceanproject.type;

public enum UserAccountStatus {
//    정지 계정, 정상 계정
    ACTIVE("정상"), BANNED("정지");

    private String value;
    private UserAccountStatus(String value)
    {
        this.value = value;
    }

    public String toString()
    {
        return this.value; //will return , or ' instead of COMMA or APOSTROPHE
    }

    public static String change(String value){
        for(UserAccountStatus status : UserAccountStatus.values()){
            status.equals(value);
            value = status.name();
            break;
        }
        return value;
    }

}
