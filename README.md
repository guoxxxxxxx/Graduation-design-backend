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
uid INT AUTO_INCREMENT COMMENT	'id',
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
| pubtime    | time         | 发布时间                                      |
| deadtime   | time         | 截止时间                                      |
| is_delete  | int          | 是否删除；1为删除，0未删除，默认为0           |

- 创建跑腿区信息表

``` sql
-- 创建跑腿区信息表
CREATE TABLE errand(
eid INT AUTO_INCREMENT COMMENT '主键',
uid INT NOT NULL COMMENT '发布订单用户信息 外: user(uid)',
euid INT  COMMENT '接单用户信息 外: user(uid)',
title VARCHAR(30) COMMENT '订单信息',
details VARCHAR(500) COMMENT '订单详细信息',
money DOUBLE DEFAULT 0 COMMENT '订单费用, 默认为0',
is_achieve INT DEFAULT 0 COMMENT '是否已完成, 0: 未完成; 1: 已完成;',
category VARCHAR(5) COMMENT '类别, 可选值为(快递, 外卖, 打水, 其他)',
pubdate DATE COMMENT '发布日期',
deadline DATE COMMENT '截止日期',
pubtime TIME comment '发布时间',
deadtime TIME comment '截止时间',
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
| pubdate    | date         | 发布日期                                  |
| pubtime    | time         | 发布时间                                  |
| is_achieve | int          | 是否已解决，1：已解决，0：未解决；默认为0 |
| is_delete  | int          | 是否已删除， 1：删除 0：未删除 默认为0    |

- 创建学习交流表

``` sql
-- 创建学习交流表
CREATE TABLE study(
sid INT AUTO_INCREMENT COMMENT '主键',
uid INT NOT NULL COMMENT '发帖用户, 外：user(uid)' ,
category VARCHAR(15) COMMENT '类别,可选项为: 数学, 物理, 英语, 其他', 
title VARCHAR(30) COMMENT '题目',
details VARCHAR(500) COMMENT '详细内容',
pubdate DATE COMMENT '提交日期',
pubtime TIME comment '发布时间',
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
| pubtime   | time         | 发布时间                                          |
| is_delete | int          | 是否删除， 0未删除， 1 已删除 默认为0；           |

- 创建交易表

``` sql
-- 创建交易表
CREATE TABLE trade(
tid INT AUTO_INCREMENT COMMENT '主键',
uid INT NOT NULL COMMENT '发布信息的用户 外: user(uid)',
category VARCHAR(5) COMMENT '类别, 选项为: 出行工具, 书本资料, 生活用品和其他' ,
title VARCHAR(30) COMMENT '题目',
details VARCHAR(500) COMMENT '详细内容',
pubdate DATE COMMENT '发布日期',
pubtime TIME comment '发布时间',
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
| pubtime   | time         | 发布时间                                |
| is_delete | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 失物招领表
CREATE TABLE lost_found(
lid INT AUTO_INCREMENT COMMENT '主键',
uid INT NOT NULL COMMENT '发布信息的用户 外: user(uid)',
TYPE INT COMMENT '类型，可选：0：找失主，1：找物品',
title VARCHAR(30) COMMENT '题目',
details VARCHAR(500) COMMENT '详细内容',
is_find INT DEFAULT 0 COMMENT '是否已经找到, 0未找到, 1找到',
pubdate DATE COMMENT '发布日期',
pubtime TIME comment '发布时间',
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
| pubtime   | time         | 发布时间                                |
| is_delete | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 互助表
CREATE TABLE HELP(
hid INT AUTO_INCREMENT COMMENT '主键',
uid INT NOT NULL COMMENT '发布信息的用户 外: user(uid)',
title VARCHAR(30) COMMENT '题目',
details VARCHAR(500) COMMENT '详细内容',
pubdate DATE COMMENT '发布日期',
pubtime TIME comment '发布时间',
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
| pubtime   | time         | 发布时间                                |
| is_delete | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 用户学习讨论表
CREATE TABLE s_discuss(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT NOT NULL COMMENT '发布信息的用户 外: user(uid)',
sid INT COMMENT '发布评论的指定帖子 外: study(sid)',
COMMENT VARCHAR(500) COMMENT '讨论的内容',
pubdate DATE COMMENT '发布日期',
pubtime TIME comment '发布时间',
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
| pubtime    | time         | 发布时间                                |
| reply      | varchar(500) | 回复内容                                |
| is_delete  | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 学习评论回复表
CREATE TABLE s_reply(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT NOT NULL COMMENT '发布信息的用户 外: user(uid)',
reply_uid INT COMMENT '回复用户id 外: user(uid)',
discuss_id INT COMMENT '回复的帖子的id 外s_discuss(id)',
pubdate DATE COMMENT '发布日期',
pubtime TIME comment '发布时间',
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
| pubtime   | time         | 发布时间                                |
| is_delete | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 交易评论表
CREATE TABLE t_discuss(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT NOT NULL COMMENT '发布信息的用户 外: user(uid)',
tid INT COMMENT '发布评论所属帖子 外键trade(tid)',
COMMENT VARCHAR(500) COMMENT '讨论的内容',
pubdate DATE COMMENT '发布日期',
pubtime TIME comment '发布时间',
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
| pubtime    | time         | 发布时间                                |
| is_delete  | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 交易回复表
CREATE TABLE t_reply(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT NOT NULL COMMENT '发布信息的用户 外: user(uid)',
reply_uid INT COMMENT '回复用户id 外: user(uid)',
discuss_id INT COMMENT '回复的帖子的id 外t_discuss(id)',
pubdate DATE COMMENT '发布日期',
pubtime TIME comment '发布时间',
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
| pubtime   | time         | 发布时间                                |
| is_delete | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 失物招领评论表
CREATE TABLE f_discuss(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT NOT NULL COMMENT '发布信息的用户 外: user(uid)',
lid INT COMMENT '发布评论所属帖子 外键lost_found(lid)',
COMMENT VARCHAR(500) COMMENT '讨论的内容',
pubdate DATE COMMENT '发布日期',
pubtime TIME comment '发布时间',
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
| pubtime    | time         | 发布时间                                |
| is_delete  | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 失物招领回复表
CREATE TABLE f_reply(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT NOT NULL COMMENT '发布信息的用户 外: user(uid)',
reply_uid INT COMMENT '回复用户id 外: user(uid)',
discuss_id INT COMMENT '回复的帖子的id 外f_discuss(id)',
pubdate DATE COMMENT '发布日期',
pubtime TIME comment '发布时间',
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
| pubtime   | time         | 发布时间                                |
| is_delete | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 互助评论表
CREATE TABLE h_discuss(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT NOT NULL COMMENT '发布信息的用户 外: user(uid)',
hid INT COMMENT '发布评论所属帖子 外键help(hid)',
COMMENT VARCHAR(500) COMMENT '讨论的内容',
pubdate DATE COMMENT '发布日期',
pubtime TIME comment '发布时间',
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
| pubtime    | time         | 发布时间                                |
| is_delete  | int          | 是否删除， 0未删除， 1 已删除 默认为0； |

``` sql
-- 互助回复表
CREATE TABLE h_reply(
id INT AUTO_INCREMENT COMMENT '主键',
uid INT NOT NULL COMMENT '发布信息的用户 外: user(uid)',
reply_uid INT COMMENT '回复用户id 外: user(uid)',
discuss_id INT COMMENT '回复的帖子的id 外h_discuss(id)',
pubdate DATE COMMENT '发布日期',
pubtime TIME comment '发布时间',
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



#### 15) errand_img跑腿区图片存储表

| 字段名    | 类型         | 说明                   |
| --------- | ------------ | ---------------------- |
| id        | int          | 主键，自增             |
| eid       | int          | errand的外键           |
| img_src   | varchar(500) | 图片存储位置           |
| is_delete | int          | 是否删除 0未删除 1删除 |

``` mysql
-- 用于存放跑腿区图片
CREATE TABLE errand_img(
id INT AUTO_INCREMENT COMMENT '主键',
eid INT COMMENT 'errand的外键',
img_src VARCHAR(500) COMMENT '图片路径',
is_delete INT DEFAULT 0 COMMENT '是否删除',	
PRIMARY KEY (id),
FOREIGN KEY (eid) REFERENCES errand(eid)
);
```





## 五、接口设计

### 1、用户

#### **1. 登录**

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

| 字段    | 说明               | 类型   | 备注     |
| ------- | ------------------ | ------ | -------- |
| status  | 表示执行成功或失败 | String |          |
| message | 响应消息           | String |          |
| object  | 对象               | object | 存放消息 |

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



#### 2. 根据用户ID更新用户头像路径到数据库

- 名称:	uploadAvatar
- 描述：根据ID更新用户头像信息到数据库
- URL: http://localhost:8080/user/updateAvatarById
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

| 字段    | 说明       | 类型   | 备注 |
| ------- | ---------- | ------ | ---- |
| status  | 响应状态码 | int    |      |
| message | 响应消息   | String |      |

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



#### 3. 注册

- 名称:	register
- 描述：新用户注册
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

| 字段    | 说明               | 类型   | 备注                     |
| ------- | ------------------ | ------ | ------------------------ |
| status  | 表示执行成功或失败 | String | 200表示成功，40X表示失败 |
| message | 响应消息           | String |                          |

- 响应示例

``` json
// 成功
{
    "status": 200,
    "message": "success",
}


// 普通失败
{
	"message":"fail",
	"status":"400",
}

// 邮箱已被注册失败
{
    "status": 401,
    "message": "fail",
}
```



#### 4. 找回密码

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

| 字段    | 说明   | 类型   | 备注             |
| ------- | ------ | ------ | ---------------- |
| status  | 状态码 | int    | 200成功，400失败 |
| message | 消息   | String |                  |

- 响应示例

``` json
// 成功
{
    "status": 200,
    "message": "success",
}

// 失败
{
    "status":"400",
    "message":"fail",
}

// 用户不存在
{
    "status": 401,
    "message": "fail",
}
```



### 2、邮箱验证码

#### 1. 已注册用户获取验证码

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

| 字段    | 说明               | 类型   | 备注 |
| ------- | ------------------ | ------ | ---- |
| status  | 表示执行成功或失败 | String |      |
| message | 响应消息           | String |      |

- 响应结果示例

``` json
// 成功
{
    "status": 200,
    "message": "success",
}

// 失败
{
	"message":"fail",
	"status":"400",
}

// 该邮箱不存在于数据库中
{
    "status": 401,
    "message": "fail",
}
```



#### 2. 新用户获取验证码

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

| 字段    | 说明               | 类型   | 备注                 |
| ------- | ------------------ | ------ | -------------------- |
| status  | 表示执行成功或失败 | String | 0表示成功，1表示失败 |
| message | 响应消息           | String |                      |

- 响应结果示例

``` json
// 成功
{
    "status": 200,
    "message": "success",
}

// 失败
{
	"message":"fail",
	"status":"400",
}
```





#### 3. 验证码校验

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

| 字段    | 说明   | 类型   | 备注 |
| ------- | ------ | ------ | ---- |
| status  | 状态码 | int    |      |
| message | 消息   | String |      |

- 响应示例

``` json
// 成功
{
    "status": 200,
    "message": "success",
}

// 失败
{
	"message":"fail",
	"status":"400",
}
```



#### 4. 通过Id修改用户信息

- 名称:	修改用户信息
- 描述：updateInfoById
- URL: http://localhost:8080/user/updateUserInfoById
- 请求方式: POST
- 请求参数

| 字段     | 说明   | 类型   | 是否必须 | 备注         |
| -------- | ------ | ------ | -------- | ------------ |
| uid      | 用户id | int    | 是       | 用户唯一标识 |
| name     | 用户名 | String | 否       |              |
| sex      | 性别   | String | 否       |              |
| birthday | 生日   | date   | 否       |              |
| faculty  | 学院   | String | 否       |              |
| grade    | 年级   | String | 否       |              |
| major    | 专业   | String | 否       |              |
| phone    | 手机号 | String | 否       |              |
| wechat   | 微信号 | String | 否       |              |
| qq       | QQ号   | String | 否       |              |

- 请求参数示例

``` json
{
    "uid": 28,
    "name": "闪光皮皮",
    "sex":"保密",
    "birthday":"2000-03-15",
    "faculty":"建工学院",
    "grade":"2018",
    "major":"土木工程",
    "phone":"12345678900"
}
```

- 响应结果

| 字段    | 说明             | 类型   | 备注 |
| ------- | ---------------- | ------ | ---- |
| status  | 状态码           | int    |      |
| message | 消息             | String |      |
| object  | 更改后的用户信息 | Object |      |

- 响应示例

``` json
{
    "status": 200,
    "message": "success",
    "object": {
        "uid": 28,
        "name": "闪光皮皮",
        "sex": "保密",
        "birthday": "2000-03-15T00:00:00.000+00:00",
        "phone": "12345678900",
        "faculty": "建工学院",
        "grade": "2018",
        "major": "土木工程",
        "email": null,
        "wechat": null,
        "qq": null,
        "isDelete": null,
        "password": null,
        "avatarPath": null
    }
}
```



### 3. 跑腿

#### 1. 查询所有跑腿订单

- 名称:	外卖代取
- 描述：查询所有代取外卖
- URL: http://localhost:8080/errand/queryAll
- 请求方式: GET
- 请求参数 无

- 请求参数示例 无
- 响应结果

| 字段    | 说明       | 类型   | 是否必须 | 备注             |
| ------- | ---------- | ------ | -------- | ---------------- |
| status  | 响应状态码 | int    | 是       |                  |
| message | 响应消息   | String | 是       |                  |
| object  | 详细消息   | obj    | 是       | 详细请看响应示例 |

- 响应示例

``` json
{
    "status": 200,
    "message": "success",
    "object": [
        {
            "eid": 1,
            "uid": 1,
            "euid": 2,
            "title": "顺丰快递代取",
            "money": 100.0,
            "isAchieve": 0,
            "category": "快递",
            "pubdate": "2022-04-04T16:00:00.000+00:00",
            "deadline": null,
            "isDelete": 0,
            "details": "取货码112233"
            "imgUrls": [],
            "pubUser": {
                "uid": 1,
                "name": "李亚斌",
                "sex": "男",
                "birthday": "1999-09-08T16:00:00.000+00:00",
                "phone": "13343466992",
                "faculty": "信息学院",
                "grade": "2018",
                "major": "软件工程",
                "email": "lyb@163.com",
                "wechat": "lyb_wechat",
                "qq": "88888888",
                "isDelete": 0,
                "password": "lybdashabi1",
                "avatarPath": null
            },
            "takeOrderUser": {
                "uid": 2,
                "name": "李子",
                "sex": "男",
                "birthday": "2022-04-17T16:00:00.000+00:00",
                "phone": "13333333333",
                "faculty": "信息学院",
                "grade": "2018",
                "major": "软件工程",
                "email": "lzx@163.com",
                "wechat": "lzx_wechat",
                "qq": "88888888",
                "isDelete": 0,
                "password": "123456",
                "avatarPath": "/avatar/d9259de915b843feaee191922e684fee.jpg"
            }
        }]
}
```



#### 2. 根据eid查询跑腿订单详细信息

- 名称:	queryDetailsByEid
- 描述：根据eid查询订单详细信息
- URL: http://localhost:8080/errand/queryDetailsByEid
- 请求方式: GET
- 请求参数

| 字段 | 说明       | 类型 | 是否必须 | 备注 |
| ---- | ---------- | ---- | -------- | ---- |
| eid  | 跑腿订单id | int  | 是       |      |

- 请求参数示例

``` json
{
    "eid": "1"
}
```

- 响应结果

| 字段    | 说明       | 类型   | 备注             |
| ------- | ---------- | ------ | ---------------- |
| status  | 响应状态码 | int    |                  |
| message | 响应消息   | String |                  |
| object  | 详细消息   | obj    | 详细请看响应示例 |

- 响应示例

``` json
{
    "status": 200,
    "message": "success",
    "object": {
        "eid": 1,
        "uid": 1,
        "euid": 2,
        "title": "顺丰快递代取",
        "money": 100.0,
        "isAchieve": 0,
        "category": "快递",
        "pubdate": "2022-04-04T16:00:00.000+00:00",
        "deadline": null,
        "isDelete": 0,
        "details": "取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456",
        "imgUrls": [],
        "pubUser": {
            "uid": 1,
            "name": "李亚斌",
            "sex": "男",
            "birthday": "1999-09-08T16:00:00.000+00:00",
            "phone": "13343466992",
            "faculty": "信息学院",
            "grade": "2018",
            "major": "软件工程",
            "email": "lyb@163.com",
            "wechat": "lyb_wechat",
            "qq": "88888888",
            "isDelete": 0,
            "password": "lybdashabi1",
            "avatarPath": null
        },
        "takeOrderUser":{}
    }
}
```



#### 3. 根据种类查询跑腿订单信息

- 名称:	/queryItemByCategory
- 描述：根据种类查询跑腿订单信息
- URL: http://localhost:8080/errand/queryItemByCategory
- 请求方式: GET
- 请求参数

| 字段     | 说明 | 类型   | 是否必须 | 备注 |
| -------- | ---- | ------ | -------- | ---- |
| category | 种类 | String | 是       |      |

- 请求参数示例

``` json
{
    "category":"快递"
}
```

- 响应结果

| 字段    | 说明       | 类型   | 备注             |
| ------- | ---------- | ------ | ---------------- |
| status  | 响应状态码 | int    |                  |
| message | 响应消息   | String |                  |
| object  | 详细消息   | obj    | 详细请看响应示例 |

- 响应示例

``` json
{
    "status": 200,
    "message": "success",
    "object": {
        "eid": 1,
        "uid": 1,
        "euid": 2,
        "title": "顺丰快递代取",
        "money": 100.0,
        "isAchieve": 0,
        "category": "快递",
        "pubdate": "2022-04-04T16:00:00.000+00:00",
        "deadline": null,
        "isDelete": 0,
        "details": "取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456取货码123456",
        "imgUrls": [],
        "pubUser": {
            "uid": 1,
            "name": "李亚斌",
            "sex": "男",
            "birthday": "1999-09-08T16:00:00.000+00:00",
            "phone": "13343466992",
            "faculty": "信息学院",
            "grade": "2018",
            "major": "软件工程",
            "email": "lyb@163.com",
            "wechat": "lyb_wechat",
            "qq": "88888888",
            "isDelete": 0,
            "password": "lybdashabi1",
            "avatarPath": null
        }
    }
}
```



#### 4. 添加跑腿订单

- 名称: addErrandItem
- 描述：添加跑腿订单
- URL: http://localhost:8080/errand/addErrandItem
- 请求方式: POST
- 请求参数

| 字段     | 说明                       | 类型     | 是否必须 | 备注 |
| -------- | -------------------------- | -------- | -------- | ---- |
| uid      | 发布订单用户信息           | int      | 是       |      |
| title    | 订单标题                   | String   | 是       |      |
| money    | 劳务费                     | int      | 是       |      |
| category | 类别                       | String   | 是       |      |
| deadline | 截止日期                   | date     | 否       |      |
| deadtime | 截止时间                   | Time     | 否       |      |
| details  | 详细信息                   | String   | 否       |      |
| imgUrls  | 跑腿订单与之对应的图片信息 | String[] | 否       |      |

- 请求参数示例

``` json
{
   	"uid":1,
    "title":"饿了么外卖代取",
    "money":"5",
    "category":"外卖代取",
    "deadline":"2022-04-12",
    "deadtime":"20:00:00",
    "details":"饿死了 快帮我取外卖 球球了~~~",
    "imgUrls":[]
}
```

- 响应结果

| 字段    | 说明   | 类型   | 备注 |
| ------- | ------ | ------ | ---- |
| status  | 状态码 | int    |      |
| message | 消息   | String |      |

- 响应示例

``` json
{
    "status": 200,
    "message": "success",
}
```

#### 5. 伪删除跑腿订单

- 名称:	fakeDeleteItemByEid
- 描述：伪删除跑腿订订单
- URL: http://localhost:8080/errand/fakeDeleteItemByEid
- 请求方式: POST
- 请求参数

| 字段 | 说明     | 类型 | 是否必须 | 备注 |
| ---- | -------- | ---- | -------- | ---- |
| eid  | 订单编号 | int  | 是       |      |

- 请求参数示例

``` json
{
    "eid":"2"
}
```

- 响应结果

| 字段    | 说明   | 类型   | 备注 |
| ------- | ------ | ------ | ---- |
| status  | 状态码 | int    |      |
| message | 消息   | String |      |

- 响应示例

``` json
{
    "status": 200,
    "message": "success",
}
```



#### 6. 更新跑腿订单信息

- 名称:	updateErrandItemByEid
- 描述：更新跑腿订单信息
- URL: http://localhost:8080/errand/updateErrandItemByEid
- 请求方式: POST
- 请求参数

| 字段     | 说明                       | 类型     | 是否必须 | 备注 |
| -------- | -------------------------- | -------- | -------- | ---- |
| eid      | 订单编号                   | int      | 是       |      |
| title    | 订单标题                   | String   | 是       |      |
| money    | 劳务费                     | int      | 是       |      |
| category | 类别                       | String   | 是       |      |
| deadline | 截止日期                   | date     | 否       |      |
| deadTime | 截止时间                   | Time     | 否       |      |
| details  | 详细信息                   | String   | 否       |      |
| imgUrls  | 跑腿订单与之对应的图片信息 | String[] | 否       |      |

- 请求参数示例

``` json
{
    "eid":5,
    "title":"饿了么外卖代取",
    "money":"5",
    "category":"外卖代取",
    "deadline":"2022-04-12",
    "deadtime":"20:00:00",
    "details":"饿死了 快帮我取外卖 球球了~~~",
    "imgUrls":[]
}
```

- 响应结果

| 字段    | 说明   | 类型   | 备注 |
| ------- | ------ | ------ | ---- |
| status  | 状态码 | int    |      |
| message | 消息   | String |      |

- 响应示例

``` json
{
    "status": 200,
    "message": "success",
}
```



#### 7. 伪删除订单图片

- 名称:	fakeDeleteImgByImgSrc
- 描述：伪删除订单图片
- URL: http://localhost:8080/
- 请求方式: GET
- 请求参数

| 字段    | 说明             | 类型   | 是否必须 | 备注 |
| ------- | ---------------- | ------ | -------- | ---- |
| img_src | 要删除图片的名称 | String | 是       |      |

- 请求参数示例

``` json
{
    img_src:"img.jpg"
}
```

- 响应结果

| 字段    | 说明   | 类型   | 备注 |
| ------- | ------ | ------ | ---- |
| status  | 状态码 | int    |      |
| message | 消息   | String |      |

- 响应示例

``` json
{
    "status": 200,
    "message": "success",
}
```



#### 8.  更新订单完成状态

- 名称:	updateErrandIsAchieveStateByEid
- 描述：更新订单完成状态
- URL: http://localhost:8080/errand/updateErrandIsAchieveStateByEid
- 请求方式: GET
- 请求参数

| 字段 | 说明   | 类型 | 是否必须 | 备注 |
| ---- | ------ | ---- | -------- | ---- |
| eid  | 订单id | int  | 是       |      |

- 请求参数示例

``` json
{
    "eid":1
}
```

- 响应结果

| 字段    | 说明   | 类型   | 备注 |
| ------- | ------ | ------ | ---- |
| status  | 状态码 | int    |      |
| message | 消息   | String |      |

- 响应示例

``` json
{
    "status": 200,
    "message": "success",
}
```





### 4 、文件上传

#### 1. 上传头像到服务器

- 名称:	uploadAvatar
- 描述： 上传头像到服务器
- URL: http://localhost:8080/upload/uploadImg
- 请求方式: POST
- 请求参数

| 字段 | 说明 | 类型 | 是否必须 | 备注 |
| ---- | ---- | ---- | -------- | ---- |
| file | 文件 | 文件 | 是       |      |

- 请求参数示例

``` json
{
    file:fileOBJ
}
```

- 响应结果

| 字段          | 说明                   | 类型   | 备注 |
| ------------- | ---------------------- | ------ | ---- |
| result        | 响应结果               | String |      |
| beginFileName | 上传时文件名称         | String |      |
| lastFileName  | 上传到服务器的文件名称 | String |      |
| fileType      | 文件类型               | String |      |
| fileSize      | 文件大小               | String |      |
| uploadUrl     | 文件上传到的路径       | String |      |

- 响应示例

``` json
{
    "result": "success",
    "beginFileName": "头像.jpg",
    "lastFileName": "7f0ea446b78a481fb96ba1cddd219f40.jpg",
    "fileType": "jpg",
    "fileSize": "59938",
    "uploadUrl": "F:\\UPLOAD\\avatar\\7f0ea446b78a481fb96ba1cddd219f40.jpg"
}
```



#### 2. 上传图片到服务器

- 名称:	uploadImg
- 描述： 上传图片到服务器
- URL: http://localhost:8080/upload/uploadImg
- 请求方式: POST
- 请求参数

| 字段 | 说明 | 类型 | 是否必须 | 备注 |
| ---- | ---- | ---- | -------- | ---- |
| file | 文件 | 文件 | 是       |      |

- 请求参数示例

``` json
{
    file:fileOBJ
}
```

- 响应结果

| 字段          | 说明                   | 类型   | 备注 |
| ------------- | ---------------------- | ------ | ---- |
| result        | 响应结果               | String |      |
| beginFileName | 上传时文件名称         | String |      |
| lastFileName  | 上传到服务器的文件名称 | String |      |
| fileType      | 文件类型               | String |      |
| fileSize      | 文件大小               | String |      |
| uploadUrl     | 文件上传到的路径       | String |      |

- 响应示例

``` json
{
    "result": "success",
    "beginFileName": "Alena.Aenami.full.2924984.png",
    "lastFileName": "95a2f6e40b45418f9d2030d762a2bbec.png",
    "fileType": "png",
    "fileSize": "3091007",
    "uploadUrl": "F:\\UPLOAD\\img\\95a2f6e40b45418f9d2030d762a2bbec.png"
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

| 字段    | 说明   | 类型   | 备注 |
| ------- | ------ | ------ | ---- |
| status  | 状态码 | int    |      |
| message | 消息   | String |      |

- 响应示例

``` json

```



## 附件	

### 一、自定义状态码

| 状态码 | 说明         |
| ------ | ------------ |
| 200    | 响应成功     |
| 400    | 相应失败     |
| 401    | 用户不存在   |
| 402    | 用户已被注册 |
|        |              |

