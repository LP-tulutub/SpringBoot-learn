package com.lp.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient(){

        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("192.168.1.115", 9200, "http"),
                new HttpHost("192.168.1.116", 9200, "http")
                ));

        return client;
    }

}
