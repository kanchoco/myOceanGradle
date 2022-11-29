package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.AlarmDTO;
import com.example.myoceanproject.domain.CommunityFileDTO;
import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.UserDTO;
import com.example.myoceanproject.entity.*;
import com.example.myoceanproject.repository.AlarmRepository;
import com.example.myoceanproject.repository.CommunityFileRepository;
import com.example.myoceanproject.repository.CommunityPostRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.CommunityCategory;
import com.example.myoceanproject.type.ReadStatus;
import com.example.myoceanproject.type.UserAccountStatus;
import com.example.myoceanproject.type.UserLoginMethod;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.myoceanproject.entity.QAlarm.alarm;
import static com.example.myoceanproject.entity.QCommunityFile.communityFile;
import static com.example.myoceanproject.entity.QCommunityPost.communityPost;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CommunityPostTest {
    @Autowired
    private CommunityPostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityFileRepository communityFileRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveTest(){

//      시나리오:해당 유저(2L)가 게시글을 작성하면 첨부파일도 같이 생성이 되게 한다.

//      userRepository 인터페이스 구현체 hibernate의 findById메서드를 이용해서
//      커뮤니티 게시글의 작성자를 추가하기위해 검색
        Optional<User> user=userRepository.findById(2L);
        
//      화면에서 입력받는 값들을 위해 게시판,파일 DTO 객체 선언
        CommunityPostDTO communityPostDTO=new CommunityPostDTO();
        CommunityFileDTO communityFileDTO=new CommunityFileDTO();
        
//      게시판을 작성한 유저, 파일을 첨부한 게시글 번호를 추가하기 위한 entity 객체 선언
        CommunityPost communityPost=new CommunityPost();
        CommunityFile communityFile=new CommunityFile();

//      커뮤니티 게시판 내용을 화면에서 입력받는다.
        communityPostDTO.setCommunityTitle("first post");
        communityPostDTO.setCommunityContent("first post content");
        communityPostDTO.setCommunityCategory(CommunityCategory.BOOK);
        communityPostDTO.setUserNickName(user.get().getUserNickname());
        
//      추가 저장을 위해 toentity메서드를 통해 communityPost객체에 저장
        communityPost=communityPostDTO.toEntity();

//      userRepository 인터페이스 구현체 hibernate의 findbyid메서드로 유저를 검색후 추가
        communityPost.changeUser(user.get());

//      커뮤니티 게시판 테이블에 해당 내용을 저장
        postRepository.save(communityPost);

//      커뮤니티 파일의 내용을 입력받는다.
        communityFileDTO.setCommunityFileName("new.png");
        communityFileDTO.setCommunityFilePath("/image/");
        communityFileDTO.setCommunityFileSize(300L);
        communityFileDTO.setCommunityFileUuid(234561L);

//      추가 저장을 위해 toEntity메서드를 통해 communityFile객체에 저장
        communityFile=communityFileDTO.toEntity();

//      postRepository 인터페이스 구현체 hibernate의 findTop1ByOrderByCommunityPostIdDesc메서드로 최신 등록된 커뮤니티 게시판 1개를 가져와서 추가
        communityFile.changeCommunityPost(postRepository.findTop1ByOrderByCommunityPostIdDesc());

//      커뮤니티 파일 테이블에 해당 내용을 저장
        communityFileRepository.save(communityFile);

    }

    @Test
    public void findAllTest(){
//      전체 조회
        List<CommunityPost> posts = jpaQueryFactory.selectFrom(communityPost)
                .fetch();
    }

    @Test
    public void findAllById(){
//      게시글 작성자의 조회
        List<CommunityPost> posts = jpaQueryFactory.selectFrom(communityPost)
                .join(communityPost.user)
                .where(communityPost.user.userId.eq(2L))
                .fetchJoin()
                .fetch();

        posts.stream().map(CommunityPost::toString).forEach(log::info);

    }

    @Test
    public void updateModifyTest(){
//  시나리오: 작성자가 게시글을 수정(기존 게시글의 카테고리와 조회수는 변경되지 않는다, 첨부파일은 변경되지 않는다고 가정)
        CommunityPost post1 = jpaQueryFactory.selectFrom(communityPost)
                .where(communityPost.communityPostId.eq(1L))
                .fetchOne();

        post1.update(post1.getCommunityCategory(),"2211290600수정","2211290600내용수정",post1.getCommunityViewNumber());
    }
    
    @Test
    public void updateViewTest(){
//  시나리오: 작성자의 게시글을 다른 유저가 조회하면 조회수가 변경된다.(기존 게시글의 카테고리, 제목, 내용은 변경되지 않는다.)
        CommunityPost post1 = jpaQueryFactory.selectFrom(communityPost)
                .where(communityPost.communityPostId.eq(1L))
                .fetchOne();

        post1.update(post1.getCommunityCategory(),post1.getCommunityTitle(),post1.getCommunityContent(),post1.getCommunityViewNumber()+1);
    }


    @Test
    public void deleteTest(){
//      게시글 삭제
        Optional<CommunityPost> communityPostOptional=postRepository.findById(postRepository.findpostByuserid(2L));

//        jpaQueryFactory.delete(communityFile)
//                .where(communityFile.communityPost.communityPostId.eq())
//                .execute();
        Long count = jpaQueryFactory
                .delete(communityPost)
                .where(communityPost.communityPostId.eq(1L))
                .execute();
    }



}