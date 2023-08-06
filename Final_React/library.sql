-- Drop the database if it already exists
DROP DATABASE IF EXISTS library;
-- Create database
CREATE DATABASE IF NOT EXISTS library;
USE library;

-- create table: Book
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`(
	id		 			INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    title    			VARCHAR(100) UNIQUE KEY NOT NULL,
    author   			VARCHAR(50) NOT NULL,
    category   			VARCHAR(50) NOT NULL,
    publication_date 	DATE NOT NULL,
    cover_image         BLOB DEFAULT NULL,
	price 				INT NOT NULL,
    `status`            ENUM('AVAILABLE', 'UNAVAILABLE') NOT NULL
);

-- create table: Account
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`(
	id		 			INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username    		VARCHAR(50) UNIQUE KEY NOT NULL,
    email   			VARCHAR(50) UNIQUE KEY NOT NULL,
    `password`          VARCHAR(50) NOT NULL,
    first_name          VARCHAR(50) NOT NULL,
    last_name           VARCHAR(50) NOT NULL,
    `role`              ENUM('ADMIN', 'NORMAL_USER', 'GUEST_USER') NOT NULL DEFAULT 'GUEST_USER'
);

-- create table: Borrowing History
DROP TABLE IF EXISTS `borrowing_history`;
CREATE TABLE `borrowing_history`(
	id		 		INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    account_id 		INT UNSIGNED,
    book_id     	INT UNSIGNED,
    borrow_date 	DATE NOT NULL,
    return_date		DATE,
    FOREIGN KEY(account_id) REFERENCES `account`(id) ON DELETE SET NULL,
    FOREIGN KEY(book_id) REFERENCES `book`(id) ON DELETE SET NULL
);

-- Add data Book
insert into book (id,	 title						,  author					, category				,  publication_date		,  price	,  status) 
values 			 (1,	'Legend Of Sleepy Hollow'	, 'Nealson Leyland'			, 'Self-help'			, '1999/04/19'			, 650264	, 'AVAILABLE'	),
				 (2, 	'Grease'					, 'Helsa Ajean'				, 'Science Fiction'		, '2015/5/2'			, 128664	, 'AVAILABLE'	),
				 (3, 	'Kirikou and the Sorceress' , 'Dennis'					, 'Youopia'				, '2008/04/12'			, 358273	, 'UNAVAILABLE'	),
				 (4, 	'PerAVAILABLEect Blue'		, 'Adria Danilevich'		, 'Photolist'			, '2006/9/17'			, 120056	, 'UNAVAILABLE'	),
				 (5, 	'Running AVAILABLEree'		, 'Elicia Barrick'			, 'Innoja'				, '1993/6/13'			, 372164	, 'AVAILABLE'	),
				 (6, 	'Paradox'					, 'Hannis Grinnell'			, 'Dynava'				, '2020/6/3'			, 506398	, 'AVAILABLE'	),
				 (7, 	'Late Phases'				, 'Jean Clevance'			, 'Yozio'				, '2015/6/13'			, 688503	, 'UNAVAILABLE'	),
				 (8, 	'Divide and Conquer' 		, 'Rebe Doret'				, 'Skyble'				, '2013/10/6'			, 500594	, 'UNAVAILABLE'	),
				 (9, 	'Vesku' 					, 'Celeste Restorick'		, 'Trunyx'				, '2008/6/20'			, 228176	, 'AVAILABLE'	),
				 (10, 	'Rafemalepart'				, 'Adara Blurton'			, 'Pixonyx'				, '2006/8/22'			, 264490	, 'AVAILABLE'	),
				 (11, 	'Aeon Malelux'				, 'Robinetta Haskew'		, 'Tazz'				, '2017/10/13'			, 446738	, 'AVAILABLE'	),
				 (12, 	'Rocket'					, 'Sidonia Zavittieri'		, 'Thought'				, '1992/5/15'			, 583234	, 'UNAVAILABLE'	),
				 (13, 	'The Water Diviner'			, 'Bradney Figgess'			, 'TopiczooUNAVAILABLE'	, '2000/4/23'			, 92780		, 'UNAVAILABLE'	),
				 (14, 	'P.U.N.K.S'					, 'Giralda'					, 'Aivee'				, '2012/8/1'			, 244982	, 'AVAILABLE'	),
				 (15, 	'The Arrival'				, 'Lynn de Najera'			, 'AVAILABLEanoodle'	, '2019/2/3'			, 732808	, 'UNAVAILABLE'	);

