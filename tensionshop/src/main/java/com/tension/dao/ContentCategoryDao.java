package com.tension.dao;

import com.tension.bean.ContentCategory;
import com.tension.bean.EasyUiTreeNode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 内容管理Dao
 */
@Repository
public interface ContentCategoryDao {
    /**
     * 获取内容分类列表
     * @param parentId
     * @return
     */
    List<ContentCategory> getContentCatList(long parentId);

    int insertCategory(ContentCategory category);
}
