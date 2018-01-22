DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `age` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '张三', '13', '123456');
INSERT INTO `user` VALUES ('3', 'ls', '24', '123456');
INSERT INTO `user` VALUES ('4', 'wd', '23', '123456');
INSERT INTO `user` VALUES ('5', 'wd', '23', '123456');
INSERT INTO `user` VALUES ('6', '李四', '12', '123456');