package com.example.myoceanproject.type;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum QuestType {
    BASIC("일반"), TODAY("오늘의 퀘스트"), EVENT("이벤트");
    private String value;
    private QuestType(String value)
    {
        this.value = value;
    }

    public String toString()
    {
        return this.value; //will return , or ' instead of COMMA or APOSTROPHE
    }

}
