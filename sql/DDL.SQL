CREATE TABLE `sandbox`.`wish` (
    `wish_id` INT NOT NULL AUTO_INCREMENT,
    `wish_name` VARCHAR(45) NOT NULL,
    `wish_price` VARCHAR(45) NOT NULL,
    `wishlist_id` int NOT NULL,
    PRIMARY KEY (`wish_id`));


CREATE TABLE `sandbox`.`user` (
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `user_name` VARCHAR(45) NOT NULL,
    `user_mail` VARCHAR(45) NOT NULL,
    `user_password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`user_id`));


CREATE TABLE `sandbox`.`wishlist` (
    `wishlist_id` INT NOT NULL AUTO_INCREMENT,
    VARCHAR(45) NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`wishlist_id`));
