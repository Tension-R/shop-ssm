package com.tension.search.service;

import com.tension.search.bean.ItemAddResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface ItemService {
    ItemAddResult importItems() throws Exception;
}
