package com.tension.service.Impl;

import com.tension.bean.ItemAddResult;
import com.tension.bean.ItemParam;
import com.tension.bean.PageBean;
import com.tension.dao.ItemParamDao;
import com.tension.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * 商品规格参数管理Service
 */
@Service
public class ItemParamServiceImpl implements ItemParamService{

    @Autowired
    private ItemParamDao itemParamDao;

    public ItemAddResult getItemParamByCid(long cid) {

        List<ItemParam> itemParamList = itemParamDao.getItemParamByCid(cid);
        if (itemParamList != null && itemParamList.size() > 0){
            ItemParam itemParam = itemParamList.get(0);
            return ItemAddResult.ok(itemParam);
        }
        return ItemAddResult.ok();
    }

    public ItemAddResult addItemParam(long cid, String paramData) {
        ItemParam itemParam = new ItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        java.util.Date date = new java.util.Date();
        Date sqlDate = new Date(date.getTime());
        itemParam.setCreateTime(sqlDate);
        itemParam.setUpdateTime(sqlDate);
        itemParamDao.addItemParam(itemParam);
        return ItemAddResult.ok();
    }

    public PageBean<ItemParam> listItemParam(PageBean<ItemParam> pageBean) {
        List<ItemParam> itemParamList = itemParamDao.getAllItemParam();
        pageBean.setResult(itemParamList);
        pageBean.setTotal(itemParamDao.getTotalNum());
        return pageBean;
    }
}
