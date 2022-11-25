package com.example.myoceanproject.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_DIARY")
@Getter
@ToString(exclude = {"senderUser", "receiverUser"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private User receiverUser;

//    public void create(Long diaryId, User user, String diaryTitle, String diaryContent, User receiverUser) {
//        this.diaryId = diaryId;
//        this.user = user;
//        this.diaryTitle = diaryTitle;
//        this.diaryContent = diaryContent;
//        this.receiverUser = receiverUser;
//    }


    public void changeUser(User user){
        this.user = user;
        user.getDiaries().add(this);
    }
    public void changeReceiverUser(User receiverUser){

        this.receiverUser = receiverUser;
        receiverUser.getDiaries().add(this);
    }

    @Builder
    public Diary(String diaryTitle, String diaryContent, User receiverUser) {
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
        this.receiverUser = receiverUser;
    }
}
