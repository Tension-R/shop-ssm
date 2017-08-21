package com.tension.portal.service;

import com.tension.portal.bean.SearchResult;

public interface SearchService {
    SearchResult search(String keyword, int page, int rows);
}
