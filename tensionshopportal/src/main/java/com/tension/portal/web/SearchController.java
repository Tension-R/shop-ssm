package com.tension.portal.web;

import com.tension.portal.bean.SearchResult;
import com.tension.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public String search(@RequestParam("q") String keyword,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "10") Integer rows,
                        Model model){

        //乱码处理
        try {
            keyword = new String(keyword.getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            keyword = "";
            e.printStackTrace();
        }
        SearchResult result = searchService.search(keyword, page, rows);
        model.addAttribute("query",keyword);
        model.addAttribute("totalPage",result.getPageCount());
        model.addAttribute("itemList",result.getItemList());
        model.addAttribute("page",result.getCurPage());

        return "search";
    }
}
