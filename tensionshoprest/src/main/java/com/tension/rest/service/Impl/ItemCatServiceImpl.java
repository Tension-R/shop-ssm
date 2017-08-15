package com.tension.rest.service.Impl;

import com.tension.rest.bean.CatNode;
import com.tension.rest.bean.ItemCat;
import com.tension.rest.bean.ItemCatResult;
import com.tension.rest.component.JedisClient;
import com.tension.rest.dao.ItemCatDao;
import com.tension.rest.service.ItemCatService;
import com.tension.rest.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatDao itemCatDao;
    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_CONTENT_KEY}")
    private String REDIS_CONTENT_KEY;

    /**
     * 商品分类列表查询
     *
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
     *
     * @param parentId
     * @return
     */
    private List getItemCatList(long parentId) {

        //先查询缓存
        try {
            String json = jedisClient.hget(REDIS_CONTENT_KEY, parentId + "");
            if (!StringUtils.isBlank(json)) {
                List<ItemCat> list = JsonUtils.jsonToList(json, ItemCat.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //查询数据库
        List<ItemCat> itemCatList = itemCatDao.getItemCatByParentID(parentId);
        List resultList = new ArrayList();
        int index = 0;
        for (ItemCat itemCat : itemCatList) {
            if (index >= 14) {
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
            } else {
                //如果是叶子节点
                String item = "/products/" + itemCat.getId() + ".html|" + itemCat.getName();
                resultList.add(item);
            }
        }

        //返回结果之前 先添加到缓存
        try {
            //为了规范key，使用hset
            //定义一个保存内容的key，hash中每个项就是parentId
            //value是list，需要把list转换成json数据
            jedisClient.hset(REDIS_CONTENT_KEY, parentId + "", JsonUtils.objectToJson(resultList));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }


}
