package com.example.myoceanproject.controller.myPage;

import com.example.myoceanproject.domain.QUserDTO;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Ejb3TransactionAnnotationParser;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Base64;

import static com.example.myoceanproject.entity.QUser.user;

@Controller
@RequestMapping("/myPage/*")
@Slf4j

public class MyPageController {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private UserRepository userRepository;

    // 마이 페이지
    @GetMapping("/index")
    public String myPage(){
        return "app/myPage/myPage";
    }

    //  마이페이지에서 변경 버튼 클릭시 정보 저장하는 비동기 요청
    @RequestMapping("/changeInfo")
    @ResponseBody
    @Transactional
    @Modifying
    public void changeInfo(@RequestBody String nickname, HttpServletRequest request){
        HttpSession session=request.getSession();
        session.setAttribute("userNickname",nickname);
        Long userid=(Long)session.getAttribute("userId");

        UserDTO userDTO=new UserDTO();
        userDTO.setUserTotalPoint(0);
        userDTO.setUserNickname("nickName");
        User users=jpaQueryFactory.selectFrom(user).where(user.userId.eq(userid)).fetchOne();

//      변경된 닉네임,파일 저장
        users.updateNicknameFile(userDTO);

    }
    // 비밀번호 변경 페이지
    @GetMapping("/passwordChange")
    public String passwordChange(){
        return "app/myPage/passwordChange";
    }

    //  입력된 비밀번호와 데이터베이스에 있는 비밀번호 비교를 위한 비동기 요청
    @RequestMapping("/confrimPassword")
    @ResponseBody
    public String confrimPassword(@RequestBody String oldPassword, HttpServletRequest request){
        HttpSession session=request.getSession();
        Long userIds=(Long)session.getAttribute("userId");

        log.info("userIds:"+userIds);
        UserDTO users=jpaQueryFactory.select(new QUserDTO(
                user.userId,
                user.userPassword,
                user.userNickname,
                user.userAccountStatus,
                user.userFileName,
                user.userFilePath,
                user.userFileSize,
                user.userFileUuid,
                user.userEmail,
                user.userLoginMethod,
                user.userTotalPoint,
                user.createDate,
                user.updatedDate,
                user.userOauthId
        )).from(user).where(user.userId.eq(userIds)).fetchOne();

        log.info("dbpassword"+users.getUserPassword());
        log.info("inputpassword"+encryption(oldPassword));

        if(users.getUserPassword().equals(encryption(oldPassword))){
            log.info("same");
            return "same";
        }else{
            log.info("different");
            return "different";
        }
    }

    //  비밀번호 변경하기 위한 비동기 요청
    @RequestMapping("/changePassword")
    @ResponseBody
    @Transactional
    @Modifying
    public void alterPassword(@RequestBody String newPassword, HttpServletRequest request){

        HttpSession session=request.getSession();
        Long userIds=(Long)session.getAttribute("userId");
        UserDTO userDTO=new UserDTO();
        userDTO.setUserTotalPoint(0);
        userDTO.setUserNickname("nickName");
        userDTO.setUserPassword(encryption(newPassword));
        User users=jpaQueryFactory.selectFrom(user).where(user.userId.eq(userIds)).fetchOne();

        users.updatePassword(userDTO);
    }

    public String encryption(String userPassword){
        String alg = "AES/CBC/PKCS5Padding";
        //키
        String aesKey = "abcdefghabcdefghabcdefghabcdefgh"; //32byte

        String aesIv = "0123456789abcdef"; //16byte

        //암호화 할 유저 패스워드
        String userPw =userPassword;

        //암호화된 유저 아이디
        String encPw="";


        //알고리즘 aes-256 **********[암호화]**********
        try {

            Cipher cipher = Cipher.getInstance(alg);

            //키로 비밀키 생성
            SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(), "AES");
            //iv 로 spec 생성
            IvParameterSpec ivParamSpec = new IvParameterSpec(aesIv.getBytes());
            //암호화 적용
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            //암호화 실행
            byte[] encrypted1 = cipher.doFinal(userPw.getBytes("UTF-8")); // ID 암호화(인코딩 설정)
            encPw = Base64.getEncoder().encodeToString(encrypted1); // 암호화 인코딩 후 저장

            System.out.println("암호화된 유저 아이디 -> " + encPw);

        }catch (Exception e) {
            System.out.println("암호화 중 오류 발생 ");
            e.printStackTrace();
        }
        //---------------------------------------------------------------------------
        return encPw;
    }

    public String decryption(String userPassword) {
        String alg = "AES/CBC/PKCS5Padding";
        //키
        String aesKey = "abcdefghabcdefghabcdefghabcdefgh"; //32byte

        String aesIv = "0123456789abcdef"; //16byte

        //복호화 할 유저 패스워드
        String encPw = userPassword;

        //복호화된 유저 패스워드
        String decPw = "";

        //----암호화 해석 코드 **********[복호화]**********
        try {
            Cipher cipher = Cipher.getInstance(alg);

            SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(aesIv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            //암호 해석
            byte[] decodedBytes = Base64.getDecoder().decode(encPw);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            decPw = new String(decrypted);


            System.out.println("복호화한 유저 아이디 -> " + decPw);

        }catch (Exception e) {
            e.printStackTrace();
        }
        //---------------------------------------------------------------------------
        return decPw;
    }
}