-- Add data Account
 insert into account (id, 	username		,  email							, password			, 	first_name		, last_name		,  role			) 
 values  			 (1, 	'fmcnea0'		, 'fmcnea0@toplist.cz'				, 'aI7~3z.VK'		, 	'Fionna'		, 'McNea'		, 'NORMAL_USER'	),
					 (2, 	'drorke1'		, 'drorke1@theglobeandmail.com'		, 'yK5_K=2~Z'		, 	'Desdemona'		, 'Rorke'		, 'NORMAL_USER'	),
					 (3, 	'gharnell2'		, 'gharnell2@auda.org.au'			, 'dZ1>BFXl&o6C'	, 	'Galvan'		, 'Harnell'		, 'GUEST_USER'	),
					 (4, 	'eryman3'		, 'eryman3@4shared.com'				, 'eP7&EI7h\e'		, 	'Earvin'		, 'Ryman'		, 'GUEST_USER'	),
					 (5, 	'rbilbrooke4'	, 'rbilbrooke4@cbc.ca'				, 'vZ8$?#.38,lP>"KH', 	'Roma'			, 'Bilbrooke'	, 'ADMIN'		),
					 (6, 	'kholley5'		, 'kholley5@reverbnation.com'		, 'vS8~a"p__k2Su'	, 	'Konstantin'	, 'Holley'		, 'GUEST_USER'	),
					 (7, 	'bschimank6'	, 'bschimank6@bloglines.com'		, 'wU6=7w#$2B'		, 	'Bjorn'			, 'Schimank'	, 'ADMIN'		),
					 (8, 	'hwalshe7'		, 'hwalshe7@icq.com'				, 'tB8.Nv}6B~QHa'	, 	'Hatty'			, 'Walshe'		, 'NORMAL_USER'	),
					 (9, 	'kbilovsky8'	, 'kbilovsky8@people.com.cn'		, 'qS1#}S7"3*Rlcs(J', 	'Kass'			, 'Bilovsky'	, 'NORMAL_USER'	),
					 (10, 	'ajorczyk9'		, 'ajorczyk9@foxnews.com'			, 'aI0\aEO#@|zg'	, 	'Arlee'			, 'Jorczyk'		, 'NORMAL_USER'	),
					 (11, 	'lfumagalloa'	, 'lfumagalloa@theglobeandmail.com'	, 'eS6*,&{lP1LMggVZ', 	'Liam'			, 'Fumagallo'	, 'GUEST_USER'	),
					 (12, 	'cbudgetb'		, 'cbudgetb@wsj.com'				, 'kE7<gA~jj9#kZBw'	, 	'Cecil'			, 'Budget'		, 'GUEST_USER'	),
					 (13, 	'dsabbinc'		, 'dsabbinc@craigslist.org'			, 'mY7|P|Z/'		, 	'Davina'		, 'Sabbin'		, 'NORMAL_USER'	),
					 (14, 	'sdyettd'		, 'sdyettd@cornell.edu'				, 'pW8\cCa=k"p5a'	, 	'Selma'			, 'Dyett'		, 'NORMAL_USER'	),
					 (15, 	'jsisnerose'	, 'jsisnerose@list-manage.com'		, 'oF7"TqAXk"'		, 	'Jillene'		, 'Sisneros'	, 'NORMAL_USER'	);


-- Add data Borrowing History
 insert into borrowing_history 	 (id	, account_id, book_id	, borrow_date , return_date	) 
						 values  (1		, 5			, 9		 	, '2023-04-06', '2022-12-18'),
								 (2		, 2			, 8			, '2022-09-09', '2023-08-01'),
								 (3		, 3			, 8			, '2023-03-22', '2022-08-25'),
								 (4		, 1			, 12		, '2023-07-02', '2023-04-16'),
								 (5		, 12		, 13		, '2023-07-17', '2023-02-16'),
								 (6		, 6			, 5			, '2023-07-22', '2022-11-08'),
								 (7		, 3			, 9			, '2023-06-25', '2023-05-11'),
								 (8		, 2			, 14		, '2022-08-25', '2022-08-17'),
								 (9		, 8			, 12		, '2023-03-05', '2022-11-15'),
								 (10	, 2			, 3			, '2023-05-17', '2023-02-11'),
								 (11	, 6			, 3			, '2023-06-01', '2022-12-27'),
								 (12	, 7			, 12		, '2023-05-19', '2023-03-15'),
								 (13	, 13		, 4			, '2023-04-30', '2023-04-29'),
								 (14	, 12		, 14		, '2023-07-09', '2022-12-09'),
								 (15	, 5			, 2			, '2023-05-19', '2023-01-06')
