package com.tension.rest.web;

import com.tension.rest.bean.ItemCatResult;
import com.tension.rest.service.ItemCatService;
import com.tension.rest.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品类目查询Controller
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

//    第一种方法
//    produce设置响应的mime类型
//    @RequestMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
//    @ResponseBody
//    public String getItemCatList(String callback){
//        ItemCatResult itemCatResult = itemCatService.getItemCatList();
//        if (StringUtils.isBlank(callback)) {
//            //没有回调，直接返回，需要把itemCatResult转换成字符串
//            String json = JsonUtils.objectToJson(itemCatResult);
//            return json;
//        }
//        //有回调，需要支持jsonp调用
//        String json = JsonUtils.objectToJson(itemCatResult);
//        return callback + "(" + json + ");";
//    }

//    第二种方法
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object getItemCatList(String callback){
        ItemCatResult itemCatResult = itemCatService.getItemCatList();
        if (StringUtils.isBlank(callback)) {
            return itemCatResult;
        }
        //有回调，需要支持jsonp调用
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(itemCatResult);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}
