package com.tension.dao;

import com.tension.bean.ItemParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemParamDao {
    /**
     * 根据类目id获取规格参数模板
     * @param cid
     * @return
     */
    List<ItemParam> getItemParamByCid(long cid);

    /**
     * 插入规格参数模板
     * @param itemParam
     * @return
     */
    int addItemParam(ItemParam itemParam);

    /**
     * 分页查询所有商品规格参数模板
     * @return
     */
    List<ItemParam> getAllItemParam();

    /**
     * 获取商品规格参数模板总数
     * @return
     */
    int getTotalNum();
}
