/*
Navicat MySQL Data Transfer

Source Server         : zkpt
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : zkpt

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-03-23 14:15:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mid_authorization_area
-- ----------------------------
DROP TABLE IF EXISTS `mid_authorization_area`;
CREATE TABLE `mid_authorization_area` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) DEFAULT NULL COMMENT '地区名称',
  `USER_CODE` char(5) DEFAULT NULL COMMENT '用户编码',
  `IS_EFFECT` char(1) DEFAULT NULL COMMENT '是否生效',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='业务授权地区';

-- ----------------------------
-- Records of mid_authorization_area
-- ----------------------------
INSERT INTO `mid_authorization_area` VALUES ('1', '周口', '1001', '1');
INSERT INTO `mid_authorization_area` VALUES ('2', '项城', '1101', '0');
INSERT INTO `mid_authorization_area` VALUES ('3', '西华', '1301', '0');
INSERT INTO `mid_authorization_area` VALUES ('4', '郸城', '1401', '0');
INSERT INTO `mid_authorization_area` VALUES ('5', '鹿邑', '1201', '0');

-- ----------------------------
-- Table structure for mid_gas_user
-- ----------------------------
DROP TABLE IF EXISTS `mid_gas_user`;
CREATE TABLE `mid_gas_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(50) DEFAULT NULL COMMENT '用户号',
  `USER_NO` varchar(50) DEFAULT NULL COMMENT '用户名',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `DELFLAG` smallint(6) DEFAULT NULL COMMENT '删除标记',
  `DEL_DATE` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`ID`),
  KEY `user_no` (`USER_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mid_gas_user
-- ----------------------------
INSERT INTO `mid_gas_user` VALUES ('52', '吴华伟', '100109200060', 'system', '2018-12-06 11:58:16', 'system', '2018-12-06 11:58:16', '0', null);
INSERT INTO `mid_gas_user` VALUES ('53', '闫帅', '100101290034', 'system', '2018-12-11 12:51:10', 'system', '2018-12-11 12:51:10', '0', null);
INSERT INTO `mid_gas_user` VALUES ('54', '郝祥亮', '100101700019', 'system', '2019-02-21 15:51:01', 'system', '2019-02-21 15:51:01', '0', null);
INSERT INTO `mid_gas_user` VALUES ('55', '李月丽', '100102990014', 'system', '2019-02-22 14:19:41', 'system', '2019-02-22 14:19:41', '0', null);
INSERT INTO `mid_gas_user` VALUES ('56', '张西岭', '100103620008', 'system', '2019-02-22 17:09:58', 'system', '2019-02-22 17:09:58', '0', null);
INSERT INTO `mid_gas_user` VALUES ('57', '牛铁路', '100103620049', 'system', '2019-02-22 17:40:56', 'system', '2019-02-22 17:40:56', '0', null);
INSERT INTO `mid_gas_user` VALUES ('58', '刘志政', '140101360004', 'system', '2019-02-22 20:04:40', 'system', '2019-02-22 20:04:40', '0', null);
INSERT INTO `mid_gas_user` VALUES ('59', '王国辉', '100108350191', 'system', '2019-02-25 10:24:18', 'system', '2019-02-25 10:24:18', '0', null);
INSERT INTO `mid_gas_user` VALUES ('60', '高宁', '100101250127', 'system', '2019-03-05 12:08:10', 'system', '2019-03-05 12:08:10', '0', null);
INSERT INTO `mid_gas_user` VALUES ('61', '徐华东', '130100660133', 'system', '2019-03-15 09:36:25', 'system', '2019-03-15 09:36:25', '0', null);
INSERT INTO `mid_gas_user` VALUES ('62', '刘光辉', '100110090080', 'system', '2019-03-18 09:40:24', 'system', '2019-03-18 09:40:24', '0', null);
INSERT INTO `mid_gas_user` VALUES ('63', '??31', '100100170008', 'system', '2019-03-21 14:48:02', 'system', '2019-03-21 14:48:02', '0', null);

-- ----------------------------
-- Table structure for mid_gas_user_behavior
-- ----------------------------
DROP TABLE IF EXISTS `mid_gas_user_behavior`;
CREATE TABLE `mid_gas_user_behavior` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPERTION_ID` int(11) DEFAULT NULL COMMENT '操作记录日志ID',
  `BANK_DEVICE_ID` int(11) DEFAULT NULL COMMENT '银行设备ID',
  `USER_NO` varchar(50) DEFAULT NULL COMMENT '用户名',
  `VALUE1` varchar(200) DEFAULT NULL COMMENT '值1',
  `VALUE2` varchar(200) DEFAULT NULL COMMENT '值2',
  `VALUE3` varchar(200) DEFAULT NULL COMMENT '值3',
  `VALUE4` varchar(200) DEFAULT NULL COMMENT '值4',
  `VALUE5` varchar(200) DEFAULT NULL COMMENT '值5',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `DELFLAG` smallint(6) DEFAULT NULL COMMENT '删除标记',
  `DEL_DATE` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`ID`),
  KEY `opertaion_id` (`OPERTION_ID`) USING BTREE,
  KEY `user_no` (`USER_NO`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8 COMMENT='记录天然气用户行为。';

-- ----------------------------
-- Records of mid_gas_user_behavior
-- ----------------------------
INSERT INTO `mid_gas_user_behavior` VALUES ('30', '60', null, '100101290034', '37027945', '7.87', '201812|', null, null, 'system', '2018-12-11 12:51:10', 'system', '2018-12-11 12:51:10', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('31', '61', null, '100101290034', '37027981', '7.87', '201812|', null, null, 'system', '2018-12-11 12:51:38', 'system', '2018-12-11 12:51:38', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('32', '62', null, '100101290034', '37028719', '7.87', '201812|', null, null, 'system', '2018-12-11 12:57:15', 'system', '2018-12-11 12:57:15', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('33', '63', null, '100101290034', '37028979', '7.87', '201812|', null, null, 'system', '2018-12-11 12:58:29', 'system', '2018-12-11 12:58:29', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('34', '64', null, '100101290034', '37029278', '100.00', '999H0010', '000000', null, 'system', '2018-12-11 13:00:16', 'system', '2018-12-11 13:00:16', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('35', '65', null, '20181217', '38017841', '0', null, null, null, 'system', '2018-12-18 00:44:49', 'system', '2018-12-18 00:44:49', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('36', '66', null, '20181218', '38190305', '0', null, null, null, 'system', '2018-12-19 00:44:59', 'system', '2018-12-19 00:44:59', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('37', '67', null, '20190217', '43237227', '0', null, null, null, 'system', '2019-02-18 00:46:15', 'system', '2019-02-18 00:46:15', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('38', '68', null, '20190218', '43317712', '0', null, null, null, 'system', '2019-02-19 00:46:22', 'system', '2019-02-19 00:46:22', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('39', '69', null, '20190219', '43389779', '0', null, null, null, 'system', '2019-02-20 00:46:06', 'system', '2019-02-20 00:46:06', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('40', '70', null, '20190220', '43485512', '0', null, null, null, 'system', '2019-02-21 00:46:18', 'system', '2019-02-21 00:46:18', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('41', '71', null, '100101700019', '43546229', '118.56', '201902|', null, null, 'system', '2019-02-21 15:51:01', 'system', '2019-02-21 15:51:01', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('42', '72', null, '100101700019', '43546261', '118.56', '201902|', null, null, 'system', '2019-02-21 15:51:12', 'system', '2019-02-21 15:51:12', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('43', '73', null, '100101700019', '43546599', '118.56', '201902|', null, null, 'system', '2019-02-21 15:53:22', 'system', '2019-02-21 15:53:22', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('44', '74', null, '100101700019', '43559820', '118.56', '201902|', null, null, 'system', '2019-02-21 17:21:06', 'system', '2019-02-21 17:21:06', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('45', '75', null, '20190221', '43581489', '0', null, null, null, 'system', '2019-02-22 00:46:19', 'system', '2019-02-22 00:46:19', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('46', '76', null, '100102990014', '43632584', '18.77', '201902|', null, null, 'system', '2019-02-22 14:19:41', 'system', '2019-02-22 14:19:41', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('47', '77', null, '100102990014', '43632598', '18.77', '201902|', null, null, 'system', '2019-02-22 14:19:46', 'system', '2019-02-22 14:19:46', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('48', '78', null, '100103620008', '43667835', '83.98', '201902|', null, null, 'system', '2019-02-22 17:09:58', 'system', '2019-02-22 17:09:58', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('49', '79', null, '100103620008', '43668192', '83.98', '201902|', null, null, 'system', '2019-02-22 17:13:12', 'system', '2019-02-22 17:13:12', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('50', '80', null, '100103620008', '43668228', '83.98', '201902|', null, null, 'system', '2019-02-22 17:13:30', 'system', '2019-02-22 17:13:30', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('51', '81', null, '100103620008', '43671165', '83.98', '201902|', null, null, 'system', '2019-02-22 17:40:07', 'system', '2019-02-22 17:40:07', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('52', '82', null, '100103620049', '43671241', '61.75', '201902|', null, null, 'system', '2019-02-22 17:40:56', 'system', '2019-02-22 17:40:56', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('53', '83', null, '100103620008', '43673344', '83.98', '201902|', null, null, 'system', '2019-02-22 18:10:12', 'system', '2019-02-22 18:10:12', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('54', '84', null, '140101360004', '43677215', '36.27', '201901|201902|', null, null, 'system', '2019-02-22 20:04:40', 'system', '2019-02-22 20:04:40', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('55', '85', null, '140101360004', '43677216', '36.27', '201901|201902|', null, null, 'system', '2019-02-22 20:05:07', 'system', '2019-02-22 20:05:07', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('56', '86', null, '100103620008', '43695246', '83.98', '201902|', null, null, 'system', '2019-02-23 09:01:57', 'system', '2019-02-23 09:01:57', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('57', '87', null, '100103620008', '43695517', '83.98', '201902|', null, null, 'system', '2019-02-23 09:09:51', 'system', '2019-02-23 09:09:51', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('58', '88', null, '100103620008', '43695565', '83.98', '201902|', null, null, 'system', '2019-02-23 09:11:31', 'system', '2019-02-23 09:11:31', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('59', '89', null, '100103620008', '43698437', '83.98', '201902|', null, null, 'system', '2019-02-23 10:15:22', 'system', '2019-02-23 10:15:22', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('60', '90', null, '100103620008', '43698462', '84.00', '999H0010', '000000', null, 'system', '2019-02-23 10:16:12', 'system', '2019-02-23 10:16:12', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('61', '91', null, '20190223', '43724843', '1', null, null, null, 'system', '2019-02-24 00:46:22', 'system', '2019-02-24 00:46:22', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('62', '92', null, '100103620008', '43735793', '83.98', '201902|', null, null, 'system', '2019-02-24 08:37:46', 'system', '2019-02-24 08:37:46', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('63', '93', null, '20190224', '43764652', '0', null, null, null, 'system', '2019-02-25 00:46:42', 'system', '2019-02-25 00:46:42', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('64', '94', null, '100108350191', '43791678', '21.65', '201902|', null, null, 'system', '2019-02-25 10:24:18', 'system', '2019-02-25 10:24:18', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('65', '95', null, '100108350191', '43791829', '22.00', '38020200', '000000', null, 'system', '2019-02-25 10:25:06', 'system', '2019-02-25 10:25:06', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('66', '96', null, '20190225', '43855967', '1', null, null, null, 'system', '2019-02-26 00:46:25', 'system', '2019-02-26 00:46:25', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('67', '97', null, '100102990014', '43921053', '18.77', '201902|', null, null, 'system', '2019-02-26 16:41:30', 'system', '2019-02-26 16:41:30', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('68', '98', null, '100102990014', '43921097', '18.77', '201902|', null, null, 'system', '2019-02-26 16:41:54', 'system', '2019-02-26 16:41:54', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('69', '99', null, '100102990014', '43921164', '100.00', '999H0010', '000000', null, 'system', '2019-02-26 16:42:54', 'system', '2019-02-26 16:42:54', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('70', '100', null, '20190226', '43940454', '1', null, null, null, 'system', '2019-02-27 00:46:16', 'system', '2019-02-27 00:46:16', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('71', '101', null, '100103620008', '6397851', '83.98', '201902|', null, null, 'system', '2019-03-02 10:58:17', 'system', '2019-03-02 10:58:17', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('72', '102', null, '20190302', '44236403', '0', null, null, null, 'system', '2019-03-03 00:46:21', 'system', '2019-03-03 00:46:21', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('73', '103', null, '100108350191', '44253299', '21.65', '201902|', null, null, 'system', '2019-03-03 10:12:55', 'system', '2019-03-03 10:12:55', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('74', '104', null, '20190303', '44284656', '0', null, null, null, 'system', '2019-03-04 00:46:23', 'system', '2019-03-04 00:46:23', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('75', '105', null, '100103620008', '44300083', '83.98', '201902|', null, null, 'system', '2019-03-04 08:41:07', 'system', '2019-03-04 08:41:07', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('76', '106', null, '20190304', '44386338', '0', null, null, null, 'system', '2019-03-05 00:46:24', 'system', '2019-03-05 00:46:24', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('77', '107', null, '100101250127', '44434066', '1348.03', '201903|', null, null, 'system', '2019-03-05 12:08:10', 'system', '2019-03-05 12:08:10', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('78', '108', null, '100101250127', '44434102', '1348.03', '201903|', null, null, 'system', '2019-03-05 12:08:22', 'system', '2019-03-05 12:08:22', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('79', '109', null, '100101250127', '44434134', '1348.03', '201903|', null, null, 'system', '2019-03-05 12:08:31', 'system', '2019-03-05 12:08:31', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('80', '110', null, '100101250127', '44434231', '1500.00', '999H0010', '000000', null, 'system', '2019-03-05 12:08:59', 'system', '2019-03-05 12:08:59', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('81', '111', null, '100103620008', '44477671', '83.98', '201902|', null, null, 'system', '2019-03-05 22:00:07', 'system', '2019-03-05 22:00:07', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('82', '112', null, '20190306', '44594426', '0', null, null, null, 'system', '2019-03-07 00:46:37', 'system', '2019-03-07 00:46:37', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('83', '113', null, '20190307', '44685665', '0', null, null, null, 'system', '2019-03-08 00:46:30', 'system', '2019-03-08 00:46:30', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('84', '114', null, '20190308', '44789329', '0', null, null, null, 'system', '2019-03-09 00:46:30', 'system', '2019-03-09 00:46:30', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('85', '115', null, '20190309', '44841971', '0', null, null, null, 'system', '2019-03-10 00:47:00', 'system', '2019-03-10 00:47:00', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('86', '116', null, '20190310', '44894039', '0', null, null, null, 'system', '2019-03-11 00:46:43', 'system', '2019-03-11 00:46:43', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('87', '117', null, '20190312', '45096621', '0', null, null, null, 'system', '2019-03-13 00:46:54', 'system', '2019-03-13 00:46:54', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('88', '118', null, '100108350191', '45153191', '66.11', '201902|201903|', null, null, 'system', '2019-03-13 12:25:24', 'system', '2019-03-13 12:25:24', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('89', '119', null, '20190313', '45200216', '0', null, null, null, 'system', '2019-03-14 00:46:36', 'system', '2019-03-14 00:46:36', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('90', '120', null, '100108350191', '45255972', '66.11', '201902|201903|', null, null, 'system', '2019-03-14 12:32:03', 'system', '2019-03-14 12:32:03', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('91', '121', null, '100108350191', '45256043', '66.11', '38020200', '000000', null, 'system', '2019-03-14 12:33:41', 'system', '2019-03-14 12:33:41', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('92', '122', null, '100103620008', '45288764', '41.97', '201903|', null, null, 'system', '2019-03-14 18:30:54', 'system', '2019-03-14 18:30:54', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('93', '123', null, '20190314', '45299221', '1', null, null, null, 'system', '2019-03-15 00:46:41', 'system', '2019-03-15 00:46:41', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('94', '124', null, '130100660133', '45325653', '44.8', '201903|', null, null, 'system', '2019-03-15 09:36:25', 'system', '2019-03-15 09:36:25', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('95', '125', null, '130100660133', '45325724', '44.8', '201903|', null, null, 'system', '2019-03-15 09:36:44', 'system', '2019-03-15 09:36:44', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('96', '126', null, '130100660133', '45325868', '200.00', '999H0010', '000000', null, 'system', '2019-03-15 09:37:37', 'system', '2019-03-15 09:37:37', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('97', '127', null, '100103620008', '45340360', '41.97', '201903|', null, null, 'system', '2019-03-15 11:10:16', 'system', '2019-03-15 11:10:16', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('98', '128', null, '100103620008', '45340506', '42.00', '999H0010', '000000', null, 'system', '2019-03-15 11:11:17', 'system', '2019-03-15 11:11:17', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('99', '129', null, '20190315', '45394647', '2', null, null, null, 'system', '2019-03-16 00:46:38', 'system', '2019-03-16 00:46:38', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('100', '130', null, '100103620008', '45432319', '41.97', '201903|', null, null, 'system', '2019-03-16 15:57:53', 'system', '2019-03-16 15:57:53', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('101', '131', null, '100103620008', '45433037', '41.97', '201903|', null, null, 'system', '2019-03-16 16:07:21', 'system', '2019-03-16 16:07:21', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('102', '132', null, '100103620008', '45433388', '41.97', '201903|', null, null, 'system', '2019-03-16 16:13:38', 'system', '2019-03-16 16:13:38', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('103', '133', null, '100103620008', '45435552', '41.97', '201903|', null, null, 'system', '2019-03-16 17:09:45', 'system', '2019-03-16 17:09:45', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('104', '134', null, '100103620008', '45435580', '41.97', '201903|', null, null, 'system', '2019-03-16 17:10:32', 'system', '2019-03-16 17:10:32', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('105', '135', null, '130100660133', '45435693', '44.8', '201903|', null, null, 'system', '2019-03-16 17:14:18', 'system', '2019-03-16 17:14:18', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('106', '136', null, '100103620008', '6397851', '41.97', '201903|', null, null, 'system', '2019-03-16 20:51:44', 'system', '2019-03-16 20:51:44', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('107', '137', null, '20190316', '45445560', '0', null, null, null, 'system', '2019-03-17 00:46:39', 'system', '2019-03-17 00:46:39', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('108', '138', null, '100108350191', '45467038', '66.11', '201902|201903|', null, null, 'system', '2019-03-17 10:54:30', 'system', '2019-03-17 10:54:30', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('109', '139', null, '20190317', '45496209', '0', null, null, null, 'system', '2019-03-18 00:47:19', 'system', '2019-03-18 00:47:19', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('110', '140', null, '100110090080', '45524666', '29.32', '201903|', null, null, 'system', '2019-03-18 09:40:24', 'system', '2019-03-18 09:40:24', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('111', '141', null, '100108350191', '45539187', '66.11', '201902|201903|', null, null, 'system', '2019-03-18 11:01:46', 'system', '2019-03-18 11:01:46', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('112', '142', null, '130100660133', '45572990', '44.8', '201903|', null, null, 'system', '2019-03-18 15:50:36', 'system', '2019-03-18 15:50:36', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('113', '143', null, '100100170008', '6397851', '8.56', '201809|201810|', null, null, 'system', '2019-03-21 14:48:02', 'system', '2019-03-21 14:48:02', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('114', '144', null, '100100170008', 'rq63978511', '8.56', '201809|201810|', null, null, 'system', '2019-03-21 15:13:49', 'system', '2019-03-21 15:13:49', '0', null);
INSERT INTO `mid_gas_user_behavior` VALUES ('115', '145', null, '100100170008', 'rq63978512', '100.0', '00100080', '000000', '01', 'system', '2019-03-21 15:15:01', 'system', '2019-03-21 15:15:01', '0', null);

-- ----------------------------
-- Table structure for mid_gas_user_cost
-- ----------------------------
DROP TABLE IF EXISTS `mid_gas_user_cost`;
CREATE TABLE `mid_gas_user_cost` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(50) DEFAULT NULL COMMENT '用户号',
  `USER_NO` varchar(50) DEFAULT NULL COMMENT '用户名',
  `MONTH` int(6) DEFAULT NULL COMMENT '月份',
  `PREV_VAL` decimal(10,2) DEFAULT NULL COMMENT '上次示度',
  `CURR_VAL` decimal(10,2) DEFAULT NULL COMMENT '本次示度',
  `AIR_VAL` decimal(10,2) DEFAULT NULL COMMENT '气量',
  `AIR_COST` decimal(10,2) DEFAULT NULL COMMENT '气费',
  `PAY_ABLE_AIR_NUM` decimal(10,2) DEFAULT NULL COMMENT '应缴气费',
  `LATE_FEE` decimal(10,2) DEFAULT NULL COMMENT '滞纳金',
  `PAY_ABLE_AIR_COST` decimal(10,2) DEFAULT NULL COMMENT '应缴费用',
  `AIR_VAL1` decimal(10,2) DEFAULT NULL COMMENT '气量1',
  `AIR_COST1` decimal(10,2) DEFAULT NULL COMMENT '气价1',
  `AIR_VAL2` decimal(10,2) DEFAULT NULL COMMENT '气量2',
  `AIR_COST2` decimal(10,2) DEFAULT NULL COMMENT '气价2',
  `AIR_VAL3` decimal(10,2) DEFAULT NULL COMMENT '气量3',
  `AIR_COST3` decimal(10,2) DEFAULT NULL COMMENT '气价3',
  `AIR_VAL4` decimal(10,2) DEFAULT NULL COMMENT '气量3',
  `AIR_COST4` decimal(10,2) DEFAULT NULL COMMENT '气价4',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `DELFLAG` smallint(6) DEFAULT NULL COMMENT '删除标记',
  `DEL_DATE` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`ID`),
  KEY `USER_NO` (`USER_NO`) USING BTREE,
  KEY `MONTH` (`MONTH`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='天然气用户欠费信息，记录欠费月份及每月的详细信息。';

-- ----------------------------
-- Records of mid_gas_user_cost
-- ----------------------------
INSERT INTO `mid_gas_user_cost` VALUES ('23', '闫帅', '100101290034', '201812', '1146.00', '1150.00', '4.00', '9.88', '7.87', '0.00', '7.87', '4.00', '2.47', null, null, null, null, null, null, 'system', '2018-12-11 12:51:10', 'system', '2018-12-11 12:51:10', '0', null);
INSERT INTO `mid_gas_user_cost` VALUES ('24', '郝祥亮', '100101700019', '201902', '1735.00', '1783.00', '48.00', '118.56', '118.56', '0.00', '118.56', '48.00', '2.47', null, null, null, null, null, null, 'system', '2019-02-21 15:51:01', 'system', '2019-02-21 15:51:01', '0', null);
INSERT INTO `mid_gas_user_cost` VALUES ('25', '李月丽', '100102990014', '201902', '968.00', '981.00', '13.00', '32.11', '18.77', '0.00', '18.77', '13.00', '2.47', null, null, null, null, null, null, 'system', '2019-02-22 14:19:41', 'system', '2019-02-22 14:19:41', '0', null);
INSERT INTO `mid_gas_user_cost` VALUES ('26', '张西岭', '100103620008', '201902', '1207.00', '1241.00', '34.00', '83.98', '83.98', '0.00', '83.98', '34.00', '2.47', null, null, null, null, null, null, 'system', '2019-02-22 17:09:58', 'system', '2019-02-22 17:09:58', '0', null);
INSERT INTO `mid_gas_user_cost` VALUES ('27', '牛铁路', '100103620049', '201902', '358.00', '383.00', '25.00', '61.75', '61.75', '0.00', '61.75', '25.00', '2.47', null, null, null, null, null, null, 'system', '2019-02-22 17:40:56', 'system', '2019-02-22 17:40:56', '0', null);
INSERT INTO `mid_gas_user_cost` VALUES ('28', '刘志政', '140101360004', '201901', '217.00', '235.00', '18.00', '46.62', '2.60', '0.00', '2.60', '18.00', '2.59', null, null, null, null, null, null, 'system', '2019-02-22 20:04:40', 'system', '2019-02-22 20:04:40', '0', null);
INSERT INTO `mid_gas_user_cost` VALUES ('29', '刘志政', '140101360004', '201902', '235.00', '248.00', '13.00', '33.67', '33.67', '0.00', '33.67', '13.00', '2.59', null, null, null, null, null, null, 'system', '2019-02-22 20:04:40', 'system', '2019-02-22 20:04:40', '0', null);
INSERT INTO `mid_gas_user_cost` VALUES ('30', '王国辉', '100108350191', '201902', '518.00', '533.00', '15.00', '37.05', '21.65', '0.00', '21.65', '15.00', '2.47', null, null, null, null, null, null, 'system', '2019-02-25 10:24:18', 'system', '2019-02-25 10:24:18', '0', null);
INSERT INTO `mid_gas_user_cost` VALUES ('31', '高宁', '100101250127', '201903', '20745.00', '21188.00', '443.00', '1348.03', '1348.03', '0.00', '1348.03', '100.00', '2.47', '343.00', '3.21', null, null, null, null, 'system', '2019-03-05 12:08:10', 'system', '2019-03-05 12:08:10', '0', null);
INSERT INTO `mid_gas_user_cost` VALUES ('32', '王国辉', '100108350191', '201903', '533.00', '551.00', '18.00', '44.46', '44.46', '0.00', '44.46', '18.00', '2.47', null, null, null, null, null, null, 'system', '2019-03-13 12:25:24', 'system', '2019-03-13 12:25:24', '0', null);
INSERT INTO `mid_gas_user_cost` VALUES ('33', '张西岭', '100103620008', '201903', '1241.00', '1258.00', '17.00', '41.99', '41.97', '0.00', '41.97', '17.00', '2.47', null, null, null, null, null, null, 'system', '2019-03-14 18:30:54', 'system', '2019-03-14 18:30:54', '0', null);
INSERT INTO `mid_gas_user_cost` VALUES ('34', '徐华东', '130100660133', '201903', '1742.00', '1765.00', '23.00', '59.57', '44.80', '0.00', '44.80', '23.00', '2.59', null, null, null, null, null, null, 'system', '2019-03-15 09:36:25', 'system', '2019-03-15 09:36:25', '0', null);
INSERT INTO `mid_gas_user_cost` VALUES ('35', '刘光辉', '100110090080', '201903', '44.00', '56.00', '12.00', '29.64', '29.32', '0.00', '29.32', '12.00', '2.47', null, null, null, null, null, null, 'system', '2019-03-18 09:40:24', 'system', '2019-03-18 09:40:24', '0', null);
INSERT INTO `mid_gas_user_cost` VALUES ('36', '??31', '100100170008', '201809', '3.00', '4.00', '2.00', '4.28', '4.28', '0.00', '4.28', '2.00', '2.14', null, null, null, null, null, null, 'system', '2019-03-21 14:48:02', 'system', '2019-03-21 14:48:02', '0', null);
INSERT INTO `mid_gas_user_cost` VALUES ('37', '??31', '100100170008', '201810', '4.00', '6.00', '2.00', '4.28', '4.28', '0.00', '4.28', '2.00', '2.14', null, null, null, null, null, null, 'system', '2019-03-21 14:48:02', 'system', '2019-03-21 14:48:02', '0', null);

-- ----------------------------
-- Table structure for mid_operation
-- ----------------------------
DROP TABLE IF EXISTS `mid_operation`;
CREATE TABLE `mid_operation` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BANK_IP` varchar(20) DEFAULT NULL COMMENT '银行IP地址',
  `BANK_PORT` int(5) DEFAULT NULL COMMENT '银行端口号',
  `BANK_SEND` varchar(500) DEFAULT NULL COMMENT '银行发送',
  `BANK_SEND_TIME` datetime DEFAULT NULL COMMENT '闂佺偓鍎奸、鎴﹀矗閹达腹鍋撴担瑙勵槯闂?',
  `BANK_SEND_TIME_MILLIS` bigint(20) DEFAULT NULL COMMENT '银行发送时间毫秒',
  `GAS_ECHO` varchar(500) DEFAULT NULL COMMENT '天然气回应',
  `GAS_ECHO_TIME` datetime DEFAULT NULL COMMENT '濠㈠灈鏅濋崝褍顫濋弬鎸庣閹煎瓨姊瑰鍌炴⒒?',
  `GAS_ECHO_TIME_MILLIS` bigint(20) DEFAULT NULL COMMENT '天然气回应时间毫秒',
  `BANK_COMMAND` varchar(10) DEFAULT NULL COMMENT '银行命令',
  `GAS_COMMAND` varchar(10) DEFAULT NULL COMMENT '天然气命令',
  `STATE` char(255) DEFAULT NULL COMMENT '状态 S:成功 F:失败',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `DELFLAG` smallint(6) DEFAULT NULL COMMENT '删除标记',
  `DEL_DATE` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`ID`),
  KEY `bank_send` (`BANK_SEND`(255)),
  KEY `gas_echo` (`GAS_ECHO`(255))
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8 COMMENT='中间件操作日志';

-- ----------------------------
-- Records of mid_operation
-- ----------------------------
INSERT INTO `mid_operation` VALUES ('60', '11.1.51.2', '62299', '010009      0537027945      0026|811|100101290034|000000|1|', '2018-12-11 12:51:10', null, '0101008000000000500370279452018121111512100000010507.87|1|100101290034|闫帅|201812|1146|1150|4|9.88|7.87|0|7.87|4|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2018-12-11 12:51:10', null, '811', '0101', 'S', 'system', '2018-12-11 12:51:10', 'system', '2018-12-11 12:51:10', '0', null);
INSERT INTO `mid_operation` VALUES ('61', '11.1.51.2', '62430', '010009      0537027981      0026|811|100101290034|000000|1|', '2018-12-11 12:51:38', null, '0101008000000000500370279812018121111514900000010507.87|1|100101290034|闫帅|201812|1146|1150|4|9.88|7.87|0|7.87|4|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2018-12-11 12:51:38', null, '811', '0101', 'S', 'system', '2018-12-11 12:51:38', 'system', '2018-12-11 12:51:38', '0', null);
INSERT INTO `mid_operation` VALUES ('62', '11.1.51.2', '65106', '010009      0537028719      0026|811|100101290034|000000|1|', '2018-12-11 12:57:15', null, '0101008000000000500370287192018121111572500000010507.87|1|100101290034|闫帅|201812|1146|1150|4|9.88|7.87|0|7.87|4|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2018-12-11 12:57:15', null, '811', '0101', 'S', 'system', '2018-12-11 12:57:15', 'system', '2018-12-11 12:57:15', '0', null);
INSERT INTO `mid_operation` VALUES ('63', '11.1.51.2', '33261', '010009      0537028979      0026|811|100101290034|000000|1|', '2018-12-11 12:58:29', null, '0101008000000000500370289792018121111584000000010507.87|1|100101290034|闫帅|201812|1146|1150|4|9.88|7.87|0|7.87|4|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2018-12-11 12:58:29', null, '811', '0101', 'S', 'system', '2018-12-11 12:58:29', 'system', '2018-12-11 12:58:29', '0', null);
INSERT INTO `mid_operation` VALUES ('64', '11.1.51.2', '34389', '010009      0137029278      0063|821|100101290034|100.00|7.87|20181211125959|999H0010|000000|1||', '2018-12-11 13:00:16', null, '010200800000000010037029278201812111200270000000000', '2018-12-11 13:00:16', null, '821', '0102', 'S', 'system', '2018-12-11 13:00:16', 'system', '2018-12-11 13:00:16', '0', null);
INSERT INTO `mid_operation` VALUES ('65', '11.1.51.2', '45322', '010009      0138017841      0020|600|20181217|0|0.00|', '2018-12-18 00:44:38', null, '010900800000000010038017841201812172345460000000000', '2018-12-18 00:44:49', null, '600', '0109', 'S', 'system', '2018-12-18 00:44:49', 'system', '2018-12-18 00:44:49', '0', null);
INSERT INTO `mid_operation` VALUES ('66', '11.1.51.2', '37130', '010009      0138190305      0020|600|20181218|0|0.00|', '2018-12-19 00:44:39', null, '010900800000000010038190305201812182345550000000000', '2018-12-19 00:44:59', null, '600', '0109', 'S', 'system', '2018-12-19 00:44:59', 'system', '2018-12-19 00:44:59', '0', null);
INSERT INTO `mid_operation` VALUES ('67', '11.1.51.2', '62188', '010009      0143237227      0020|600|20190217|0|0.00|', '2019-02-18 00:45:55', null, '010900800000000010043237227201902172345510000000000', '2019-02-18 00:46:15', null, '600', '0109', 'S', 'system', '2019-02-18 00:46:15', 'system', '2019-02-18 00:46:15', '0', null);
INSERT INTO `mid_operation` VALUES ('68', '11.1.51.2', '36555', '010009      0143317712      0020|600|20190218|0|0.00|', '2019-02-19 00:46:02', null, '010900800000000010043317712201902182345560000000000', '2019-02-19 00:46:22', null, '600', '0109', 'S', 'system', '2019-02-19 00:46:22', 'system', '2019-02-19 00:46:22', '0', null);
INSERT INTO `mid_operation` VALUES ('69', '11.1.51.2', '37743', '010009      0143389779      0020|600|20190219|0|0.00|', '2019-02-20 00:45:56', null, '010900800000000010043389779201902192345380000000000', '2019-02-20 00:46:06', null, '600', '0109', 'S', 'system', '2019-02-20 00:46:06', 'system', '2019-02-20 00:46:06', '0', null);
INSERT INTO `mid_operation` VALUES ('70', '11.1.51.2', '64514', '010009      0143485512      0020|600|20190220|0|0.00|', '2019-02-21 00:45:58', null, '010900800000000010043485512201902202345480000000000', '2019-02-21 00:46:18', null, '600', '0109', 'S', 'system', '2019-02-21 00:46:18', 'system', '2019-02-21 00:46:18', '0', null);
INSERT INTO `mid_operation` VALUES ('71', '11.1.51.2', '36768', '010009      0543546229      0026|811|100101700019|000000|1|', '2019-02-21 15:51:01', null, '010100800000000050043546229201902211450300000001170118.56|1|100101700019|郝祥亮|201902|1735|1783|48|118.56|118.56|0|118.56|48|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-21 15:51:01', null, '811', '0101', 'S', 'system', '2019-02-21 15:51:01', 'system', '2019-02-21 15:51:01', '0', null);
INSERT INTO `mid_operation` VALUES ('72', '11.1.51.2', '36852', '010009      0543546261      0026|811|100101700019|000000|1|', '2019-02-21 15:51:12', null, '010100800000000050043546261201902211450410000001170118.56|1|100101700019|郝祥亮|201902|1735|1783|48|118.56|118.56|0|118.56|48|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-21 15:51:12', null, '811', '0101', 'S', 'system', '2019-02-21 15:51:12', 'system', '2019-02-21 15:51:12', '0', null);
INSERT INTO `mid_operation` VALUES ('73', '11.1.51.2', '37816', '010009      0543546599      0026|811|100101700019|000000|1|', '2019-02-21 15:53:22', null, '010100800000000050043546599201902211452500000001170118.56|1|100101700019|郝祥亮|201902|1735|1783|48|118.56|118.56|0|118.56|48|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-21 15:53:22', null, '811', '0101', 'S', 'system', '2019-02-21 15:53:22', 'system', '2019-02-21 15:53:22', '0', null);
INSERT INTO `mid_operation` VALUES ('74', '11.1.51.2', '40069', '010009      0543559820      0026|811|100101700019|000000|1|', '2019-02-21 17:21:06', null, '010100800000000050043559820201902211620340000001170118.56|1|100101700019|郝祥亮|201902|1735|1783|48|118.56|118.56|0|118.56|48|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-21 17:21:06', null, '811', '0101', 'S', 'system', '2019-02-21 17:21:06', 'system', '2019-02-21 17:21:06', '0', null);
INSERT INTO `mid_operation` VALUES ('75', '11.1.51.2', '58771', '010009      0143581489      0020|600|20190221|0|0.00|', '2019-02-22 00:45:59', null, '010900800000000010043581489201902212345470000000000', '2019-02-22 00:46:19', null, '600', '0109', 'S', 'system', '2019-02-22 00:46:19', 'system', '2019-02-22 00:46:19', '0', null);
INSERT INTO `mid_operation` VALUES ('76', '11.1.51.2', '35695', '010009      0543632584      0026|811|100102990014|000000|1|', '2019-02-22 14:19:31', null, '01010080000000005004363258420190222131907000000111018.77|1|100102990014|李月丽|201902|968|981|13|32.11|18.77|0|18.77|13|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-22 14:19:41', null, '811', '0101', 'S', 'system', '2019-02-22 14:19:41', 'system', '2019-02-22 14:19:41', '0', null);
INSERT INTO `mid_operation` VALUES ('77', '11.1.51.2', '35739', '010009      0543632598      0026|811|100102990014|000000|1|', '2019-02-22 14:19:46', null, '01010080000000005004363259820190222131912000000111018.77|1|100102990014|李月丽|201902|968|981|13|32.11|18.77|0|18.77|13|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-22 14:19:46', null, '811', '0101', 'S', 'system', '2019-02-22 14:19:46', 'system', '2019-02-22 14:19:46', '0', null);
INSERT INTO `mid_operation` VALUES ('78', '11.1.51.2', '50623', '010009      0543667835      0026|811|100103620008|000000|1|', '2019-02-22 17:09:58', null, '01010080000000005004366783520190222160924000000113083.98|1|100103620008|张西岭|201902|1207|1241|34|83.98|83.98|0|83.98|34|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-22 17:09:58', null, '811', '0101', 'S', 'system', '2019-02-22 17:09:58', 'system', '2019-02-22 17:09:58', '0', null);
INSERT INTO `mid_operation` VALUES ('79', '11.1.51.2', '51439', '010009      0543668192      0026|811|100103620008|000000|1|', '2019-02-22 17:13:12', null, '01010080000000005004366819220190222161238000000113083.98|1|100103620008|张西岭|201902|1207|1241|34|83.98|83.98|0|83.98|34|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-22 17:13:12', null, '811', '0101', 'S', 'system', '2019-02-22 17:13:12', 'system', '2019-02-22 17:13:12', '0', null);
INSERT INTO `mid_operation` VALUES ('80', '11.1.51.2', '51550', '010009      0543668228      0026|811|100103620008|000000|1|', '2019-02-22 17:13:30', null, '01010080000000005004366822820190222161256000000113083.98|1|100103620008|张西岭|201902|1207|1241|34|83.98|83.98|0|83.98|34|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-22 17:13:30', null, '811', '0101', 'S', 'system', '2019-02-22 17:13:30', 'system', '2019-02-22 17:13:30', '0', null);
INSERT INTO `mid_operation` VALUES ('81', '11.1.51.2', '58339', '010009      0143671165      0026|811|100103620008|000000|1|', '2019-02-22 17:40:07', null, '01010080000000001004367116520190222163933000000113083.98|1|100103620008|张西岭|201902|1207|1241|34|83.98|83.98|0|83.98|34|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-22 17:40:07', null, '811', '0101', 'S', 'system', '2019-02-22 17:40:07', 'system', '2019-02-22 17:40:07', '0', null);
INSERT INTO `mid_operation` VALUES ('82', '11.1.51.2', '58533', '010009      0543671241      0026|811|100103620049|000000|1|', '2019-02-22 17:40:56', null, '01010080000000005004367124120190222164021000000111061.75|1|100103620049|牛铁路|201902|358|383|25|61.75|61.75|0|61.75|25|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-22 17:40:56', null, '811', '0101', 'S', 'system', '2019-02-22 17:40:56', 'system', '2019-02-22 17:40:56', '0', null);
INSERT INTO `mid_operation` VALUES ('83', '11.1.51.2', '63890', '010009      0543673344      0026|811|100103620008|000000|1|', '2019-02-22 18:10:12', null, '01010080000000005004367334420190222170938000000113083.98|1|100103620008|张西岭|201902|1207|1241|34|83.98|83.98|0|83.98|34|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-22 18:10:12', null, '811', '0101', 'S', 'system', '2019-02-22 18:10:12', 'system', '2019-02-22 18:10:12', '0', null);
INSERT INTO `mid_operation` VALUES ('84', '11.1.51.2', '40872', '010009      0543677215      0026|811|140101360004|000000|1|', '2019-02-22 20:04:40', null, '01010080000000005004367721520190222190406000000210036.27|2|140101360004|刘志政|201901|217|235|18|46.62|2.6|0|2.6|18|2.59|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$140101360004|刘志政|201902|235|248|13|33.67|33.67|0|33.67|13|2.59|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-22 20:04:40', null, '811', '0101', 'S', 'system', '2019-02-22 20:04:40', 'system', '2019-02-22 20:04:40', '0', null);
INSERT INTO `mid_operation` VALUES ('85', '11.1.51.2', '40878', '010009      0543677216      0026|811|140101360004|000000|1|', '2019-02-22 20:05:07', null, '01010080000000005004367721620190222190433000000210036.27|2|140101360004|刘志政|201901|217|235|18|46.62|2.6|0|2.6|18|2.59|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$140101360004|刘志政|201902|235|248|13|33.67|33.67|0|33.67|13|2.59|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-22 20:05:07', null, '811', '0101', 'S', 'system', '2019-02-22 20:05:07', 'system', '2019-02-22 20:05:07', '0', null);
INSERT INTO `mid_operation` VALUES ('86', '11.1.51.2', '54103', '010009      0143695246      0026|811|100103620008|000000|1|', '2019-02-23 09:01:47', null, '01010080000000001004369524620190223080122000000113083.98|1|100103620008|张西岭|201902|1207|1241|34|83.98|83.98|0|83.98|34|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-23 09:01:57', null, '811', '0101', 'S', 'system', '2019-02-23 09:01:57', 'system', '2019-02-23 09:01:57', '0', null);
INSERT INTO `mid_operation` VALUES ('87', '11.1.51.2', '54796', '010009      0143695517      0026|811|100103620008|000000|1|', '2019-02-23 09:09:51', null, '01010080000000001004369551720190223080915000000113083.98|1|100103620008|张西岭|201902|1207|1241|34|83.98|83.98|0|83.98|34|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-23 09:09:51', null, '811', '0101', 'S', 'system', '2019-02-23 09:09:51', 'system', '2019-02-23 09:09:51', '0', null);
INSERT INTO `mid_operation` VALUES ('88', '11.1.51.2', '54938', '010009      0143695565      0026|811|100103620008|000000|1|', '2019-02-23 09:11:31', null, '01010080000000001004369556520190223081055000000113083.98|1|100103620008|张西岭|201902|1207|1241|34|83.98|83.98|0|83.98|34|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-23 09:11:31', null, '811', '0101', 'S', 'system', '2019-02-23 09:11:31', 'system', '2019-02-23 09:11:31', '0', null);
INSERT INTO `mid_operation` VALUES ('89', '11.1.51.2', '62898', '010009      0543698437      0026|811|100103620008|000000|1|', '2019-02-23 10:15:22', null, '01010080000000005004369843720190223091447000000113083.98|1|100103620008|张西岭|201902|1207|1241|34|83.98|83.98|0|83.98|34|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-23 10:15:22', null, '811', '0101', 'S', 'system', '2019-02-23 10:15:22', 'system', '2019-02-23 10:15:22', '0', null);
INSERT INTO `mid_operation` VALUES ('90', '11.1.51.2', '62981', '010009      0143698462      0063|821|100103620008|84.00|83.98|20190223101545|999H0010|000000|1||', '2019-02-23 10:16:12', null, '010200800000000010043698462201902230915370000000000', '2019-02-23 10:16:12', null, '821', '0102', 'S', 'system', '2019-02-23 10:16:12', 'system', '2019-02-23 10:16:12', '0', null);
INSERT INTO `mid_operation` VALUES ('91', '11.1.51.2', '35322', '010009      0143724843      0021|600|20190223|1|84.00|', '2019-02-24 00:46:01', null, '010900800000000010043724843201902232345450000000000', '2019-02-24 00:46:22', null, '600', '0109', 'S', 'system', '2019-02-24 00:46:22', 'system', '2019-02-24 00:46:22', '0', null);
INSERT INTO `mid_operation` VALUES ('92', '11.1.51.2', '63432', '010009      0543735793      0026|811|100103620008|000000|1|', '2019-02-24 08:37:36', null, '01010080000000005004373579320190224073708000000113083.98|1|100103620008|张西岭|201902|1207|1241|34|83.98|83.98|0|83.98|34|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-24 08:37:46', null, '811', '0101', 'S', 'system', '2019-02-24 08:37:46', 'system', '2019-02-24 08:37:46', '0', null);
INSERT INTO `mid_operation` VALUES ('93', '11.1.51.2', '40976', '010009      0143764652      0020|600|20190224|0|0.00|', '2019-02-25 00:46:22', null, '010900800000000010043764652201902242346030000000000', '2019-02-25 00:46:42', null, '600', '0109', 'S', 'system', '2019-02-25 00:46:42', 'system', '2019-02-25 00:46:42', '0', null);
INSERT INTO `mid_operation` VALUES ('94', '11.1.51.2', '49023', '010009      0143791678      0026|811|100108350191|000000|1|', '2019-02-25 10:24:17', null, '01010080000000001004379167820190225092338000000111021.65|1|100108350191|王国辉|201902|518|533|15|37.05|21.65|0|21.65|15|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-25 10:24:18', null, '811', '0101', 'S', 'system', '2019-02-25 10:24:18', 'system', '2019-02-25 10:24:18', '0', null);
INSERT INTO `mid_operation` VALUES ('95', '11.1.51.2', '49450', '010009      0143791829      0063|821|100108350191|22.00|21.65|20190225102437|38020200|000000|1||', '2019-02-25 10:25:06', null, '010200800000000010043791829201902250924260000000000', '2019-02-25 10:25:06', null, '821', '0102', 'S', 'system', '2019-02-25 10:25:06', 'system', '2019-02-25 10:25:06', '0', null);
INSERT INTO `mid_operation` VALUES ('96', '11.1.51.2', '59181', '010009      0143855967      0021|600|20190225|1|22.00|', '2019-02-26 00:46:05', null, '010900800000000010043855967201902252345440000000000', '2019-02-26 00:46:25', null, '600', '0109', 'S', 'system', '2019-02-26 00:46:25', 'system', '2019-02-26 00:46:25', '0', null);
INSERT INTO `mid_operation` VALUES ('97', '11.1.51.2', '42135', '010009      0543921053      0026|811|100102990014|000000|1|', '2019-02-26 16:41:30', null, '01010080000000005004392105320190226154047000000111018.77|1|100102990014|李月丽|201902|968|981|13|32.11|18.77|0|18.77|13|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-26 16:41:30', null, '811', '0101', 'S', 'system', '2019-02-26 16:41:30', 'system', '2019-02-26 16:41:30', '0', null);
INSERT INTO `mid_operation` VALUES ('98', '11.1.51.2', '42266', '010009      0543921097      0026|811|100102990014|000000|1|', '2019-02-26 16:41:54', null, '01010080000000005004392109720190226154112000000111018.77|1|100102990014|李月丽|201902|968|981|13|32.11|18.77|0|18.77|13|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-02-26 16:41:54', null, '811', '0101', 'S', 'system', '2019-02-26 16:41:54', 'system', '2019-02-26 16:41:54', '0', null);
INSERT INTO `mid_operation` VALUES ('99', '11.1.51.2', '42479', '010009      0143921164      0064|821|100102990014|100.00|18.77|20190226164223|999H0010|000000|1||', '2019-02-26 16:42:54', null, '010200800000000010043921164201902261542120000000000', '2019-02-26 16:42:54', null, '821', '0102', 'S', 'system', '2019-02-26 16:42:54', 'system', '2019-02-26 16:42:54', '0', null);
INSERT INTO `mid_operation` VALUES ('100', '11.1.51.2', '57817', '010009      0143940454      0022|600|20190226|1|100.00|', '2019-02-27 00:46:05', null, '010900800000000010043940454201902262345320000000000', '2019-02-27 00:46:16', null, '600', '0109', 'S', 'system', '2019-02-27 00:46:16', 'system', '2019-02-27 00:46:16', '0', null);
INSERT INTO `mid_operation` VALUES ('101', '11.49.35.48', '37460', '010008      016397851       0026|811|100103620008|000000|1|', '2019-03-02 10:58:17', '1551495497053', '01010080000000001000639785120190302095727000000113083.98|1|100103620008|张西岭|201902|1207|1241|34|83.98|83.98|0|83.98|34|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-02 10:58:17', '1551495497118', '811', '0101', 'S', 'system', '2019-03-02 10:58:17', 'system', '2019-03-02 10:58:17', '0', null);
INSERT INTO `mid_operation` VALUES ('102', '11.1.51.2', '46014', '010009      0144236403      0020|600|20190302|0|0.00|', '2019-03-03 00:46:11', '1551545171045', '010900800000000010044236403201903022345300000000000', '2019-03-03 00:46:21', '1551545181445', '600', '0109', 'S', 'system', '2019-03-03 00:46:21', 'system', '2019-03-03 00:46:21', '0', null);
INSERT INTO `mid_operation` VALUES ('103', '11.1.51.2', '57747', '010009      0144253299      0026|811|100108350191|000000|1|', '2019-03-03 10:12:55', '1551579174576', '01010080000000001004425329920190303091202000000111021.65|1|100108350191|王国辉|201902|518|533|15|37.05|21.65|0|21.65|15|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-03 10:12:55', '1551579174646', '811', '0101', 'S', 'system', '2019-03-03 10:12:55', 'system', '2019-03-03 10:12:55', '0', null);
INSERT INTO `mid_operation` VALUES ('104', '11.1.51.2', '45445', '010009      0144284656      0020|600|20190303|0|0.00|', '2019-03-04 00:46:12', '1551631572273', '010900800000000010044284656201903032345290000000000', '2019-03-04 00:46:23', '1551631582640', '600', '0109', 'S', 'system', '2019-03-04 00:46:23', 'system', '2019-03-04 00:46:23', '0', null);
INSERT INTO `mid_operation` VALUES ('105', '11.1.51.2', '52072', '010009      0544300083      0026|811|100103620008|000000|1|', '2019-03-04 08:41:07', '1551660067214', '01010080000000005004430008320190304074013000000113083.98|1|100103620008|张西岭|201902|1207|1241|34|83.98|83.98|0|83.98|34|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-04 08:41:07', '1551660067294', '811', '0101', 'S', 'system', '2019-03-04 08:41:07', 'system', '2019-03-04 08:41:07', '0', null);
INSERT INTO `mid_operation` VALUES ('106', '11.1.51.2', '39554', '010009      0144386338      0020|600|20190304|0|0.00|', '2019-03-05 00:46:14', '1551717973865', '010900800000000010044386338201903042345280000000000', '2019-03-05 00:46:24', '1551717984223', '600', '0109', 'S', 'system', '2019-03-05 00:46:24', 'system', '2019-03-05 00:46:24', '0', null);
INSERT INTO `mid_operation` VALUES ('107', '11.1.51.2', '43205', '010009      0544434066      0026|811|100101250127|000000|1|', '2019-03-05 12:08:10', '1551758890231', '0101008000000000500444340662019030511071400000012001348.03|1|100101250127|高宁|201903|20745|21188|443|1348.03|1348.03|0|1348.03|100|2.47|343|3.21|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-05 12:08:10', '1551758890414', '811', '0101', 'S', 'system', '2019-03-05 12:08:10', 'system', '2019-03-05 12:08:10', '0', null);
INSERT INTO `mid_operation` VALUES ('108', '11.1.51.2', '43312', '010009      0544434102      0026|811|100101250127|000000|1|', '2019-03-05 12:08:22', '1551758901591', '0101008000000000500444341022019030511072500000012001348.03|1|100101250127|高宁|201903|20745|21188|443|1348.03|1348.03|0|1348.03|100|2.47|343|3.21|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-05 12:08:22', '1551758901710', '811', '0101', 'S', 'system', '2019-03-05 12:08:22', 'system', '2019-03-05 12:08:22', '0', null);
INSERT INTO `mid_operation` VALUES ('109', '11.1.51.2', '43417', '010009      0544434134      0026|811|100101250127|000000|1|', '2019-03-05 12:08:31', '1551758910544', '0101008000000000500444341342019030511073400000012001348.03|1|100101250127|高宁|201903|20745|21188|443|1348.03|1348.03|0|1348.03|100|2.47|343|3.21|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-05 12:08:31', '1551758910662', '811', '0101', 'S', 'system', '2019-03-05 12:08:31', 'system', '2019-03-05 12:08:31', '0', null);
INSERT INTO `mid_operation` VALUES ('110', '11.1.51.2', '43685', '010009      0144434231      0067|821|100101250127|1500.00|1348.03|20190305120820|999H0010|000000|1||', '2019-03-05 12:08:59', '1551758939191', '010200800000000010044434231201903051108030000000000', '2019-03-05 12:08:59', '1551758939353', '821', '0102', 'S', 'system', '2019-03-05 12:08:59', 'system', '2019-03-05 12:08:59', '0', null);
INSERT INTO `mid_operation` VALUES ('111', '11.1.51.2', '37624', '010009      0544477671      0026|811|100103620008|000000|1|', '2019-03-05 22:00:07', '1551794406610', '01010080000000005004447767120190305205909000000113083.98|1|100103620008|张西岭|201902|1207|1241|34|83.98|83.98|0|83.98|34|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-05 22:00:07', '1551794406761', '811', '0101', 'S', 'system', '2019-03-05 22:00:07', 'system', '2019-03-05 22:00:07', '0', null);
INSERT INTO `mid_operation` VALUES ('112', '11.1.51.2', '54164', '010009      0144594426      0020|600|20190306|0|0.00|', '2019-03-07 00:46:17', '1551890776782', '010900800000000010044594426201903062346380000000000', '2019-03-07 00:46:37', '1551890797276', '600', '0109', 'S', 'system', '2019-03-07 00:46:37', 'system', '2019-03-07 00:46:37', '0', null);
INSERT INTO `mid_operation` VALUES ('113', '11.1.51.2', '47033', '010009      0144685665      0020|600|20190307|0|0.00|', '2019-03-08 00:46:20', '1551977180054', '010900800000000010044685665201903072346290000000000', '2019-03-08 00:46:30', '1551977190406', '600', '0109', 'S', 'system', '2019-03-08 00:46:30', 'system', '2019-03-08 00:46:30', '0', null);
INSERT INTO `mid_operation` VALUES ('114', '11.1.51.2', '38382', '010009      0144789329      0020|600|20190308|0|0.00|', '2019-03-09 00:46:20', '1552063579590', '010900800000000010044789329201903082346260000000000', '2019-03-09 00:46:30', '1552063589948', '600', '0109', 'S', 'system', '2019-03-09 00:46:30', 'system', '2019-03-09 00:46:30', '0', null);
INSERT INTO `mid_operation` VALUES ('115', '11.1.51.2', '47964', '010009      0144841971      0020|600|20190309|0|0.00|', '2019-03-10 00:46:40', '1552149999911', '010900800000000010044841971201903092346550000000000', '2019-03-10 00:47:00', '1552150020291', '600', '0109', 'S', 'system', '2019-03-10 00:47:00', 'system', '2019-03-10 00:47:00', '0', null);
INSERT INTO `mid_operation` VALUES ('116', '11.1.51.2', '54863', '010009      0144894039      0020|600|20190310|0|0.00|', '2019-03-11 00:46:22', '1552236382120', '010900800000000010044894039201903110046350000000000', '2019-03-11 00:46:43', '1552236402510', '600', '0109', 'S', 'system', '2019-03-11 00:46:43', 'system', '2019-03-11 00:46:43', '0', null);
INSERT INTO `mid_operation` VALUES ('117', '11.1.51.2', '35008', '010009      0145096621      0020|600|20190312|0|0.00|', '2019-03-13 00:46:43', '1552409203155', '010900800000000010045096621201903130046420000000000', '2019-03-13 00:46:54', '1552409213512', '600', '0109', 'S', 'system', '2019-03-13 00:46:54', 'system', '2019-03-13 00:46:54', '0', null);
INSERT INTO `mid_operation` VALUES ('118', '11.1.51.2', '59684', '010009      0145153191      0026|811|100108350191|000000|1|', '2019-03-13 12:25:14', '1552451114351', '01010080000000001004515319120190313122511000000214066.11|2|100108350191|王国辉|201902|518|533|15|37.05|21.65|0|21.65|15|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$100108350191|王国辉|201903|533|551|18|44.46|44.46|0|44.46|18|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-13 12:25:24', '1552451124471', '811', '0101', 'S', 'system', '2019-03-13 12:25:24', 'system', '2019-03-13 12:25:24', '0', null);
INSERT INTO `mid_operation` VALUES ('119', '11.1.51.2', '58987', '010009      0145200216      0020|600|20190313|0|0.00|', '2019-03-14 00:46:25', '1552495585384', '010900800000000010045200216201903140046220000000000', '2019-03-14 00:46:36', '1552495595788', '600', '0109', 'S', 'system', '2019-03-14 00:46:36', 'system', '2019-03-14 00:46:36', '0', null);
INSERT INTO `mid_operation` VALUES ('120', '11.1.51.2', '48143', '010009      0145255972      0026|811|100108350191|000000|1|', '2019-03-14 12:32:03', '1552537922881', '01010080000000001004525597220190314123148000000214066.11|2|100108350191|王国辉|201902|518|533|15|37.05|21.65|0|21.65|15|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$100108350191|王国辉|201903|533|551|18|44.46|44.46|0|44.46|18|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-14 12:32:03', '1552537922940', '811', '0101', 'S', 'system', '2019-03-14 12:32:03', 'system', '2019-03-14 12:32:03', '0', null);
INSERT INTO `mid_operation` VALUES ('121', '11.1.51.2', '48375', '010009      0145256043      0063|821|100108350191|66.11|66.11|20190314123250|38020200|000000|1||', '2019-03-14 12:33:41', '1552538020508', '010200800000000010045256043201903141233260000000000', '2019-03-14 12:33:41', '1552538020631', '821', '0102', 'S', 'system', '2019-03-14 12:33:41', 'system', '2019-03-14 12:33:41', '0', null);
INSERT INTO `mid_operation` VALUES ('122', '11.1.51.2', '45425', '010009      0545288764      0026|811|100103620008|000000|1|', '2019-03-14 18:30:54', '1552559454322', '01010080000000005004528876420190314183039000000113041.97|1|100103620008|张西岭|201903|1241|1258|17|41.99|41.97|0|41.97|17|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-14 18:30:54', '1552559454380', '811', '0101', 'S', 'system', '2019-03-14 18:30:54', 'system', '2019-03-14 18:30:54', '0', null);
INSERT INTO `mid_operation` VALUES ('123', '11.1.51.2', '37601', '010009      0145299221      0021|600|20190314|1|66.11|', '2019-03-15 00:46:31', '1552581990622', '010900800000000010045299221201903150046250000000000', '2019-03-15 00:46:41', '1552582001011', '600', '0109', 'S', 'system', '2019-03-15 00:46:41', 'system', '2019-03-15 00:46:41', '0', null);
INSERT INTO `mid_operation` VALUES ('124', '11.1.51.2', '41009', '010009      0545325653      0026|811|130100660133|000000|1|', '2019-03-15 09:36:15', '1552613775267', '01010080000000005004532565320190315093609000000110044.8|1|130100660133|徐华东|201903|1742|1765|23|59.57|44.8|0|44.8|23|2.59|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-15 09:36:25', '1552613785477', '811', '0101', 'S', 'system', '2019-03-15 09:36:25', 'system', '2019-03-15 09:36:25', '0', null);
INSERT INTO `mid_operation` VALUES ('125', '11.1.51.2', '41194', '010009      0545325724      0026|811|130100660133|000000|1|', '2019-03-15 09:36:44', '1552613803925', '01010080000000005004532572420190315093627000000110044.8|1|130100660133|徐华东|201903|1742|1765|23|59.57|44.8|0|44.8|23|2.59|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-15 09:36:44', '1552613803983', '811', '0101', 'S', 'system', '2019-03-15 09:36:44', 'system', '2019-03-15 09:36:44', '0', null);
INSERT INTO `mid_operation` VALUES ('126', '11.1.51.2', '41634', '010009      0145325868      0064|821|130100660133|200.00|44.80|20190315093644|999H0010|000000|1||', '2019-03-15 09:37:36', '1552613856418', '010200800000000010045325868201903150937200000000000', '2019-03-15 09:37:37', '1552613856524', '821', '0102', 'S', 'system', '2019-03-15 09:37:37', 'system', '2019-03-15 09:37:37', '0', null);
INSERT INTO `mid_operation` VALUES ('127', '11.1.51.2', '52425', '010009      0545340360      0026|811|100103620008|000000|1|', '2019-03-15 11:10:16', '1552619416322', '01010080000000005004534036020190315110959000000113041.97|1|100103620008|张西岭|201903|1241|1258|17|41.99|41.97|0|41.97|17|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-15 11:10:16', '1552619416452', '811', '0101', 'S', 'system', '2019-03-15 11:10:16', 'system', '2019-03-15 11:10:16', '0', null);
INSERT INTO `mid_operation` VALUES ('128', '11.1.51.2', '52916', '010009      0145340506      0063|821|100103620008|42.00|41.97|20190315111025|999H0010|000000|1||', '2019-03-15 11:11:17', '1552619477061', '010200800000000010045340506201903151111000000000000', '2019-03-15 11:11:17', '1552619477183', '821', '0102', 'S', 'system', '2019-03-15 11:11:17', 'system', '2019-03-15 11:11:17', '0', null);
INSERT INTO `mid_operation` VALUES ('129', '11.1.51.2', '42261', '010009      0145394647      0022|600|20190315|2|242.00|', '2019-03-16 00:46:27', '1552668387451', '010900800000000010045394647201903160046280000000000', '2019-03-16 00:46:38', '1552668397805', '600', '0109', 'S', 'system', '2019-03-16 00:46:38', 'system', '2019-03-16 00:46:38', '0', null);
INSERT INTO `mid_operation` VALUES ('130', '11.1.51.2', '54737', '010009      0545432319      0026|811|100103620008|000000|1|', '2019-03-16 15:57:43', '1552723063057', '01010080000000005004543231920190316155742000000113041.97|1|100103620008|张西岭|201903|1241|1258|17|41.99|41.97|0|41.97|17|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-16 15:57:53', '1552723073143', '811', '0101', 'S', 'system', '2019-03-16 15:57:53', 'system', '2019-03-16 15:57:53', '0', null);
INSERT INTO `mid_operation` VALUES ('131', '11.1.51.2', '56847', '010009      0545433037      0026|811|100103620008|000000|1|', '2019-03-16 16:07:21', '1552723641331', '01010080000000005004543303720190316160710000000113041.97|1|100103620008|张西岭|201903|1241|1258|17|41.99|41.97|0|41.97|17|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-16 16:07:21', '1552723641392', '811', '0101', 'S', 'system', '2019-03-16 16:07:21', 'system', '2019-03-16 16:07:21', '0', null);
INSERT INTO `mid_operation` VALUES ('132', '11.1.51.2', '57886', '010009      0545433388      0026|811|100103620008|000000|1|', '2019-03-16 16:13:38', '1552724017908', '01010080000000005004543338820190316161327000000113041.97|1|100103620008|张西岭|201903|1241|1258|17|41.99|41.97|0|41.97|17|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-16 16:13:38', '1552724017965', '811', '0101', 'S', 'system', '2019-03-16 16:13:38', 'system', '2019-03-16 16:13:38', '0', null);
INSERT INTO `mid_operation` VALUES ('133', '11.1.51.2', '64326', '010009      0545435552      0026|811|100103620008|000000|1|', '2019-03-16 17:09:45', '1552727384525', '01010080000000005004543555220190316170933000000113041.97|1|100103620008|张西岭|201903|1241|1258|17|41.99|41.97|0|41.97|17|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-16 17:09:45', '1552727384582', '811', '0101', 'S', 'system', '2019-03-16 17:09:45', 'system', '2019-03-16 17:09:45', '0', null);
INSERT INTO `mid_operation` VALUES ('134', '11.1.51.2', '64378', '010009      0545435580      0026|811|100103620008|000000|1|', '2019-03-16 17:10:32', '1552727431590', '01010080000000005004543558020190316171020000000113041.97|1|100103620008|张西岭|201903|1241|1258|17|41.99|41.97|0|41.97|17|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-16 17:10:32', '1552727431648', '811', '0101', 'S', 'system', '2019-03-16 17:10:32', 'system', '2019-03-16 17:10:32', '0', null);
INSERT INTO `mid_operation` VALUES ('135', '11.1.51.2', '64717', '010009      0545435693      0026|811|130100660133|000000|1|', '2019-03-16 17:14:18', '1552727657651', '01010080000000005004543569320190316171406000000110044.8|1|130100660133|徐华东|201903|1742|1765|23|59.57|44.8|0|44.8|23|2.59|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-16 17:14:18', '1552727657727', '811', '0101', 'S', 'system', '2019-03-16 17:14:18', 'system', '2019-03-16 17:14:18', '0', null);
INSERT INTO `mid_operation` VALUES ('136', '11.49.35.48', '36048', '010008      016397851       0026|811|100103620008|000000|1|', '2019-03-16 20:51:44', '1552740704268', '01010080000000001000639785120190316205133000000113041.97|1|100103620008|张西岭|201903|1241|1258|17|41.99|41.97|0|41.97|17|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-16 20:51:44', '1552740704327', '811', '0101', 'S', 'system', '2019-03-16 20:51:44', 'system', '2019-03-16 20:51:44', '0', null);
INSERT INTO `mid_operation` VALUES ('137', '11.1.51.2', '57497', '010009      0145445560      0020|600|20190316|0|0.00|', '2019-03-17 00:46:29', '1552754788545', '010900800000000010045445560201903170046270000000000', '2019-03-17 00:46:39', '1552754798896', '600', '0109', 'S', 'system', '2019-03-17 00:46:39', 'system', '2019-03-17 00:46:39', '0', null);
INSERT INTO `mid_operation` VALUES ('138', '11.1.51.2', '48910', '010009      0145467038      0026|811|100108350191|000000|1|', '2019-03-17 10:54:20', '1552791259810', '01010080000000001004546703820190317105417000000214066.11|2|100108350191|王国辉|201902|518|533|15|37.05|21.65|0|21.65|15|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$100108350191|王国辉|201903|533|551|18|44.46|44.46|0|44.46|18|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-17 10:54:30', '1552791270012', '811', '0101', 'S', 'system', '2019-03-17 10:54:30', 'system', '2019-03-17 10:54:30', '0', null);
INSERT INTO `mid_operation` VALUES ('139', '11.1.51.2', '63403', '010009      0145496209      0020|600|20190317|0|0.00|', '2019-03-18 00:46:58', '1552841218349', '010900800000000010045496209201903180047050000000000', '2019-03-18 00:47:19', '1552841238708', '600', '0109', 'S', 'system', '2019-03-18 00:47:19', 'system', '2019-03-18 00:47:19', '0', null);
INSERT INTO `mid_operation` VALUES ('140', '11.1.51.2', '41457', '010009      0545524666      0026|811|100110090080|000000|1|', '2019-03-18 09:40:14', '1552873213856', '01010080000000005004552466620190318094009000000109029.32|1|100110090080|刘光辉|201903|44|56|12|29.64|29.32|0|29.32|12|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-18 09:40:24', '1552873224024', '811', '0101', 'S', 'system', '2019-03-18 09:40:24', 'system', '2019-03-18 09:40:24', '0', null);
INSERT INTO `mid_operation` VALUES ('141', '11.1.51.2', '53234', '010009      0145539187      0026|811|100108350191|000000|1|', '2019-03-18 11:01:45', '1552878105424', '01010080000000001004553918720190318110130000000214066.11|2|100108350191|王国辉|201902|518|533|15|37.05|21.65|0|21.65|15|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$100108350191|王国辉|201903|533|551|18|44.46|44.46|0|44.46|18|2.47|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-18 11:01:46', '1552878105543', '811', '0101', 'S', 'system', '2019-03-18 11:01:46', 'system', '2019-03-18 11:01:46', '0', null);
INSERT INTO `mid_operation` VALUES ('142', '11.1.51.2', '53474', '010009      0545572990      0026|811|130100660133|000000|1|', '2019-03-18 15:50:36', '1552895435733', '01010080000000005004557299020190318155020000000110044.8|1|130100660133|徐华东|201903|1742|1765|23|59.57|44.8|0|44.8|23|2.59|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-18 15:50:36', '1552895435790', '811', '0101', 'S', 'system', '2019-03-18 15:50:36', 'system', '2019-03-18 15:50:36', '0', null);
INSERT INTO `mid_operation` VALUES ('143', '192.168.1.6', '64958', '010008      016397851       0026|811|100100170008|000000|1|', '2019-03-21 14:47:43', '1553150863002', '0101008000000000100063978512018110113174800000019108.56|2|100100170008|??31|201809|3|4|2|4.28|4.28|0|4.28|2|2.14|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$100100170008|??31|201810|4|6|2|4.28|4.28|0|4.28|2|2.14|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-21 14:48:02', '1553150881586', '811', '0101', 'S', 'system', '2019-03-21 14:48:02', 'system', '2019-03-21 14:48:02', '0', null);
INSERT INTO `mid_operation` VALUES ('144', '192.168.1.6', '49364', '010008      0163978511      0026|811|100100170008|000000|1|', '2019-03-21 15:13:45', '1553152424658', '0101008000000000100063978512018110113174800000019108.56|2|100100170008|??31|201809|3|4|2|4.28|4.28|0|4.28|2|2.14|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$100100170008|??31|201810|4|6|2|4.28|4.28|0|4.28|2|2.14|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|FFFFF|$', '2019-03-21 15:13:49', '1553152428831', '811', '0101', 'S', 'system', '2019-03-21 15:13:49', 'system', '2019-03-21 15:13:49', '0', null);
INSERT INTO `mid_operation` VALUES ('145', '192.168.1.6', '49415', '010008      0163978512      0061|821|100100170008|100.0|100.0|20190321151439|00100080|000000|1', '2019-03-21 15:14:40', '1553152479536', '01020010000000000rq62981656201808260618020000000000', '2019-03-21 15:15:01', '1553152500825', '821', '0102', 'S', 'system', '2019-03-21 15:15:01', 'system', '2019-03-21 15:15:01', '0', null);

-- ----------------------------
-- Table structure for t_s_type
-- ----------------------------
DROP TABLE IF EXISTS `t_s_type`;
CREATE TABLE `t_s_type` (
  `ID` varchar(32) NOT NULL COMMENT 'id',
  `typecode` varchar(50) DEFAULT NULL COMMENT '字典编码',
  `typename` varchar(50) DEFAULT NULL COMMENT '字典名称',
  `typepid` varchar(32) DEFAULT NULL COMMENT '无用字段',
  `typegroupid` varchar(32) DEFAULT NULL COMMENT '字典组ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_name` varchar(36) DEFAULT NULL COMMENT '创建用户',
  `order_num` int(3) DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`ID`),
  KEY `FK_nw2b22gy7plh7pqows186odmq` (`typepid`) USING BTREE,
  KEY `FK_3q40mr4ebtd0cvx79matl39x1` (`typegroupid`) USING BTREE,
  CONSTRAINT `t_s_type_ibfk_1` FOREIGN KEY (`typegroupid`) REFERENCES `t_s_typegroup` (`id`),
  CONSTRAINT `t_s_type_ibfk_2` FOREIGN KEY (`typepid`) REFERENCES `t_s_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_type
-- ----------------------------
INSERT INTO `t_s_type` VALUES ('1', '811', '查询欠费明细', null, '1', '2018-11-27 11:00:56', 'sys', '1');
INSERT INTO `t_s_type` VALUES ('2', '821', '交易缴费', null, '1', '2018-11-27 11:04:24', 'sys', '2');
INSERT INTO `t_s_type` VALUES ('3', '850', '用户冲帐', null, '1', '2018-11-27 11:04:39', 'sys', '3');
INSERT INTO `t_s_type` VALUES ('4', '600', '对总帐', null, '1', '2018-11-27 11:05:05', 'sys', '4');

-- ----------------------------
-- Table structure for t_s_typegroup
-- ----------------------------
DROP TABLE IF EXISTS `t_s_typegroup`;
CREATE TABLE `t_s_typegroup` (
  `ID` varchar(32) NOT NULL COMMENT 'id',
  `typegroupcode` varchar(50) DEFAULT NULL COMMENT '字典分组编码',
  `typegroupname` varchar(50) DEFAULT NULL COMMENT '字典分组名称',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_name` varchar(36) DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`ID`),
  KEY `index_typegroupcode` (`typegroupcode`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_typegroup
-- ----------------------------
INSERT INTO `t_s_typegroup` VALUES ('1', 'protocol.command', '协议命令', '2018-11-27 11:00:38', 'sys');

-- ----------------------------
-- View structure for v_opeartion
-- ----------------------------
DROP VIEW IF EXISTS `v_opeartion`;
CREATE ALGORITHM=UNDEFINED DEFINER=`zkpt`@`%` SQL SECURITY DEFINER VIEW `v_opeartion` AS select `o`.`BANK_COMMAND` AS `BANK_COMMAND`,date_format(`o`.`CREATE_DATE`,'%Y') AS `YEAR`,date_format(`o`.`CREATE_DATE`,'%c') AS `MONTH`,date_format(`o`.`CREATE_DATE`,'%e') AS `DAY`,(((dayofmonth(`o`.`CREATE_DATE`) + weekday((`o`.`CREATE_DATE` - interval dayofmonth(`o`.`CREATE_DATE`) day))) DIV 7) + 1) AS `WEEK`,date_format(`o`.`CREATE_DATE`,'%Y%m%d') AS `FULL` from `mid_operation` `o` where ((`o`.`STATE` = 'S') and (`o`.`BANK_COMMAND` in (811,821,850))) ;

-- ----------------------------
-- View structure for v_payment_stream
-- ----------------------------
DROP VIEW IF EXISTS `v_payment_stream`;
CREATE ALGORITHM=UNDEFINED DEFINER=`zkpt`@`%` SQL SECURITY DEFINER VIEW `v_payment_stream` AS select `b`.`VALUE2` AS `MONEY`,date_format(`b`.`CREATE_DATE`,'%Y') AS `YEAR`,date_format(`b`.`CREATE_DATE`,'%c') AS `MONTH`,date_format(`b`.`CREATE_DATE`,'%e') AS `DAY`,(((dayofmonth(`b`.`CREATE_DATE`) + weekday((`b`.`CREATE_DATE` - interval dayofmonth(`b`.`CREATE_DATE`) day))) DIV 7) + 1) AS `WEEK`,date_format(`b`.`CREATE_DATE`,'%Y%m%d') AS `FULL` from (`mid_gas_user_behavior` `b` join `mid_operation` `o` on(((`b`.`OPERTION_ID` = `o`.`ID`) and (`o`.`STATE` = 'S') and (`o`.`BANK_COMMAND` = 821)))) ;
