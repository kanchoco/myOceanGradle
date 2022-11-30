package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.QUserDTO;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.QUser;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.UserAccountStatus;
import com.example.myoceanproject.type.UserLoginMethod;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import static com.example.myoceanproject.entity.QUser.user;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class UserTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void saveUserTest(){
//      시나리오 : 회워가입 1,2페이지를 통해 누적된 회원의 정보를 회원 테이블에 등록하게된다.
//      정보 : 이메일,닉네임,패스워드
//      타입 : 회원가입은 일반(UserLoginMethod.GENERAL), 활성화(UserAccountStatus.ACTIVE)
//      포인트 : 회원가입포인트(5000) - 임의로 지정
        
//      화면에서 이메일, 닉네임, 패스워드를 입력받고 저장하기 위해 DTO객체 생성
        UserDTO userDTO = new UserDTO();

//      입력받은 패스워드를 암호화하기 위한 과정
        String pw="12345";
        String salt=Salt();
        String encryptPw=SHA512(pw,salt);
        
//      DTO객체에 화면에서 입력받은 정보와 계정의 추가 상태를 지정한다.
        userDTO.setUserEmail("aaa@aaa.com");
        userDTO.setUserNickname("abcmouse");
        userDTO.setUserPassword(encryptPw);
        userDTO.setUserLoginMethod(UserLoginMethod.GENERAL);
        userDTO.setUserAccountStatus(UserAccountStatus.ACTIVE);
        userDTO.setUserTotalPoint(5000);

//      toEntity메서드로 User타입의 엔티티에 저장
        User user=userDTO.toEntity();
        
//      유저 테이블에 저장
        userRepository.save(user);

    }


    @Test
    public void findAllTest(){
//      시나리오 : 사용자의 전체 정보를 조회한다.
        List<UserDTO> users=jpaQueryFactory.select(new QUserDTO(
                user.userPassword,
                user.userNickname,
                user.userAccountStatus,
                user.userFileName,
                user.userFilePath,
                user.userFileSize,
                user.userFileUuid,
                user.userEmail,
                user.userLoginMethod,
                user.userTotalPoint
        )).from(user).fetch();

        log.info("------------------------------------------------------------");
        users.stream().map(UserDTO::toString).forEach(log::info);
        log.info("------------------------------------------------------------");
    }

    @Test
    public void findById(){
//      시나리오 : 사용자(3L)의 정보를 조회한다.
        List<UserDTO> users=jpaQueryFactory.select(new QUserDTO(
                user.userPassword,
                user.userNickname,
                user.userAccountStatus,
                user.userFileName,
                user.userFilePath,
                user.userFileSize,
                user.userFileUuid,
                user.userEmail,
                user.userLoginMethod,
                user.userTotalPoint
        )).from(user).where(user.userId.eq(3L)).fetch();

        log.info("------------------------------------------------------------");
        users.stream().map(UserDTO::toString).forEach(log::info);
        log.info("------------------------------------------------------------");
    }

    @Test
    public void updateNicknameFileTest(){
//      시나리오 : 마이페이지에서 회원(3L)의 닉네임, 및 회원 프로필 사진을 수정한다.
        
//      화면에서 회원의 닉네임 및 프로필 사진을 수정하여 입력받는다.
        UserDTO userDTO=new UserDTO(null,"cutenick",null,"dog.png","/image/",300L,123456L,null,null,0);

//      화면에 로그인 중인 회원의 회원번호를 넘겨받고 회원 객체를 생성
        User users=jpaQueryFactory.selectFrom(user).where(user.userId.eq(3L)).fetchOne();
        users.updateNicknameFile(userDTO);
    }
    
    @Test
    public void updatePasswordTest(){
//      시나리오 : 마이페이지에서 회원(3L)의 비밀번호 변경 버튼을 클릭해 비밀번호를 수정한다.

//      입력받은 패스워드를 암호화하여 저장하기 위한 과정
        String pw="23456";
        String salt=Salt();
        String encryptPw=SHA512(pw,salt);

//      화면에서 변경된 패스워드를 입력받는다.
        UserDTO userDTO=new UserDTO(encryptPw,null,null,null,null,null,null,null,null,0);

//      화면에서 로그인 중인 회원의 회원번호를 넘겨받고 객체를 생성
        User users=jpaQueryFactory.selectFrom(user).where(user.userId.eq(3L)).fetchOne();
        users.updatePassword(userDTO);
    }

    @Test
    public void updateManagerTest(){
//      시나리오 : 관리자가 회원(3L)의 상태를 계정 정지상태로 변경한다.

//      관리자에 의해 화면에서 회원의 상태가 변경된 값을 입력받는다.
        UserDTO userDTO=new UserDTO(null,null,UserAccountStatus.BANNED,null,null,null,null,null,null,0);

//      화면에서 로그인 중인 회원의 회원번호를 넘겨받고 객체를 생성
        User users=jpaQueryFactory.selectFrom(user).where(user.userId.eq(3L)).fetchOne();
        users.updateManager(userDTO);
    }

    @Test
    public void deleteTest(){
//      시나리오 : 화면에서 사용자(3L)의 번호를 입력받는다.

//      화면에서 사용자가 탈퇴를 클릭하면 해당 사용자의 번호를 입력받는다.
        User user=userRepository.findById(3L).get();
        
//      유저 테이블에서 회원의 정보를 삭제
        userRepository.delete(user);
    }

    //양방향 암호화 알고리즘인 AES256 암호화를 지원하는 클래스
    public static String Salt() {

        String salt="";
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] bytes = new byte[16];
            random.nextBytes(bytes);
            salt = new String(Base64.encodeBase64(bytes));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return salt;
    }

    public static String SHA512(String password, String hash) {
        String salt = hash+password;
        String hex = null;
        try {
            MessageDigest msg = MessageDigest.getInstance("SHA-512");
            msg.update(salt.getBytes());

            hex = String.format("%128x", new BigInteger(1, msg.digest()));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hex;
    }
}



