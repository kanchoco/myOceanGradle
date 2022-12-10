package com.example.myoceanproject.controller.bulletin_board;


import com.example.myoceanproject.domain.Criteria;
import com.example.myoceanproject.domain.GroupDTO;
import com.example.myoceanproject.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/group/*")
public class BulletinboardRestController {

    private final GroupService groupService;

    @GetMapping("/group/{page}/{keyword}")
    public GroupDTO getGroupList(@PathVariable int page, @PathVariable(required = false) String keyword){
        String decodeKeyword = URLDecoder.decode(keyword, StandardCharsets.UTF_8);

        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setKeyword(decodeKeyword);

        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 10);

        Page<GroupDTO> groupDTOPage = groupService.showGroup(pageable, criteria);
        int endPage = (int)(Math.ceil(groupDTOPage.getNumber()+1 / (double)10)) * 10;
        if(groupDTOPage.getTotalPages() < endPage){
            endPage = groupDTOPage.getTotalPages() == 0 ? 1 : groupDTOPage.getTotalPages();
        }

        GroupDTO groupDTO = new GroupDTO();

        groupDTO.setGroupList(groupDTOPage.getContent());
        groupDTO.setEndPage(endPage);

        groupDTOPage.getContent().stream().map(GroupDTO::toString).forEach(log::info);

        return groupDTO;
    }


}
