package com.tension.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.HttpResponse;
import com.tension.bean.Item;
import com.tension.bean.PageBean;
import com.tension.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 商品查询Controller
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public Item getItemById(@PathVariable long itemId, HttpServletResponse resp) throws IOException {
        Item item = itemService.getItemById(itemId);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(item.getAddress());
        return null;
    }

    /**
     * 分页查询商品列表
     * @param page
     * @param rows
     * @param resp
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/item/list")
    public String listBlogType(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            HttpServletResponse resp) throws IOException {

        System.out.println(page + " " + rows);
        //根据页面传回的参数初始化pageBean
        PageBean<Item> itemPageBean = new PageBean<Item>(
                Integer.parseInt(page), Integer.parseInt(rows));

        //设置分页结果和总数
        itemPageBean = itemService.listByPage(itemPageBean);

        //使用阿里巴巴的FastJson创建jsonObject
        JSONObject jsonObject = new JSONObject();

        //通过fastJson序列化分页结果
        String jsonArray = JSON.toJSONString(itemPageBean.getResult());
        JSONArray array = JSONArray.parseArray(jsonArray);

        //序列化结果放入json对象
        jsonObject.put("rows", array);
        jsonObject.put("total", itemPageBean.getTotal());

        //响应输出
        respWrite(resp, jsonObject);

        return null;
    }

    /**
     * 响应输出
     * @param resp
     * @param jsonObject
     * @throws IOException
     */
    private void respWrite(HttpServletResponse resp, JSONObject jsonObject) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject.toJSONString());
        writer.flush();
        writer.close();
    }
}
