package com.example.myoceanproject.controller.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search/*")
public class SearchController {
    // 통합 검색 페이지
    @GetMapping("/totalSearch")
    public String totalSearch(){
        return "app/Search/Search";
    }

    // 통합 검색이 없을때 페이지
    @GetMapping("/noTotalSearch")
    public String noTotalSearch(){
        return "app/Search/NoSearch";
    }
}
