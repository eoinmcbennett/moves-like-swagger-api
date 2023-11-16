USE MovesLikeSwagger_MeganM;

CREATE TABLE `Users` (
  `user_id` smallint unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password_hash` blob NOT NULL,
  `password_hash_iterations` int unsigned NOT NULL,
  `password_salt` binary(16) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;