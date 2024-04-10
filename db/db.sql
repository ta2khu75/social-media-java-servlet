CREATE DATABASE `java4` 
-- java4.Favorites definition

CREATE TABLE `Users` (
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `fullname` varchar(50) DEFAULT NULL,
  `admin` bit(1) DEFAULT b'0',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- java4.Videos definition

CREATE TABLE `Videos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `video` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `views` int DEFAULT '0',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- java4.Shares definition

CREATE TABLE `Shares` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `userId` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `videoId` int DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `shareDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Shares_Users_FK` (`userId`),
  KEY `Shares_Videos_FK` (`videoId`),
  CONSTRAINT `Shares_Users_FK` FOREIGN KEY (`userId`) REFERENCES `Users` (`email`),
  CONSTRAINT `Shares_Videos_FK` FOREIGN KEY (`videoId`) REFERENCES `Videos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Favorites` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `userId` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `videoId` int DEFAULT NULL,
  `likeDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Favorites_Users_FK` (`userId`),
  KEY `Favorites_Videos_FK` (`videoId`),
  CONSTRAINT `Favorites_Users_FK` FOREIGN KEY (`userId`) REFERENCES `Users` (`email`),
  CONSTRAINT `Favorites_Videos_FK` FOREIGN KEY (`videoId`) REFERENCES `Videos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO java4.Users (email,password,fullname,admin) VALUES
	 ('minh@gmail.com','123','minh',1),
	 ('tranngocminh474@gmail.com','123','minh',0),
	 ('trungkien44888888@gmail.com','123','kien',0);

INSERT INTO java4.Videos (title,video,views,description) VALUES
	 ('SƠN TÙNG M-TP | CHÚNG TA CỦA TƯƠNG LAI | OFFICIAL MUSIC VIDEO','https://www.youtube.com/embed/zoEtcR5EW08?si=GTp1s4Xrst9-2j2F',82,'Hãy thưởng thức ca khúc CHÚNG TA CỦA TƯƠNG LAI ngay tại đây: https://MTP.bfan.link/chung-ta-cua-tu... 🪐🪐🪐✨✨✨'),
	 ('Quang Hùng MasterD - ‘Thuỷ Triều’ - Official Visualizer','https://www.youtube.com/embed/7ig2lXjozdw?si=xRlLtuWF4s1DFxE-',65,'The product was produced by MasterD Management
Worldwide Distribution: Euphoria Media Vietnam
Publishing: Euphoria Media Group'),
('(xuân): Hư không - Kha // EP tình','https://www.youtube.com/embed/35iQw6K_ty8?si=rWfx9jvKCEvUgKTl',8,'🔊bài hát được phát ở tần số 432hz
Hư không - Kha'),
	 ('Anh Chỉ Là Người Thay Thế - HUI (Official MV)','https://www.youtube.com/embed/URH-BLX-VvY?si=SJ1E6mi90u7PO-ax',4,'Music Video: Anh Chỉ Là Người Thay Thế - HUI'),
	 ('Nothing In Your Eyes 2 | Mr T x Yanbi x Bảo Thy | Official Music Video','https://www.youtube.com/embed/OhJacEzWPE8?si=CUtHKbh0Zm7hvn06',1,'Bài hát: Nothing In Your Eyes 2
Thể hiện: Mr T x Yanbi x Bảo Thy
POPS Music - Kênh âm nhạc trực tuyến hàng đầu Việt Nam.'),
	 ('MONO - ‘Em Xinh’ (Official Music Video)','https://www.youtube.com/embed/rYc1UbgbMIY?si=K2i_rf2hE8gYCxh3',9,'MONO - ‘Em Xinh’ (Official Music Video)
‘Em Xinh’ nằm trong EP “ĐẸP” của MONO ra mắt ngày 30.11.2023
Mọi người có thể nghe và ủng hộ full EP "ĐẸP" tại đây: https://mmusicrecords.lnk.to/dep'),
	 ('HOA NỞ BÊN ĐƯỜNG - QUANG ĐĂNG TRẦN | OFFICIAL MUSIC VIDEO','https://www.youtube.com/embed/LURC_c6m9dY?si=tskLtAiDvxhr7En4',3,'Độc quyền phát hành và truyền thông ACV Entertainment
Founder/Music Executive ACV Lê Cương');

INSERT INTO java4.Favorites (userId,videoId,likeDate) VALUES
	 ('tranngocminh474@gmail.com',8,'2024-03-22'),
	 ('tranngocminh474@gmail.com',3,'2024-03-22'),
	 ('minh@gmail.com',3,'2024-03-23'),
	 ('minh@gmail.com',9,'2024-03-26'),
	 ('minh@gmail.com',8,'2024-03-26');
	
INSERT INTO java4.Shares (userId,videoId,email,shareDate) VALUES
	 ('tranngocminh474@gmail.com',8,'minhtnps34359@fpt.edu.vn','2024-03-22'),
	 ('minh@gmail.com',8,'minhtnps34359@fpt.edu.vn','2024-03-23'),
	 ('minh@gmail.com',3,'minhtnps34359@fpt.edu.vn','2024-03-23'),
	 ('minh@gmail.com',10,'minhtnps34359@fpt.edu.vn','2024-03-23'),
	 ('minh@gmail.com',12,'minhtnps34359@fpt.edu.vn','2024-03-23'),
	 ('minh@gmail.com',12,'trungkien44888888@gmail.com','2024-03-23'),
	 ('minh@gmail.com',14,'trungkien44888888@gmail.com','2024-04-02');
-- java4.Users definition


/*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
