package com.tension.service;

import com.tension.bean.EasyUiTreeNode;

import java.util.List;

public interface ItemCatService {

    List<EasyUiTreeNode> getItemCatList(long parentId);
}
