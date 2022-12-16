package com.example.myoceanproject.service;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.DiaryDTO;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.entity.Diary;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.DiaryRepository;
import com.example.myoceanproject.repository.DiaryRepositoryImpl;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.CommunityCategory;
import com.example.myoceanproject.type.DiaryCategory;
import com.sun.istack.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class DiaryService {

    @Autowired
    private DiaryRepositoryImpl diaryRepositoryImpl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    public Page<DiaryDTO> showDiary(Pageable pageable, Long userId, Criteria criteria){
        return criteria.getKeyword() == null ? diaryRepositoryImpl.findAllByUserId(pageable, userId) : diaryRepositoryImpl.findAllByUserId(pageable, userId,criteria);
    }
    public Page<DiaryDTO> showExchangeDiary(Pageable pageable, Long userId, Criteria criteria){
        return criteria.getKeyword() == null ? diaryRepositoryImpl.findAllByUserIdExchange(pageable, userId) : diaryRepositoryImpl.findAllByUserIdExchange(pageable, userId,criteria);
    }

    //  게시글 등록
    public Page<DiaryDTO> showSelectDiarys(Pageable pageable, List<String> dateData, Long userId,Criteria criteria) {
        return criteria.getKeyword().equals("null") ? diaryRepositoryImpl.findAllByDiaryDuration(pageable,dateData,userId) : diaryRepositoryImpl.findAllByDiaryDuration(pageable,dateData,userId,criteria);
    }
    //  게시글 등록
    public Page<DiaryDTO> showFirstDiarys(Pageable pageable,Long userId,Criteria criteria) {
        return criteria.getKeyword().equals("null") ? diaryRepositoryImpl.findAllByDiaryByUser(pageable,userId) : diaryRepositoryImpl.findAllByDiaryByUser(pageable,userId,criteria);
    }
    //  게시글 등록
    public int registerShareDiary(Long userId,DiaryCategory diaryCategory) {
        return diaryRepositoryImpl.registerReceiverByUser(userId,diaryCategory);
    }

    public DiaryDTO searchBeforeShareWriter(DiaryCategory diaryCategory){
        return diaryRepositoryImpl.findBeforeShareWriter(diaryCategory);
    }

    public UserDTO findReceiverNullUser(Long userId){
        return diaryRepositoryImpl.findByUserId(userId);
    }

    public int checkSameUser(Long userId,DiaryCategory diaryCategory){
        return diaryRepositoryImpl.checkSameUser(userId,DiaryCategory.OPENDIARY);
    }
    //    public int searchMyDiaryCount(Long userId,DiaryCategory diaryCategory){
//        return diaryRepositoryImpl.checkSameUser(userId);
//    }
    public List<DiaryDTO> todayWriteDiaryDuplicateCheck(Long userId,DiaryCategory diaryCategory) {
        return diaryRepositoryImpl.checkTodayWriteDiary(userId, diaryCategory);
    }

    public String determineSaveOrExDiary(Long userId,DiaryDTO diaryDTO){
        Long randomReceiver=0L;
        UserDTO selectedReceiver=null;

        Diary diary=diaryDTO.toEntity();
        User users=userRepository.findById(userId).get();
        diary.setUser(users);
        log.info("diaryDTO:"+diaryDTO);
        log.info("diary:"+diary);

        String todayWriteDate=LocalDateTime.now().toLocalDate().toString();
        List<DiaryDTO> selectDiaryDTOS=todayWriteDiaryDuplicateCheck(userId,DiaryCategory.OPENDIARY);
        for(DiaryDTO selectDiary:selectDiaryDTOS){
            log.info("selectDiary:"+selectDiary.getCreateDate());
            log.info("selectDiary substring:"+selectDiary.getCreateDate().substring(0,10));
        }

        int receiverNullSearchNum=registerShareDiary(userId,DiaryCategory.change(diaryDTO.getDiaryCategory()));
//        int searchMyDiaryDuplicate=diaryService.searchMyDiaryCount(userId,DiaryCategory.change(diaryDTO.getDiaryCategory()));
        int checkSameUser=checkSameUser(userId,DiaryCategory.OPENDIARY);

        if(diaryDTO.getDiaryCategory().equals("나의 일기")){
            log.info("나의 일기 들어옴");
            User myDiary = userRepository.findById(userId).get();
            Diary myDiaryRegist = diaryDTO.toEntity();
            myDiaryRegist.setUser(myDiary);
            diaryRepository.save(myDiaryRegist);
            return "MyDiary";
        }else if(diaryDTO.getDiaryCategory().equals("교환 일기")){
            log.info("교환 일기 들어옴");

            log.info("receiverNullSearchNum:"+receiverNullSearchNum);
            log.info("checkSameUser:"+checkSameUser);

//      유저가 처음 교환일기를 등록하는 부분
            if (receiverNullSearchNum == 0 && checkSameUser == 0) {
                log.info("유저가 처음 교환일기를 등록하는 부분");
                log.info("firstRegisterUser");
                log.info("diary:" + diary);
                diaryRepository.save(diary);
                return "standby";
            }
//      동일한 유저가 교환 일기를 당일 작성후 재작성시 처리 로직
            else if (receiverNullSearchNum == 0 && checkSameUser == 1) {
                log.info("동일한 유저가 교환 일기를 당일 작성후 재작성시 부분");

//              db에 저장되어있는 회원의 교환일기 작성 목록을 카운트하기 위한 변수
                int checkDuplicate=0;

//              db에 저장되어있는 회원의 교환일기 목록 불러오기
                List<DiaryDTO> searchDiaryCreateDates=todayWriteDiaryDuplicateCheck(userId,DiaryCategory.OPENDIARY);

//              회원이 작성한 시점의 날짜를 포맷에 맞추어 변환후 db의 작성 날짜와 비교
                for(DiaryDTO selectDiary:searchDiaryCreateDates){
                    if(selectDiary.getCreateDate().substring(0,10).equals(todayWriteDate)){
//                      같으면 1증가
                        checkDuplicate++;
                    }
                    log.info("checkDuplicate:"+checkDuplicate);
                    log.info("db date:"+selectDiary.getCreateDate().substring(0,10));
                }
//              같은 일자가 있다면
                if(checkDuplicate!=0){
                    return "alreadyRegisterToday";
                }
//              같은 일자가 없다면
                else{
                    Diary otherDayExDiary=diaryDTO.toEntity();
                    User otherDayWriteExDiary=userRepository.findById(userId).get();
                    otherDayExDiary.setUser(otherDayWriteExDiary);
                    diaryRepository.save(otherDayExDiary);
                    return "otherDayWriteExDiary";
                }
            }
//      처음 일기를 등록한 사람이 있으며 다른 회원이 교환일기 신청시 교환되게 하는 부분
            else if (receiverNullSearchNum == 1 && checkSameUser == 0) {
                log.info("처음 일기를 등록한 사람이 있으며 다른 회원이 교환일기 신청시 교환되게 하는 부분");
//          이전에 교환일기를 신청한 회원의 정보에 현재 교환일기를 요청한 회원의 번호(receverId)를 저장한다.
                randomReceiver =searchBeforeShareWriter(DiaryCategory.OPENDIARY).getUserId();
//                log.info("randomReceiver:"+randomReceiver);
                Diary diary1=diaryRepository.findByUser_UserIdAndDiaryCategoryAndReceiverUserIsNull(randomReceiver,DiaryCategory.OPENDIARY).get();
                diary1.setReceiverUser(userRepository.findById(userId).get());

                log.info("after diary1:"+diary1);

//              교환일기를 신청한 회원의 정보를 저장한다.userId
                User requestExDiaryUser=userRepository.findById(userId).get();
                log.info("requestExDiaryUser:"+requestExDiaryUser);



                User responseExDiaryUser=userRepository.findById(diary1.getUser().getUserId()).get();
                log.info("responseExDiaryUser:"+responseExDiaryUser);


                Diary diary2 = diaryDTO.toEntity();
                log.info("diary2:"+diary2);


                diary2.setUser(requestExDiaryUser);
                log.info("diary2.setUser:"+diary2);


                diary2.setReceiverUser(responseExDiaryUser);
                log.info("diary2.setReceiverUser:"+diary2);


                diaryRepository.save(diary2);
                return "exchangeDiary";
            } else {
                log.info("??????????????????????????????????");
                log.info("other");
            }
        }
        return "communityIndex";
    }
}
