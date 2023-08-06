package com.spartaMonster.train.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {
    /**
     * RefreshScope注解
     * 当注册中心的配置更新，服务检测到后， 会自动更新该注解修饰的注入对象的值
     * org.springframework.cloud.context.config.annotation.RefreshScope
     * 说明该注解与Nacos无关，可无缝切换为其他注册中心
     *
     */

    /**
     *  读取配置文件配置的方式
     *
     *  如果想动态修改配置，可以基于Nacos来实现
     */

    /**
     * 配置好nacos的配置中心后，该变量的读取和更新会基于配置中心
     */

    @Value("${test.nacos}")
    private String testNacos;

    @Autowired
    Environment environment;

    @GetMapping("/hello")
    public String hello() {
        String port = environment.getProperty("local.server.port");
        return String.format("Hello %s! 端口：%s", testNacos, port);
    }
}
