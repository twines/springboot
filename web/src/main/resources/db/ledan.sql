/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : ledan

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-10-28 00:10:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for asset_bills
-- ----------------------------
DROP TABLE IF EXISTS `asset_bills`;
CREATE TABLE `asset_bills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '总系统用户id',
  `asset_bill` varchar(255) DEFAULT NULL COMMENT '资产证明文件url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asset_bills
-- ----------------------------
INSERT INTO `asset_bills` VALUES ('1', '0', '/758/b577c7a6-4a08-4ede-9bb2-8fb8b15312b0.jpg');
INSERT INTO `asset_bills` VALUES ('2', '0', '/758/cdc5b61c-74bf-4b69-a96c-38753ce83a4d.jpg');
INSERT INTO `asset_bills` VALUES ('3', '1', '/758/b577c7a6-4a08-4ede-9bb2-8fb8b15312b0.jpg');
INSERT INTO `asset_bills` VALUES ('4', '1', '/758/cdc5b61c-74bf-4b69-a96c-38753ce83a4d.jpg');
INSERT INTO `asset_bills` VALUES ('5', '1', '/758/b577c7a6-4a08-4ede-9bb2-8fb8b15312b0.jpg');
INSERT INTO `asset_bills` VALUES ('6', '1', '/758/cdc5b61c-74bf-4b69-a96c-38753ce83a4d.jpg');
INSERT INTO `asset_bills` VALUES ('7', '1', '/758/b577c7a6-4a08-4ede-9bb2-8fb8b15312b0.jpg');
INSERT INTO `asset_bills` VALUES ('8', '1', '/758/cdc5b61c-74bf-4b69-a96c-38753ce83a4d.jpg');
INSERT INTO `asset_bills` VALUES ('9', '1', '/758/e0fd304a-8bfa-4598-92c9-a6d09e666c7d.jpg');
INSERT INTO `asset_bills` VALUES ('10', '1', '/758/0d8ebf66-bfe0-4d19-9482-19dceef3c5da.jpg');

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '企业认证状态',
  `social_code` varchar(255) DEFAULT NULL COMMENT '社会统一信用代码',
  `legal_person_name` varchar(255) DEFAULT NULL COMMENT '法人代表',
  `idcard_name` varchar(255) DEFAULT NULL COMMENT '身份证姓名',
  `idcard_serial` varchar(255) DEFAULT NULL COMMENT '身份证号码',
  `business_license_bill` varchar(255) NOT NULL COMMENT '营业执照',
  `idcard_up` varchar(255) NOT NULL COMMENT '身份证正面',
  `idcard_down` varchar(255) NOT NULL COMMENT '身份证反面',
  `user_id` int(11) NOT NULL COMMENT '总系统中的用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of author
-- ----------------------------
INSERT INTO `author` VALUES ('1', '0', '统一社会信用代码', '统一社会信用代码', '统一社会信用代码', '统一社会信用代码', '/758/d3e2afde-791c-4ddd-be61-68ed2c86508a.jpg', '/758/2a3713c7-946c-42af-bca0-c42b9f050be4.jpg', '/758/3852edf9-ba2d-4187-b1a4-4c144daa6b7c.jpg', '1');

-- ----------------------------
-- Table structure for improv
-- ----------------------------
DROP TABLE IF EXISTS `improv`;
CREATE TABLE `improv` (
  `id` int(11) NOT NULL,
  `code` varchar(255) NOT NULL COMMENT '统一社会信用代码',
  `name` varchar(255) NOT NULL COMMENT '法人姓名',
  `code_url` varchar(255) NOT NULL COMMENT '企业营业执照',
  `name_url` varchar(255) NOT NULL COMMENT '法人身份证',
  `state` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of improv
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `resource_type` varchar(255) DEFAULT NULL COMMENT '资源类型，[menu|button]  ',
  `url` varchar(255) DEFAULT NULL COMMENT '资源路径.  ',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view ',
  `parent_id` int(11) DEFAULT NULL COMMENT '父编号  0代表自身为父',
  `parent_ids` varchar(255) DEFAULT NULL COMMENT '父编号列表  0/1/2/3分别为层级',
  `available` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------

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
-- Records of role_copy1
-- ----------------------------

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` varchar(255) NOT NULL,
  `permission_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for trade_bills
-- ----------------------------
DROP TABLE IF EXISTS `trade_bills`;
CREATE TABLE `trade_bills` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户总系统中用户id',
  `tax_bill` varchar(255) NOT NULL COMMENT '税单',
  `logistics_bill` varchar(255) NOT NULL COMMENT '物流单',
  `insurance_bill` varchar(255) NOT NULL COMMENT '保险单',
  `customs_bill` varchar(255) NOT NULL COMMENT '报关单',
  `trade_contract_bill` varchar(255) NOT NULL COMMENT '贸易合同',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trade_bills
-- ----------------------------
INSERT INTO `trade_bills` VALUES ('1', '1', '/758/cda4f3a9-de30-4069-a5ac-32e895faea83.jpg', '/758/3c623041-f868-42d9-a7e2-241696ca0e81.jpg', '/758/b348652c-a6b0-4cac-8e67-48a5a12e577c.jpg', '/758/24ccb1c8-4a7f-4c4e-ae6d-5d889fda1378.jpg', '/758/1cfb20ad-c335-424d-94b7-c52ddb71f4bf.jpg');
INSERT INTO `trade_bills` VALUES ('2', '1', '/758/cda4f3a9-de30-4069-a5ac-32e895faea83.jpg', '/758/3c623041-f868-42d9-a7e2-241696ca0e81.jpg', '/758/b348652c-a6b0-4cac-8e67-48a5a12e577c.jpg', '/758/24ccb1c8-4a7f-4c4e-ae6d-5d889fda1378.jpg', '/758/1cfb20ad-c335-424d-94b7-c52ddb71f4bf.jpg');
INSERT INTO `trade_bills` VALUES ('3', '1', '/758/cda4f3a9-de30-4069-a5ac-32e895faea83.jpg', '/758/3c623041-f868-42d9-a7e2-241696ca0e81.jpg', '/758/b348652c-a6b0-4cac-8e67-48a5a12e577c.jpg', '/758/24ccb1c8-4a7f-4c4e-ae6d-5d889fda1378.jpg', '/758/1cfb20ad-c335-424d-94b7-c52ddb71f4bf.jpg');
INSERT INTO `trade_bills` VALUES ('4', '1', '/758/cda4f3a9-de30-4069-a5ac-32e895faea83.jpg', '/758/3c623041-f868-42d9-a7e2-241696ca0e81.jpg', '/758/b348652c-a6b0-4cac-8e67-48a5a12e577c.jpg', '/758/24ccb1c8-4a7f-4c4e-ae6d-5d889fda1378.jpg', '/758/1cfb20ad-c335-424d-94b7-c52ddb71f4bf.jpg');
INSERT INTO `trade_bills` VALUES ('5', '1', '/758/cda4f3a9-de30-4069-a5ac-32e895faea83.jpg', '/758/3c623041-f868-42d9-a7e2-241696ca0e81.jpg', '/758/b348652c-a6b0-4cac-8e67-48a5a12e577c.jpg', '/758/24ccb1c8-4a7f-4c4e-ae6d-5d889fda1378.jpg', '/758/1cfb20ad-c335-424d-94b7-c52ddb71f4bf.jpg');
INSERT INTO `trade_bills` VALUES ('6', '1', '/758/cda4f3a9-de30-4069-a5ac-32e895faea83.jpg', '/758/3c623041-f868-42d9-a7e2-241696ca0e81.jpg', '/758/b348652c-a6b0-4cac-8e67-48a5a12e577c.jpg', '/758/24ccb1c8-4a7f-4c4e-ae6d-5d889fda1378.jpg', '/758/1cfb20ad-c335-424d-94b7-c52ddb71f4bf.jpg');

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
