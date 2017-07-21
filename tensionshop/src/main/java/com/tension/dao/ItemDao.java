package com.tension.dao;

import com.tension.bean.Item;
import com.tension.bean.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 商品dao
 */
@Repository
public interface ItemDao {

    Item getItemById(long itemId);

    /**
     * 分页查询商品列表
     * @return
     */
    List<Item> listItemByPage(@Param("start") int start, @Param("end") int end);

    long getTotalNum();
}
