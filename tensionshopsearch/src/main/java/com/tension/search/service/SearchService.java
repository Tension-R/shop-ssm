package com.tension.search.service;

import com.tension.search.bean.SearchResult;

public interface SearchService {
    SearchResult search(String queryString, int page, int rows) throws Exception;
}
