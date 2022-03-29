-- 创建数据库
CREATE DATABASE assistance;

USE assistance;

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

-- 学习讨论表
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