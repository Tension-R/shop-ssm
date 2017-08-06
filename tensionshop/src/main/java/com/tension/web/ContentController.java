package com.tension.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tension.bean.Content;
import com.tension.bean.ItemAddResult;
import com.tension.bean.PageBean;
import com.tension.service.ContentService;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.tension.util.RespWriteUtil.respWrite;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public String listContent(@RequestParam(value = "categoryId", required = true) long categoryId,
                              @RequestParam(value = "page", required = false) String page,
                              @RequestParam(value = "rows", required = false) String rows,
                              HttpServletResponse resp) throws IOException {
//根据页面传回的参数初始化pageBean
        PageBean<Content> contentPageBean = new PageBean<Content>(
                Integer.parseInt(page), Integer.parseInt(rows));

        //设置分页结果和总数
        contentPageBean = contentService.listByPage(categoryId,contentPageBean);

        //使用阿里巴巴的FastJson创建jsonObject
        JSONObject jsonObject = new JSONObject();

        //通过fastJson序列化分页结果
        String jsonArray = JSON.toJSONString(contentPageBean.getResult());
        JSONArray array = JSONArray.parseArray(jsonArray);

        //序列化结果放入json对象
        jsonObject.put("rows", array);
        jsonObject.put("total", contentPageBean.getTotal());

        //响应输出
        respWrite(resp, jsonObject);

        return null;

    }

    @RequestMapping("/save")
    @ResponseBody
    public ItemAddResult addContent(Content content){
        ItemAddResult result = contentService.addContent(content);
        return result;
    }
}
