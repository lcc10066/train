package com.spartaMonster.train.member.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;


@SpringBootApplication
@ComponentScan("com.spartaMonster")
//启动类默认只扫描当前包及子包下的组件，位于其他地方的需要开启扫描

@MapperScan("com.spartaMonster.train.member.mapper")
public class MemberApplication {

    private static final Logger LOG = LoggerFactory.getLogger(MemberApplication.class);
    public static void main(String[] args) {
//        SpringApplication.run(MemberApplication.class, args);
        SpringApplication app = new SpringApplication(MemberApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功");
        LOG.info("ADDRESS: http://127.0.0.1:{}{}/hello",env.getProperty("server.port"),
                env.getProperty("server.servlet.context-path"));
    }

}
