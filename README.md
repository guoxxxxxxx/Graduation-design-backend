# 	毕业设计开发文档说明书

# 题目：大学生互助平台

## 说明

**本人与2022年6月30日毕业，该项目为本人毕业设计，故在2022-06-30之前请勿完全照搬照用！2022-07-01之后欢迎大家使用该项目！！谢谢您的合作  联系邮箱：guo_x0315@163.com**

**-- power by guoXun**

------

​		

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



#### 1) **user（用户信息表）**（实体）

| 字段        | 类型         | 说明                                             |
| ----------- | ------------ | ------------------------------------------------ |
| uid         | int          | 用户id；主键，自增                               |
| name        | varchar(10)  | 用户姓名；                                       |
| sex         | varchar(2)   | 用户性别；仅有（男，女，保密）三个值，默认为保密 |
| birthday    | date         | 用户生日；格式为yyyy-MM-dd                       |
| phone       | char(11)     | 用户电话号码；固定为11位                         |
| faculty     | varchar(20)  | 用户所属院系                                     |
| grade       | char(4)      | 所属年级；例如2018                               |
| major       | varchar(20)  | 所属专业                                         |
| email       | varchar(50)  | 用户邮箱                                         |
| wechat      | varchar(30)  | 用户微信号                                       |
| qq          | varchar(20)  | 用户qq号                                         |
| password    | varchar(20)  | 用户登录密码                                     |
| is_delete   | int          | 是否删除；1为删除，0为未删除，默认为0            |
| avatar_path | varchar(500) | 用户头像路径                                     |

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
password varchar(20) NOT NULL COMMENT '用户登录密码',
avatar_path varchar(500) NOT NULL COMMENT '用户头像路径',
PRIMARY KEY (uid)
);
```





#### 2) **errand(跑腿区信息表)**（实体）

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
details VARCHAR(500) COMMENT '订单详细信息',
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





#### 3)**study(学习交流表)**（实例）

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





#### 4) **trade（交易区）**（实体）

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





#### 5) **lost_found（失物招领）**（实体）

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





#### 6) **help（互助 校友圈）**（实体）

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





#### 7) **s_discuss(用户学习讨论表)**（关系）

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





#### 8) **s_reply(学习评论回复表)**（关系）

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





#### 9) **t_discuss(交易评论表)**（关系）

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





#### 10) **t_reply(交易回复表)**(关系)

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





#### 11) **f_discuss(失物招领评论表)**

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



#### 12) **f_reply(失物招领回复表)**(关系)

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



#### 13) **h_discuss(互助评论表)**

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





#### 14) **h_reply(互助回复表)**(关系)

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



#### 15) mail_verif邮箱验证码暂存表

| 字段名     | 类型        | 说明             |
| ---------- | ----------- | ---------------- |
| id         | int         | 主键自增         |
| email      | varchar(50) | 用户邮箱，unique |
| verif_code | varchar(6)  | 随机生成的验证码 |

``` sql
-- 用于存放邮箱验证码
CREATE TABLE mail_verify(
id INT AUTO_INCREMENT COMMENT '主键',
email VARCHAR(50) COMMENT '用户邮箱',
verify_code VARCHAR(6) COMMENT '随机生成的验证码',
UNIQUE(email)
PRIMARY KEY (id)
);
```





## 五、接口设计

### 1、登录

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
| object   | 用户信息 | User   | 是       |      |

- 请求参数示例

``` json
{
    "email":"lyb@163.com",
    "password":"password",
    "object": 用户信息对象
}
```

- 响应结果

| 字段    | 说明               | 类型   | 是否必须 | 备注     |
| ------- | ------------------ | ------ | -------- | -------- |
| status  | 表示执行成功或失败 | String | 是       |          |
| message | 响应消息           | String | 是       |          |
| object  | 对象               | object | 否       | 存放消息 |

- 响应结果实例

```json
// 登录成功
{
    "status": 200,
    "message": "success",
    "object": null
}

