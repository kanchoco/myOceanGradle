package com.example.myoceanproject.controller.login;

import com.example.myoceanproject.domain.QUserDTO;
import com.example.myoceanproject.domain.QUserFindDTO;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.domain.UserFindDTO;
import com.example.myoceanproject.entity.UserFind;
import com.example.myoceanproject.repository.UserFindRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import static com.example.myoceanproject.entity.QUser.user;
import static com.example.myoceanproject.entity.QUserFind.userFind;

@Slf4j
@Controller
@RequestMapping("/login/*")
public class LoginController {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFindRepository userFindRepository;

    // 로그인 페이지
    @GetMapping("/index")
    public String login(Model model){
        model.addAttribute("userDTO",new UserDTO());
        return "app/login/login";
    }

    @RequestMapping("/checkUserPassword")
    @ResponseBody
    public String checkuserPassword(@RequestBody ObjectNode saveObj){
        BooleanBuilder builder=new BooleanBuilder();
        ObjectMapper mapper = new ObjectMapper();   // JSON을 Object화 하기 위한 Jackson ObjectMapper 이용
//        Board board = mapper.treeToValue(saveObj.get("board"), Board.class);
//        User user = mapper.treeToValue(saveObj.get("user"), User.class);
        String userEmail=saveObj.get("userEmail").asText();
        String userPassword = saveObj.get("userPassword").asText();
//        return boardService.update(boardDTO, password);

        builder.and(user.userEmail.eq(userEmail));
        builder.and(user.userPassword.eq(encryption(userPassword)));
        List<UserDTO> users=jpaQueryFactory.select(new QUserDTO(
                user.userId,
                user.userEmail,
                user.userNickname,
                user.userAccountStatus,
                user.userFileName,
                user.userFilePath,
                user.userFileSize,
                user.userFileUuid,
                user.userPassword,
                user.userLoginMethod,
                user.userTotalPoint
        )).from(user).where(builder).fetch();
//        log.info(userEmail);
//        log.info(password);

        if(users.size()>=1){
            return "member";
        }else{return "notmember";}
    }

    @PostMapping("/loginOk")
    public String afterLogin(UserDTO userDTO, HttpServletRequest request){
        HttpSession session=request.getSession();


        BooleanBuilder builder=new BooleanBuilder();
        builder.and(user.userEmail.eq(userDTO.getUserEmail()));
        builder.and(user.userPassword.eq(encryption(userDTO.getUserPassword())));

        UserDTO loginUser=jpaQueryFactory.select(new QUserDTO(
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
                user.userTotalPoint
        )).from(user).where(builder).fetchOne();

        session.setAttribute("userId",loginUser.getUserId());
        session.setAttribute("userEmail",loginUser.getUserEmail());
        session.setAttribute("userNickname",loginUser.getUserNickname());

        return "redirect:/main/index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.removeAttribute("userId");
        session.removeAttribute("userEmail");
        session.removeAttribute("userNickname");
        return "redirect:/main/index";
    }
    // 비밀번호 찾기 페이지
    @GetMapping("/findPw")
    public String findPw(){
        return "app/login/findPw";
    }

    @RequestMapping("/checkUserEmail")
    @ResponseBody
    public String checkUserEmail(@RequestBody String email){
        List<UserDTO> users=jpaQueryFactory.select(new QUserDTO(
                user.userId,
                user.userEmail,
                user.userNickname,
                user.userAccountStatus,
                user.userFileName,
                user.userFilePath,
                user.userFileSize,
                user.userFileUuid,
                user.userPassword,
                user.userLoginMethod,
                user.userTotalPoint
        )).from(user).where(user.userEmail.eq(email)).fetch();

        if(users.size()>=1){
            return "available";
        }else{return "unavailable";}
    }

    @RequestMapping("/requestFind")
    @ResponseBody
    public void saveRequestFind(@RequestBody String email){
        UserFindDTO userFindDTO=new UserFindDTO();

        UserDTO users=jpaQueryFactory.select(new QUserDTO(
                user.userId,
                user.userEmail,
                user.userNickname,
                user.userAccountStatus,
                user.userFileName,
                user.userFilePath,
                user.userFileSize,
                user.userFileUuid,
                user.userPassword,
                user.userLoginMethod,
                user.userTotalPoint
        )).from(user).where(user.userEmail.eq(email)).fetchOne();

        userFindDTO.setUserEmail(email);
        userFindDTO.setUserUuid(UUID.randomUUID().toString());
        userFindDTO.setUserId(users.getUserId());
        UserFind userFind=userFindDTO.toentity();
        userFindRepository.save(userFind);
    }
    // 비밀번호 찾기 요청후 페이지
    @GetMapping("/findPwComplete")
    public String findPwComplete(){
        return "app/login/findPwComplete";
    }

    // 비밀번호 변경 페이지
    @GetMapping("/changePassword")
    public String changePassword(){
        return "app/login/changePassword";
    }

    @RequestMapping
    @ResponseBody
    @Transactional
    @Modifying
    public UserFindDTO giveRequest(@RequestBody String email){
        UserFindDTO userFinds=jpaQueryFactory.select(new QUserFindDTO(
                userFind.userId,
                userFind.userUuid,
                userFind.userEmail,
                userFind.userFindtime
        )).from(userFind).where(userFind.userEmail.eq(email)).fetchOne();

        jpaQueryFactory.delete(userFind).where(userFind.userEmail.eq(email)).execute();
        return userFinds;
    }
    // 비밀번호 변경 완료 페이지
    @GetMapping("/changePwComplete")
    public String changePwComplete(){
        return "app/login/changePwComplete";
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
