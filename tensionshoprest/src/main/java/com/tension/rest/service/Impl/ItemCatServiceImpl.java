package com.tension.rest.service.Impl;

import com.tension.rest.bean.CatNode;
import com.tension.rest.bean.ItemCat;
import com.tension.rest.bean.ItemCatResult;
import com.tension.rest.dao.ItemCatDao;
import com.tension.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private ItemCatDao itemCatDao;

    /**
     * 商品分类列表查询
     * @return
     */
    public ItemCatResult getItemCatList() {
        List list = getItemCatList(0L);
        ItemCatResult itemCatResult = new ItemCatResult();
        itemCatResult.setData(list);
        return itemCatResult;
    }

    /**
     * 根据parentId查询列表
     * @param parentId
     * @return
     */
    private List getItemCatList(long parentId){
        List<ItemCat> itemCatList = itemCatDao.getItemCatByParentID(parentId);
        List resultList = new ArrayList();
        int index = 0;
        for (ItemCat itemCat : itemCatList) {
            if(index >=14){
                break;
            }
            //如果是父节点
            if (itemCat.getIsParent() != 0) {
                CatNode catNode = new CatNode();
                catNode.setUrl("/products/" + itemCat.getId() + ".html");

                if (itemCat.getParentId() == 0) {
                    catNode.setName("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
                    //第一级节点不能超过14个元素，index为计数器
                    index++;
                } else {
                    catNode.setName(itemCat.getName());
                }
                catNode.setItems(getItemCatList(itemCat.getId()));
                resultList.add(catNode);
            }else {
                //如果是叶子节点
                String item = "/products/"+itemCat.getId()+".html|"+itemCat.getName();
                resultList.add(item);
            }
        }
        return resultList;
    }
}
