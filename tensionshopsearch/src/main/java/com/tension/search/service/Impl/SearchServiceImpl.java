package com.tension.search.service.Impl;

import com.tension.search.bean.SearchResult;
import com.tension.search.dao.Impl.SearchDaoImpl;
import com.tension.search.dao.SearchDao;
import com.tension.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Solr查询service
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDaoImpl searchDaoImpl;

    public SearchResult search(String queryString, int page, int rows) throws Exception {

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(queryString);

        solrQuery.setStart((page - 1) * rows);
        solrQuery.setRows(rows);

        //默认搜索域
        solrQuery.set("df", "item_title");

        //设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em>");
        solrQuery.setHighlightSimplePost("</em>");

        SearchResult result = searchDaoImpl.search(solrQuery);

        //计算总页数
        Long recordCount = result.getRecordCount();
        int pageCount = (int) (recordCount / rows);
        if (recordCount % rows > 0) {
            pageCount++;
        }
        result.setPageCount(pageCount);
        result.setCurPage(page);
        return result;
    }
}
