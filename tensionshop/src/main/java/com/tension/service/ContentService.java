package com.tension.service;

import com.tension.bean.Content;
import com.tension.bean.ItemAddResult;
import com.tension.bean.PageBean;


public interface ContentService {
    PageBean<Content> listByPage(long categoryId, PageBean<Content> pageBean);

    ItemAddResult addContent(Content content);
}
