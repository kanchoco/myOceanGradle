package com.example.myoceanproject.controller.host;

import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.entity.Group;
import com.example.myoceanproject.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/host/*")
public class HostRestController {

    private final GroupService groupService;

    @PostMapping(value="/index", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> host(@RequestBody GroupDTO groupDTO, HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session=request.getSession();

        Long userId = (Long) session.getAttribute("userId");
        groupDTO.setUserId(userId);
        log.info(userId +"    ===== 유저 아이디");
        log.info("==============================");
        log.info("그룹명: " + groupDTO.getGroupName());
        log.info("그룹카테고리: " + groupDTO.getGroupCategory());
        log.info("그룹 최소인원: " + groupDTO.getMinMember());
        log.info("그룹 최대인원: " + groupDTO.getMaxMember());
        log.info("그룹 포인트: " + groupDTO.getGroupPoint());
        log.info("국내/해외 여부: " + groupDTO.getGroupOverSea());
        log.info("모임 타입: " + groupDTO.getGroupLocationType());
        log.info("모임 장소 이름: " + groupDTO.getGroupLocationName());
        log.info("모임 장소: " + groupDTO.getGroupLocation());
        log.info("모임 상세 주소: " + groupDTO.getGroupLocationDetail());
        log.info("주차 가능 여부: " + groupDTO.getGroupParkingAvailable());
        log.info("모임 주의사항: " + groupDTO.getGroupMoreInformation());
        log.info("상세 내용: " + groupDTO.getGroupContent());
        log.info("썸네일 이름: " + groupDTO.getGroupFileName());
        log.info("썸네일 경로: " + groupDTO.getGroupFilePath());
        log.info("썸네일 파일 크기: " + groupDTO.getGroupFileSize());
        log.info("썸네일 UUID: "+ groupDTO.getGroupFileUuid());
        log.info("유저 아이디: " + groupDTO.getUserId());


        groupService.add(groupDTO);
        return new ResponseEntity<>(new String("register success".getBytes(), "UTF-8"), HttpStatus.OK);
    }

    @PostMapping("/thumbnail")
    public List<GroupDTO> upload(List<MultipartFile> upload) throws IOException {
        String rootPath = "C:/upload/group";
        String uploadFileName = null;
        List<GroupDTO> files = new ArrayList<>();

        File uploadPath = new File(rootPath, createDirectoryByNow());
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        for(MultipartFile multipartFile : upload){
            GroupDTO groupDTO = new GroupDTO();
            UUID uuid = UUID.randomUUID();
            String fileName = multipartFile.getOriginalFilename();
            String fileUuid = uuid.toString();
            String groupFilePath = createDirectoryByNow();
            Long groupFileSize = multipartFile.getSize();
            uploadFileName = uuid.toString() + "_" + fileName;

            log.info("==========================");
            log.info(fileName);
            log.info(fileUuid);
            log.info(uploadFileName);
            log.info(groupFilePath);
            log.info(groupFileSize+"");
            log.info("==========================");

            groupDTO.setGroupFileName(fileName);
            groupDTO.setGroupFileUuid(fileUuid);
            groupDTO.setGroupFileSize(multipartFile.getSize());
            groupDTO.setGroupFilePath(groupFilePath);

            try{
                File saveGroupFile = new File(uploadPath, uploadFileName);
                multipartFile.transferTo(saveGroupFile);

//                if(checkImageType(saveGroupFile)){
//                    FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
//                    Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
//                    thumbnail.close();
//                }
            } catch(Exception e){
                log.error(e.getMessage());
            }
            files.add(groupDTO);
        }
        return files;
    }

    @GetMapping("/display")
    public byte[] display(String fileName) throws IOException{
        File file = new File("C:/upload/group", fileName);
        return FileCopyUtils.copyToByteArray(file);
    }

    @PostMapping("/delete")
    public void delete(String uploadPath, String fileName){
        File file = new File("C:/upload/group", uploadPath + "/" + fileName);
        if(file.exists()){
            file.delete();
        }
    }

    public String createDirectoryByNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        return format.format(now);
    }

    public boolean checkImageType(File file) throws IOException{
        return Files.probeContentType(file.toPath()).startsWith("image");
    }
}