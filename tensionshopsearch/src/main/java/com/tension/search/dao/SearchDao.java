package com.tension.search.dao;

import com.tension.search.bean.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

public interface SearchDao {

    SearchResult search(SolrQuery solrQuery) throws SolrServerException;
}
