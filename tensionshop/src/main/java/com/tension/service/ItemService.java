package com.tension.service;

import com.tension.bean.Item;


public interface ItemService {
    /**
     * 根据id查询商品
     * @param itemId
     * @return
     */
    Item getItemById(long itemId);
}
