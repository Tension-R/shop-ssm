package com.tension.service;

import com.tension.bean.Item;
import com.tension.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemDao itemDao;

    public Item getItemById(long itemId) {
        Item item = itemDao.getItemById(itemId);
        return item;
    }
}
