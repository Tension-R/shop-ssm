package com.tension.service;

import com.tension.bean.EasyUiTreeNode;
import com.tension.bean.ItemAddResult;

import java.util.List;

/**
 *  内容管理Service
 */
public interface ContentCategoryService {
    List<EasyUiTreeNode> getContentCatList(long parentId);

    ItemAddResult insertCategory(Long parentId,String name);
}
