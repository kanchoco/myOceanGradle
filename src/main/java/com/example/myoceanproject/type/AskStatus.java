package com.example.myoceanproject.type;

public enum AskStatus {
    WAITING("답변대기"), COMPLETE("답변완료");

    private String value;
    private AskStatus(String value)
    {
        this.value = value;
    }

    public String toString()
    {
        return this.value; //will return , or ' instead of COMMA or APOSTROPHE
    }
}
