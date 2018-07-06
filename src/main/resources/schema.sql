DROP DATABASE IF EXISTS TaskManager;

CREATE DATABASE TaskManager;
USE TaskManager;

CREATE TABLE Users
(UserID int NOT NULL auto_increment,
 Name nvarchar(20) NULL,
 Username nvarchar(20) NOT NULL,
 Password nvarchar(60) NULL,
 Email nvarchar(50) NULL,
 Role nvarchar(15) NULL,
 
 PRIMARY KEY (UserID)
);

CREATE TABLE Tasks
(TaskID int NOT NULL auto_increment,
UserID int NOT NULL,
Time TIMESTAMP NOT NULL,
Type VARCHAR(100) NOT NULL,
Title VARCHAR(100) NULL,
Description VARCHAR(1000) NULL,

PRIMARY KEY (TaskID),

FOREIGN KEY (UserID) REFERENCES Users (UserID) ON DELETE CASCADE
);