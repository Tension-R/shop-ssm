package com.tension.dao;

import com.tension.bean.Content;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentDao {
    List<Content> getContentList(@Param("categoryId")long categoryId,@Param("start") int start, @Param("end") int end);

    int getTotalNum();

    int addContent(Content content);
}
