package com.tension.dao;

import com.tension.bean.ItemParamItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品规格信息Dao
 */
@Repository
public interface ItemParamItemDao {
    int insertItemParamItem(ItemParamItem itemParamItem);

//    List<ItemParamItem> getItemParamHtml(long itemId);
}
