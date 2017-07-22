package com.tension.service.Impl;

import com.tension.bean.EasyUiTreeNode;
import com.tension.bean.ItemCat;
import com.tension.dao.ItemCatDao;
import com.tension.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类管理Service
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatDao itemCatDao;

    public List<EasyUiTreeNode> getItemCatList(long parentId) {
        List<ItemCat> itemCatList = itemCatDao.getItemCatByParentID(parentId);
        List<EasyUiTreeNode> resultList = new ArrayList<EasyUiTreeNode>();
        for (ItemCat itemcat : itemCatList) {
            EasyUiTreeNode node = new EasyUiTreeNode();
            node.setId(itemcat.getId());
            node.setText(itemcat.getName());
            node.setState(itemcat.getIsParent() == 1 ? "closed" : "open");
            resultList.add(node);
        }
        return resultList;
    }
}