// 登录失败
{
    "status": 400,
    "message": "fail",
    "object": null
}

// 邮箱尚未被注册
{
    "status": 401,
    "message": "fail",
    "object": null
}
```



### 2、邮箱验证码

#### 1）已注册用户

- 名称:	getCode
- 描述：获取随机验证码
- URL: http://localhost:8080/mail/getCode
- 请求方式: GET
- 请求参数

| 字段  | 说明                   | 类型   | 是否必须 | 备注 |
| ----- | ---------------------- | ------ | -------- | ---- |
| email | 所要接收邮件的邮箱地址 | String | 是       |      |

- 请求参数示例

``` json
{
    "email":"guo_x0315@163.com"
}
```

- 响应结果

| 字段    | 说明               | 类型   | 是否必须 | 备注 |
| ------- | ------------------ | ------ | -------- | ---- |
| status  | 表示执行成功或失败 | String | 是       |      |
| message | 响应消息           | String | 是       |      |
| object  | 其他对象           | Object | 否       |      |

- 响应结果示例

``` json
// 成功
{
    "status": 200,
    "message": "success",
    "object": null
}

// 失败
{
	"message":"fail",
	"status":"400",
    "object": null
}

// 该邮箱不存在于数据库中
{
    "status": 401,
    "message": "fail",
    "object": null
}
```



#### 2）新用户获取验证码

- 名称:	getRegisterCode
- 描述：获取随机验证码
- URL: http://localhost:8080/mail/getRegisterCode
- 请求方式: GET
- 请求参数

| 字段  | 说明                   | 类型   | 是否必须 | 备注 |
| ----- | ---------------------- | ------ | -------- | ---- |
| email | 所要接收邮件的邮箱地址 | String | 是       |      |

- 请求参数示例

``` json
{
    "email":"guo_x0315@163.com"
}
```

- 响应结果

| 字段    | 说明               | 类型   | 是否必须 | 备注                 |
| ------- | ------------------ | ------ | -------- | -------------------- |
| status  | 表示执行成功或失败 | String | 是       | 0表示成功，1表示失败 |
| message | 响应消息           | String | 是       |                      |
| object  | 其他对象           | Object | 否       |                      |

- 响应结果示例

``` json
// 成功
{
    "status": 200,
    "message": "success",
    "object": null
}

// 失败
{
	"message":"fail",
	"status":"400",
    "object": null
}
```





#### 3）验证码校验

- 名称: verify
- 描述: 验证码校验
- URL: http://localhost:8080/mail/verify
- 请求方式: POST
- 请求参数

| 字段       | 说明             | 类型   | 是否必须 | 备注 |
| ---------- | ---------------- | ------ | -------- | ---- |
| email      | 邮箱             | String | 是       |      |
| verifyCode | 用户输入的验证码 | String | 是       |      |

- 请求参数示例

``` json
{
    "email":"guo_x0315@163.com",
    "verifyCode":"as23De"
}
```

- 响应结果

| 字段    | 说明   | 类型   | 是否必须 | 备注 |
| ------- | ------ | ------ | -------- | ---- |
| status  | 状态码 | int    | 是       |      |
| message | 消息   | String |          |      |

- 响应示例

``` json
// 成功
{
    "status": 200,
    "message": "success",
    "object": null
}

// 失败
{
	"message":"fail",
	"status":"400",
    "object": null
}
```



#### 4）通过Id修改用户信息

- 名称:	修改用户信息
- 描述：updateInfoById
- URL: http://localhost:8080/user/updateInfoById
- 请求方式: POST
- 请求参数

| 字段 | 说明           | 类型 | 是否必须 | 备注 |
| ---- | -------------- | ---- | -------- | ---- |
| user | 更新的User信息 | User | 是       |      |

- 请求参数示例

``` json

```

- 响应结果

| 字段 | 说明 | 类型 | 是否必须 | 备注 |
| ---- | ---- | ---- | -------- | ---- |
|      |      |      |          |      |

- 响应示例

``` json

