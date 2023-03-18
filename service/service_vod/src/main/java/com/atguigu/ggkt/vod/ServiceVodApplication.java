package com.atguigu.ggkt.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


/**
 * @Author mz_Andy
 * @Create 2023/02/15/18:52z
 * @Description
 */
@SpringBootApplication
@ComponentScan("com.atguigu")
@EnableDiscoveryClient
public class ServiceVodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class, args);
    }
}
