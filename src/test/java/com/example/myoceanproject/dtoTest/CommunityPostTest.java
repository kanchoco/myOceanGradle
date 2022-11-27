package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.AlarmDTO;
import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.entity.QCommunityPost;
import com.example.myoceanproject.entity.User;
import com.example.myoceanproject.repository.AlarmRepository;
import com.example.myoceanproject.repository.CommunityPostRepository;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.type.ReadStatus;
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
    private JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveTest(){

//      1번 유저 불러오기
        Optional<User> user = userRepository.findById(1L);
        CommunityPostDTO postDTO = new CommunityPostDTO();

//      alarmDTO에 필요한 값 저장
        postDTO.setCommunityTitle("인기없는 게시글");
        postDTO.setCommunityContent("집에 가고싶다");
        postDTO.setCommunityCategory("작성");
        postDTO.setCommunityViewNumber("0");

//      alarmDTO에 저장한 값들을 entity로 변환
        CommunityPost post1 = postDTO.toEntity();

//      alarmDTO에 처음에 조회했던 유저 정보를 저장(optional이기 때문에 get 사용)
//      changeUser 메소드로 alarmDTO에 저장된 User값을 alarm1로 전달
        postDTO.setUser(user.get());
        post1.changeUser(postDTO.getUser());

//      alarm 엔티티에 해당 값들을 모두 저장
        postRepository.save(post1);
    }

    @Test
    public void findAllTest(){
        List<CommunityPost> posts = jpaQueryFactory.selectFrom(communityPost)
                .fetch();
    }

    @Test
    public void findById(){
        List<CommunityPost> posts = jpaQueryFactory.selectFrom(communityPost)
                .join(communityPost.user)
                .where(communityPost.user.userId.eq(1L))
                .fetchJoin()
                .fetch();

        posts.stream().map(CommunityPost::toString).forEach(log::info);

    }

    @Test
    public void updateTest(){

        CommunityPost post1 = jpaQueryFactory.selectFrom(communityPost)
                .where(communityPost.communityPostId.eq(112L))
                .fetchOne();

        post1.update("수정","수정","수정","수정");


    }


    @Test
    public void deleteTest(){
        Long count = jpaQueryFactory
                .delete(communityPost)
                .where(communityPost.communityContent.eq("수정"))
                .execute();
    }



}
