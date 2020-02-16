package com.lp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot默认支持两种技术来和ES交互；
 * 1、Jest（默认不生效）
 * 	需要导入jest的工具包（io.searchbox.client.JestClient）
 */
@SpringBootApplication
public class AppElasticsearchJest {
    public static void main(String[] args) {
        SpringApplication.run(AppElasticsearchJest.class, args);
    }
}
