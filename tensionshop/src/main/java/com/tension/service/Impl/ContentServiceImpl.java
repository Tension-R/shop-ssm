package com.tension.service.Impl;

import com.tension.bean.Content;
import com.tension.bean.ItemAddResult;
import com.tension.bean.PageBean;
import com.tension.dao.ContentDao;
import com.tension.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService{

    @Autowired
    private ContentDao contentDao;

    public PageBean<Content> listByPage(long categoryId,PageBean<Content> pageBean) {
        List<Content> contentList = contentDao.getContentList(categoryId,pageBean.getStart(),pageBean.getEnd());

        //记录查询分页的结果
        pageBean.setResult(contentList);
        //记录查询总数
        pageBean.setTotal(contentDao.getTotalNum());
        return pageBean;
    }

    public ItemAddResult addContent(Content content) {

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        content.setCreateTime(sqlDate);
        content.setUpdateTime(sqlDate);
        contentDao.addContent(content);
        return ItemAddResult.ok();
    }
}
