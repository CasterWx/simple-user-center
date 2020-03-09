CREATE DATABASE `antzms`;

USE `antzms`;

CREATE TABLE `user` (
    `id` bigint(10) NOT NULL COMMENT '用户ID，手机号码',
    `nickname` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL COMMENT '用户邮箱',
    `email_valid` int(1) DEFAULT 0 COMMENT '邮箱是否激活，0未激活，1激活',
    `password` varchar(32) DEFAULT NULL COMMENT 'MD5(MD5(pass明文+固定salt)+salt)',
    `salt` varchar(10) DEFAULT NULL,
    `head` varchar(120) DEFAULT NULL COMMENT '头像，云存储的ID',
    `register_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册日期',
    `last_login_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次登录时间',
    `login_count` int(11) DEFAULT '0' COMMENT '登录次数',
    primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
