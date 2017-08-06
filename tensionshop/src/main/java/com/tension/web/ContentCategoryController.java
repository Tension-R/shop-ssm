package com.tension.web;

import com.tension.bean.EasyUiTreeNode;
import com.tension.bean.ItemAddResult;
import com.tension.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容管理controller
 */
@Controller
@RequestMapping("content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUiTreeNode> getContentCatList(@RequestParam(value = "id" ,defaultValue = "0") Long parentId){

        List<EasyUiTreeNode> contentCatList = contentCategoryService.getContentCatList(parentId);
        return contentCatList;
    }

    @RequestMapping("/create")
    @ResponseBody
    public ItemAddResult createNode(long parentId, String name){
        ItemAddResult result = contentCategoryService.insertCategory(parentId, name);
        return result;
    }
}
