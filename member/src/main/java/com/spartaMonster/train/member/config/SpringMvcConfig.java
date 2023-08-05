package com.spartaMonster.train.member.config;

import com.spartaMonster.train.common.interceptor.LogInterceptor;
import com.spartaMonster.train.common.interceptor.MemberInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    /*
       不是每个模块都需要使用位于common模块的拦截器，模块内部可以实现WebMvcConfigurer接口来配置标明本模块需要使用的拦截器
       同时可以进行url配置
     */
   @Resource
    LogInterceptor logInterceptor;

   @Resource
   MemberInterceptor memberInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(logInterceptor);

       // 路径不要包含context-path
       registry.addInterceptor(memberInterceptor)
               .addPathPatterns("/**")
               .excludePathPatterns(
                       "/hello",
                       "/member/send-code",
                       "/member/login"
               );
   }
}
