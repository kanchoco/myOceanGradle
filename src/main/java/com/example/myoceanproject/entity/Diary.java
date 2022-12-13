package com.example.myoceanproject.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_DIARY")
@Getter
@ToString(exclude = {"user", "receiverUser"})
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class Diary extends Period{
    @Id
    @GeneratedValue
    private Long diaryId; //PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @NotNull
    private User user;
    @NotNull
    private String diaryTitle;
    @NotNull
    private String diaryContent;
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn
    private User receiverUser; //아직 받을 사람이 없을 수도 있기 때문에 NotNull이 아님

    @NotNull
    private LocalDateTime localDateTime;


//  양방향
    public void setUser(User user){
        this.user = user;
    }
//  양방향
    public void setReceiverUser(User receiverUser){
        this.receiverUser = receiverUser;
    }
    
    @Builder
    public Diary(String diaryTitle, String diaryContent, User receiverUser,LocalDateTime localDateTime) {
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
        this.receiverUser = receiverUser;
        this.localDateTime=localDateTime;
    }
    
//  일기는 한 번 쓰면 수정이 불가하기 때문에 update가 없음 
    
}
