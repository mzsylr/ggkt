package com.atguigu.ggkt.vod.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author mz_Andy
 * @Create 2023/02/15/18:55
 * @Description
 */
@Configuration
@MapperScan("com.atguigu.ggkt.vod.mapper")
public class VodConfig {
    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor innerInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
