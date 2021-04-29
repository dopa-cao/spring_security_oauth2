/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : user_db

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2021-04-29 20:19:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL COMMENT '客户端标识',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='接入客户端信息';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('c1', 'res1', '$2a$10$elc0WomTYbTSnInc8R32ouE8vIE3Hh9B.ql3CO1C/yYTTcklJLqLO', 'ROLE_ADMIN,ROLE_USER,ROLE_API,all', 'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.baidu.com', null, '7200', '259200', null, '2021-04-29 13:30:02', '0', '0', 'false');
INSERT INTO `oauth_client_details` VALUES ('c2', 'res2', '$2a$10$elc0WomTYbTSnInc8R32ouE8vIE3Hh9B.ql3CO1C/yYTTcklJLqLO', 'ROLE_API', 'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.baidu.com', null, '31536000', '2592000', null, '2021-04-29 13:28:33', '0', '0', 'false');

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `code` varchar(255) DEFAULT NULL,
  `authentication` blob,
  KEY `code_index` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` varchar(32) NOT NULL,
  `code` varchar(32) NOT NULL COMMENT '权限标识符',
  `description` varchar(64) DEFAULT NULL COMMENT '描述',
  `url` varchar(128) DEFAULT NULL COMMENT '请求地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'p1', '测试资源1', '/r/r1');
INSERT INTO `t_permission` VALUES ('2', 'p3', '测试资源3', '/r/r3');
INSERT INTO `t_permission` VALUES ('3', 'p2', '测试资源2', '/r/r2');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(32) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员', null, null, null, '');
INSERT INTO `t_role` VALUES ('2', '管理员2', null, null, null, 'Y');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `role_id` varchar(32) NOT NULL,
  `permission_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1', '1');
INSERT INTO `t_role_permission` VALUES ('1', '2');
INSERT INTO `t_role_permission` VALUES ('2', '3');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `fullname` varchar(255) NOT NULL COMMENT '用户姓名',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'zhangsan', '$2a$10$elc0WomTYbTSnInc8R32ouE8vIE3Hh9B.ql3CO1C/yYTTcklJLqLO', '张三', '18823654895');
INSERT INTO `t_user` VALUES ('2', 'lisi', '$2a$10$elc0WomTYbTSnInc8R32ouE8vIE3Hh9B.ql3CO1C/yYTTcklJLqLO', '李四', '12345678');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', null, null);
INSERT INTO `t_user_role` VALUES ('2', '2', null, null);
