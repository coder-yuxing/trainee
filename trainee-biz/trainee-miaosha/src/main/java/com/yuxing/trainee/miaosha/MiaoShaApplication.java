package com.yuxing.trainee.miaosha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 秒杀项目
 *
 * @author yuxing
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.yuxing.trainee.miaosha.infrastructure.dao.mapper")
public class MiaoShaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiaoShaApplication.class, args);
    }
}
