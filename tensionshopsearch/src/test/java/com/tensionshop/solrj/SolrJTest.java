package com.tensionshop.solrj;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest {

    @Test
    public void testSolrJ() throws Exception{

        SolrServer solrServer = new HttpSolrServer("http://192.168.0.110:8080/solr");
        //创建文档
        SolrInputDocument document = new SolrInputDocument();

        //添加域
        document.addField("id","solrtest01");
        document.addField("item_title","测试商品");
        //添加到索引库
        solrServer.add(document);

        //提交
        solrServer.commit();
    }
}
