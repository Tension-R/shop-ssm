package com.tension.search.dao.Impl;

import com.tension.search.bean.Item;
import com.tension.search.bean.SearchResult;
import com.tension.search.dao.SearchDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SolrServer solrServer;

    public SearchResult search(SolrQuery solrQuery) throws SolrServerException {

        QueryResponse response = solrServer.query(solrQuery);

        SolrDocumentList solrDocumentList = response.getResults();

        List<Item> itemList = new ArrayList<Item>();
        for (SolrDocument solrDocument : solrDocumentList) {
            Item item = new Item();
            item.setCategory_name((String) solrDocument.get("item_category_name"));
            item.setId((String) solrDocument.get("id"));
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((Long) solrDocument.get("item_price"));
            item.setSell_point((String) solrDocument.get("item_sell_point"));
            //取高亮显示
            Map<String, Map<String, List<String>>> highLighting = response.getHighlighting();
            List<String> list = highLighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle = "";

            if (list != null && list.size() > 0) {
                itemTitle = list.get(0);
            } else {
                itemTitle = (String) solrDocument.get("item_title");
            }
            item.setTitle(itemTitle);


            itemList.add(item);
        }
        SearchResult result = new SearchResult();
        result.setItemList(itemList);
        result.setRecordCount(solrDocumentList.getNumFound());

        return result;
    }
}
