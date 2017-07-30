package com.tension.service;

import com.tension.bean.ItemAddResult;
import com.tension.bean.ItemParam;
import com.tension.bean.PageBean;

public interface ItemParamService {
    ItemAddResult getItemParamByCid(long cid);
    ItemAddResult addItemParam(long cid, String paramData);
    PageBean<ItemParam> listItemParam(PageBean<ItemParam> pageBean);
}
