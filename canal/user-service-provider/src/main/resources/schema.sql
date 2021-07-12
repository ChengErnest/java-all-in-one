CREATE TABLE `t_user` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) DEFAULT NULL COMMENT '姓名',
    `phone` int DEFAULT NULL COMMENT '手机号',
    `password` varchar(100) DEFAULT NULL COMMENT '密码',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;