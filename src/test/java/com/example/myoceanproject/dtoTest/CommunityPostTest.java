package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.*;
import com.example.myoceanproject.entity.*;
import com.example.myoceanproject.repository.*;
import com.example.myoceanproject.type.CommunityCategory;
import com.example.myoceanproject.type.ReadStatus;
import com.example.myoceanproject.type.UserAccountStatus;
import com.example.myoceanproject.type.UserLoginMethod;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
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
    private CommunityFileRepositoryImpl fileRepositoryImpl;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveTest(){

//      시나리오:해당 유저(2L)가 게시글을 작성하면 첨부파일도 같이 생성이 되게 한다.

//      userRepository 인터페이스 구현체 hibernate의 findById메서드를 이용해서
//      커뮤니티 게시글의 작성자를 추가하기위해 검색
        Optional<User> user=userRepository.findById(14L);
        
//      화면에서 입력받는 값들을 위해 게시판,파일 DTO 객체 선언
        CommunityPostDTO communityPostDTO=new CommunityPostDTO();
        CommunityFileDTO communityFileDTO=new CommunityFileDTO();
        
//      게시판을 작성한 유저, 파일을 첨부한 게시글 번호를 추가하기 위한 entity 객체 선언
        CommunityPost communityPost=new CommunityPost();
        CommunityFile communityFile=new CommunityFile();

//      커뮤니티 게시판 내용을 화면에서 입력받는다.
        communityPostDTO.setCommunityTitle("second post");
        communityPostDTO.setCommunityContent("second post content");
        communityPostDTO.setCommunityCategory(CommunityCategory.BOOK);
        communityPostDTO.setUserNickName(user.get().getUserNickname());
        
//      추가 저장을 위해 toentity메서드를 통해 communityPost객체에 저장
        communityPost=communityPostDTO.toEntity();

//      userRepository 인터페이스 구현체 hibernate의 findbyid메서드로 유저를 검색후 추가
        communityPost.setUser(user.get());

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
//       게시글의 정보를 출력, 따라서 DTO로 반환한다
        List<CommunityPostDTO> posts = jpaQueryFactory.select(new QCommunityPostDTO(
                communityPost.user.userId,
                communityPost.user.userNickname,
                communityPost.user.userFileName,
                communityPost.user.userFilePath,
                communityPost.user.userFileSize,
                communityPost.user.userFileUuid,
                communityPost.communityCategory,
                communityPost.communityTitle,
                communityPost.communityContent,
                communityPost.communityViewNumber)).from(communityPost).fetch();
//        확인
        log.info("------------------------------------------------------------");
        posts.stream().map(CommunityPostDTO::toString).forEach(log::info);
        log.info("------------------------------------------------------------");
    }
    @Test
    public void findAllById(){
//      게시글 14번 회원의 글만 조회
//        14번의 내 게시글 조회, DTO로 출력
        List<CommunityPostDTO> posts = jpaQueryFactory.select(new QCommunityPostDTO(
                communityPost.user.userId,
                communityPost.user.userNickname,
                communityPost.user.userFileName,
                communityPost.user.userFilePath,
                communityPost.user.userFileSize,
                communityPost.user.userFileUuid,
                communityPost.communityCategory,
                communityPost.communityTitle,
                communityPost.communityContent,
                communityPost.communityViewNumber)).from(communityPost).where(communityPost.user.userId.eq(14L)).fetch();

        log.info("------------------------------------------------------------");
        posts.stream().map(CommunityPostDTO::toString).forEach(log::info);
        log.info("------------------------------------------------------------");
    }

    @Test
    public void updateModifyTest(){
//        로직
//  사용자가 자신의 게시글 중 임의의 게시글을 선택하여 게시글 상세보기 페이지에 들어간다
//  수정 페이지로 이동하여 임의의 값을 수정한다.
//  수정된 값은 컨트롤러를 통해 백단으로 넘어온다(DTO)
//  컨트롤러로 넘어온 값을 서비스의 메소드를 사용하여 DB에 저장한다(DTO -> Entity)

//        외부에서 포스트 번호가 전달됨 15L   -> 변수로
//        가져온 DTO, 수정된 내용
        CommunityPostDTO postDTO = new CommunityPostDTO(14L,"칸초코", null, null, null, null, CommunityCategory.FREEBOARD, "수정할래요", "자유게시판으로 수정합니다", 0);
//        외부에서 넘겨온 포스트번호로 개체를 가져온다(영속성 컨텍스트가 관리하는 개체)
        CommunityPost post = jpaQueryFactory.selectFrom(communityPost).where(communityPost.communityPostId.eq(15L)).fetchOne();
//        entity로 변환하면서, 수정이 불가한 내용들은 모두 지워진다. (유저정보, 게시글 번호) ~ set으로 값 할당
        post.setUser(userRepository.findById(postDTO.getUserId()).get());
        post.setCommunityPostId(15L);

        post.update(postDTO);

    }
    
    @Test
    public void updateViewTest(){
//        유저 정보 검사는 단테에서 진행 하지 않고 해당 유저가 아니라는 전제하에 로직
//        조회한 유저의 정보를 나타내지 않으므로 유저 선언 없이 진행함

//      15번 포스트를 조회함.
        CommunityPost post = jpaQueryFactory.selectFrom(communityPost)
                .where(communityPost.communityPostId.eq(15L))
                .fetchOne();
//영속성 컨텍스트가 관리하는 개체이므로 업데이트 메서드를 Entity에서 선언해서 사용한다.
        post.updateView();
    }


    @Test
    public void deleteTest(){
//        받아온 포스트번호가 17
//        우선 파일있는지 확인하고, 파일이 존재할 경우ㅡ 파일을 전체 삭제 후 게시글 삭제를 진행한다.

//      17번 포스트를 찾아서 객체 선언
        CommunityPost post = postRepository.findById(17L).get();
//      17번 포스트 객체로 파일 전체를 불러옴
        List<CommunityFileDTO> postFiles = fileRepositoryImpl.findByCommunityPost(post);

//        만약 포스트객체에 파일이 있다면?
        if(!postFiles.isEmpty()) {
//        전체 삭제 메소드를 실행하여 포스트와 관련된 파일을 전체 삭제한다.
            fileRepositoryImpl.deleteByCommunityPost(post);
        }

//        만약 포스트객체에 파일이 없다면 포스트 삭제를 진행한다.
        postRepository.delete(post);
    }



}