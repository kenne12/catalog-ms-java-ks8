CREATE TABLE `t_inventory`
(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `sku_code` varchar(255) default null,
    `quantity` int(11) default null,
    PRIMARY KEY (`id`)
);