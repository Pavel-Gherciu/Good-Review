-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3325
-- Время создания: Янв 18 2023 г., 22:17
-- Версия сервера: 10.3.16-MariaDB
-- Версия PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `good_review`
--

-- --------------------------------------------------------

--
-- Структура таблицы `categories`
--

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL,
  `category_name` text NOT NULL,
  `category_img` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `categories`
--

INSERT INTO `categories` (`category_id`, `category_name`, `category_img`) VALUES
(1, 'Bank', 'https://realitatea.md/wp-content/uploads/2022/03/banca-900x505.png'),
(2, 'Restaurant', 'https://castelmimi.ro/img/carousel/restaurant/header-restaurant-16.png'),
(3, 'Supermarket', 'https://previews.123rf.com/images/iuriigagarin/iuriigagarin2103/iuriigagarin210300417/165388529-february-7-2021-balti-moldova-large-store-or-supermarket-illustrative-editorial-fruit-and-vegetables.jpg'),
(4, 'Clothing store', 'https://ea.md/wp-content/uploads/2021/02/4.jpeg');

-- --------------------------------------------------------

--
-- Структура таблицы `comments`
--

CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL,
  `comment_text` text NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `review_id` int(11) NOT NULL,
  `comment_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `companies`
--

CREATE TABLE `companies` (
  `company_id` int(11) NOT NULL,
  `title` text NOT NULL,
  `logo` text NOT NULL,
  `top_img` text NOT NULL,
  `city` text NOT NULL,
  `location` text NOT NULL,
  `site` text NOT NULL,
  `site_link` text NOT NULL,
  `schedule` text NOT NULL,
  `phone` text NOT NULL,
  `route` text NOT NULL,
  `about` text NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `companies`
--

INSERT INTO `companies` (`company_id`, `title`, `logo`, `top_img`, `city`, `location`, `site`, `site_link`, `schedule`, `phone`, `route`, `about`, `category_id`) VALUES
(1, 'Moldova Agroindbank', 'https://upload.wikimedia.org/wikipedia/commons/d/d2/Maib_ID_new_artwork_colour.png', 'https://www.maib.md/storage/media/2022/07/26//image_1658826536_3fRqo4D2tI.png', 'Chisinau, Moldova', 'Chisinau, Constantin Tănase street, 9/1', 'maib.md', 'https://www.maib.md', '9:00-17:00', '1313', '/maib', 'With more than 25 years of experience, Moldova Agroindbank continues to be a well-known brand in Moldova and abroad. MAIB is a top universal bank on the Moldovan market. It provides a full range of financial products and services to individuals and legal entities, through multiple distribution channels, including branches, agencies, a wide network of ATMs and POS terminals, as well as remote banking services (Internet banking, Mobile banking), etc.', 1),
(2, 'Moldova Agroindbank', 'https://upload.wikimedia.org/wikipedia/commons/d/d2/Maib_ID_new_artwork_colour.png', 'https://www.maib.md/storage/media/2022/07/26//image_1658826536_3fRqo4D2tI.png', 'Chisinau, Moldova', 'Chisinau, Constantin Tănase street, 9/1', 'maib.md', 'https://www.maib.md', '9:00-17:00', '1313', '/maib', 'With more than 25 years of experience, Moldova Agroindbank continues to be a well-known brand in Moldova and abroad. MAIB is a top universal bank on the Moldovan market. It provides a full range of financial products and services to individuals and legal entities, through multiple distribution channels, including branches, agencies, a wide network of ATMs and POS terminals, as well as remote banking services (Internet banking, Mobile banking), etc.', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `favorites`
--

CREATE TABLE `favorites` (
  `user_id` bigint(20) NOT NULL,
  `company_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `flyway_schema_history`
--

CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `flyway_schema_history`
--

INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
(1, '1', '<< Flyway Baseline >>', 'BASELINE', '<< Flyway Baseline >>', NULL, 'root', '2022-10-22 12:32:58', 0, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(19);

-- --------------------------------------------------------

--
-- Структура таблицы `review`
--

CREATE TABLE `review` (
  `review_id` int(11) NOT NULL,
  `review_text` varchar(5000) NOT NULL,
  `review_image` varchar(255) DEFAULT NULL,
  `review_rating` float DEFAULT 0,
  `company_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `review_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `created` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated` timestamp NOT NULL DEFAULT current_timestamp(),
  `status` varchar(25) NOT NULL DEFAULT 'ACTIVE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `roles`
--

INSERT INTO `roles` (`role_id`, `name`, `created`, `updated`, `status`) VALUES
(1, 'ROLE_USER', '2022-10-22 13:29:07', '2022-10-22 13:29:07', 'ACTIVE'),
(2, 'ROLE_ADMIN', '2022-10-22 13:29:07', '2022-10-22 13:29:07', 'ACTIVE');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nume` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `prenume` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `created` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated` timestamp NOT NULL DEFAULT current_timestamp(),
  `status` varchar(25) NOT NULL DEFAULT 'ACTIVE',
  `icon` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`user_id`, `email`, `nume`, `password`, `prenume`, `login`, `created`, `updated`, `status`, `icon`) VALUES
(1, 'murasovdavid@gmail.com', 'David', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'Murasov', 'Alfa', '2022-10-22 13:27:26', '2022-10-22 13:27:26', 'ACTIVE', NULL),
(2, 'klesher22812@gmail.com', 'Ricardo', '$2a$10$WJn39e6HeEhLs35uN32ASurEUUcfDSzTLFSHmg7HxYTO81Q/.LDei', 'Milos', 'Baron', '2022-10-22 13:27:26', '2022-10-22 13:27:26', 'ACTIVE', NULL),
(10, 'pavel@gmail.com', 'Pavel', '$2a$10$kpPL93BWohnV1uGs7J6KWeSsc8ijRR1jkyEof7pCcMPq1W6GIf20m', 'Gherciu', 'pavel12345', '2022-10-22 13:27:26', '2022-10-22 13:27:26', 'ACTIVE', 'test'),
(17, 'plesca12345@gmail.com', 'Virgiliu', '$2a$10$GSeCjauUSj3rcVhclQkLhe6Bss0DgWAGWLe/QU5xVhBlPW8RVcu4y', 'Plesca', 'VirgiliuPl', '2022-10-22 20:40:44', '2022-10-22 20:40:44', 'ACTIVE', NULL),
(18, 'Test@gmail.com', 'Test', '$2a$10$b0xsJbPnY28HMIFgRvDAz.swPtAaBQRPigxunc6IRUhS/yUV2doPa', 'Test', 'Testl12345', '2022-10-23 19:48:46', '2022-10-23 19:48:46', 'ACTIVE', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 2),
(2, 1),
(10, 1),
(17, 1),
(18, 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Индексы таблицы `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`comment_id`);

--
-- Индексы таблицы `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`company_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Индексы таблицы `favorites`
--
ALTER TABLE `favorites`
  ADD UNIQUE KEY `user_id` (`user_id`,`company_id`),
  ADD KEY `company_id` (`company_id`);

--
-- Индексы таблицы `flyway_schema_history`
--
ALTER TABLE `flyway_schema_history`
  ADD PRIMARY KEY (`installed_rank`),
  ADD KEY `flyway_schema_history_s_idx` (`success`);

--
-- Индексы таблицы `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`review_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `review_ibfk_2` (`company_id`);

--
-- Индексы таблицы `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Индексы таблицы `user_roles`
--
ALTER TABLE `user_roles`
  ADD UNIQUE KEY `user_id` (`user_id`,`role_id`) USING BTREE,
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `comments`
--
ALTER TABLE `comments`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `companies`
--
ALTER TABLE `companies`
  MODIFY `company_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `review`
--
ALTER TABLE `review`
  MODIFY `review_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `companies`
--
ALTER TABLE `companies`
  ADD CONSTRAINT `companies_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `favorites`
--
ALTER TABLE `favorites`
  ADD CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `companies` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `review_ibfk_2` FOREIGN KEY (`company_id`) REFERENCES `companies` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
