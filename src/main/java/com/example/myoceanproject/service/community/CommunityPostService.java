package com.example.myoceanproject.service.community;

import com.example.myoceanproject.aspect.annotation.PostAlarm;
import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.repository.UserRepository;
import com.example.myoceanproject.repository.community.post.CommunityPostRepository;
import com.example.myoceanproject.repository.community.post.CommunityPostRepositoryImpl;
import com.example.myoceanproject.type.CommunityCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service @Qualifier("community") @Primary
@RequiredArgsConstructor
@Slf4j
public class CommunityPostService implements CommunityService {

    private final CommunityPostRepositoryImpl postRepositoryImpl;

    private final CommunityPostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

//  게시글 등록
    public Page<CommunityPostDTO> showCounseling(Pageable pageable, CommunityCategory communityCategory, Criteria criteria) {
        return criteria.getKeyword().equals("null") ? postRepositoryImpl.findAllByCategory(pageable, communityCategory) : postRepositoryImpl.findAllByCategory(pageable, communityCategory, criteria);
    }

    public Page<CommunityPostDTO> showPost(Pageable pageable, Criteria criteria) {
        return criteria.getKeyword().equals("null") ? postRepositoryImpl.findAll(pageable) : postRepositoryImpl.findAll(pageable, criteria);
    }


    public void remove(Long communityPostId) {
        CommunityPost post = postRepository.findById(communityPostId).get();
        postRepositoryImpl.deleteByPost(post);
        postRepository.delete(post);
    }

    //  게시글 등록
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CommunityPostDTO communityPostDTO) {
        CommunityPost communityPost = communityPostDTO.toEntity();
        communityPost.setUser(userRepository.findById(communityPostDTO.getUserId()).get());
        postRepository.save(communityPost);
    }

    @Override
    public List<CommunityPostDTO> showCommunity() {
        return null;
    }

//    게시글 출력
    @Override
    public List<CommunityPostDTO> findAllByList(Long userId) {
        List<CommunityPostDTO> communityPostDTO = postRepositoryImpl.findAllByList(userId);
        return communityPostDTO;
    }

    public List<CommunityPostDTO> findAllByList() {
        List<CommunityPostDTO> communityPostDTO = postRepositoryImpl.findAllByListWithoutLogin();
        return communityPostDTO;
    }

    public List<CommunityPostDTO> findBoardByCategory(List<String> communityCategories){
        List<CommunityPostDTO> communityPostDTO = postRepositoryImpl.filterCommunityBoard(communityCategories);
        return communityPostDTO;
    }

//    비회원 전용
    public List<CommunityPostDTO> findBoardByCategory(int page, List<String> communityCategories){
        List<CommunityPostDTO> communityPostDTO = postRepositoryImpl.filterCommunityBoard(page, communityCategories);
        return communityPostDTO;
    }

//    비회원 전용(카테고리 없을 때)
    public List<CommunityPostDTO> findBoardByCategory(int page){
        List<CommunityPostDTO> communityPostDTO = postRepositoryImpl.filterCommunityBoard(page);
        return communityPostDTO;
    }





//  회원 전용
    public List<CommunityPostDTO> findBoardByCategory(int page, List<String> communityCategories, Long userId){
        log.info("===============" + userId);
        List<CommunityPostDTO> communityPostDTO = postRepositoryImpl.filterCommunityBoard(page, communityCategories, userId);
        return communityPostDTO;
    }

//  회원 전용(카테고리 없을 때)
    public List<CommunityPostDTO> findBoardByCategory(int page, Long userId){
        log.info("===============" + userId);
        List<CommunityPostDTO> communityPostDTO = postRepositoryImpl.filterCommunityBoard(page, userId);
        return communityPostDTO;
    }






    public List<CommunityPostDTO> findBoardByCategory(List<String> communityCategories, Long userId){
        List<CommunityPostDTO> communityPostDTO = postRepositoryImpl.filterCommunityBoard(communityCategories, userId);
        return communityPostDTO;
    }




    @Override
    public CommunityPostDTO find(Long communityPostId) {
        CommunityPostDTO communityPostDTO = postRepositoryImpl.findByCommunityId(communityPostId);
        return communityPostDTO;
    }

    @Override
    public void update(CommunityPostDTO communityPostDTO) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long communityPostId) {
        postRepository.deleteById(communityPostId);
    }

//  무한스크롤
    @Override
    public List<CommunityPostDTO> selectScrollBoards(int page) {
        List<CommunityPostDTO> communityPostDTO = postRepositoryImpl.selectScrollBoards(page);
        return communityPostDTO;
    }

    @Override
    public List<CommunityPostDTO> selectScrollBoards(int page, Long userId) {
        List<CommunityPostDTO> communityPostDTO = postRepositoryImpl.selectScrollBoards(page, userId);
        return communityPostDTO;
    }
}

