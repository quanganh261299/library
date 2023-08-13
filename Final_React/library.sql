-- Drop the database if it already exists
DROP DATABASE IF EXISTS library;
-- Create database
CREATE DATABASE IF NOT EXISTS library;
USE library;

-- create table: Book
DROP TABLE IF EXISTS `book`;
CREATE TABLE book (
    id 				INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    title 			VARCHAR(50) NOT NULL,
	cover_image 	BLOB DEFAULT NULL,
    author 			VARCHAR(50) NOT NULL,
    category 		VARCHAR(50) NOT NULL,
    `description` 	TEXT,
    created_at 		TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `status` 		ENUM('AVAILABLE', 'BORROWED') NOT NULL DEFAULT 'AVAILABLE'
);


-- create table: Account
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`(
	id		 			INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username    		VARCHAR(50) UNIQUE KEY NOT NULL,
    email   			VARCHAR(50) UNIQUE KEY NOT NULL,
    `password`          VARCHAR(72) NOT NULL,
    first_name          VARCHAR(50) NOT NULL,
    last_name           VARCHAR(50) NOT NULL,
    created_at 			TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `role`              ENUM('ADMIN', 'NORMAL_USER')
);

-- create table: Borrowing History
DROP TABLE IF EXISTS `borrowing_history`;
CREATE TABLE `borrowing_history`(
	id		 		INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    account_id 		INT UNSIGNED,
    book_id     	INT UNSIGNED UNIQUE KEY,
    borrow_date 	DATE NOT NULL,
    return_date		DATE,
    FOREIGN KEY(account_id) REFERENCES `account`(id) ON DELETE SET NULL,
    FOREIGN KEY(book_id) REFERENCES `book`(id) ON DELETE SET NULL
);

-- Add data Book
 insert into book 	 (id, title						, author				, category			, description											, 	status) 
	 values 		 (1, 'Cult of the Cobra'		, 'Sauncho Huyton'		, 'Horror'			, 'Cuộc phiêu lưu hấp dẫn trong kỳ nghỉ hè.'			, 	'AVAILABLE') ,
					 (2, 'Amish Grace'				, 'Glenden Brager'		, 'Drama'			, 'Tình yêu đẹp ngọt ngào nở hoa dưới ánh mặt trời.'	, 	'AVAILABLE') ,
					 (3, 'Two Cents Worth of Hope'	, 'Viole Kinnier'		, 'Comedy'			, 'Hành trình khám phá vũ trụ bí ẩn'					, 	'AVAILABLE') ,
					 (4, 'Echoes of the Rainbow'    , 'Ingaborg Lurner'		, 'Comedy'			, 'Hồi hộp theo dấu vết trong cuộc truy đuổi nguy hiểm.', 	'AVAILABLE') ,
					 (5, 'All I Desire'				, 'Horst Tomet'			, 'Drama'			, 'Khoảnh khắc sâu lắng suy tư về cuộc sống.'			, 	'AVAILABLE') ,
					 (6, 'Three Ages'				, 'Bobbi Kordt'			, 'Comedy'			, 'Góc nhìn mới về lịch sử quyến rũ.'					, 	'AVAILABLE') ,
					 (7, 'Princess and the Warrior'	, 'Farly Olesen'		, 'Drama'			, 'Sức mạnh của tình bạn vượt qua mọi thử thách.'		, 	'AVAILABLE') ,
					 (8, 'French Fried Vacation'	, 'Charlena Ackerman'	, 'Comedy'			, 'Đắm chìm trong thế giới huyền bí của cổ tích.'		, 	'AVAILABLE') ,
					 (9, 'Koti-ikävä'				, 'Felike Chadney'		, 'Drama'			, 'Cuộc hành trình tìm kiếm ý nghĩa đích thực.'			, 	'AVAILABLE') ,
					 (10, 'Vault of Horror'			, 'Dulce Cady'			, 'Horror'			, 'Bí mật kinh hoàng đằng sau vẻ ngoài hoàn hảo.'		,	'AVAILABLE') ,
					 (11, 'Boys on the Side'		, 'Berke Motten'		, 'Comedy'			, 'Hương vị của niềm hy vọng và sự tự do.'				, 	'AVAILABLE') ,
					 (12, 'Magician, The (Ansiktet)', 'Armin Braithwaite'	, 'Drama'			, 'Cuộc đối đầu gay cấn giữa ác quỷ và thiên thần.'		, 	'AVAILABLE') ,
					 (13, 'Act of Valor'			, 'Ingram O''Feeny'		, 'Action'			, 'Nghịch lý cuộc sống qua góc nhìn sâu sắc.'			, 	'AVAILABLE') ,
					 (14, 'Wizard of Oz, The'		, 'Marsha Greenstock'	, 'Adventure'		, 'Vũ điệu đẹp như mơ trong ánh đèn lung linh.'			, 	'AVAILABLE') ,
					 (15, 'Young Again'				, 'Elsbeth Sherringham'	, 'Fantasy'			, 'Nụ cười tỏa sáng giữa bão táp cuộc đời.'				, 	'AVAILABLE') ,
					 (16, 'Man Who Love'			, 'Brooke Rodd'			, 'Western'			, 'Bức tranh tình yêu mãnh liệt và chân thành.'			, 	'AVAILABLE') ,
					 (17, 'Coming Home (Gui lai)'	, 'Ulric Eldritt'		, 'Drama'			, 'Cuộc chiến tưởng chừng không thể thắng.'				, 	'AVAILABLE') ,
					 (18, 'Unknown'					, 'Collete Colegate'	, 'Drama'			, 'Hành trình trở thành phiên bản tốt hơn của bản thân'	, 	'AVAILABLE') ,
					 (19, 'Possession'				, 'Chiquita Pole'		, 'Drama'			, 'Đam mê nghệ thuật biến thành sức sống.'				, 	'AVAILABLE') ,
					 (20, 'Malice in Wonderland'	, 'Horatia Russilll'	, 'Drama'			, 'Cuộc sống nhỏ bé chứa đựng nhiều điều kỳ diệu.'		, 	'AVAILABLE');

-- Add data Account
 insert into account (id, 	username		,  email							, password			, 	first_name		, last_name		,  role			) 
 values  			 (1, 	'fmcnea0'		, 'fmcnea0@toplist.cz'				, 'aI7~3z.VK'		, 	'Fionna'		, 'McNea'		, 'NORMAL_USER'	),
					 (2, 	'drorke1'		, 'drorke1@theglobeandmail.com'		, 'yK5_K=2~Z'		, 	'Desdemona'		, 'Rorke'		, 'NORMAL_USER'	),
					 (3, 	'gharnell2'		, 'gharnell2@auda.org.au'			, 'dZ1>BFXl&o6C'	, 	'Galvan'		, 'Harnell'		, 'NORMAL_USER'	),
					 (4, 	'eryman3'		, 'eryman3@4shared.com'				, 'eP7&EI7h\e'		, 	'Earvin'		, 'Ryman'		, 'NORMAL_USER'	),
					 (5, 	'rbilbrooke4'	, 'rbilbrooke4@cbc.ca'				, 'vZ8$?#.38,lP>"KH', 	'Roma'			, 'Bilbrooke'	, 'ADMIN'		),
					 (6, 	'kholley5'		, 'kholley5@reverbnation.com'		, 'vS8~a"p__k2Su'	, 	'Konstantin'	, 'Holley'		, 'NORMAL_USER'	),
					 (7, 	'bschimank6'	, 'bschimank6@bloglines.com'		, 'wU6=7w#$2B'		, 	'Bjorn'			, 'Schimank'	, 'ADMIN'		),
					 (8, 	'hwalshe7'		, 'hwalshe7@icq.com'				, 'tB8.Nv}6B~QHa'	, 	'Hatty'			, 'Walshe'		, 'NORMAL_USER'	),
					 (9, 	'kbilovsky8'	, 'kbilovsky8@people.com.cn'		, 'qS1#}S7"3*Rlcs(J', 	'Kass'			, 'Bilovsky'	, 'NORMAL_USER'	),
					 (10, 	'ajorczyk9'		, 'ajorczyk9@foxnews.com'			, 'aI0\aEO#@|zg'	, 	'Arlee'			, 'Jorczyk'		, 'NORMAL_USER'	);


-- Add data Borrowing History
 insert into borrowing_history 	 (id	, account_id, book_id	, borrow_date , return_date	) 
						 values  (1		, 5			, 9		 	, '2022-04-06', '2023-12-18'),
								 (2		, 2			, 8			, '2022-09-09', '2023-08-01'),
								 (3		, 3			, 7			, '2022-03-22', '2023-08-25'),
								 (4		, 1			, 12		, '2022-07-02', '2023-04-16'),
								 (5		, 10		, 13		, '2022-07-17', '2023-02-16'),
								 (6		, 6			, 5			, '2022-07-22', '2023-11-08'),
								 (7		, 3			, 1			, '2022-06-25', '2023-05-11'),
								 (8		, 2			, 14		, '2022-08-25', '2023-08-17'),
								 (9		, 8			, 16		, '2022-03-05', '2023-11-15'),
								 (10	, 2			, 3			, '2022-05-17', '2023-02-11');

UPDATE book
SET status = 'BORROWED'
WHERE id IN (SELECT book_id FROM borrowing_history);
