package com.tension.dao;

import com.tension.bean.ItemCat;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品分类管理dao
 */
@Repository
public interface ItemCatDao {
    List<ItemCat> getItemCatByParentID(long parentId);
}
