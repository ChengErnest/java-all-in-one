CREATE TABLE `t_warehouse` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `quantity` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO `t_warehouse`(`id`, `quantity`) VALUES (1, 7);