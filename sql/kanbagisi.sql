-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 15, 2018 at 09:05 AM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kanbagisi`
--

-- --------------------------------------------------------

--
-- Table structure for table `Bagisci`
--

CREATE TABLE `Bagisci` (
  `id` int(10) NOT NULL,
  `ad` varchar(50) COLLATE utf8_bin NOT NULL,
  `soyad` varchar(50) COLLATE utf8_bin NOT NULL,
  `adres` varchar(250) COLLATE utf8_bin NOT NULL,
  `telefon` bigint(11) NOT NULL,
  `eposta` varchar(50) COLLATE utf8_bin NOT NULL,
  `kangrubu` varchar(3) COLLATE utf8_bin NOT NULL,
  `parola` varchar(50) COLLATE utf8_bin NOT NULL,
  `durum` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `Harita`
--

CREATE TABLE `Harita` (
  `id` int(11) NOT NULL,
  `bagisci_id` int(11) NOT NULL,
  `enlem` double NOT NULL,
  `boylam` double NOT NULL,
  `aciklama` varchar(250) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Bagisci`
--
ALTER TABLE `Bagisci`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Harita`
--
ALTER TABLE `Harita`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Bagisci`
--
ALTER TABLE `Bagisci`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `Harita`
--
ALTER TABLE `Harita`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
