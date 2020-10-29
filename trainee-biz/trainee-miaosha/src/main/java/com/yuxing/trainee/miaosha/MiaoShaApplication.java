package com.yuxing.trainee.miaosha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 秒杀项目
 *
 * @author yuxing
 */
@Controller
@SpringBootApplication
public class MiaoShaApplication {

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(MiaoShaApplication.class, args);
    }
}
