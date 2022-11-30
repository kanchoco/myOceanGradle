package com.example.myoceanproject.dtoTest;

import com.example.myoceanproject.domain.CommunityFileDTO;
import com.example.myoceanproject.entity.Alarm;
import com.example.myoceanproject.entity.CommunityFile;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.repository.CommunityFileRepository;
import com.example.myoceanproject.repository.CommunityFileRepositoryImpl;
import com.example.myoceanproject.repository.CommunityPostRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private CommunityFileRepositoryImpl fileRepositoryImpl;

    @Test
    public void saveCommunityFileTest(){

//      커뮤니티 게시글 3번 불러오기
        Optional<CommunityPost> communityPost = communityPostRepository.findById(4L);
        CommunityFileDTO communityFileDTO = new CommunityFileDTO(4L, "파일이름", "파일 경로", 1234L,1235L );

//      communityFileDTO에 저장한 값들을 entity로 변환
        CommunityFile communityFile1 = communityFileDTO.toEntity();

//      communityFileDTO에 처음 조회했던 커뮤니티 게시글 정보를 저장
//      changeCommunityPost 메소드로 communityFileDTO에 저장된 changeCommunityPost값을 communityFile1로 전달
//        communityFileDTO.setCommunityPost(communityPost.get());
        communityFile1.setCommunityPost(communityPost.get());

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
//        전부 삭제하고 업로드하는걸로 하기로
//        넘겨온 파일들
        CommunityFileDTO communityFileDTO1 = new CommunityFileDTO(4L, "파일이름", "파일 경로", 1234L,1235L );
        CommunityFileDTO communityFileDTO2 = new CommunityFileDTO(4L, "파일이름", "파일 경로", 1234L,1235L );
        CommunityFileDTO communityFileDTO3 = new CommunityFileDTO(4L, "파일이름", "파일 경로", 1234L,1235L );
//       넘겨온 포스트
        CommunityPost post = communityPostRepository.findById(4L).get();
//        이전 파일 전체 삭제
        fileRepositoryImpl.deleteByCommunityPost(post);

        List<CommunityFileDTO> files = new ArrayList<>();
        files.add(communityFileDTO1);
        files.add(communityFileDTO2);
        files.add(communityFileDTO3);


        files.stream().map(CommunityFileDTO::toEntity).forEach(communityFile ->{
                    communityFile.setCommunityPost(post);
                    communityFileRepository.save(communityFile);
                });
    }

    @Test
    public void deleteTest(){
//        전체 삭제, -> 하나만 삭제하는건 없음 수정 시 (항상 전부 날리고 새로 저장되기 때문에)
        CommunityPost post = communityPostRepository.findById(4L).get();

        fileRepositoryImpl.deleteByCommunityPost(post);
    }
}
