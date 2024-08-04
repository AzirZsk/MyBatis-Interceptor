SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `tenant_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'A-张三', 18, 1);
INSERT INTO `user` VALUES (2, 'A-李四', 26, 1);
INSERT INTO `user` VALUES (3, 'A-王五', 11, 1);
INSERT INTO `user` VALUES (4, 'B-张三', 12, 2);
INSERT INTO `user` VALUES (5, 'B-李四', 45, 2);
INSERT INTO `user` VALUES (6, 'B-王五', 32, 2);

SET FOREIGN_KEY_CHECKS = 1;
