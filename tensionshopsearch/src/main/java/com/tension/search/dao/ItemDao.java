package com.tension.search.dao;

import com.tension.search.bean.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDao {
    List<Item> getItemList();
}
