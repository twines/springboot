/*
 Navicat Premium Data Transfer

 Source Server         : LeDan
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : ledan

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 27/10/2019 21:15:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for asset_bills
-- ----------------------------
DROP TABLE IF EXISTS `asset_bills`;
CREATE TABLE `asset_bills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '总系统用户id',
  `asset_bill` varchar(255) DEFAULT NULL COMMENT '资产证明文件url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `id` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '企业认证状态',
  `social_code` varchar(255) DEFAULT NULL COMMENT '社会统一信用代码',
  `legal_person_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '法人代表',
  `idcard_name` varchar(255) DEFAULT NULL COMMENT '身份证姓名',
  `idcard_serial` varchar(255) DEFAULT NULL COMMENT '身份证号码',
  `business_license_bill` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '营业执照',
  `idcard_up` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证正面',
  `idcard_down` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证反面',
  `user_id` int(11) NOT NULL COMMENT '总系统中的用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `state` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for role_copy1
-- ----------------------------
DROP TABLE IF EXISTS `role_copy1`;
CREATE TABLE `role_copy1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL COMMENT '角色标识程序中判断使用,如"admin",这个是唯一的:',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述,UI界面显示使用 ',
  `available` tinyint(2) DEFAULT '1' COMMENT ' 是否可用,如果不可用将不会添加给用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
-- Table structure for trade_bills
-- ----------------------------
DROP TABLE IF EXISTS `trade_bills`;
CREATE TABLE `trade_bills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户总系统中用户id',
  `tax_bill` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '税单',
  `logistics_bill` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物流单',
  `insurance_bill` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '保险单',
  `customs_bill` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报关单',
  `trade_contract_bill` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '贸易合同',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
