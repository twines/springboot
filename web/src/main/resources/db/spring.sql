/*
 Navicat Premium Data Transfer

 Source Server         : iCarrier
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : spring

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 05/09/2019 13:34:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for improv
-- ----------------------------
DROP TABLE IF EXISTS `improv`;
CREATE TABLE `improv` (
  `id` int(11) NOT NULL,
  `code` varchar(255) NOT NULL COMMENT '统一社会信用代码',
  `name` varchar(255) NOT NULL COMMENT '法人姓名',
  `code_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业营业执照',
  `name_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '法人身份证',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of improv
-- ----------------------------
BEGIN;
INSERT INTO `improv` VALUES (758, '12345678', '323', '/tmp/8b83122d-0fb2-4325-9ac5-6602da53e1f1.png', '/tmp/0fe9f07d-90fa-4029-839b-2fcc3c3fcc39.jpg');
COMMIT;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限名称',
  `resource_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源类型，[menu|button]  ',
  `url` varchar(255) DEFAULT NULL COMMENT '资源路径.  ',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view ',
  `parent_id` int(11) DEFAULT NULL COMMENT '父编号  0代表自身为父',
  `parent_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父编号列表  0/1/2/3分别为层级',
  `available` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES (1, '用户管理', 'menu', '\'userInfo/userList', 'userInfo:view', 0, '0/', 1);
INSERT INTO `permission` VALUES (2, '用户添加', 'button', 'userInfo/userAdd', 'userInfo:add', 1, '0/1', 1);
INSERT INTO `permission` VALUES (3, '用户删除', 'button', 'userInfo/userDel', 'userInfo:del', 1, '0/1', 1);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL COMMENT '角色标识程序中判断使用,如"admin",这个是唯一的:',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述,UI界面显示使用 ',
  `available` tinyint(2) DEFAULT '1' COMMENT ' 是否可用,如果不可用将不会添加给用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 'admin', '系统管理员', 1);
INSERT INTO `role` VALUES (2, 'vip', 'VIP会员\'', 1);
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permission_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
INSERT INTO `role_permission` VALUES ('1', 1, 1);
INSERT INTO `role_permission` VALUES ('2', 1, 2);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'lee', 'e10adc3949ba59abbe56e057f20f883e', 'Lee');
INSERT INTO `user` VALUES (2, 'jack', 'e10adc3949ba59abbe56e057f20f883e', 'Jack');
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 2, 2);
INSERT INTO `user_role` VALUES (3, 2, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
