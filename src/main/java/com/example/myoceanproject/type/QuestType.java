package com.example.myoceanproject.type;


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
