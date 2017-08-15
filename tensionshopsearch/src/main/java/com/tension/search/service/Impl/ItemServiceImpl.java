package com.tension.search.service.Impl;

import com.tension.search.bean.Item;
import com.tension.search.bean.ItemAddResult;
import com.tension.search.dao.ItemDao;
import com.tension.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private SolrServer solrServer;

    @Autowired
    private ItemDao itemDao;

    public ItemAddResult importItems() throws Exception{

        List<Item> itemList = itemDao.getItemList();

        for (Item item : itemList) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",item.getId());
            document.addField("item_title", item.getTitle());
            document.addField("address", item.getSell_point());
            document.addField("item_price", item.getPrice());
            document.addField("item_image", item.getImage());
            document.addField("item_category_name", item.getCategory_name());
            document.addField("item_desc", item.getItem_desc());

            solrServer.add(document);
        }
        solrServer.commit();
        return ItemAddResult.ok();
    }
}
