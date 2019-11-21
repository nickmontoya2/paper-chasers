/** 
DISCLAIMER: This file was made while heavily referencing the Chinook
	example provided in class and found online at:
    https://raw.githubusercontent.com/lerocha/chinook-database/master/ChinookDatabase/DataSources/Chinook_MySql_AutoIncrementPKs.sql
**/

/**
Drop database if exists, make new and use the new one. 
**/
DROP DATABASE IF EXISTS `paper_chasers`;
CREATE DATABASE `paper_chasers`;
USE `paper_chasers`;

/**
Create tables for project
**/
-- Storing user information, should reference to Role table 
CREATE TABLE `User`
(
    `ID` INT NOT NULL AUTO_INCREMENT,
    `first_name` NVARCHAR(50) NOT NULL,
    `last_name` NVARCHAR(50) NOT NULL,
    `username` NVARCHAR(50) NOT NULL,
    `password` NVARCHAR(50) NOT NULL,
    -- role_ID should be a foreign key to role table
    `role_ID` INT NOT NULL, 
    CONSTRAINT `PK_User` PRIMARY KEY  (`ID`)
);

-- Look up table, used as FK in User table
CREATE TABLE `Role` 
(
	`ID` INT NOT NULL AUTO_INCREMENT,
    `title` NVARCHAR(50) NOT NULL,
    CONSTRAINT `PK_role` PRIMARY KEY (`ID`)
);

-- Storing timesheet related info, has reference to User table and Status table 
CREATE TABLE `Timesheet` 
(
	`ID` INT NOT NULL AUTO_INCREMENT,
    -- userId will be a foreign key to user table showing who owns this timesheet
    `user_ID` INT NOT NULL,
    -- status will be a foreign key to status table showing whether TS is saved or submitted
    `status_ID` INT NOT NULL,
    `monday_hours` DECIMAL(4,2) NOT NULL,
    `tuesday_hours` DECIMAL(4,2) NOT NULL,
    `wednesday_hours` DECIMAL(4,2) NOT NULL,
    `thursday_hours` DECIMAL(4,2) NOT NULL,
    `friday_hours` DECIMAL(4,2) NOT NULL,
    `week_ending` DATE NOT NULL,
    CONSTRAINT `PK_Timesheet` PRIMARY KEY (`ID`)
);

-- Shows whether a TS is saved (and thus editable) or submitted (NOT editable)
CREATE TABLE `Status` 
(
	`ID` INT NOT NULL AUTO_INCREMENT,
    `status` NVARCHAR(50) NOT NULL,
    CONSTRAINT `PK_Status` PRIMARY KEY (`ID`)
);

/**
Create/add foreign keys for tables as needed
**/
-- Able to create different roles for user (eg. Employee, Manager, Admin, etc.)
ALTER TABLE `User` ADD CONSTRAINT `FK_UserRoleID`
    FOREIGN KEY (`role_ID`) REFERENCES `Role` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
CREATE INDEX `IFK_UserRoleID` ON `User` (`role_ID`);

-- Shows which user owns this timesheet
ALTER TABLE `Timesheet` ADD CONSTRAINT `FK_TimesheetUserID`
	FOREIGN KEY (`user_ID`) REFERENCES `User` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
CREATE INDEX `IFK_TimesheetUserID` ON `Timesheet` (`user_ID`);

-- Shows whether or not this timesheet is saved or submitted
ALTER TABLE `Timesheet` ADD CONSTRAINT `FK_TimesheetStatusID`
	FOREIGN KEY (`status_ID`) REFERENCES `Status` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
CREATE INDEX `IFK_TimesheetStatusID` ON `Timesheet` (`status_ID`);

/*
Populate tables with sample data
*/
-- Populate Role table
INSERT INTO `Role`(`title`) VALUES (N'Employee');
INSERT INTO `Role`(`title`) VALUES (N'Manager');

-- Populate Status table
INSERT INTO `Status`(`status`) VALUES (N'Saved');
INSERT INTO `Status`(`status`) VALUES (N'Submitted');

-- Populate User table (5 users to start)
INSERT INTO `User`(`first_name`, `last_name`, `username`, `password`, `role_ID`)
	VALUES (N'Stan', N'Marsh', N'Young_Marsh', N'southPark1', 1);

INSERT INTO `User`(`first_name`, `last_name`, `username`, `password`, `role_ID`)
	VALUES (N'Eric', N'Cartman', N'funnyCartman', N'southPark2', 1);

INSERT INTO `User`(`first_name`, `last_name`, `username`, `password`, `role_ID`)
	VALUES (N'Kyle', N'Broflovski', N'greenHatKid', N'southPark3', 1);

INSERT INTO `User`(`first_name`, `last_name`, `username`, `password`, `role_ID`)
	VALUES (N'Kenny', N'McCormick', N'muffledVoice', N'southPark4', 1);

INSERT INTO `User`(`first_name`, `last_name`, `username`, `password`, `role_ID`)
	VALUES (N'Randy', N'Marsh', N'Cool_Marsh', N'southPark5', 1);

-- Populate Timesheet table (a sample saved & submitted)

INSERT INTO `Timesheet`(`user_ID`, `status_ID`, `monday_hours`, `tuesday_hours`, `wednesday_hours`, `thursday_hours`, `friday_hours`, `week_ending`)
	VALUES (1, 1, 7.5, 8.50, 8, 8, 8, '2019-10-27'); -- Sample saved timesheet for Stan
    
INSERT INTO `Timesheet`(`user_ID`, `status_ID`, `monday_hours`, `tuesday_hours`, `wednesday_hours`, `thursday_hours`, `friday_hours`, `week_ending`)
	VALUES (1, 2, 7, 8, 7, 9, 9, '2019-10-20'); -- Sample submitted timesheet for Stan
    
