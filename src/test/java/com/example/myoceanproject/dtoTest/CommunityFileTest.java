package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.CommunityFileDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.CommunityFile;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.repository.CommunityFileRepository;
import com.example.myoceanproject.repository.CommunityPostRepository;
import com.example.myoceanproject.type.ReadStatus;
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

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CommunityFileTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private CommunityFileRepository communityFileRepository;
    @Autowired
    private CommunityPostRepository communityPostRepository;

    @Test
    public void saveCommunityFileTest(){
        
//      커뮤니티 게시글 3번 불러오기
        Optional<CommunityPost> communityPost = communityPostRepository.findById(3L);
        CommunityFileDTO communityFileDTO = new CommunityFileDTO();
        
//      communityFileDTO에 필요한 값 저장(임베디드)


//      communityFileDTO에 저장한 값들을 entity로 변환
        CommunityFile communityFile1 = communityFileDTO.toEntity();

//      communityFileDTO에 처음 조회했던 커뮤니티 게시글 정보를 저장
//      changeCommunityPost 메소드로 communityFileDTO에 저장된 changeCommunityPost값을 communityFile1로 전달
        communityFileDTO.setCommunityPost(communityPost.get());
        communityFile1.changeCommunityPost(communityFileDTO.getCommunityPost());

//      communityFile 엔티티에 해당 값들을 모두 저장
        communityFileRepository.save(communityFile1);
    }

    @Test
    public void findAllTest(){
        List<CommunityFile> communityFiles = jpaQueryFactory.selectFrom(communityFile)
                .join(communityFile.communityPost)
                .fetchJoin()
                .fetch();
        communityFiles.stream().map(CommunityFile::toString).forEach(log::info);
    }

    @Test
    public void findById(){
        List<CommunityFile> communityFiles = jpaQueryFactory.selectFrom(communityFile)
                .join(communityFile.communityPost)
                .where(communityFile.communityPost.communityPostId.eq(1L))
                .fetchJoin()
                .fetch();

        communityFiles.stream().map(CommunityFile::toString).forEach(log::info);
    }

    @Test
    public void updateTest(){

        CommunityFile communityFile1 = jpaQueryFactory.selectFrom(communityFile)
                .where(communityFile.communityFileId.eq(1L))
                .fetchOne();

//        communityFile1.update();

    }

    @Test
    public void deleteTest(){
        Long count = jpaQueryFactory
                .delete(communityFile)
                .where(communityFile.communityFileId.eq(2L))
                .execute();
    }
}
