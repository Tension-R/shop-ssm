package com.tension.service.Impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.tension.bean.*;
import com.tension.dao.ItemDao;
import com.tension.dao.ItemParamItemDao;
import com.tension.service.ItemService;
import com.tension.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ItemParamItemDao itemParamItemDao;

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

    public ItemAddResult addItem(Item item, String desc, String itemParam) {
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

        //添加商品规格参数处理
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreateTime(sqlDate);
        itemParamItem.setUpdateTime(sqlDate);

        itemParamItemDao.insertItemParamItem(itemParamItem);
        return ItemAddResult.ok();
    }

//    /**
//     * 根据商品id查询规格参数
//     * @param itemId
//     * @return
//     */
//    public String itemParamItemResult(long itemId) {
//        List<ItemParamItem> itemParamItemList = itemParamItemDao.getItemParamHtml(itemId);
//        if (itemParamItemList == null || itemParamItemList.isEmpty()){
//            return "";
//        }
//        ItemParamItem itemParamItem = itemParamItemList.get(0);
//        String paramData = itemParamItem.getParamData();
//        return null;
//    }
}
