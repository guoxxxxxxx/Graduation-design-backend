# 毕业设计开发文档说明书

# 题目：大学生互助平台

## 说明：本人与2022年6月30日毕业，该项目为本人毕业设计，故在2022-06-30之前请勿完全照搬照用！2022-07-01之后欢迎大家使用该项目！！谢谢您的合作  联系邮箱：guo_x0315@163.com, -- power by guoXun



## 一、 需求说明

​	该平台开发主要目的时为在校大学生提供一个可以互帮互助的平台，主要提供跳蚤市场、快递/外卖代取、失物招领、校友圈、学科题目互助等多项功能。



## 二、概要设计

​	平台主要功能包括供跳蚤市场、快递/外卖代取、失物招领、校友圈、学科题目互助。



## 三、详细设计

**2022-03-14 设计, 后续改动将写在后面**

![image-20220328204640903](https://raw.githubusercontent.com/guoxxxxxxx/Pic-Go/main/img/image-20220328204640903.png)

---



​	**2022-03-22 改进**

![image-20220328204707679](https://raw.githubusercontent.com/guoxxxxxxx/Pic-Go/main/img/image-20220328204707679.png)





## 四、数据库设计

**2022-03-22**



**创建数据库**

``` sql
-- 创建数据库
CREATE DATABASE assistance;
USE assistance;
```



**user（用户信息表）**（实体）

| 字段      | 类型        | 说明                                             |
| --------- | ----------- | ------------------------------------------------ |
| uid       | int         | 用户id；主键，自增                               |
| name      | varchar(10) | 用户姓名；                                       |
| sex       | varchar(2)  | 用户性别；仅有（男，女，保密）三个值，默认为保密 |
| birthday  | date        | 用户生日；格式为yyyy-MM-dd                       |
| phone     | char(11)    | 用户电话号码；固定为11位                         |
| faculty   | varchar(20) | 用户所属院系                                     |
| grade     | char(4)     | 所属年级；例如2018                               |
| major     | varchar(20) | 所属专业                                         |
| email     | varchar(50) | 用户邮箱                                         |
| wechat    | varchar(30) | 用户微信号                                       |
| qq        | varchar(20) | 用户qq号                                         |
| password  | varchar(20) | 用户登录密码                                     |
| is_delete | int         | 是否删除；1为删除，0为未删除，默认为0            |

- 创建用户信息表

``` sql
-- 创建用户信息表
CREATE TABLE USER(
uid INT NOT NULL AUTO_INCREMENT COMMENT	'id',
NAME VARCHAR(10) COMMENT '姓名',
sex VARCHAR(2) DEFAULT '保密' COMMENT '性别, 仅有(男,女,保密)三个值, 默认为保密',
birthday DATE DEFAULT '2000-01-01' COMMENT '生日 格式为yyyy-MM-dd',
phone CHAR(11) COMMENT '电话号码, 固定长度为11位',
faculty VARCHAR(20) COMMENT '所属院系',
grade CHAR(4) COMMENT '所属年级, 例如: 2018',
major VARCHAR(20) COMMENT '所属专业',
email VARCHAR(50) COMMENT '邮箱',
wechat VARCHAR(30) COMMENT '微信号',
qq VARCHAR(20) COMMENT 'qq号',
is_delete INT DEFAULT 0 COMMENT '是否删除, 1: 删除; 0: 未删除; 默认为0',
PRIMARY KEY (uid)
);
```





**errand(跑腿区信息表)**（实体）

| 字段       | 类型         | 说明                                          |
| ---------- | ------------ | --------------------------------------------- |
| eid        | int          | 订单id，主键自增                              |
| uid        | int          | 发布订单用户的信息，外键user(uid)             |
| euid       | int          | 接单用户信息，外键user(uid)                   |
| title      | varchar(30)  | 订单题目                                      |
| details    | varchar(500) | 订单详细信息                                  |
| money      | dobule       | 订单费用，默认为0                             |
| is_achieve | int          | 是否已完成，0代表未完成，1代表已完成；默认为0 |
| category   | varchar(5)   | 类别，（外卖，快递，打水，其他）              |
| pubdate    | date         | 发布日期                                      |
| deadline   | date         | 截至日期                                      |
| is_delete  | int          | 是否删除；1为删除，0未删除，默认为0           |

- 创建跑腿区信息表

``` sql
-- 创建跑腿区信息表
CREATE TABLE errand(
eid INT AUTO_INCREMENT COMMENT '主键',
uid INT COMMENT '发布订单用户信息 外: user(uid)',
euid INT  COMMENT '接单用户信息 外: user(uid)',
title VARCHAR(30) COMMENT '订单信息',
money DOUBLE DEFAULT 0 COMMENT '订单费用, 默认为0',
is_achieve INT DEFAULT 0 COMMENT '是否已完成, 0: 未完成; 1: 已完成;',
category VARCHAR(5) COMMENT '类别, 可选值为(快递, 外卖, 打水, 其他)',
pubdate DATE COMMENT '发布日期',
deadline DATE COMMENT '截止日期',
is_delete INT DEFAULT 0 COMMENT '是否删除, 1: 删除; 0: 未删除; 默认为0',
PRIMARY KEY (eid),
FOREIGN KEY (uid) REFERENCES USER(uid),
FOREIGN KEY (euid) REFERENCES USER(uid)
);
```





**study(学习交流表)**（实例）

| 字段       | 类型         | 说明                                      |
| ---------- | ------------ | ----------------------------------------- |
| sid        | int          | 主键，自增                                |
| uid        | int          | 发帖用户id，user(uid)外键                 |
| category   | varchar(15)  | 类别，可选项为: 数学，物理，英语，其他    |
| title      | varchar(30)  | 题目                                      |
| details    | varchar(500) | 详细内容                                  |
| pubdate    | date         | 发布时间                                  |
| is_achieve | int          | 是否已解决，1：已解决，0：未解决；默认为0 |
| is_delete  | int          | 是否已删除， 1：删除 0：未删除 默认为0    |

- 创建学习交流表

``` sql
-- 创建学习交流表
CREATE TABLE study(
sid INT AUTO_INCREMENT COMMENT '主键',
uid INT COMMENT '发帖用户, 外：user(uid)' ,
category VARCHAR(15) COMMENT '类别,可选项为: 数学, 物理, 英语, 其他', 
title VARCHAR(30) COMMENT '题目',
details VARCHAR(500) COMMENT '详细内容',
pubdate DATE COMMENT '提交时间',
is_achieve INT DEFAULT 0 COMMENT '是否已解决, 1:已解决 0:未解决 默认为0',
is_delete INT DEFAULT 0 COMMENT '是否删除, 1: 删除; 0: 未删除; 默认为0',
PRIMARY KEY (sid),
FOREIGN KEY (uid) REFERENCES USER(uid)
);
```





**trade（交易区）**（实体）

| 字段      | 类型         | 说明                                              |
| --------- | ------------ | ------------------------------------------------- |
| tid       | int          | 主键，自增                                        |
| uid       | int          | 发布信息的用户，user(uid)外键                     |
| category  | varchar(5)   | 类别，可选项为:出行工具，书本资料，生活用品和其他 |
| title     | varchar(30)  | 题目                                              |
| details   | varchar(500) | 详细内容                                          |
| pubdate   | date         | 发布日期                                          |
| is_delete | int          | 是否删除， 0未删除， 1 已删除 默认为0；           |

- 创建交易表

``` sql
-- 创建交易表
CREATE TABLE trade(
tid INT AUTO_INCREMENT COMMENT '主键',
uid INT COMMENT '发布信息的用户 外: user(uid)',
category VARCHAR(5) COMMENT '类别, 选项为: 出行工具, 书本资料, 生活用品和其他' ,
title VARCHAR(30) COMMENT '题目',
details VARCHAR(500) COMMENT '详细内容',
pubdate DATE COMMENT '发布日期',
is_delete INT DEFAULT 0 COMMENT '是否删除, 1: 删除; 0: 未删除; 默认为0',
PRIMARY KEY(tid),
FOREIGN KEY (uid) REFERENCES USER(uid)  
);
```





**lost_found（失物招领）**（实体）

| 字段      | 类型         | 说明                                    |
| --------- | ------------ | --------------------------------------- |
| lid       | int          | 主键，自增                              |
| uid       | int          | 发布信息的用户，user(uid)外键           |
| type      | int          | 类型，可选：0：找失主，1：找物品        |
| title     | varchar(30)  | 题目                                    |
| details   | varchar(500) | 详细内容                                |
| is_find   | int          | 是否找到; 0未找到，1找到                |
| pubdate   | date         | 发布日期                                |
| is_delete | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 失物招领表
CREATE TABLE lost_found(
lid INT AUTO_INCREMENT COMMENT '主键',
uid INT COMMENT '发布信息的用户 外: user(uid)',
TYPE INT COMMENT '类型，可选：0：找失主，1：找物品',
title VARCHAR(30) COMMENT '题目',
details VARCHAR(500) COMMENT '详细内容',
is_find INT DEFAULT 0 COMMENT '是否已经找到, 0未找到, 1找到',
pubdate DATE COMMENT '发布日期',
is_delete INT DEFAULT 0 COMMENT '是否删除, 1: 删除; 0: 未删除; 默认为0',
PRIMARY KEY(lid),
FOREIGN KEY (uid) REFERENCES USER(uid)
);
```





**help（互助）**（实体）

| 字段      | 类型         | 说明                                    |
| --------- | ------------ | --------------------------------------- |
| hid       | int          | 主键，自增                              |
| uid       | int          | 发布信息的用户，user(uid)外键           |
| title     | varchar(30)  | 题目                                    |
| details   | varchar(500) | 详细内容                                |
| pubdate   | date         | 发布日期                                |
| is_delete | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 互助表
CREATE TABLE HELP(
hid INT AUTO_INCREMENT COMMENT '主键',
uid INT COMMENT '发布信息的用户 外: user(uid)',
title VARCHAR(30) COMMENT '题目',
details VARCHAR(500) COMMENT '详细内容',
pubdate DATE COMMENT '发布日期',
is_delete INT DEFAULT 0 COMMENT '是否删除, 1: 删除; 0: 未删除; 默认为0',
PRIMARY KEY (hid),
FOREIGN KEY (uid) REFERENCES USER(uid)
);
```





**s_discuss(用户学习讨论表)**（关系）

| 字段      | 类型         | 说明                                    |
| --------- | ------------ | --------------------------------------- |
| id        | int          | 主键，自增                              |
| uid       | int          | 发布评论的用户信息 外键  user(uid)      |
| sid       | int          | 发布评论的指定帖子 外键 study(sid)      |
| comment   | varchar(500) | 讨论的内容                              |
| pubdate   | date         | 发布日期                                |
| is_delete | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 用户学习讨论表
CREATE TABLE s_discuss(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT COMMENT '发布信息的用户 外: user(uid)',
sid INT COMMENT '发布评论的指定帖子 外: study(sid)',
COMMENT VARCHAR(500) COMMENT '讨论的内容',
pubdate DATE COMMENT '发布日期',
is_delete INT DEFAULT 0 COMMENT '是否删除, 1: 删除; 0: 未删除; 默认为0',
PRIMARY KEY (id),
FOREIGN KEY (uid) REFERENCES USER(uid),
FOREIGN KEY (sid) REFERENCES study(sid)  
);
```





**s_reply(学习评论回复表)**（关系）

| 字段       | 类型         | 说明                                    |
| ---------- | ------------ | --------------------------------------- |
| id         | int          | 主键，自增                              |
| uid        | int          | 发布评论的用户，外键user(uid)           |
| reply_uid  | int          | 回复用户id 外键user(uid)                |
| discuss_id | int          | 回复的帖子的id 外键s_discuss(id)        |
| pubdate    | date         | 发布日期                                |
| reply      | varchar(500) | 回复内容                                |
| is_delete  | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 学习评论回复表
CREATE TABLE s_reply(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT COMMENT '发布信息的用户 外: user(uid)',
reply_uid INT COMMENT '回复用户id 外: user(uid)',
discuss_id INT COMMENT '回复的帖子的id 外s_discuss(id)',
pubdate DATE COMMENT '发布日期',
reply VARCHAR(500) COMMENT '回复内容',
is_delete INT DEFAULT 0 COMMENT '是否删除, 1: 删除; 0: 未删除; 默认为0',
PRIMARY KEY (id),
FOREIGN KEY (uid) REFERENCES USER(uid),
FOREIGN KEY (reply_uid) REFERENCES USER(uid),
FOREIGN KEY (discuss_id) REFERENCES s_discuss(id)
);
```





**t_discuss(交易评论表)**（关系）

| 字段      | 类型         | 说明                                    |
| --------- | ------------ | --------------------------------------- |
| id        | int          | 主键，自增                              |
| uid       | int          | 用于记录发帖用户信息 外键user(uid)      |
| tid       | int          | 发布评论所属帖子 外键trade(tid)         |
| comment   | varchar(500) | 评论内容                                |
| pubdate   | date         | 发布日期                                |
| is_delete | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 交易评论表
CREATE TABLE t_discuss(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT COMMENT '发布信息的用户 外: user(uid)',
tid INT COMMENT '发布评论所属帖子 外键trade(tid)',
COMMENT VARCHAR(500) COMMENT '讨论的内容',
pubdate DATE COMMENT '发布日期',
is_delete INT DEFAULT 0 COMMENT '是否删除, 1: 删除; 0: 未删除; 默认为0',
PRIMARY KEY (id),
FOREIGN KEY (uid) REFERENCES USER(uid),
FOREIGN KEY (tid) REFERENCES trade(tid)
);
```





**t_reply(交易回复表)**(关系)

| 字段       | 类型         | 说明                                    |
| ---------- | ------------ | --------------------------------------- |
| id         | int          | 主键，自增                              |
| uid        | int          | 发布回复信息的用户 外键user(uid)        |
| reply_uid  | int          | 回复用户的id 外键user(uid)              |
| discuss_id | int          | 回复帖子id 外键t_discuss(id)            |
| reply      | varchar(500) | 回复内容                                |
| pubdate    | date         | 发布日期                                |
| is_delete  | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 交易回复表
CREATE TABLE t_reply(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT COMMENT '发布信息的用户 外: user(uid)',
reply_uid INT COMMENT '回复用户id 外: user(uid)',
discuss_id INT COMMENT '回复的帖子的id 外t_discuss(id)',
pubdate DATE COMMENT '发布日期',
reply VARCHAR(500) COMMENT '回复内容',
is_delete INT DEFAULT 0 COMMENT '是否删除, 1: 删除; 0: 未删除; 默认为0',
PRIMARY KEY (id),
FOREIGN KEY (uid) REFERENCES USER(uid),
FOREIGN KEY (reply_uid) REFERENCES USER(uid),
FOREIGN KEY (discuss_id) REFERENCES t_discuss(id)
);
```





**f_discuss(失物招领评论表)**

| 字段      | 类型         | 说明                                    |
| --------- | ------------ | --------------------------------------- |
| id        | int          | 主键，自增                              |
| uid       | int          | 发布评论的用户id 外键user(uid)          |
| lid       | int          | 发布评论所属帖子 外键lost_found(lid)    |
| comment   | varchar(500) | 评论信息                                |
| pubdate   | date         | 发布日期                                |
| is_delete | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 失物招领评论表
CREATE TABLE f_discuss(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT COMMENT '发布信息的用户 外: user(uid)',
lid INT COMMENT '发布评论所属帖子 外键lost_found(lid)',
COMMENT VARCHAR(500) COMMENT '讨论的内容',
pubdate DATE COMMENT '发布日期',
is_delete INT DEFAULT 0 COMMENT '是否删除, 1: 删除; 0: 未删除; 默认为0',
PRIMARY KEY (id),
FOREIGN KEY (uid) REFERENCES USER(uid),
FOREIGN KEY (lid) REFERENCES lost_found(lid)
);
```



**f_reply(失物招领回复表)**(关系)

| 字段       | 类型         | 说明                                    |
| ---------- | ------------ | --------------------------------------- |
| id         | int          | 主键，自增                              |
| uid        | int          | 发布回复信息的用户 外键user(uid)        |
| reply_uid  | int          | 回复用户的id 外键user(uid)              |
| discuss_id | int          | 回复帖子id 外键f_discuss(id)            |
| reply      | varchar(500) | 回复内容                                |
| pubdate    | date         | 发布日期                                |
| is_delete  | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 失物招领回复表
CREATE TABLE f_reply(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT COMMENT '发布信息的用户 外: user(uid)',
reply_uid INT COMMENT '回复用户id 外: user(uid)',
discuss_id INT COMMENT '回复的帖子的id 外f_discuss(id)',
pubdate DATE COMMENT '发布日期',
reply VARCHAR(500) COMMENT '回复内容',
is_delete INT DEFAULT 0 COMMENT '是否删除, 1: 删除; 0: 未删除; 默认为0',
PRIMARY KEY (id),
FOREIGN KEY (uid) REFERENCES USER(uid),
FOREIGN KEY (reply_uid) REFERENCES USER(uid),
FOREIGN KEY (discuss_id) REFERENCES f_discuss(id)
);
```



**h_discuss(互助评论表)**

| 字段      | 类型         | 说明                                    |
| --------- | ------------ | --------------------------------------- |
| id        | int          | 主键，自增                              |
| uid       | int          | 发布评论的用户id 外键user(uid)          |
| hid       | int          | 发布评论所属帖子 外键help(hid)          |
| comment   | varchar(500) | 评论信息                                |
| pubdate   | date         | 发布日期                                |
| is_delete | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 互助评论表
CREATE TABLE h_discuss(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT COMMENT '发布信息的用户 外: user(uid)',
hid INT COMMENT '发布评论所属帖子 外键help(hid)',
COMMENT VARCHAR(500) COMMENT '讨论的内容',
pubdate DATE COMMENT '发布日期',
is_delete INT DEFAULT 0 COMMENT '是否删除, 1: 删除; 0: 未删除; 默认为0',
PRIMARY KEY (id),
FOREIGN KEY (uid) REFERENCES USER(uid),
FOREIGN KEY (hid) REFERENCES HELP(hid)
);
```





**h_reply(互助回复表)**(关系)

| 字段       | 类型         | 说明                                    |
| ---------- | ------------ | --------------------------------------- |
| id         | int          | 主键，自增                              |
| uid        | int          | 发布回复信息的用户 外键user(uid)        |
| reply_uid  | int          | 回复用户的id 外键user(uid)              |
| discuss_id | int          | 回复帖子id 外键h_discuss(id)            |
| reply      | varchar(500) | 回复内容                                |
| pubdate    | date         | 发布日期                                |
| is_delete  | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 互助回复表
CREATE TABLE h_reply(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT COMMENT '发布信息的用户 外: user(uid)',
reply_uid INT COMMENT '回复用户id 外: user(uid)',
discuss_id INT COMMENT '回复的帖子的id 外h_discuss(id)',
pubdate DATE COMMENT '发布日期',
reply VARCHAR(500) COMMENT '回复内容',
is_delete INT DEFAULT 0 COMMENT '是否删除, 1: 删除; 0: 未删除; 默认为0',
PRIMARY KEY (id),
FOREIGN KEY (uid) REFERENCES USER(uid),
FOREIGN KEY (reply_uid) REFERENCES USER(uid),
FOREIGN KEY (discuss_id) REFERENCES h_discuss(id)
);
```



**mail_verif邮箱验证码暂存表(已弃用)**

| 字段名     | 类型        | 说明             |
| ---------- | ----------- | ---------------- |
| id         | int         | 主键自增         |
| email      | varchar(50) | 用户邮箱，unique |
| verif_code | varchar(6)  | 随机生成的验证码 |

``` sql
-- 用于存放邮箱验证码
CREATE TABLE mail_verif(
id INT AUTO_INCREMENT COMMENT '主键',
email VARCHAR(50) UNIQUE COMMENT '用户邮箱',
verif_code VARCHAR(6) COMMENT '随机生成的验证码',
PRIMARY KEY (id)
);
```



## 五、接口设计

### 一、登录

**1. 登录**

- 名称： login
- 描述：用户登录
- URL：http://localhost:8080/user/login
- 请求方式：POST
- 请求参数

| 字段     | 说明     | 类型   | 是否必须 | 备注 |
| -------- | -------- | ------ | -------- | ---- |
| email    | 用户邮箱 | String | 是       |      |
| password | 密码     | String | 是       |      |

- 请求参数示例

JSON格式数据

``` json
{
    "email":"lyb@163.com",
    "password":"password"
}
```

- 响应结果

| 字段   | 说明               | 类型   | 是否必须 | 备注                 |
| ------ | ------------------ | ------ | -------- | -------------------- |
| status | 表示执行成功或失败 | String | 是       | 0表示成功，1表示失败 |
| msg    | 响应消息           | String | 是       |                      |

- 响应结果实例

```
// 成功
{
	"message":"success",
	"status":"200",
	"object":{"msg":"success", "status":"0"}
}

// 失败
{
	"message":"fail",
	"status":"400",
	"object":{"msg":"fail", "status":"1"}
}
```



### 二、邮箱验证码

**1. 获取验证码**

- 名称:	getCode
- 描述：获取随机验证码
- URL: http://localhost:8080/mail/getCode
- 请求方式: GET
- 请求参数

| 字段        | 说明                   | 类型   | 是否必须 | 备注 |
| ----------- | ---------------------- | ------ | -------- | ---- |
| targetEmail | 所要接收邮件的邮箱地址 | String | 是       |      |

- 请求参数示例

``` json
{
    "email":"guo_x0315@163.com"
}
```

- 响应结果

| 字段    | 说明               | 类型   | 是否必须 | 备注                       |
| ------- | ------------------ | ------ | -------- | -------------------------- |
| status  | 表示执行成功或失败 | String | 是       | 0表示成功，1表示失败       |
| message | 响应消息           | String | 是       |                            |
| code    | 验证码             | String | 是       | 随机生成的验证码，用于校验 |

- 响应结果示例

``` json
// 成功
{
	"message":"success",
	"status":"200",
    "code":"ASDFGH",
}

// 失败
{
	"message":"fail",
	"status":"400",
	"code":"null"
}
```



### 三、用户注册

- 名称:	register
- 描述：获取随机验证码
- URL: http://localhost:8080/user/register
- 请求方式: POST
- 请求参数

| 字段     | 说明 | 类型   | 是否必须 | 备注 |
| -------- | ---- | ------ | -------- | ---- |
| name     | 姓名 | String | 是       |      |
| email    | 邮箱 | String | 是       |      |
| password | 密码 | String | 是       |      |

- 请求参数示例

``` json
{
    "name":"username",
    "emial":"abc@123.com",
    "password":"123456"
}
```

- 响应结果

| 字段    | 说明               | 类型   | 是否必须 | 备注                     |
| ------- | ------------------ | ------ | -------- | ------------------------ |
| status  | 表示执行成功或失败 | String | 是       | 200表示成功，40X表示失败 |
| message | 响应消息           | String | 是       |                          |

- 响应示例

``` json
// 成功
{
	"message":"success",
	"status":"200",
}

// 失败
// 普通失败
{
	"message":"fail",
	"status":"400",
}
// 邮箱已被注册失败
{
	"message":"fail",
	"status":"401",
}
```



### 四、找回密码

- 名称:	forget_password
- 描述：找回密码
- URL: http://localhost:8080/user/forget_password
- 请求方式: POST
- 请求参数

| 字段     | 说明                 | 类型   | 是否必须 | 备注 |
| -------- | -------------------- | ------ | -------- | ---- |
| email    | 所需要找回密码的邮箱 | String | 是       |      |
| password | 新密码               | String | 是       |      |

- 请求参数示例

``` json
{
    "email":"guo_x0315@163.com",
    "password":"123456"
}
```

- 响应结果

| 字段    | 说明   | 类型   | 是否必须 | 备注             |
| ------- | ------ | ------ | -------- | ---------------- |
| status  | 状态码 | int    | 是       | 200成功，400失败 |
| message | 消息   | String | 否       |                  |

- 响应示例

``` json
// 成功
{
    "status":"200",
    "message":"success"
}

// 失败
{
    "status":"400",
    "message":"fail"
}
```









### 模板



- 名称:	
- 描述：
- URL: http://localhost:8080/
- 请求方式: 
- 请求参数

| 字段 | 说明 | 类型 | 是否必须 | 备注 |
| ---- | ---- | ---- | -------- | ---- |
|      |      |      |          |      |

- 请求参数示例

``` json

```

- 响应结果

| 字段 | 说明 | 类型 | 是否必须 | 备注 |
| ---- | ---- | ---- | -------- | ---- |
|      |      |      |          |      |

- 响应示例

``` json
// 成功
{
    "status":"200",
    "message":"success"
}

// 失败
{
    "status":"400",
    "message":"fail"
}

// 用户不存在
{
    "status":"401",
    "message":"fail"
}
```

