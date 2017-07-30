package com.tension.service;

import com.tension.bean.Item;
import com.tension.bean.ItemAddResult;
import com.tension.bean.PageBean;


public interface ItemService {
    /**
     * 根据id查询商品
     * @param itemId
     * @return
     */
    Item getItemById(long itemId);

    /**
     * 商品列表
     * @return
     */
    PageBean<Item> listByPage(PageBean<Item> pageBean);

    ItemAddResult addItem(Item item,String desc, String itemParam);

//    String itemParamItemResult(long itemId);
}
