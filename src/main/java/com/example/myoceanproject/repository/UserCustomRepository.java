package com.example.myoceanproject.repository;

// 유저 엔티티 사용자 지정 레포지토리
public interface UserCustomRepository {
    public int findCountByemail(String email);
}
