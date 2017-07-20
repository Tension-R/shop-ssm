package com.tension.dao;

import com.tension.bean.Item;
import org.springframework.stereotype.Repository;

/**
 * 商品dao
 */
@Repository
public interface ItemDao {

    Item getItemById(long itemId);
}
