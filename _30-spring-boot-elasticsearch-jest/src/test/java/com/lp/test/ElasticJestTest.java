package com.lp.test;


import com.lp.pojo.Article;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.ClearCache;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticJestTest {

	@Autowired
	private JestClient jestClient;

	//创建索引
	@Test
	public void create() throws IOException {
		CreateIndex createIndex = new CreateIndex.Builder("lp_test").build();
		jestClient.execute(createIndex);
	}

	//保存
	@Test
	public void contextLoads() throws IOException {
		//1、给Es中索引（保存）一个文档；
		Article article = new Article();
		article.setId(1);
		article.setTitle("title hello");
		article.setAuthor("张三");
		article.setContent("Hello World");

		//构建一个索引功能
		Index index = new Index.Builder(article).index("lp_test").type("article").build();

		//执行
		jestClient.execute(index);
	}

	//搜索
	@Test
	public void search() throws IOException {

		//查询表达式
		String json ="{\n" +
				"    \"query\" : {\n" +
				"        \"match\" : {\n" +
				"            \"content\" : \"hello\"\n" +
				"        }\n" +
				"    }\n" +
				"}";

		//更多操作：https://github.com/searchbox-io/Jest/tree/master/jest
		//构建搜索功能
		Search search = new Search.Builder(json).addIndex("lp_test").addType("article").build();

		//执行
		SearchResult result = jestClient.execute(search);
		System.out.println(result.getJsonString());

	}

	//修改
	@Test
	public void update() throws IOException {
		Article article = new Article();
		article.setId(1);
		article.setTitle("title hello");
		article.setAuthor("张三");
		article.setContent("Hello World2");

		Index index = new Index.Builder(article).index("lp_test").type("article").id(article.getId().toString()).build();
		//执行
		jestClient.execute(index);
	}

	//删除索引
	@Test
	public void delete() throws IOException {

		DeleteIndex deleteIndex = new DeleteIndex.Builder("lp_test").build();
		jestClient.execute(deleteIndex);
	}

	//清理缓存
	@Test
	public void clearCache() throws IOException {

		ClearCache clearCache = new ClearCache.Builder().build();
		jestClient.execute(clearCache);
	}
}
