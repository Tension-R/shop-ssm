package com.tension.service.Impl;

import com.github.pagehelper.PageHelper;
import com.tension.bean.Item;
import com.tension.bean.PageBean;
import com.tension.dao.ItemDao;
import com.tension.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    public Item getItemById(long itemId) {
        Item item = itemDao.getItemById(itemId);
        return item;
    }

    public PageBean<Item> listByPage(PageBean<Item> pageBean) {
        List<Item> itemList = itemDao.listItemByPage(pageBean.getStart(),pageBean.getEnd());
        //记录查询分页的结果
        pageBean.setResult(itemList);
        //记录查询总数
        pageBean.setTotal(itemDao.getTotalNum());
        return pageBean;
    }
}
