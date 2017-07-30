package com.tension.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tension.bean.ItemAddResult;
import com.tension.bean.ItemParam;
import com.tension.bean.PageBean;
import com.tension.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.tension.util.RespWriteUtil.respWrite;

/**
 * 商品规格参数模板管理Controller
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/query/itemcatid/{cid}")
    @ResponseBody
    public ItemAddResult getItemCatByCid(@PathVariable long cid){
        ItemAddResult itemAddResult = itemParamService.getItemParamByCid(cid);
        return itemAddResult;
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public ItemAddResult addItemParam(@PathVariable long cid,String paramData){
        ItemAddResult result = itemParamService.addItemParam(cid,paramData);
        return result;
    }

    @RequestMapping("/list")
    public String listItemParam(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            HttpServletResponse resp) throws IOException {

        //根据页面传回的参数初始化pageBean
        PageBean<ItemParam> itemPageBean = new PageBean<ItemParam>(
                Integer.parseInt(page), Integer.parseInt(rows));

        //设置分页结果和总数
        itemPageBean = itemParamService.listItemParam(itemPageBean);

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
}
