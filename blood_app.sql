-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 15, 2016 at 08:35 AM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `blood_app`
--
CREATE DATABASE IF NOT EXISTS `blood_app` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `blood_app`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `username` varchar(30) NOT NULL,
  `password` varchar(40) NOT NULL,
  `email` varchar(45) NOT NULL,
  `salt` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`, `email`, `salt`) VALUES
('abinb', 'E6nqd/yQ+qy6dbDb5bykc4Mgza5kY2ExZDBkYzFj', 'admin', 'dca1d0dc1c');

-- --------------------------------------------------------

--
-- Table structure for table `donates_blood_to`
--

CREATE TABLE IF NOT EXISTS `donates_blood_to` (
  `volunteer_id_type` varchar(40) NOT NULL,
  `volunteer_id_number` varchar(40) NOT NULL,
  `seeker_id_number` varchar(40) NOT NULL,
  `seeker_id_type` varchar(40) NOT NULL,
  `agnecy` varchar(45) NOT NULL,
  `date_time` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `seeker`
--

CREATE TABLE IF NOT EXISTS `seeker` (
  `username` varchar(30) NOT NULL,
  `password` varchar(40) NOT NULL,
  `salt` varchar(40) NOT NULL,
  `email` varchar(45) NOT NULL,
  `name` varchar(40) NOT NULL,
  `phone` varchar(40) NOT NULL,
  `location` varchar(40) DEFAULT NULL,
  `id_number` varchar(40) NOT NULL,
  `id_type` varchar(40) NOT NULL,
  `verified_by` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seeker`
--