```





### 3、用户注册

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
| object  |                    |        |          |                          |

- 响应示例

``` json
// 成功
{
    "status": 200,
    "message": "success",
    "object": null
}


// 普通失败
{
	"message":"fail",
	"status":"400",
    "object": null
}

// 邮箱已被注册失败
{
    "status": 401,
    "message": "fail",
    "object": null
}
```





### 4、找回密码

- 名称:	forget_password
- 描述：找回密码
- URL: http://localhost:8080/user/forget_password
- 请求方式: POST
- 请求参数

| 字段     | 说明                 | 类型   | 是否必须 | 备注 |
| -------- | -------------------- | ------ | -------- | ---- |
| email    | 所需要找回密码的邮箱 | String | 是       |      |
| password | 新密码               | String | 是       |      |
| object   |                      |        |          |      |

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
    "status": 200,
    "message": "success",
    "object": null
}

// 失败
{
    "status":"400",
    "message":"fail",
    "object": null
}

// 用户不存在
{
    "status": 401,
    "message": "fail",
    "object": null
}
```



### 5. 跑腿

#### 1）外卖代取（获取概要信息）

- 名称:	外卖代取
- 描述：查询所有代取外卖
- URL: http://localhost:8080/
- 请求方式: POST
- 请求参数

| 字段   | 说明   | 类型   | 是否必须 | 备注 |
| ------ | ------ | ------ | -------- | ---- |
| method | 方法名 | String | 使       |      |

- 请求参数示例

``` json
{
    method:""
}
```

- 响应结果

| 字段        | 说明                           | 类型   | 是否必须 | 备注                  |
| ----------- | ------------------------------ | ------ | -------- | --------------------- |
| params      | 该返回对象为下述对象的对象类型 | obj    |          |                       |
| title       | 标题                           | String | 是       |                       |
| name        | 发布人姓名                     | String | 是       |                       |
| details     | 详细信息                       | String | 否       |                       |
| head_img    | 发布人头像信息                 | String | 否       |                       |
| content_img | 内容图片                       | String | 否       |                       |
| price       | 价格                           | int    | 是       | 若为免费则标记为0即可 |
| pubdate     | 发布日期                       | date   | 是       |                       |

- 响应示例

``` json

```



### 6 、文件上传

#### 1） 上传用户头像

- 名称:	uploadAvatar
- 描述：根据ID更新用户头像信息
- URL: http://localhost:8080/
- 请求方式: POST
- 请求参数

| 字段       | 说明         | 类型 | 是否必须 | 备注 |
| ---------- | ------------ | ---- | -------- | ---- |
| uid        | 用户id       | int  | 是       |      |
| avatarPath | 用户头像地址 | int  | 是       |      |

- 请求参数示例

``` json
{
    "uid":2,
    "avatarPath":"/avatar/a.jpg"
}
```

- 响应结果

| 字段    | 说明       | 类型   | 是否必须 | 备注 |
| ------- | ---------- | ------ | -------- | ---- |
| status  | 响应状态码 | int    | 是       |      |
| message | 响应消息   | String | 是       |      |

- 响应示例

``` json
// 成功
{
    status:200,
    message:"success"
}

// 失败
{
    status:400,
    message:"fail"
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

```



## 六、白盒测试

### 1、登录界面

**1. 等价类划分**

- 1）成功登录
- 2）失败

**2. 设计测试用例**

| 邮箱              | 密码   | 预期结果 | 实际结果 |
| ----------------- | ------ | -------- | -------- |
| guo_x0315@163.com | 123456 | 成功登录 |          |



## 附件	

### 一、自定义状态码

| 状态码 | 说明         |
| ------ | ------------ |
| 200    | 响应成功     |
| 400    | 相应失败     |
| 401    | 用户不存在   |
| 402    | 用户已被注册 |
|        |              |

