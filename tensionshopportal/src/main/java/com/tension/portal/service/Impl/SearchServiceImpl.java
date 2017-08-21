package com.tension.portal.service.Impl;

import com.alibaba.druid.util.HttpClientUtils;
import com.tension.portal.bean.Item;
import com.tension.portal.bean.ItemAddResult;
import com.tension.portal.bean.SearchResult;
import com.tension.portal.service.SearchService;
import com.tension.portal.util.HttpClientUtil;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.tension.portal.util.HttpClientUtil.doGet;

@Service
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    public SearchResult search(String keyword, int page, int rows) {



        Map<String,String> param = new HashMap<String, String>();
        param.put("keyword",keyword);
        param.put("page",page + "");
        param.put("rows",rows + "");

        //调用服务
        String json = HttpClientUtil.doGet(SEARCH_BASE_URL,param);
        ItemAddResult itemAddResult = ItemAddResult.formatToPojo(json, Item.class);

        SearchResult result = (SearchResult) itemAddResult.getData();
        return result;
    }
}
