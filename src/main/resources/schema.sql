CREATE TABLE IF NOT EXISTS `customer` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(255) NOT NULL,
    `role` VARCHAR(255) NOT NULL,
    `status` BOOLEAN NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `customer_pk` PRIMARY KEY (`id`)
);

INSERT INTO `customer` (`name`, `email`, `password`, `phone`, `role`, `status`)
VALUES
    ('John Smith', 'johnsmith1@example.com', 'password1', '1234567890', 'role1', true),
    ('Jane Doe', 'janedoe2@example.com', 'password2', '1234567891', 'role2', true),
    ('Robert Johnson', 'robertjohnson3@example.com', 'password3', '1234567892', 'role3', true),
    ('Michael Brown', 'michaelbrown4@example.com', 'password4', '1234567893', 'role4', true),
    ('Emily Davis', 'emilydavis5@example.com', 'password5', '1234567894', 'role5', true),
    ('Sarah Miller', 'sarahmiller6@example.com', 'password6', '1234567895', 'role6', true),
    ('James Wilson', 'jameswilson7@example.com', 'password7', '1234567896', 'role7', true),
    ('Patricia Moore', 'patriciamoore8@example.com', 'password8', '1234567897', 'role8', true),
    ('Richard Taylor', 'richardtaylor9@example.com', 'password9', '1234567898', 'role9', true),
    ('Linda Anderson', 'lindaanderson10@example.com', 'password10', '1234567899', 'role10', true);

CREATE TABLE IF NOT EXISTS `event` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `date` DATETIME NOT NULL,
    `status` BOOLEAN NOT NULL,
    `customers` VARCHAR(255) NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `event_pk` PRIMARY KEY (`id`)
);

INSERT INTO `event` (`name`, `description`, `date`, `status`, `customers`)
VALUES
    ('Partido de Fútbol: Medellín vs Nacional', 'Partido de la liga de fútbol', '2023-01-01 00:00:00', true, 'John Smith, Jane Doe'),
    ('Partido de Fútbol: Real Madrid vs Barcelona', 'Partido de la liga de fútbol', '2023-02-01 00:00:00', true, 'Robert Johnson, Michael Brown'),
    ('Partido de Fútbol: Manchester United vs Liverpool', 'Partido de la liga de fútbol', '2023-03-01 00:00:00', true, 'Emily Davis, Sarah Miller'),
    ('Partido de Fútbol: Boca Juniors vs River Plate', 'Partido de la liga de fútbol', '2023-04-01 00:00:00', true, 'James Wilson, Patricia Moore'),
    ('Partido de Fútbol: Bayern Munich vs Borussia Dortmund', 'Partido de la liga de fútbol', '2023-05-01 00:00:00', true, 'Richard Taylor, Linda Anderson'),
    ('Partido de Fútbol: Santos vs Flamengo', 'Partido de la liga de fútbol', '2023-06-01 00:00:00', true, 'John Smith, Jane Doe'),
    ('Partido de Fútbol: Inter Milan vs AC Milan', 'Partido de la liga de fútbol', '2023-07-01 00:00:00', true, 'Robert Johnson, Michael Brown'),
    ('Partido de Fútbol: Paris Saint-Germain vs Olympique Lyon', 'Partido de la liga de fútbol', '2023-08-01 00:00:00', true, 'Emily Davis, Sarah Miller'),
    ('Partido de Fútbol: LA Galaxy vs Seattle Sounders', 'Partido de la liga de fútbol', '2023-09-01 00:00:00', true, 'James Wilson, Patricia Moore'),
    ('Partido de Fútbol: Juventus vs Napoli', 'Partido de la liga de fútbol', '2023-10-01 00:00:00', true, 'Richard Taylor, Linda Anderson');

CREATE TABLE IF NOT EXISTS `recording` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `event` BIGINT NOT NULL,
    `duration` TIME NOT NULL,
    `status` BOOLEAN NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `recording_pk` PRIMARY KEY (`id`)
);

INSERT INTO `recording` (`name`, `event`, `duration`, `status`)
VALUES
    ('GPF2023-01-01T00:00:00', 1, '01:30:00', true),
    ('GPF2023-02-01T00:00:00', 2, '01:45:00', true),
    ('GPF2023-03-01T00:00:00', 3, '01:40:00', true),
    ('GPF2023-04-01T00:00:00', 4, '01:35:00', true),
    ('GPF2023-05-01T00:00:00', 5, '01:50:00', true),
    ('GPF2023-06-01T00:00:00', 6, '01:55:00', true),
    ('GPF2023-07-01T00:00:00', 7, '01:45:00', true),
    ('GPF2023-08-01T00:00:00', 8, '01:50:00', true),
    ('GPF2023-09-01T00:00:00', 9, '01:40:00', true),
    ('GPF2023-10-01T00:00:00', 10, '01:45:00', true);

CREATE TABLE IF NOT EXISTS `statistics` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `timestamp` VARCHAR(255) NOT NULL,
    `recording` BIGINT NOT NULL,
    `data` BIGINT NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `statistics_pk` PRIMARY KEY (`id`)
);

INSERT INTO `statistics` (`timestamp`, `recording`, `data`)
VALUES
    ('2023-01-01 00:30:00', 1, 1),
    ('2023-01-01 00:40:00', 1, 1),
    ('2023-01-01 00:45:00', 1, 2),
    ('2023-01-01 01:05:00', 1, 2),
    ('2023-01-01 01:20:00', 1, 2),
    ('2023-02-01 00:45:00', 2, 3),
    ('2023-02-01 00:45:00', 2, 4),
    ('2023-03-01 00:15:00', 3, 5),
    ('2023-03-01 00:55:00', 3, 6),
    ('2023-03-01 01:05:00', 3, 5);
