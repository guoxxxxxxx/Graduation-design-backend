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

____



**2022-04-14 更新  在学习交流区又新增了几个分区**

![大学生互助平台](https://raw.githubusercontent.com/guoxxxxxxx/Pic-Go/main/img/%E5%A4%A7%E5%AD%A6%E7%94%9F%E4%BA%92%E5%8A%A9%E5%B9%B3%E5%8F%B0.png)

## 四、数据库设计

**2022-03-22**



**创建数据库**

``` sql
-- 创建数据库
CREATE DATABASE assistance;
USE assistance;
```



#### 1. user 用户信息表 

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





#### 2. errand 跑腿区信息表

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





#### 3. **study 学习交流表**

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





#### 4. trade 交易区  

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





#### 5.  lost_found 失物招领 

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





#### 6.  help 互助 校友圈 

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





#### 7. **s_discuss 用户学习讨论表 **

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





#### 8. s_reply 学习评论回复表   

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





#### 9. **t_discuss 交易评论表 **  

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





#### 10. **t_reply 交易回复表 ** 

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





#### 11. **f_discuss 失物招领评论表 **

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



#### 12.  f_reply 失物招领回复表  

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



#### 13. **h_discuss 互助评论表 **

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





#### 14. **h_reply 互助回复表 ** 

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



#### 15. mail_verif 邮箱验证码暂存表

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



#### 15. errand_img 跑腿区图片存储表

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



#### 16. errand_discuss 跑腿区评论表

| 字段名                 | 类型         | 说明                 |
| ---------------------- | ------------ | -------------------- |
| id                     | int          | 主键 自增            |
| eid                    | int          | 该评论所属的跑腿订单 |
| comment_uid            | INT          | 发布评论的用户id     |
| target_uid(该字段弃用) | INT          | 被评论用户id         |
| content                | VARCHAR(500) | 评论内容             |
| pubdate                | DATE         | 发表评论的日期       |
| pubtime                | TIME         | 发表评论的时间       |
| is_delete              | INT          | 是否删除             |

```sql
-- 创建跑腿用户评论表
CREATE TABLE errand_discuss(
id INT AUTO_INCREMENT COMMENT '主键自增',
eid INT NOT NULL COMMENT '该评论所属的跑腿订单',
comment_uid INT NOT NULL COMMENT '发布评论的用户id',
target_uid INT COMMENT '被评论用户',
content VARCHAR(500) NOT NULL COMMENT '评论内容',
create_date DATETIME COMMENT '发表评论的时间',
is_delete INT DEFAULT '0' COMMENT '是否删除',
PRIMARY KEY (id),
FOREIGN KEY (comment_uid) REFERENCES USER(uid),
FOREIGN KEY (target_uid) REFERENCES USER(uid)
)AUTO_INCREMENT=100000;
```



#### 17. errand_reply 跑腿区评论回复表

| 字段名            | 类型         | 说明             |
| ----------------- | ------------ | ---------------- |
| id                | int          | 主键 自增        |
| parent_discuss_id | INT          | 所属父评论id     |
| comment_uid       | INT          | 发布评论的用户id |
| target_uid        | INT          | 被评论用户id     |
| content           | VARCHAR(500) | 评论内容         |
| pubdate           | DATE         | 发表评论的日期   |
| pubtime           | TIME         | 发表评论的时间   |
| is_delete         | INT          | 是否删除         |

``` sql
-- 创建跑腿用户评论回复表
CREATE TABLE errand_reply(
id INT AUTO_INCREMENT COMMENT '主键自增',
parent_discuss_id INT NOT NULL COMMENT '所属父评论id',
comment_uid INT NOT NULL COMMENT '发布评论的用户id',
target_uid INT NOT NULL COMMENT '被评论用户',
content VARCHAR(500) NOT NULL COMMENT '评论内容',
create_date DATETIME COMMENT '发表评论的时间',
is_delete INT DEFAULT '0' COMMENT '是否删除',
PRIMARY KEY (id),
FOREIGN KEY (comment_uid) REFERENCES USER(uid),
FOREIGN KEY (target_uid) REFERENCES USER(uid),
FOREIGN KEY (parent_discuss_id) REFERENCES errand_discuss(id) 
);

```



#### 18 通过eid伪删除跑腿订单

- 名称:	fakeDeleteItem
- 描述：伪删除跑腿订单
- URL: http://localhost:8080/trade/fakeDeleteItem
- 请求方式: GET
- 请求参数

| 字段 | 说明 | 类型 | 是否必须 | 备注 |
| ---- | ---- | ---- | -------- | ---- |
| eid  | 主键 | int  | 是       |      |

- 请求参数示例

``` json
{
    eid: "1"
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
    status: 200,
    message: "success"
}
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
- 请求参数 

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

**（暂时已被弃用，目前使用Vue中的过滤器来过滤属性，以减小服务器的开销）**

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



#### 9. 通过eid查询订单对应的评论信息

- 名称:	queryAllCommentsAndChildComments
- 描述：通过eid查询订单对应的评论信息
- URL: http://localhost:8080/errand/queryAllCommentsAndChildComments
- 请求方式: GET
- 请求参数

| 字段 | 说明         | 类型 | 是否必须 | 备注 |
| ---- | ------------ | ---- | -------- | ---- |
| eid  | 跑腿订单主键 | int  | 是       |      |

- 请求参数示例

``` json
{
    "eid":33
}
```

- 响应结果

| 字段    | 说明         | 类型   | 备注         |
| ------- | ------------ | ------ | ------------ |
| status  | 状态码       | int    |              |
| message | 消息         | String |              |
| object  | 具体查询对象 | obj    | 详情请见示例 |

- 响应示例

``` json
{
    "status": 200,
    "message": "success",
    "object": [
        {
            "id": 1,
            "commentUid": 27,
            "commentUser": {
                "id": 27,
                "nickName": "郭迅",
                "avatar": "http://localhost:8080/avatar/871eb6b698104be9842123bbdf978cbf.jpg"
            },
            "targetUid": 28,
            "targetUser": {
                "id": 28,
                "nickName": "闪光皮皮",
                "avatar": "http://localhost:8080/avatar/default.png"
            },
            "content": "郭迅->闪光皮皮",
            "createDate": "2022-04-12 19:18:54",
            "isDelete": 0,
            "eid": 30,
            "childrenList": [
                {
                    "id": 1,
                    "parentDiscussId": 1,
                    "commentUid": 3,
                    "commentUser": {
                        "id": 3,
                        "nickName": "张思恺",
                        "avatar": "http://localhost:8080/avatar/5c113c3a597244d091d462c8b6cdf1cb.png"
                    },
                    "targetUid": 2,
                    "targetUser": {
                        "id": 2,
                        "nickName": "李子",
                        "avatar": "http://localhost:8080/avatar/d9259de915b843feaee191922e684fee.jpg"
                    },
                    "content": "思凯->子轩",
                    "createDate": "2022-04-12 19:20:18",
                    "isDelete": 0
                },
                {
                    "id": 2,
                    "parentDiscussId": 1,
                    "commentUid": 28,
                    "commentUser": {
                        "id": 28,
                        "nickName": "闪光皮皮",
                        "avatar": "http://localhost:8080/avatar/default.png"
                    },
                    "targetUid": 27,
                    "targetUser": {
                        "id": 27,
                        "nickName": "郭迅",
                        "avatar": "http://localhost:8080/avatar/871eb6b698104be9842123bbdf978cbf.jpg"
                    },
                    "content": "闪光皮皮",
                    "createDate": "2022-04-12 20:03:20",
                    "isDelete": 0
                }
            ]
        },
        {
            "id": 2,
            "commentUid": 4,
            "commentUser": {
                "id": 4,
                "nickName": "谭帅华",
                "avatar": "http://localhost:8080/avatar/default.png"
            },
            "targetUid": 3,
            "targetUser": {
                "id": 3,
                "nickName": "张思恺",
                "avatar": "http://localhost:8080/avatar/5c113c3a597244d091d462c8b6cdf1cb.png"
            },
            "content": "菜鸡->思凯",
            "createDate": "2022-04-12 19:19:31",
            "isDelete": 0,
            "eid": 30,
            "childrenList": []
        }
    ]
}
```



#### 10. 发送评论

- 名称:	sendDiscuss
- 描述：发送评论
- URL: http://localhost:8080/errand/sendDiscuss
- 请求方式: POST
- 请求参数

| 字段       | 说明                 | 类型   | 是否必须 | 备注 |
| ---------- | -------------------- | ------ | -------- | ---- |
| eid        | 该评论所属的跑腿订单 | int    | 是       |      |
| commentUid | 发表该评论的用户     | int    | 是       |      |
| content    | 发表评论的内容       | String | 是       |      |

- 请求参数示例

``` json
{
    "eid":30,
    "commentUid":20,
   	"content":"测试内容",
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
    "sataus":200,
    "message":"success"
}
```



#### 11. 发表回复

- 名称:	sendReply
- 描述：发表回复
- URL: http://localhost:8080/errand/sendReply
- 请求方式: POST
- 请求参数

| 字段            | 说明               | 类型   | 是否必须 | 备注 |
| --------------- | ------------------ | ------ | -------- | ---- |
| parentDiscussId | 回复所属的父评论   | int    | 是       |      |
| commentUid      | 发表回复的的用户id | int    | 是       |      |
| targetUid       | 被回复人的目标id   | int    | 是       |      |
| content         | 回复内容           | String | 是       |      |

- 请求参数示例

``` json
{
    "parentDiscussId":1,
    "commentUid":2,
    "targetUid":3,
    "content":"测试"
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
    "sataus":200,
    "message":"success"
}
```



#### 12. 通过条件获取符合条件信息的数目

- 名称: queryByCondition
- 描述：通过条件查询项目信息
- URL: http://localhost:8080/errand/queryByCondititon
- 请求方式: POST
- 请求参数

| 字段               | 说明               | 类型    | 是否必须 | 备注 |
| ------------------ | ------------------ | ------- | -------- | ---- |
| page               | 所要查询的页码     | int     | 是       |      |
| category           | 所要查询的种类信息 | String  | 是       |      |
| fuzzyParam         | 模糊查询参数       | String  |          |      |
| isHiddenAchieve    | 是否隐藏已完成项目 | boolean |          |      |
| isHiddenTakeOrders | 是否隐藏已接单项目 | boolean |          |      |

- 请求参数示例

``` json
{
    "page":1,
    "category":"全部",
    "fuzzyParam":"",
    "isHiddenAchieve":true,
    "isHiddenTakeOrders":true
}
```

- 响应结果

| 字段    | 说明   | 类型   | 备注 |
| ------- | ------ | ------ | ---- |
| status  | 状态码 | int    |      |
| message | 消息   | String |      |

- 响应示例

``` json

```



#### 13. 通过条件查询符合查询条件项目的数量

- 名称: queryCountByCondition
- 描述：通过条件查询项目信息
- URL: http://localhost:8080/errand/queryCountByCondition
- 请求方式: POST
- 请求参数

| 字段               | 说明               | 类型    | 是否必须 | 备注    |
| ------------------ | ------------------ | ------- | -------- | ------- |
| page               | 所要查询的页码     | int     | 是       |         |
| category           | 所要查询的种类信息 | String  | 是       |         |
| fuzzyParam         | 模糊查询参数       | String  |          |         |
| isHiddenAchieve    | 是否隐藏已完成项目 | boolean |          | 0否 1是 |
| isHiddenTakeOrders | 是否隐藏已接单项目 | boolean |          |         |

- 请求参数示例

```
{
    "page":1,
    "category":"全部",
    "fuzzyParam":"",
    "isHiddenAchieve":0
}
```

- 响应结果

| 字段    | 说明   | 类型   | 备注 |
| ------- | ------ | ------ | ---- |
| status  | 状态码 | int    |      |
| message | 消息   | String |      |

- 响应示例

```

```



#### 14. 查询评论的总数量，通过eid

- 名称: queryDiscussCount
- 描述：通过sid查询当前项目的评论总条数
- URL: http://localhost:8080/errand/queryDiscussCount
- 请求方式: GET
- 请求参数

| 字段 | 说明            | 类型 | 是否必须 | 备注 |
| ---- | --------------- | ---- | -------- | ---- |
| sid  | 所查询界面的sid | int  | 是       |      |

- 请求参数示例

``` json
{
    "sid": 1
}
```

- 响应结果

| 字段    | 说明         | 类型   | 备注 |
| ------- | ------------ | ------ | ---- |
| status  | 状态码       | int    |      |
| message | 消息         | String |      |
| object  | 评论信息数量 | object |      |

- 响应示例

``` json
{
    "status":200,
    "message":"success",
    "object":100
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



### 5. 学习交流模块

#### 1. 向学习表中添加新项目 以及 上传的图片

- 名称:	addNewItem
- 描述：向学习表中添加新项目
- URL: http://localhost:8080/study/addNewItem
- 请求方式: GET
- 请求参数

| 字段     | 说明             | 类型     | 是否必须 | 备注 |
| -------- | ---------------- | -------- | -------- | ---- |
| uid      | 发布信息用户的id | int      | 是       |      |
| title    | 标题             | String   | 是       |      |
| category | 所属种类         | String   | 是       |      |
| details  | 详细信息         | String   | 是       |      |
| imgUrls  | 上传的图片名称   | String[] | 是       |      |

- 请求参数示例

``` json
{
    "uid":1,
    "title":"测试",
    "category":"种类",
    "details":"详细信息",
    "imgUrls":[]
}
```

- 响应结果

| 字段    | 说明           | 类型   | 备注 |
| ------- | -------------- | ------ | ---- |
| status  | 状态码         | int    |      |
| message | 消息           | String |      |
| object  | 发布新消息的id | int    |      |

- 响应示例

``` json
{
    "status":200,
    "message":"success"
    "object": 1,
}
```



#### 2. 查询所有信息

- 名称:	selectAll
- 描述：查询学习模块的所有信息(分页查询)
- URL: http://localhost:8080/
- 请求方式: GET
- 请求参数：

| 字段     | 说明                 | 类型 | 是否必须 | 备注     |
| -------- | -------------------- | ---- | -------- | -------- |
| page     | 所要显示的页码       | int  | 否       | 默认为1  |
| pageSize | 每页所显示的信息数量 | int  | 否       | 默认为15 |

- 请求参数示例

```
{
	"page": 1
}
```

- 响应结果

| 字段    | 说明           | 类型   | 备注 |
| ------- | -------------- | ------ | ---- |
| status  | 状态码         | int    |      |
| message | 消息           | String |      |
| object  | 返回查询的对象 | object |      |

- 响应示例

```json
{
    "status": 200,
    "message": "success",
    "object": [
        {
            "sid": 1,
            "uid": 2,
            "pubUser": {
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
            },
            "category": "数学",
            "title": "112233",
            "details": "112233",
            "pubdate": null,
            "isAchieve": 0,
            "isDelete": 0
        }
    ]
}
```



#### 3. 查询记录总条数



- 名称: selectItemCount
- 描述：查询记录总条数
- URL: http://localhost:8080/study/selectItemCount
- 请求方式: get
- 请求参数

| 字段 | 说明 | 类型 | 是否必须 | 备注 |
| ---- | ---- | ---- | -------- | ---- |
| 无   |      |      |          |      |

- 请求参数示例

``` json

```

- 响应结果

| 字段    | 说明       | 类型   | 备注 |
| ------- | ---------- | ------ | ---- |
| status  | 状态码     | int    |      |
| message | 消息       | String |      |
| object  | 记录总条数 | int    |      |

- 响应示例

``` json
{
    "sataus": 200,
    "message": "success", 
    object: 100
}
```



#### 4. 通过sid查询该学习项目的详细信息

- 名称:	selectDetailsBySid
- 描述：通过sid查询该学习项目的详细信息
- URL: http://localhost:8080/study/selectDetailsBySid
- 请求方式: GET
- 请求参数

| 字段 | 说明               | 类型 | 是否必须 | 备注 |
| ---- | ------------------ | ---- | -------- | ---- |
| sid  | 所要查询项目的主键 | int  | 是       |      |

- 请求参数示例

``` json
{
    "sid": 1
}
```

- 响应结果

| 字段    | 说明   | 类型   | 备注 |
| ------- | ------ | ------ | ---- |
| status  | 状态码 | int    |      |
| message | 消息   | String |      |

- 响应示例

``` json

```



#### 5. 通过sid查询当前项目的评论信息及回复信息

- 名称:	selectDiscussBySid
- 描述：通过sid查询当前项目的评论信息及回复信息
- URL: http://localhost:8080/study/selectDiscussBySid
- 请求方式: GET
- 请求参数

| 字段 | 说明           | 类型 | 是否必须 | 备注 |
| ---- | -------------- | ---- | -------- | ---- |
| sid  | 所要查询的项目 | int  | 是       |      |

- 请求参数示例

``` json
{
    "sid": 1
}
```

- 响应结果

| 字段    | 说明               | 类型   | 备注             |
| ------- | ------------------ | ------ | ---------------- |
| status  | 状态码             | int    |                  |
| message | 消息               | String |                  |
| object  | 具体所要查询的对象 | object | 详情请看响应示例 |

- 响应示例

``` json
{
    "status": 200,
    "message": "success",
    "object": [
        {
            "id": 1,
            "sid": 1,
            "commentUid": 1,
            "content": "这个是测试内容！",
            "createDate": "2022-04-15T18:43:12.000+00:00",
            "isDelete": 0,
            "childrenList": [
                {
                    "id": 100000,
                    "parentDiscussId": 1,
                    "commentUid": 2,
                    "targetUid": 1,
                    "content": "这个是第一个评论的回复测试\r\n",
                    "createDate": "2022-04-15T18:44:16.000+00:00",
                    "isDelete": 0
                }
            ]
        },
        {
            "id": 2,
            "sid": 1,
            "commentUid": 4,
            "content": "这个是第二个测试内容！",
            "createDate": "2022-04-15T18:43:41.000+00:00",
            "isDelete": 0,
            "childrenList": []
        }
    ]
}
```



#### 6. 通过sid查询当前项目的评论总条数

- 名称: selectDiscussCountBySid
- 描述：通过sid查询当前项目的评论总条数
- URL: http://localhost:8080/study/selectDiscussCountBySid
- 请求方式: GET
- 请求参数

| 字段 | 说明            | 类型 | 是否必须 | 备注 |
| ---- | --------------- | ---- | -------- | ---- |
| sid  | 所查询界面的sid | int  | 是       |      |

- 请求参数示例

``` json
{
    "sid": 1
}
```

- 响应结果

| 字段    | 说明         | 类型   | 备注 |
| ------- | ------------ | ------ | ---- |
| status  | 状态码       | int    |      |
| message | 消息         | String |      |
| object  | 评论信息数量 | object |      |

- 响应示例

``` json
{
    "status":200,
    "message":"success",
    "object":100
}
```



#### 7. 通过sid将状态改为已解决

- 名称: setAchieveBySid
- 描述：通过sid将状态改为已解决
- URL: http://localhost:8080/study/setAchieveBySid
- 请求方式:  GET
- 请求参数

| 字段 | 说明   | 类型 | 是否必须 | 备注 |
| ---- | ------ | ---- | -------- | ---- |
| sid  | 项目id | int  | 是       |      |

- 请求参数示例

``` json
{
    "sid": 1
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
    "status":200,
    "message":"success"
}
```



#### 8. 通过sid伪删除信息

- 名称: fakeDeleteBySid
- 描述：通过sid伪删除信息
- URL: http://localhost:8080/study/fakeDeleteSid
- 请求方式: GET
- 请求参数

| 字段 | 说明   | 类型 | 是否必须 | 备注 |
| ---- | ------ | ---- | -------- | ---- |
| sid  | 项目id | int  | 是       |      |

- 请求参数示例

```json
{
    "sid": 1
}
```

- 响应结果

| 字段    | 说明   | 类型   | 备注 |
| ------- | ------ | ------ | ---- |
| status  | 状态码 | int    |      |
| message | 消息   | String |      |

- 响应示例

```json
{
    "status":200,
    "message":"success"
}
```



#### 9. 发送评论

- 名称:	sendDiscuss
- 描述：发送评论
- URL: http://localhost:8080/study/sendDiscuss
- 请求方式: POST
- 请求参数

| 字段       | 说明             | 类型   | 是否必须 | 备注 |
| ---------- | ---------------- | ------ | -------- | ---- |
| sid        | 该评论所属项目   | int    | 是       |      |
| commentUid | 发表该评论的用户 | int    | 是       |      |
| content    | 发表评论的内容   | String | 是       |      |

- 请求参数示例

``` json
{
    "sid":30,
    "commentUid":20,
   	"content":"测试内容",
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
    "sataus":200,
    "message":"success"
}
```



#### 10. 通过图片名称伪删除图片

- 名称: fakeDeleteImgByFilename
- 描述：通过图片名称伪删除图片
- URL: http://localhost:8080/study/fakeDeleteImgByFilename
- 请求方式: GET
- 请求参数

| 字段    | 说明     | 类型   | 是否必须 | 备注 |
| ------- | -------- | ------ | -------- | ---- |
| img_src | 图片名称 | String | 是       |      |

- 请求参数示例

``` json
{
    "img_src": "filename.png"
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
    "sataus":200,
    "message":"success"
}
```



#### 11. 通过sid更新整个项目信息

- 名称: updateBySid
- 描述：通过sid更新整个项目信息
- URL: http://localhost:8080/study/updateBySid
- 请求方式: POST
- 请求参数

| 字段     | 说明               | 类型   | 是否必须 | 备注 |
| -------- | ------------------ | ------ | -------- | ---- |
| sid      | 所要修改的项目编号 | int    | 是       |      |
| category | 所属类别           | String | 是       |      |
| title    | 标题               | String | 是       |      |
| details  | 详细内容           | String | 否       |      |

- 请求参数示例

``` json
{
    "sid":1,
    "category":"英语",
    "title":"测试",
    "details":"详细信息测试"
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
    "sataus":200,
    "message":"success"
}
```



#### 12. 发送回复

- 名称: doSendReply
- 描述：发送回复
- URL: http://localhost:8080/study/doSendReply
- 请求方式: POST
- 请求参数

| 字段            | 说明               | 类型   | 是否必须 | 备注 |
| --------------- | ------------------ | ------ | -------- | ---- |
| parentDiscussId | 回复所属的父评论   | int    | 是       |      |
| commentUid      | 发表回复的的用户id | int    | 是       |      |
| targetUid       | 被回复人的目标id   | int    | 是       |      |
| content         | 回复内容           | String | 是       |      |

- 请求参数示例

``` json
{
    "parentDiscussId":1,
    "commentUid":2,
    "targetUid":3,
    "content":"测试"
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
    "sataus":200,
    "message":"success"
}
```



#### 13. 通过条件查询项目信息

- 名称: queryByCondition
- 描述：通过条件查询项目信息
- URL: http://localhost:8080/study/queryByCondition
- 请求方式: POST
- 请求参数

| 字段            | 说明               | 类型   | 是否必须 | 备注    |
| --------------- | ------------------ | ------ | -------- | ------- |
| page            | 所要查询的页码     | int    | 是       |         |
| category        | 所要查询的种类信息 | String | 是       |         |
| fuzzyParam      | 模糊查询参数       | String |          |         |
| isHiddenAchieve | 是否隐藏已完成项目 | int    |          | 0否 1是 |

- 请求参数示例

``` json
{
    "page":1,
    "category":"全部",
    "fuzzyParam":"",
    "isHiddenAchieve":0
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
    "object": [
        {
            "sid": 4,
            "uid": 2,
            "pubUser": {
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
            },
            "category": "英语",
            "title": "222222222",
            "details": "123123",
            "pubdate": "2022-04-14 02:53:35",
            "isAchieve": 0,
            "isDelete": 0,
            "imgUrls": []
        }
    ]
}
```



#### 14. 条件查询记录总条数

- 名称: queryItemsCountByCondition
- 描述：条件查询记录总条数
- URL: http://localhost:8080/study/queryItemsCountByCondition
- 请求方式: POST
- 请求参数

| 字段            | 说明               | 类型   | 是否必须 | 备注    |
| --------------- | ------------------ | ------ | -------- | ------- |
| category        | 所要查询的种类信息 | String | 是       |         |
| fuzzyParam      | 模糊查询参数       | String |          |         |
| isHiddenAchieve | 是否隐藏已完成项目 | int    |          | 0否 1是 |

- 请求参数示例

``` json
{
    "category":"全部",
    "fuzzyParam":"",
    "isHiddenAchieve":0
}
```

- 响应结果

| 字段    | 说明   | 类型   | 备注 |
| ------- | ------ | ------ | ---- |
| status  | 状态码 | int    |      |
| message | 消息   | String |      |

- 响应示例

``` json

```



### 6. 交易模块

#### 1. 向交易表中添加新项目以及上传图片

- 名称:	addNewItem
- 描述：向学习表中添加新项目
- URL: http://localhost:8080/trade/addNewItem
- 请求方式: POST
- 请求参数

| 字段     | 说明             | 类型     | 是否必须 | 备注 |
| -------- | ---------------- | -------- | -------- | ---- |
| uid      | 发布信息用户的id | int      | 是       |      |
| title    | 标题             | String   | 是       |      |
| category | 所属种类         | String   | 是       |      |
| money    | 价格             | double   | 是       |      |
| details  | 详细信息         | String   | 是       |      |
| imgUrls  | 上传的图片名称   | String[] | 是       |      |

- 请求参数示例

``` json
{
    "uid":1,
    "title":"测试",
    "category":"种类",
    "details":"详细信息",
    "imgUrls":[]
}
```

- 响应结果

| 字段    | 说明           | 类型   | 备注 |
| ------- | -------------- | ------ | ---- |
| status  | 状态码         | int    |      |
| message | 消息           | String |      |
| object  | 发布新消息的id | int    |      |

- 响应示例

``` json
{
    "status":200,
    "message":"success"
    "object": 1,
}
```



#### 2. 条件查询信息

- 名称:	queryItemsByCondition
- 描述：查询交易模块的所有信息(分页查询)
- URL: http://localhost:8080/trade/queryItemsByCondition
- 请求方式: POST
- 请求参数：

| 字段               | 说明                   | 类型   | 是否必须 | 备注    |
| ------------------ | ---------------------- | ------ | -------- | ------- |
| page               | 所要显示的页码         | int    | 否       | 默认为1 |
| category           | 所要查询的种类信息     | String | 是       |         |
| fuzzyParam         | 模糊查询参数           | String |          |         |
| isHiddenAchieve    | 是否隐藏已完成项目     | int    |          | 0否 1是 |
| isHiddenTakeOrders | 是否隐藏已经接单的项目 | int    |          |         |

- 请求参数示例

```json
{
	"page": 1,
    "category":"全部",
    "fuzzyParam":"",
    "isHiddenAchieve":0,
    "isHiddenTakeOrders":0
}
```

- 响应结果

| 字段    | 说明           | 类型   | 备注 |
| ------- | -------------- | ------ | ---- |
| status  | 状态码         | int    |      |
| message | 消息           | String |      |
| object  | 返回查询的对象 | object |      |

- 响应示例

```json
{
    "status": 200,
    "message": "success",
    "object": []
}
```



#### 3. 查询记录总条数



- 名称: queryItemsCountByCondition
- 描述：查询记录总条数
- URL: http://localhost:8080/trade/queryItemsCountByCondition
- 请求方式: POST
- 请求参数

| 字段               | 说明                   | 类型   | 是否必须 | 备注    |
| ------------------ | ---------------------- | ------ | -------- | ------- |
| category           | 所要查询的种类信息     | String | 是       |         |
| fuzzyParam         | 模糊查询参数           | String |          |         |
| isHiddenAchieve    | 是否隐藏已完成项目     | int    |          | 0否 1是 |
| isHiddenTakeOrders | 是否隐藏已经接单的项目 | int    |          |         |

- 请求参数示例

``` json
{
    "category":"全部",
    "fuzzyParam":"",
    "isHiddenAchieve":0,
    "isHiddenTakeOrders":0
}
```

- 响应结果

| 字段    | 说明       | 类型   | 备注 |
| ------- | ---------- | ------ | ---- |
| status  | 状态码     | int    |      |
| message | 消息       | String |      |
| object  | 记录总条数 | int    |      |

- 响应示例

``` json
{
    "sataus": 200,
    "message": "success", 
    object: 100
}
```



#### 4. 通过tid查询项目的详细信息

- 名称:	queryDetailsByTid
- 描述：通过sid查询该学习项目的详细信息
- URL: http://localhost:8080/trade/queryDetailsByTid
- 请求方式: GET
- 请求参数

| 字段 | 说明               | 类型 | 是否必须 | 备注 |
| ---- | ------------------ | ---- | -------- | ---- |
| tid  | 所要查询项目的主键 | int  | 是       |      |

- 请求参数示例

``` json
{
    "tid": 1
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
    "object": {
        "tid": 1,
        "uid": 27,
        "pubUser": {},
        "tuid": null,
        "takeOrdersUser": null,
        "category": "电脑配件",
        "title": "卖笔记本电脑",
        "details": "9成新，18款XPS，\n处理器i5 8300H \n显卡1050\n固态硬盘： 3TB\n机不可失，失不再来",
        "money": 2000.0,
        "pubdate": "2022-04-20 09:31:25",
        "isDelete": 0,
        "isAchieve": 0,
        "imgUrls": [
            "/img/dddb26ae88a14e99a6119114ae400ff1.jpeg"
        ]
    }
}
```



#### 5. 通过tid查询当前项目的评论信息及回复信息

- 名称:	queryDiscussByTid
- 描述：通过sid查询当前项目的评论信息及回复信息
- URL: http://localhost:8080/trade/queryDiscussByTid
- 请求方式: GET
- 请求参数

| 字段 | 说明           | 类型 | 是否必须 | 备注 |
| ---- | -------------- | ---- | -------- | ---- |
| tid  | 所要查询的项目 | int  | 是       |      |

- 请求参数示例

``` json
{
    "tid": 1
}
```

- 响应结果

| 字段    | 说明               | 类型   | 备注             |
| ------- | ------------------ | ------ | ---------------- |
| status  | 状态码             | int    |                  |
| message | 消息               | String |                  |
| object  | 具体所要查询的对象 | object | 详情请看响应示例 |

- 响应示例

``` json
{
    "status": 200,
    "message": "success",
    "object": [
        {
            "id": 1,
            "sid": 1,
            "commentUid": 1,
            "content": "这个是测试内容！",
            "createDate": "2022-04-15T18:43:12.000+00:00",
            "isDelete": 0,
            "childrenList": [
                {
                    "id": 100000,
                    "parentDiscussId": 1,
                    "commentUid": 2,
                    "targetUid": 1,
                    "content": "这个是第一个评论的回复测试\r\n",
                    "createDate": "2022-04-15T18:44:16.000+00:00",
                    "isDelete": 0
                }
            ]
        },
        {
            "id": 2,
            "sid": 1,
            "commentUid": 4,
            "content": "这个是第二个测试内容！",
            "createDate": "2022-04-15T18:43:41.000+00:00",
            "isDelete": 0,
            "childrenList": []
        }
    ]
}
```



#### 6. 通过sid查询当前项目的评论总条数

- 名称: selectDiscussCountBySid
- 描述：通过sid查询当前项目的评论总条数
- URL: http://localhost:8080/study/selectDiscussCountBySid
- 请求方式: GET
- 请求参数

| 字段 | 说明            | 类型 | 是否必须 | 备注 |
| ---- | --------------- | ---- | -------- | ---- |
| sid  | 所查询界面的sid | int  | 是       |      |

- 请求参数示例

``` json
{
    "sid": 1
}
```

- 响应结果

| 字段    | 说明         | 类型   | 备注 |
| ------- | ------------ | ------ | ---- |
| status  | 状态码       | int    |      |
| message | 消息         | String |      |
| object  | 评论信息数量 | object |      |

- 响应示例

``` json
{
    "status":200,
    "message":"success",
    "object":100
}
```



#### 7. 通过tid将状态改为已解决

- 名称: setAchieveByTid
- 描述：通过tid将状态改为已解决
- URL: http://localhost:8080/trade/setAchieveByTid
- 请求方式:  GET
- 请求参数

| 字段 | 说明   | 类型 | 是否必须 | 备注 |
| ---- | ------ | ---- | -------- | ---- |
| tid  | 项目id | int  | 是       |      |

- 请求参数示例

``` json
{
    "tid": 1
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
    "status":200,
    "message":"success"
}
```



#### 8. 通过tid伪删除信息

- 名称: fakeDeleteByTid
- 描述：通过sid伪删除信息
- URL: http://localhost:8080/trade/fakeDeleteTid
- 请求方式: GET
- 请求参数

| 字段 | 说明   | 类型 | 是否必须 | 备注 |
| ---- | ------ | ---- | -------- | ---- |
| tid  | 项目id | int  | 是       |      |

- 请求参数示例

```json
{
    "tid": 1
}
```

- 响应结果

| 字段    | 说明   | 类型   | 备注 |
| ------- | ------ | ------ | ---- |
| status  | 状态码 | int    |      |
| message | 消息   | String |      |

- 响应示例

```json
{
    "status":200,
    "message":"success"
}
```



#### 9. 发送评论

- 名称:	sendDiscuss
- 描述：发送评论
- URL: http://localhost:8080/trade/sendDiscuss
- 请求方式: POST
- 请求参数

| 字段       | 说明             | 类型   | 是否必须 | 备注 |
| ---------- | ---------------- | ------ | -------- | ---- |
| tid        | 该评论所属项目   | int    | 是       |      |
| commentUid | 发表该评论的用户 | int    | 是       |      |
| content    | 发表评论的内容   | String | 是       |      |

- 请求参数示例

``` json
{
    "tid":30,
    "commentUid":20,
   	"content":"测试内容",
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
    "sataus":200,
    "message":"success"
}
```



#### 10. 通过图片名称伪删除图片

- 名称: fakeDeleteImgByFilename
- 描述：通过图片名称伪删除图片
- URL: http://localhost:8080/study/fakeDeleteImgByFilename
- 请求方式: GET
- 请求参数

| 字段    | 说明     | 类型   | 是否必须 | 备注 |
| ------- | -------- | ------ | -------- | ---- |
| img_src | 图片名称 | String | 是       |      |

- 请求参数示例

``` json
{
    "img_src": "filename.png"
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
    "sataus":200,
    "message":"success"
}
```



#### 11. 通过Tid更新整个项目信息

- 名称: updateByTid
- 描述：通过sid更新整个项目信息
- URL: http://localhost:8080/trade/updateByTid
- 请求方式: POST
- 请求参数

| 字段     | 说明               | 类型   | 是否必须 | 备注 |
| -------- | ------------------ | ------ | -------- | ---- |
| tid      | 所要修改的项目编号 | int    | 是       |      |
| category | 所属类别           | String | 是       |      |
| title    | 标题               | String | 是       |      |
| details  | 详细内容           | String | 否       |      |

- 请求参数示例

``` json
{
    "tid":1,
    "category":"英语",
    "title":"测试",
    "details":"详细信息测试"
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
    "sataus":200,
    "message":"success"
}
```



#### 12. 发送回复

- 名称: SendReply
- 描述：发送回复
- URL: http://localhost:8080/trade/SendReply
- 请求方式: POST
- 请求参数

| 字段            | 说明               | 类型   | 是否必须 | 备注 |
| --------------- | ------------------ | ------ | -------- | ---- |
| parentDiscussId | 回复所属的父评论   | int    | 是       |      |
| commentUid      | 发表回复的的用户id | int    | 是       |      |
| targetUid       | 被回复人的目标id   | int    | 是       |      |
| content         | 回复内容           | String | 是       |      |

- 请求参数示例

``` json
{
    "parentDiscussId":1,
    "commentUid":2,
    "targetUid":3,
    "content":"测试"
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
    "sataus":200,
    "message":"success"
}
```





#### 13. 预定订单

- 名称: wantToBuy
- 描述：通过tid预定订单
- URL: http://localhost:8080/trade/wantToBuy
- 请求方式: GET
- 请求参数

| 字段 | 说明           | 类型 | 是否必须 | 备注 |
| ---- | -------------- | ---- | -------- | ---- |
| tid  | 想要购买的项目 | int  | 是       |      |
| tuid | 谁想购买？     | int  | 是       |      |

- 请求参数示例

``` json
{
    "tid":1,
    "tuid":1
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
    status: 200,
	message: "success"
}
```



#### 14. 通过tid查询与之对应的图片信息

- 名称: queryImgByTid
- 描述：通过tid查询与之对应的图片信息
- URL: http://localhost:8080/trade/queryImgByTid
- 请求方式: GET
- 请求参数

| 字段 | 说明 | 类型 | 是否必须 | 备注 |
| ---- | ---- | ---- | -------- | ---- |
| tid  |      |      |          |      |

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



### 7. 失物招领模块

#### 1. 发布信息

- 名称：publishNewItem

#### 2. 上传图片到数据库中

- 名称：uploadImg



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

