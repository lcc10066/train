package com.spartaMonster.train.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;


@SpringBootApplication
@ComponentScan("com.spartaMonster")
//启动类默认只扫描当前包及子包下的组件，位于其他地方的需要开启扫描
public class GatewayApplication {

    /**
     * 启动虚拟机的参数：   添加请求日志  每次请求的信息
     * -Dreactor.netty.http.server.accessLogEnabled=true
     *
     * log example :
     * 36:43.495 INFO  r.netty.http.server.AccessLog :279  reactor-http-nio-2
     * 127.0.0.1 - - [02/7月/2023:21:36:43 +0800] "GET /member/hello HTTP/1.1" 200 14 8
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(GatewayApplication.class);
    public static void main(String[] args) {
//        SpringApplication.run(MemberApplication.class, args);
        SpringApplication app = new SpringApplication(GatewayApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功");
        LOG.info("网关: http://127.0.0.1:{}",env.getProperty("server.port"));
    }

}
