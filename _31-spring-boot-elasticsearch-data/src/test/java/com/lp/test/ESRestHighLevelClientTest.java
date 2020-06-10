package com.lp.test;

import com.alibaba.fastjson.JSON;
import com.lp.AppElasticsearchData;
import com.lp.pojo.MyUser;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppElasticsearchData.class)
public class ESRestHighLevelClientTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引
     * @throws IOException
     */
    @Test
    public void testCreateIndex() throws IOException {
        // 创建索引
        CreateIndexRequest request = new CreateIndexRequest("mylpspringboottest");
        // 执行请求
        CreateIndexResponse createIndexResponse = this.restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);

    }

    /**
     * 判断索引是否存在
     * @throws IOException
     */
    @Test
    public void testExistIndex() throws IOException {
        // 创建索引
        GetIndexRequest request = new GetIndexRequest("mylpspringboottest");
        // 获取请求
        boolean exists = this.restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     * 删除索引
     * @throws IOException
     */
    @Test
    public void testDeleteIndex() throws IOException {
        // 创建索引
        DeleteIndexRequest request = new DeleteIndexRequest("mylpspringboottest");
        // 获取请求
        AcknowledgedResponse delete = this.restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    /**
     * 添加文档
     * @throws IOException
     */
    @Test
    public void testAddDocument() throws IOException {
        MyUser user = new MyUser();
        user.setName("user01");
        user.setAge(23);
        // 创建请求
        IndexRequest indexRequest = new IndexRequest("mylpspringboottest");
        indexRequest.id("1");
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        // 将数据放入请求 json
        indexRequest.source(JSON.toJSONString(user), XContentType.JSON);
        // 客户端发送请求
        IndexResponse indexResponse = this.restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status());
    }

    /**
     * 判断文档是否存在
     * @throws IOException
     */
    @Test
    public void testExistDocument() throws IOException {
        GetRequest getRequest = new GetRequest("mylpspringboottest", "1");
        // 不获取返回的 _source 的上下文
        getRequest.fetchSourceContext(new FetchSourceContext(false));

        boolean exists = this.restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     * 获取文档内容
     * @throws IOException
     */
    @Test
    public void testGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest("mylpspringboottest", "1");
        GetResponse documentFields = this.restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);

        System.out.println(documentFields.getSourceAsString());
    }

    /**
     * 更新文档
     * @throws IOException
     */
    @Test
    public void testUpdateDocument() throws IOException {
        MyUser user = new MyUser("user01", 24);
        UpdateRequest updateRequest = new UpdateRequest("mylpspringboottest", "1");
        updateRequest.timeout("1s");
        updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);

        UpdateResponse updateResponse = this.restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse);
    }

    /**
     * 删除文档
     * @throws IOException
     */
    @Test
    public void testDeleteDocument() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("mylpspringboottest", "1");
        deleteRequest.timeout("1s");

        DeleteResponse delete = this.restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(delete);
    }

    /**
     * 批量操作文档
     * @throws IOException
     */
    @Test
    public void testBulkDocument() throws IOException {
        List<MyUser> list = new ArrayList<>();
        list.add(new MyUser("user01", 20));
        list.add(new MyUser("user02", 30));
        list.add(new MyUser("user03", 40));
        list.add(new MyUser("user04", 50));
        list.add(new MyUser("user05", 70));
        list.add(new MyUser("user06", 80));

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        for (int i = 0; i < list.size(); i++) {
            // 批量更新、删除修改这里
            bulkRequest.add(
                    new IndexRequest("mylpspringboottest")
                            .id("" + i + 1)
                            .source(JSON.toJSONString(list.get(i)), XContentType.JSON)
            );
        }

        BulkResponse bulk = this.restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk);
    }

    /**
     * 复杂查询
     * @throws IOException
     */
    @Test
    public void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("mylpspringboottest");
        // 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 查询条件用 QueryBuilders 完成
        // QueryBuilders.termQuery 精确查询
        // QueryBuilders.matchAllQuery 匹配查询
        sourceBuilder.query(QueryBuilders.termQuery("name", "user04"));
        sourceBuilder.timeout(TimeValue.timeValueSeconds(60));
        sourceBuilder.from(0);
        sourceBuilder.size(3);

        searchRequest.source(sourceBuilder);
        SearchResponse search = this.restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(search);

    }

    /**
     * 复杂查询高亮
     * @throws IOException
     */
    @Test
    public void testSearchHighlighter() throws IOException {
        SearchRequest searchRequest = new SearchRequest("mylpspringboottest");
        // 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 查询条件用 QueryBuilders 完成
        // QueryBuilders.termQuery 精确查询
        // QueryBuilders.matchAllQuery 匹配查询
        sourceBuilder.query(QueryBuilders.termQuery("name", "user04"));
        sourceBuilder.timeout(TimeValue.timeValueSeconds(60));
        sourceBuilder.from(0);
        sourceBuilder.size(3);
        // 添加高亮查询规则
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        highlightBuilder.preTags("<p class='key' style='color:red'>");
        highlightBuilder.postTags("</p>");
        sourceBuilder.highlighter(highlightBuilder);

        searchRequest.source(sourceBuilder);
        SearchResponse search = this.restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(search);
    }


}
