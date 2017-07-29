package com.tension.service.Impl;

import com.tension.bean.Item;
import com.tension.bean.ItemAddResult;
import com.tension.bean.ItemDesc;
import com.tension.bean.PageBean;
import com.tension.dao.ItemDao;
import com.tension.service.ItemService;
import com.tension.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public ItemAddResult addItem(Item item, String desc) {
        //生成商品ID
        long itemId = IDUtil.getItemId();
        item.setId(itemId);
        //商品状态：1-正常，2-下架，3-删除
        item.setStatus(1);
        java.util.Date date = new java.util.Date();
        Date sqlDate = new Date(date.getTime());
        item.setCreateTime(sqlDate);
        item.setUpdateTime(sqlDate);

        itemDao.addItem(item);

        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreateTime(sqlDate);
        itemDesc.setUpdateTime(sqlDate);

        itemDao.addItemDesc(itemDesc);
        return ItemAddResult.ok();
    }
}
