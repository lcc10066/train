## 开发笔记

### 项目介绍
 火车购票系统

模块架构
- member 成员信息模块
- common 公共类
  - aspect拦截日志
- gateway 网关模块

- web 前端页面模块

### 数据库设计
##### 库及相关软件的安装：
MySQL8、DBeaver

DBeaver下载地址： https://dbeaver.io/download/

连接时报错解决文档：https://www.imooc.com/article/321153

##### 表结构
member （id 、 mobile）

### 待学习知识点
- springGateway与Zuul的底层实现区别    NIO相关知识
- hutool中各种包的实现，如反射在对象拷贝中的应用
- token组成与验证原理