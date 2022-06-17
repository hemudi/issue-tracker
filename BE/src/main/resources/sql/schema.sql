SET
    FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS member;

CREATE TABLE `member`
(
    `member_id`       BIGINT AUTO_INCREMENT PRIMARY KEY,
    `user_id`         VARCHAR(50) NOT NULL,
    `name`            VARCHAR(50) NOT NULL,
    `image_url`       VARCHAR(500),
    `o_auth_provider` VARCHAR(20),
    `created_at`      timestamp   not null
);

SET
    FOREIGN_KEY_CHECKS = 1;
