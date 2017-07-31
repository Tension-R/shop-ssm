package com.tension.rest.dao;

import com.tension.rest.bean.ItemCat;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品分类Dao
 */
@Repository
public interface ItemCatDao {
    /**
     * 根据parentId查询
     * @return
     */
    List<ItemCat> getItemCatByParentID(long parentId);
}
