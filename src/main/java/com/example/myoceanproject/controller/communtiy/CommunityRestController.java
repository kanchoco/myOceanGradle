package com.example.myoceanproject.controller.communtiy;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.domain.CommunityReplyDTO;
import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.entity.CommunityPost;
import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.repository.GroupRepository;
import com.example.myoceanproject.repository.community.post.CommunityPostRepository;
import com.example.myoceanproject.service.community.CommunityPostService;
import com.example.myoceanproject.service.community.CommunityReplyService;
import com.example.myoceanproject.type.GroupStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/community/*")
public class CommunityRestController {

    private final CommunityPostService communityPostService;
    private final CommunityPostRepository communityPostRepository;

    //  리스트 출력
    @GetMapping("/list")
    public List<CommunityPostDTO> getCommunity(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        List<CommunityPostDTO> communityPostDTO= communityPostService.findAllByList(userId);
        return communityPostDTO;
    }

    //  비회원 전용 리스트 출력
    @GetMapping("/list-not-user")
    public List<CommunityPostDTO> getCommunity(){

        List<CommunityPostDTO> communityPostDTO= communityPostService.findAllByList();
        return communityPostDTO;
    }

    @GetMapping(value="/list-filter/{communityCategories}")
    public List<CommunityPostDTO> getFilterCommunity(@PathVariable("communityCategories") List<String> communityCategories){
        List<CommunityPostDTO> communityPostDTO = communityPostService.findBoardByCategory(communityCategories);
        return communityPostDTO;
    }

    @GetMapping(value="/list-filter-login-user/{communityCategories}")
    public List<CommunityPostDTO> getFilterCommunity(@PathVariable("communityCategories") List<String> communityCategories, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        List<CommunityPostDTO> communityPostDTO = communityPostService.findBoardByCategory(communityCategories, userId);
        return communityPostDTO;
    }

    //무한스크롤 비회원전용(카테고리 있을 때)
    @GetMapping("/scroll/{page}/{communityCategories}")
    public List<CommunityPostDTO> infiniteScroll(@PathVariable int page, @PathVariable("communityCategories") List<String> communityCategories){
        return communityPostService.findBoardByCategory(page, communityCategories);
    }


    //무한스크롤 비회원전용(카테고리 없을 때)
    @GetMapping("/scroll/{page}/")
    public List<CommunityPostDTO> infiniteScroll(@PathVariable int page){
        return communityPostService.findBoardByCategory(page);
    }

    //무한스크롤 회원전용(카테고리 있을 때)
    @GetMapping("/scroll-user/{page}/{communityCategories}")
    public List<CommunityPostDTO> selectScrollBoards(@PathVariable int page, @PathVariable("communityCategories") List<String> communityCategories, HttpSession session){

        Long userId = (Long) session.getAttribute("userId");

        return communityPostService.findBoardByCategory(page, communityCategories, userId);
    }

    //무한스크롤 회원전용(카테고리 없을 때)
    @GetMapping("/scroll-user/{page}/")
    public List<CommunityPostDTO> selectScrollBoards(@PathVariable int page, HttpSession session){

        Long userId = (Long) session.getAttribute("userId");

        return communityPostService.findBoardByCategory(page, userId);
    }

    // 게시글 작성 후 커뮤니티 페이지로 이동
    @PostMapping(value="/index", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> communityWriting(@RequestBody CommunityPostDTO communityPostDTO, HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();

        Long userId = (Long) session.getAttribute("userId");
        communityPostDTO.setUserId(userId);

        communityPostService.add(communityPostDTO);

        return new ResponseEntity<>(new String("register success".getBytes(), "UTF-8"), HttpStatus.OK);
    }


//  커뮤니티 게시글 작성 시 썸네일 저장
    @PostMapping("/thumbnail")
    public List<CommunityPostDTO> upload(List<MultipartFile> upload) throws IOException {
        String rootPath = "C:/upload/community";
        String uploadFileName = null;
        List<CommunityPostDTO> files = new ArrayList<>();

        File uploadPath = new File(rootPath, createDirectoryByNow());
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        for(MultipartFile multipartFile : upload){
            CommunityPostDTO communityPostDTO = new CommunityPostDTO();
            UUID uuid = UUID.randomUUID();
            String fileName = multipartFile.getOriginalFilename();
            String fileUuid = uuid.toString();
            String communityPostFilePath = createDirectoryByNow();
            Long communityPostFileSize = multipartFile.getSize();
            uploadFileName = uuid.toString() + "_" + fileName;

            log.info("==========================");
            log.info(fileName);
            log.info(fileUuid);
            log.info(uploadFileName);
            log.info(communityPostFilePath);
            log.info(communityPostFileSize+"");
            log.info("==========================");

            communityPostDTO.setCommunityFileName(fileName);
            communityPostDTO.setCommunityFileUuid(fileUuid);
            communityPostDTO.setCommunityFileSize(multipartFile.getSize());
            communityPostDTO.setCommunityFilePath(communityPostFilePath);

            log.info(communityPostDTO.toString());

            try{
                File saveCommunityFile = new File(uploadPath, uploadFileName);
                multipartFile.transferTo(saveCommunityFile);
            } catch(Exception e){
                log.error(e.getMessage());
            }
            files.add(communityPostDTO);
        }
        return files;
    }

//  썸네일 출력   
    @GetMapping("/display")
    public byte[] display(String fileName) throws IOException{
        File file = new File("C:/upload/community", fileName);
        return FileCopyUtils.copyToByteArray(file);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value="/update", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> communityUpdate(@RequestBody CommunityPostDTO communityPostDTO, HttpServletRequest request) throws UnsupportedEncodingException {

        System.out.println("업데이트 컨트롤러로 들어옴");
        HttpSession session=request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        communityPostDTO.setUserId(userId);
        /*groupDTO로 받아온 값의 communityPostId를 엔티티화하여 communityPost를 찾는다.*/
        CommunityPost communityPost = communityPostRepository.findById(communityPostDTO.getCommunityPostId()).get();
        /*그룹 안에 선언한 update메소드를 통해 communityPostDTO로 받아온 값으로 값을 수정한다.*/
        communityPost.update(communityPostDTO);
        log.info(communityPost.toString());

        return new ResponseEntity<>(new String("register success".getBytes(), "UTF-8"), HttpStatus.OK);
    }

//  썸네일 취소 시 파일 자동 삭제
    @PostMapping("/delete")
    public void delete(String uploadPath, String fileName){
        File file = new File("C:/upload/community", uploadPath + "/" + fileName);
        if(file.exists()){
            file.delete();
        }
    }

//  이미지 경로로 오늘의 날짜 지정
    public String createDirectoryByNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        return format.format(now);
    }
}
