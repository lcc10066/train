## 开发笔记

### 项目介绍
 火车购票系统

模块架构
- member 成员信息模块
- common 公共类
- gateway 网关模块
- batch 定时任务 批处理模块
  - 基于基础数据 生成每日火车、车站、车厢、车座、车票 数据
- web 前端页面模块

### 数据库设计
##### 库及相关软件的安装：
MySQL8、DBeaver

##### 表结构


### 知识点
- springGateway与Zuul的底层实现区别    NIO相关知识
- hutool中各种包的实现，如反射在对象拷贝中的应用copyProperties
- 两种单点登录方案
  - redis+token (生成的token存储至redis中，token为key，用户信息为value)
  - jwt (也可以把token放到redis中，一般没用，访问时使用工具包解密校验信息)
    - 存在问题：
    - 1. token被拦截解密： 加盐值（密钥）
    - 2. token被第三方包装给更多用户使用：服务端限流
- token组成与验证原理： header + payload + 签名

- 前端使用vuex保存用户登录后的token、用户信息

- 拦截器和过滤器的区别以及原理
