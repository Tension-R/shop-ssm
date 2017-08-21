package com.tension.search.web;

import com.tension.search.bean.ItemAddResult;
import com.tension.search.bean.SearchResult;
import com.tension.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 发布搜索服务
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/q")
    @ResponseBody
    public ItemAddResult search(@RequestParam(defaultValue = "") String keyword,
                                @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer rows) {

        try {
            //转化字符集
            keyword = new String(keyword.getBytes("iso8859-1"),"utf-8");
            SearchResult result = searchService.search(keyword, page, rows);
            return ItemAddResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ItemAddResult.build(500, "wrong");
        }
    }
}
