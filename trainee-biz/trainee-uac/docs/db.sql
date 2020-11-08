drop table if exists `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL COMMENT '客户端标\n识',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '接入资源列表',
  `client_secret` varchar(255) DEFAULT NULL COMMENT '客户端秘钥',
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` longtext,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` tinyint(4) DEFAULT NULL,
  `trusted` tinyint(4) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='接入客户端信息';
INSERT INTO `oauth_client_details`(`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `create_time`, `archived`, `trusted`, `autoapprove`) VALUES ('ORDER', 'ORDER', '$2a$10$KAJSE3UKyYxr0PjNw42DjOhOu6bYxHZbjn9WzW42d3ubGqP4OcyOW', 'ROLE_ADMIN,ROLE_USER,ROLE_API', 'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.baidu.com', NULL, 7200, 259200, NULL, '2020-11-07 12:21:15', 0, 0, 'false');

drop table if exists `uac_user`;
CREATE TABLE `uac_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',
  `phone` char(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `avatar` char(128) NOT NULL DEFAULT '' COMMENT '头像',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
INSERT INTO `uac_user`(`id`, `name`, `phone`, `password`, `avatar`) VALUES (1, 'yuxing', '18140001000', '$2a$10$9zvEj4HpFAjlgsWT2Wu/C.s7JlgGGDH6ZWkL/G/Ek/SfmVWJEtS5e', 'https://dbj-test.oss-cn-beijing.aliyuncs.com/image/20201106/timg.jpeg');

drop table if exists `uac_client_role`;
create table `uac_client_role` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `client_id` varchar(32) NOT NULL DEFAULT '' COMMENT '权限组所属客户端',
    `name` varchar(50) NOT NULL DEFAULT '' COMMENT '权限组名称',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限(角色)';

drop table if exists `uac_client_permission`;
create table `uac_client_permission` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `client_id` varchar(32) NOT NULL DEFAULT '' COMMENT '权限组所属客户端',
    `name` varchar(50) NOT NULL DEFAULT '' COMMENT '权限组名称',
    `code` varchar(50) NOT NULL DEFAULT '' COMMENT '权限标识',
     PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户端权限';

drop table if exists `uac_role_permission_relation`;
create table `uac_role_permission_relation`(
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `role_id` bigint(20) unsigned NOT NULL default 0 comment '角色ID',
    `permission_id` bigint(20) unsigned NOT NULL default 0 comment '权限ID',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限与角色关系表';

drop table if exists `uac_user_role_relation`;
create table `uac_user_role_relation`(
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) unsigned NOT NULL default 0 comment '用户ID',
    `client_id` varchar(32) NOT NULL DEFAULT '' COMMENT '客户端标识',
    `role_id` bigint(20) unsigned NOT NULL default 0 comment '角色ID',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与角色关系表';

drop table if exists `uac_user_client_relation`;
create table `uac_user_client_relation` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) unsigned NOT NULL default 0 comment '用户ID',
    `client_id` varchar(32) NOT NULL DEFAULT '' COMMENT '客户端标识',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与客户端关系表';
