package com.tension.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tension.bean.EasyUiTreeNode;
import com.tension.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品分类管理Controller
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUiTreeNode> getItemCatList(@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<EasyUiTreeNode> list = itemCatService.getItemCatList(parentId);
        return list;
    }
}
