-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 16, 2020 at 08:42 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cts`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `reg_date` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `email`, `password`, `reg_date`) VALUES
(1, 'Johndoe', 'admin@admin.com', 'password', '2020-07-13 14:17:10');

-- --------------------------------------------------------

--
-- Table structure for table `criminal_details`
--

CREATE TABLE `criminal_details` (
  `id` int(11) NOT NULL,
  `caseno` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `crime` text DEFAULT NULL,
  `address` text DEFAULT NULL,
  `stateoforigin` varchar(100) DEFAULT NULL,
  `lga` varchar(100) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `age` varchar(20) DEFAULT NULL,
  `ipo` varchar(100) DEFAULT NULL,
  `town` varchar(100) DEFAULT NULL,
  `court` varchar(100) DEFAULT NULL,
  `verdict` varchar(100) DEFAULT NULL,
  `cellno` varchar(20) DEFAULT NULL,
  `arrestdate` varchar(100) DEFAULT NULL,
  `dateconvicted` varchar(100) DEFAULT NULL,
  `reg_date` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `criminal_details`
--

INSERT INTO `criminal_details` (`id`, `caseno`, `name`, `crime`, `address`, `stateoforigin`, `lga`, `sex`, `age`, `ipo`, `town`, `court`, `verdict`, `cellno`, `arrestdate`, `dateconvicted`, `reg_date`) VALUES
(2, '001', 'John Doe', 'Phone Theft', '667, XY N', 'Abidjan', 'Negopoli', 'Female', '22', 'WET-23', 'Musigal', 'High Court Mgistrate', 'OYE-45', '882525027', '07/08/2020', '07/13/2017', '2020-07-15 21:19:46'),
(4, '002', 'Mill Smith', 'Car Theft', '31 Cresent', 'New York', 'Maliami', 'Male', '19', 'WET-23', 'Muritame', 'High Court Mgistrate', 'ER-21', '2233456188', '07/28/2020', '08/15/2020', '2020-07-15 21:19:46');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `criminal_details`
--
ALTER TABLE `criminal_details`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `criminal_details`
--
ALTER TABLE `criminal_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
