package com.spartaMonster.train.business.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan("com.spartaMonster")
@MapperScan("com.spartaMonster.train.*.mapper")
@EnableFeignClients("com.spartaMonster.train.business.feign")
public class BusinessApplication {

	private static final Logger LOG = LoggerFactory.getLogger(BusinessApplication.class);

	public static void main(String[] args) {

//		SpringApplication.run(BusinessApplication.class, args);
		SpringApplication app = new SpringApplication(BusinessApplication.class);
		Environment env = app.run(args).getEnvironment();
	}

}
