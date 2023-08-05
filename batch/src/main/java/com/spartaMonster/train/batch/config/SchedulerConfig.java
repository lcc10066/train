package com.spartaMonster.train.batch.config;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SchedulerConfig {

    @Resource
    private MyJobFactory myJobFactory;

    /**
     * 配置quartz定时任务存储的mysql数据库源
     * 其表信息可从官方下载构建SQL，位于sql/batch.sql
     *
     * 会将定时任务的元数据存储至数据库中
     *
     * @param dataSource
     * @return
     * @throws IOException
     */

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("dataSource") DataSource dataSource) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setJobFactory(myJobFactory);
        factory.setStartupDelay(2);  // springboot任务启动后，两秒后才会启动该定时任务
        return factory;
    }
}
