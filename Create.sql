DROP DATABASE IF EXISTS TaskManager;

CREATE DATABASE TaskManager;
USE TaskManager;

CREATE TABLE Users
(UserID int NOT NULL auto_increment,
 Name nvarchar(20) NULL,
 Username nvarchar(20) NOT NULL,
 Password nvarchar(20) NULL,
 
 PRIMARY KEY (UserID)
);

CREATE TABLE Tasks
(TaskID int NOT NULL auto_increment,
UserID int NOT NULL,
Time TIMESTAMP NOT NULL,
Type VARCHAR(100) NOT NULL,
Title VARCHAR(100) NULL,
Description VARCHAR(100) NULL,

PRIMARY KEY (TaskID),

FOREIGN KEY (UserID) REFERENCES Users (UserID) ON DELETE CASCADE
);

INSERT INTO Users VALUES 
	(NULL, 'Alex Bent', 'alex', '12345');
INSERT INTO Users VALUES 
	(NULL, 'Matt Heafy', 'matt', '12345');   
    
INSERT INTO Tasks VALUES
		(NULL, (SELECT UserID from Users WHERE username='alex'), STR_TO_DATE('2018-05-15', '%Y-%m-%d'), 
        'test', 'test', 'test');
INSERT INTO Tasks VALUES
		(NULL, (SELECT UserID from Users WHERE username='alex'), STR_TO_DATE('2018-05-15', '%Y-%m-%d'), 
        'test2', 'test2', 'test2');  
INSERT INTO Tasks VALUES
		(NULL, (SELECT UserID from Users WHERE username='matt'), STR_TO_DATE('2018-05-15', '%Y-%m-%d'), 
        'test3', 'test3', 'test3'); 
INSERT INTO Tasks VALUES
		(NULL, (SELECT UserID from Users WHERE username='matt'), STR_TO_DATE('2018-05-15', '%Y-%m-%d'), 
        'test4', 'test4', 'test4'); 
        
        
SELECT * from Users;
SELECT * from Tasks; 
SELECT * from Users WHERE UserId=1;       