INSERT INTO `seeker` (`username`, `password`, `salt`, `email`, `name`, `phone`, `location`, `id_number`, `id_type`, `verified_by`) VALUES
('abin', 'qcVo/hBUJrPBwhTQu27WJzGS4Kk4MmMzOWVmOTM4', '82c39ef938', 'abinbhattacharya@gmail.com', 'Abin', '987654321', NULL, '09876', 'Driver License', NULL),
('abinb', 'GPrnnkSImgHgCJ5hOTBKbTCj60QwOTk5YjU4NWE1', '0999b585a5', 'abinbhattacharya@gmail.com', 'Abin', '9876543210', NULL, '1234567', 'drivers license', NULL),
('yashm', 'Mq/t9obSKEYv5hnBrIPPK2DxUsczYTRhMDJhZjI5', '3a4a02af29', 'ymograi@gmail.com', 'Yash', '9876543210', NULL, '1234567a', 'drivers license', NULL),
('abinb1', 'iFZpFat5WIaxk3oy4sJrXyxn8fg2ZjAzYzkzOTY3', '6f03c93967', 'abinbhattacharya@gmail.com', 'Abin Bhattacharya', '9876543210', NULL, '2345', 'Driver License', NULL),
('watcheronthewall', 'H146oQh4xqoxohIclteekFVdQqhjNDRjZGUxMjVk', 'c44cde125d', 'brucewayne@waynecorp.com', 'Bruce Wayne', '22286266', NULL, '31071942', 'Bat72', NULL),
('ymograi', '0XAErKujQ+up8FeEzfnxS1yRPwNmYmFiNjkxMGM2', 'fbab6910c6', 'ymograi@gmail com', 'yash', '01165170045', NULL, 'shshshsbdbd', 'driver lic', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `volunteer`
--

CREATE TABLE IF NOT EXISTS `volunteer` (
  `username` varchar(30) NOT NULL,
  `password` varchar(40) NOT NULL,
  `salt` varchar(40) NOT NULL,
  `email` varchar(45) NOT NULL,
  `name` varchar(40) NOT NULL,
  `phone` varchar(40) NOT NULL,
  `lat` double DEFAULT NULL,
  `lon` double DEFAULT NULL,
  `id_type` varchar(40) NOT NULL,
  `id_number` varchar(40) NOT NULL,
  `verified_by` varchar(40) DEFAULT NULL,
  `blood_group` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `volunteer`
--

INSERT INTO `volunteer` (`username`, `password`, `salt`, `email`, `name`, `phone`, `lat`, `lon`, `id_type`, `id_number`, `verified_by`, `blood_group`) VALUES
('', 'VaTm5ikywikVnON0j761wnh7zH9mYTk4ODI4M2Q3', 'fa988283d7', '', '', '', NULL, NULL, '', '', NULL, ''),
('abin', 'A1rJbGUEjfwdMOsbc55J5Eta9fY1Y2U1YzgzOWIw', '5ce5c839b0', 'abin', '', '235689', NULL, NULL, 'Abin', 'abin', NULL, 'B'),
('ymograi', 'eIiCPKUE1pHNKcQdw/p36G8Vb4c3YTE3N2JjMzJi', '7a177bc32b', 'ymograi@gmail.com', 'Yash', '9567759732', 10, 20, 'Behshs', 'shshe627', NULL, 'O'),
('wallywest92', '1FMG6ks9OteK7kF+Na8flYGV9c8wZjk2YzQ1Yzli', '0f96c45c9b', 'wallywest@gmail.com', 'Wally West', '9876598765', NULL, NULL, 'dhdh437', 'djejeh343', NULL, 'O'),
('abinb1', 'qqKo7i69RStWOChQzYgg0+iB1C0zZDQ2MDM1MDA2', '3d46035006', 'abinbhattacharya@gmail.com', 'Abin Bhattacharya', '9876543210', NULL, NULL, 'Driver License', '234456', NULL, 'B'),
('abinb2', 'OwzMvoaynMUbnl8ijCyZQXGc5XM3NzI4ZWY1YmU1', '7728ef5be5', 'abinbhattacharya@gmail.com', 'Abin Bhattacharya', '9876543210', NULL, NULL, 'Driver License', '2344566', NULL, 'B'),
('abinb3', 'iFsvbZ6UdZytT8o9FrHUXUsHItZiYjljYjE0YmU4', 'bb9cb14be8', 'abinbhattacharya@gmail.com', 'Abin', '9876543210', NULL, NULL, 'Driver License', 'asd1', NULL, 'B'),
('abinb4', '92HYRf649q80jOQcVFBtSxeLkfEwNzA3MTJjMjNk', '070712c23d', 'abinbhattacharya@gmail.com', 'Abin Bhattachary', '9876543210', NULL, NULL, 'Driver License', 'asdd2', NULL, 'B'),
('abinb5', '4a8t+aR7N/Gvf3iN9C46DG3ByDEzNzEyMjI5MGM2', '37122290c6', 'abinbhattacharya@gmail.com', 'Abin', '9876543210', NULL, NULL, 'Driver License', 'asdfg5', NULL, 'B'),
('abinbhattacharya', 'WNxJqrnKp1Hn1yeN7365L8MpEVVmNjNlZmQ3ZDgw', 'f63efd7d80', 'abinbhattacharya@gmail.com', 'Abin Bhattacharya', '9830097007', NULL, NULL, 'Driver''s License', '123455678910', NULL, 'B'),
('abinb', 'o+Rfq6iggY/oZUz9758E1FS8JchlY2ZjZThkZTdi', 'ecfce8de7b', 'ymograi@gmail.com', 'Abin', '9876543210', 11.31523099, 75.93263937, 'drivers license', '1234567', NULL, 'B'),
('dontmesswithmartha', 'fqzrvLlPqYhaMeL7TWgM3UB6GkM1ZjZmY2Y2NGQx', '5f6fcf64d1', 'clarksmallviller@dailyplanet.com', 'Clark Kent', '7873762666', NULL, NULL, 'Kpto128', '61acbw', NULL, 'O');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
 ADD PRIMARY KEY (`email`), ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `donates_blood_to`
--
ALTER TABLE `donates_blood_to`
 ADD PRIMARY KEY (`volunteer_id_type`,`volunteer_id_number`,`seeker_id_number`,`seeker_id_type`), ADD KEY `fk_volunteer_has_seeker_seeker1_idx` (`seeker_id_number`,`seeker_id_type`), ADD KEY `fk_volunteer_has_seeker_volunteer1_idx` (`volunteer_id_type`,`volunteer_id_number`);

--
-- Indexes for table `seeker`
--
ALTER TABLE `seeker`
 ADD PRIMARY KEY (`id_number`,`id_type`), ADD UNIQUE KEY `username_UNIQUE` (`username`), ADD UNIQUE KEY `username` (`username`), ADD KEY `verify_1_fk_idx` (`verified_by`);

--
-- Indexes for table `volunteer`
--
ALTER TABLE `volunteer`
 ADD PRIMARY KEY (`id_type`,`id_number`), ADD UNIQUE KEY `username_UNIQUE` (`username`), ADD KEY `verify_2_idx` (`verified_by`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `donates_blood_to`
--
ALTER TABLE `donates_blood_to`
ADD CONSTRAINT `fk_volunteer_has_seeker_seeker1` FOREIGN KEY (`seeker_id_number`, `seeker_id_type`) REFERENCES `seeker` (`id_number`, `id_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_volunteer_has_seeker_volunteer1` FOREIGN KEY (`volunteer_id_type`, `volunteer_id_number`) REFERENCES `volunteer` (`id_type`, `id_number`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `seeker`
--
ALTER TABLE `seeker`
ADD CONSTRAINT `verify_1_fk` FOREIGN KEY (`verified_by`) REFERENCES `admin` (`username`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `volunteer`
--
ALTER TABLE `volunteer`
ADD CONSTRAINT `verify_2` FOREIGN KEY (`verified_by`) REFERENCES `admin` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
