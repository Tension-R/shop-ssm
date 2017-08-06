package com.tension.service.Impl;

import com.tension.bean.ContentCategory;
import com.tension.bean.EasyUiTreeNode;
import com.tension.bean.ItemAddResult;
import com.tension.dao.ContentCategoryDao;
import com.tension.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{

    @Autowired
    private ContentCategoryDao contentCategoryDao;

    public List<EasyUiTreeNode> getContentCatList(long parentId) {
        List<ContentCategory> contentCatList = contentCategoryDao.getContentCatList(parentId);
        List<EasyUiTreeNode> resultList = new ArrayList<EasyUiTreeNode>();
        for (ContentCategory contentCategory : contentCatList) {
            EasyUiTreeNode easyUiTreeNode = new EasyUiTreeNode();
            easyUiTreeNode.setId(contentCategory.getId());
            easyUiTreeNode.setText(contentCategory.getName());
            easyUiTreeNode.setState(contentCategory.getIsParent()==1?"closed":"open");
            resultList.add(easyUiTreeNode);
        }
        
        return resultList;
    }

    public ItemAddResult insertCategory(Long parentId, String name) {
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setName(name);
        contentCategory.setParentId(parentId);
        contentCategory.setStatus(1);
        contentCategory.setIsParent(0);
        contentCategory.setSortOrder(1);
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        contentCategory.setCreateTime(sqlDate);
        contentCategory.setUpdateTime(sqlDate);
        contentCategoryDao.insertCategory(contentCategory);

        long id = contentCategory.getId();

        return ItemAddResult.ok(id);
    }
}
