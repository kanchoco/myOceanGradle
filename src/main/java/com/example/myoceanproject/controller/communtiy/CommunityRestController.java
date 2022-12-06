package com.example.myoceanproject.controller.communtiy;

import com.example.myoceanproject.domain.CommunityPostDTO;
import com.example.myoceanproject.service.community.CommunityPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // 게시글 작성 후 커뮤니티 페이지로 이동
    @PostMapping(value="/index", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> communityWriting(@RequestBody CommunityPostDTO communityPostDTO, HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();

        Long userId = (Long) session.getAttribute("userId");
        communityPostDTO.setUserId(userId);

        communityPostService.add(communityPostDTO);

        return new ResponseEntity<>(new String("register success".getBytes(), "UTF-8"), HttpStatus.OK);
    }


//  커뮤니티 게시글 작성 시 썸네일 띄우기
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

    @GetMapping("/display")
    public byte[] display(String fileName) throws IOException{
        File file = new File("C:/upload/community", fileName);
        return FileCopyUtils.copyToByteArray(file);
    }

    @PostMapping("/delete")
    public void delete(String uploadPath, String fileName){
        File file = new File("C:/upload/community", uploadPath + "/" + fileName);
        if(file.exists()){
            file.delete();
        }
    }

    public String createDirectoryByNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        return format.format(now);
    }


//  리스트 출력

}
