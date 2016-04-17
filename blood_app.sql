-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 17, 2016 at 08:16 PM
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

DROP TABLE IF EXISTS `admin`;
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

DROP TABLE IF EXISTS `donates_blood_to`;
CREATE TABLE IF NOT EXISTS `donates_blood_to` (
  `seeker` varchar(30) NOT NULL,
  `volunteer` varchar(30) NOT NULL,
  `date_time` varchar(45) NOT NULL,
  `agency` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `donates_blood_to`
--

INSERT INTO `donates_blood_to` (`seeker`, `volunteer`, `date_time`, `agency`) VALUES
('abin', 'abinb', '2016-3-17 0:46', 'abin'),
('abinb', 'abin', '2016-3-17 0:46', 'abin'),
('abinb', 'abinb', '2016-3-17 0:45', 'abin'),
('abinb', 'clarkkent', '2016-3-17 0:59', 'Daily Planet'),
('abinb', 'ymograi', '2016-3-17 13:18', 'Red Cross');

-- --------------------------------------------------------

--
-- Table structure for table `seeker`
--

DROP TABLE IF EXISTS `seeker`;
CREATE TABLE IF NOT EXISTS `seeker` (
  `username` varchar(30) NOT NULL,
  `password` varchar(40) NOT NULL,
  `salt` varchar(40) NOT NULL,
  `email` varchar(45) NOT NULL,
  `name` varchar(40) NOT NULL,
  `phone` varchar(40) NOT NULL,
  `location` varchar(40) DEFAULT NULL,
  `id_number` varchar(40) NOT NULL,
  `id_type` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seeker`
--

INSERT INTO `seeker` (`username`, `password`, `salt`, `email`, `name`, `phone`, `location`, `id_number`, `id_type`) VALUES
('abin', 'qcVo/hBUJrPBwhTQu27WJzGS4Kk4MmMzOWVmOTM4', '82c39ef938', 'abinbhattacharya@gmail.com', 'Abin', '987654321', NULL, '09876', 'Driver License'),
('abinb', 'GPrnnkSImgHgCJ5hOTBKbTCj60QwOTk5YjU4NWE1', '0999b585a5', 'abinbhattacharya@gmail.com', 'Abin', '9876543210', NULL, '1234567', 'drivers license'),
('yashm', 'Mq/t9obSKEYv5hnBrIPPK2DxUsczYTRhMDJhZjI5', '3a4a02af29', 'ymograi@gmail.com', 'Yash', '9876543210', NULL, '1234567a', 'drivers license'),
('anuragpal', 'Kj+JnBmjouP3Qx1Aa3/rFXRocWszODU4MTBjMzZl', '385810c36e', 'anurag.pal.2191@gmail.com', 'Anurag Pal', '7034316651', NULL, '20114567334', 'Passport'),
('abinb1', 'iFZpFat5WIaxk3oy4sJrXyxn8fg2ZjAzYzkzOTY3', '6f03c93967', 'abinbhattacharya@gmail.com', 'Abin Bhattacharya', '9876543210', NULL, '2345', 'Driver License'),
('watcheronthewall', 'H146oQh4xqoxohIclteekFVdQqhjNDRjZGUxMjVk', 'c44cde125d', 'brucewayne@waynecorp.com', 'Bruce Wayne', '22286266', NULL, '31071942', 'Bat72'),
('ahaha', '+tzQ2Rfcof/gfR2RsUIg5PVPO9k0OWIyYjhjNWZm', '49b2b8c5ff', 'ymograi@gmail.com', 'ahaha', '9567759732', NULL, 'ahaha', 'PAN card'),
('yashmog', 'ZN2LkcMd5tQZd3wzvzdaHxHMo1I1MmRjMmVlYzY5', '52dc2eec69', 'ymograi@gmail.com', 'yashmograi', '9567759732', NULL, 'ddbdnd', 'Ration Card'),
('markhamill', 'YWzD0uufWnXN75bGRFYUUWYjr380MzMwZTA4ZjBi', '4330e08f0b', 'mark.hamill@gmail.com', 'Mark Hamill', '7784566132', NULL, 'euetu566', 'Passport'),
('bdjd', '9RpVaoRNccHPJOffK9yEVYEEHA42ZDY5Yjg1M2Q5', '6d69b853d9', 'anurag.pal.2191@gmail.com', 'hddh', '7709960332', NULL, 'FHD5673838', 'Ration Card'),
('ritika', 'UjcBrKUrj4CU/smC76y/8jPc9eBlYzRlODIzMzUw', 'ec4e823350', 'ymograi@gmail.com', 'ritika', '9567759732', NULL, 'shdbdd', 'PAN card'),
('asdf', '+pMUpfa7jm/QOEDYxZhzKYII02I4MGUyOGE1MWNi', '80e28a51cb', 'ymograi@gmail.com', 'asdf', '9567759732', NULL, 'shdhdd', 'PAN card'),
('ahahaha', 'L9pncCYby+lZZg+5R+IVAyrXLu5hMjRhZjZhMzc1', 'a24af6a375', 'ymograi@gmail.com', 'ahahaha', '9567759732', NULL, 'shshshs', 'Ration Card'),
('ymograi', '0XAErKujQ+up8FeEzfnxS1yRPwNmYmFiNjkxMGM2', 'fbab6910c6', 'ymograi@gmail com', 'yash', '01165170045', NULL, 'shshshsbdbd', 'driver lic');

-- --------------------------------------------------------

--
-- Table structure for table `volunteer`
--

DROP TABLE IF EXISTS `volunteer`;
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
  `blood_group` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `volunteer`
--

INSERT INTO `volunteer` (`username`, `password`, `salt`, `email`, `name`, `phone`, `lat`, `lon`, `id_type`, `id_number`, `verified_by`, `blood_group`) VALUES
('abinbhattacharya', 'WNxJqrnKp1Hn1yeN7365L8MpEVVmNjNlZmQ3ZDgw', 'f63efd7d80', 'abinbhattacharya@gmail.com', 'Abin Bhattacharya', '9830097007', 11.3182, 75.9376, 'Driver License', '123455678910', 'abinb', 'B+'),
('abinb', 'o+Rfq6iggY/oZUz9758E1FS8JchlY2ZjZThkZTdi', 'ecfce8de7b', 'ymograi@gmail.com', 'Abin', '9876543210', 11.31503757, 75.93249688, 'Driver License', '1234567', 'abinb', 'A+'),
('abinb1', 'qqKo7i69RStWOChQzYgg0+iB1C0zZDQ2MDM1MDA2', '3d46035006', 'abinbhattacharya@gmail.com', 'Abin Bhattacharya', '9876543210', 11.3182, 75.9376, 'Driver License', '234456', 'abinb', 'B-'),
('abinb2', 'OwzMvoaynMUbnl8ijCyZQXGc5XM3NzI4ZWY1YmU1', '7728ef5be5', 'anurag.pal.2191@gmail.com', 'Abin Bhattacharya', '9876543210', 11.3651, 75.9623, 'Driver License', '2344566', 'abinb', 'A+'),
('abinb3', 'iFsvbZ6UdZytT8o9FrHUXUsHItZiYjljYjE0YmU4', 'bb9cb14be8', 'abinbhattacharya@gmail.com', 'Abin', '9876543210', 11.3214, 75.937, 'Driver License', 'asd1', 'abinb', 'AB-'),
('abinb4', '92HYRf649q80jOQcVFBtSxeLkfEwNzA3MTJjMjNk', '070712c23d', 'abinbhattacharya@gmail.com', 'Abin Bhattacharya', '9876543210', 11.3049, 75.8771, 'Driver License', 'asdd2', 'abinb', 'B+'),
('abinb5', '4a8t+aR7N/Gvf3iN9C46DG3ByDEzNzEyMjI5MGM2', '37122290c6', 'abinbhattacharya@gmail.com', 'Abin', '9876543210', 28.6139, 77.209, 'Driver License', 'asdfg5', 'abinb', 'A-'),
('clarkkent', 'fqzrvLlPqYhaMeL7TWgM3UB6GkM1ZjZmY2Y2NGQx', '5f6fcf64d1', 'anurag.pal.2191@gmail.com', 'Clark Kent', '9633578049', 21.1458, 79.0882, 'Driver License', 'CBW546372', 'abinb', 'O-'),
('ymograi', 'eIiCPKUE1pHNKcQdw/p36G8Vb4c3YTE3N2JjMzJi', '7a177bc32b', 'ymograi@gmail.com', 'Yash', '9567759732', 28.6139, 77.209, 'Driver License', 'shshe627', 'abinb', 'O+'),
('liyak', 'RHgjYQehmaGpkXEFCmgxVkRon6U1NTc0MjU3NGI1', '55742574b5', 'liyak@gmail.com', 'Liya', '49497989595', 22.5726, 88.3639, 'Driver License', 'xbxbxb', 'abinb', 'O-'),
('asdf', '6o5HaMaxILna9AJlwDDoZGPYFtJhMDkzMDgxZDUy', 'a093081d52', 'ymograi@gmail.com', 'asdf', '9567759732', NULL, NULL, 'PAN card', 'dhdhd', NULL, 'A-'),
('lovelykumari', '/vYfvuEXtfh5/ow0BTzg+XGToGNmMTBlNDMzZWY1', 'f10e433ef5', 'ymograi@gmail.com', 'Lovely Kumari', '9700716433', 11.3049, 75.8771, 'PAN card', 'GWB2263758', 'abinb', 'AB-'),
('tomhanks', 'Mva+4zY8qVngDuL0wAMnkI3/mPA5ZTA0ZDAyOGUx', '9e04d028e1', 'ymograi@gmail.com', 'Tom Hanks', '9708525233', 11.3214, 75.937, 'PAN card', 'GWB2692280', 'abinb', 'AB+'),
('stevens', 'ZZNv2kkEXA6kJW4/nlHXt7HZiEFiN2U2MjhjODRj', 'b7e628c84c', 'abinbhattacharya@gmail.com', 'Steven Speilberg', '9708548159', 11.31498384, 75.93238661, 'PAN card', 'TGB2692280', 'abinb', 'O+'),
('harrisonford', 'i0yvhd2fooaGAoehwuCw6lD5s1VjMzI3ZjAzMjEx', 'c327f03211', 'anurag.pal.2191@gmail.com', 'Harisson Ford', '7034316651', 13.0827, 80.2707, 'Passport', 'ADG3735748', 'abinb', 'A+'),
('liamneeson', '/8037TSZ1RyAopZbKuCOJKSNExZjMDdmYTBhYmE1', 'c07fa0aba5', 'abinbhattacharya@gmail.com', 'Liam Neeson', '7709960332', 17.385, 78.4867, 'Passport', 'ADG4755748', 'abinb', 'AB+'),
('abin', 'A1rJbGUEjfwdMOsbc55J5Eta9fY1Y2U1YzgzOWIw', '5ce5c839b0', 'abinbhattacharya@gmail.com', 'Suresh Babu', '9830097007', 12.9716, 77.5946, 'Passport', 'AGB567382', 'abinb', 'A+'),
('kevinconroy', '9l2ajrw5psxMf5U1EtvNlrRaEgo0OWI1MDZiZjRl', '49b506bf4e', 'anurag.pal.2191@gmail.com', 'Kevin Conroy', '7784556131', 19.8134, 85.8315, 'Passport', 'dbgf4475', 'abinb', 'O+'),
('yashm', 'i+j/Q6CPZIpnck4u5B32R3xAfa02YmY5ODg0ZGZk', '6bf9884dfd', 'ymograi@gmail.com', 'Yash Mograi', '9567759732', 21.1938, 81.3509, 'Passport', 'dishe3737', 'abinb', 'O-'),
('yashma', 'ZROIvdHTCXKjbiHq7INWjTZ8QV9lNDgzMmRmNGM2', 'e4832df4c6', 'ymograi@gmail.com', 'yashma', '9567759732', 23.2156, 72.6369, 'Passport', 'dishe3737dhdb', 'abinb', 'O-'),
('ya', 'XRS4EULb2F+7g9RkjooxniKzg004ZGVkYTVhODEx', '8deda5a811', 'ymograi@gmail.com', 'ya', '9567759732', 11.31498384, 75.93238662, 'Passport', 'dishe3737dhdbzzbz', 'abinb', 'O-'),
('sherlock', 'cbELZwX9e7Z8Gy5vWQpKJ7DlSZc0MTVkZDNiOWFi', '415dd3b9ab', 'ymograi@gmail.com', 'Sherlock Holmes', '4576765356', NULL, NULL, 'Passport', 'egdhr45', NULL, 'B+'),
('abinb29', 'X9uhFyzfh/xU5xP6Fx2X2RgrTBo5ZjZhN2VjOWQz', '9f6a7ec9d3', 'abinbhattacharya@gmail.com', 'Abin', '9830097007', NULL, NULL, 'Passport', 'sbdhckzj', NULL, 'B+'),
('yan', 'ajLZitplf0JmIbE4pHOdl6HwzsE1ZjNmNzVlZDQ3', '5f3f75ed47', 'ymograi@gmail.com', 'yan', '9567759732', 11.4064, 76.6932, 'Passport', 'sbsbbsjs', 'abinb', 'O-'),
('preeti', 'mki2bkUGBTD4BtGzddrJ57a2tegyZDEzZDVlZDgw', '2d13d5ed80', 'anurag.pal.2191@gmail.com', 'Preeti', '9784465321', 11.3651, 75.9623, 'Ration Card', 'ABG456773', 'abinb', 'A-'),
('asdfgh', 'NvbvStIOH1u2NMFT8uA+5cSSa5c5MzU5OWRhZGY1', '93599dadf5', 'anurag.pal.2191@gmail.com', 'asdfgh', '7709960332', NULL, NULL, 'Ration Card', 'FHD4737483', 'abinb', 'A+');

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
 ADD PRIMARY KEY (`seeker`,`volunteer`,`date_time`), ADD KEY `volunteer_user_idx` (`volunteer`);

--
-- Indexes for table `seeker`
--
ALTER TABLE `seeker`
 ADD PRIMARY KEY (`id_number`,`id_type`), ADD UNIQUE KEY `username_UNIQUE` (`username`), ADD UNIQUE KEY `username` (`username`);

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
ADD CONSTRAINT `seeker_user` FOREIGN KEY (`seeker`) REFERENCES `seeker` (`username`) ON DELETE NO ACTION ON UPDATE CASCADE,
ADD CONSTRAINT `volunteer_user` FOREIGN KEY (`volunteer`) REFERENCES `volunteer` (`username`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `volunteer`
--
ALTER TABLE `volunteer`
ADD CONSTRAINT `verify_2` FOREIGN KEY (`verified_by`) REFERENCES `admin` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
