package com.example.myoceanproject.type;

public enum PointType {
    PAY("결제"),REFUND("환불");

    private String value;
    private PointType(String value)
    {
        this.value = value;
    }

    public String toString()
    {
        return this.value; //will return , or ' instead of COMMA or APOSTROPHE
    }
    //    value(한글) -> UserAccountStatus로
    public static PointType change(String value){
        PointType result = null;
        for(PointType category : PointType.values()){
            if(category.toString().equals(value)){
                result = category;
                break;
            }
        }
        return result;
    }
}
