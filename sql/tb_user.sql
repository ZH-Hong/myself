/*
 Navicat Premium Data Transfer

 Source Server         : MysqlConn
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : powerup

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 28/11/2021 22:29:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `delete_flag` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'Jone', 18, 'test1@baomidou.com', 1);
INSERT INTO `tb_user` VALUES (2, 'Jack', 20, 'test2@baomidou.com', 1);
INSERT INTO `tb_user` VALUES (3, 'Tom', 28, 'test3@baomidou.com', 1);
INSERT INTO `tb_user` VALUES (4, 'Sandy', 21, 'test4@baomidou.com', 1);
INSERT INTO `tb_user` VALUES (5, 'Billie', 24, 'test5@baomidou.com', 1);
INSERT INTO `tb_user` VALUES (1464138004358754306, '张三', 20, '1@git.com', 1);
INSERT INTO `tb_user` VALUES (1464142050712809473, '张三1', 20, '1@git.com', 1);
INSERT INTO `tb_user` VALUES (1464142479651684353, '张三1', 20, '1@git.com', 0);
INSERT INTO `tb_user` VALUES (1464144591047532546, '张三1', 20, '1@git.com', NULL);

SET FOREIGN_KEY_CHECKS = 1;
