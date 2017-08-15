package com.tension.search.web;

import com.tension.search.bean.ItemAddResult;
import com.tension.search.service.ItemService;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/importall")
    @ResponseBody
    public ItemAddResult importAll(){

        try {
            ItemAddResult itemAddResult = itemService.importItems();
            return itemAddResult;
        } catch (Exception e) {
            e.printStackTrace();
            return ItemAddResult.build(500,"wrong");
        }

    }
}
