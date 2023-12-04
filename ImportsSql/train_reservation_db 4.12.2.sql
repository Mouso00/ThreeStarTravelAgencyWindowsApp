-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2023 at 06:43 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `train_reservation_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `cancelrecord`
--

CREATE TABLE `cancelrecord` (
  `cancel_id` int(11) NOT NULL,
  `reservation_id` int(11) DEFAULT NULL,
  `cancel_reason` varchar(255) DEFAULT NULL,
  `cancelled_by_user_id` int(11) DEFAULT NULL,
  `cancelled_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE `cities` (
  `city_id` int(11) NOT NULL,
  `city_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`city_id`, `city_name`) VALUES
(1, 'Athens'),
(2, 'Thessaloniki'),
(3, 'Patra'),
(4, 'Heraklion'),
(5, 'Larissa'),
(6, 'Volos'),
(7, 'Ioannina'),
(8, 'Trikala'),
(9, 'Serres'),
(10, 'Kalamata');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `reservation_id` int(11) NOT NULL,
  `class_type` varchar(255) DEFAULT NULL,
  `date_of_journey` date DEFAULT NULL,
  `source_location` varchar(255) DEFAULT NULL,
  `destination_location` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `time_of_journey` time DEFAULT NULL,
  `seat` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `train_number` int(11) DEFAULT NULL,
  `pnr_enquiry` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`reservation_id`, `class_type`, `date_of_journey`, `source_location`, `destination_location`, `status`, `created_at`, `time_of_journey`, `seat`, `price`, `train_number`, `pnr_enquiry`) VALUES
(11, 'Economy', '2023-12-01', 'Athens', 'Athens', 'Pending', '2023-12-04 17:03:04', '01:00:00', 'E1', 100.00, 969, NULL),
(12, 'Economy', '2023-12-15', 'Athens', 'Athens', 'Pending', '2023-12-04 17:09:35', '01:00:00', 'E1', 100.00, 483, NULL),
(14, 'Economy', '2023-12-08', 'Athens', 'Athens', 'Pending', '2023-12-04 17:17:54', '01:00:00', 'E1', 100.00, 474, NULL),
(15, 'Economy', '2023-12-01', 'Athens', 'Athens', 'Pending', '2023-12-04 17:33:01', '01:00:00', 'E1', 100.00, 655, NULL),
(16, 'Economy', '2023-12-14', 'Athens', 'Athens', 'Pending', '2023-12-04 17:41:59', '01:00:00', 'E1', 100.00, 614, 'PNR1701711718999');

-- --------------------------------------------------------

--
-- Table structure for table `trains`
--

CREATE TABLE `trains` (
  `train_id` int(11) NOT NULL,
  `train_number` varchar(255) DEFAULT NULL,
  `seat_number` varchar(255) DEFAULT NULL,
  `departure_time` datetime DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `source_city_id` int(11) DEFAULT NULL,
  `destination_city_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `trains`
--

INSERT INTO `trains` (`train_id`, `train_number`, `seat_number`, `departure_time`, `price`, `source_city_id`, `destination_city_id`) VALUES
(1, 'T123', 'A12', '2023-01-10 08:00:00', 50.00, 1, 5),
(2, 'T456', 'B23', '2023-01-15 12:30:00', 60.00, 3, 2),
(3, 'T789', 'C34', '2023-01-20 15:45:00', 70.00, 8, 10);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `full_name` varchar(255) DEFAULT NULL,
  `email_address` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`, `created_at`, `full_name`, `email_address`, `gender`, `date_of_birth`) VALUES
(1, 'a', 'a', 'admin', '2023-12-04 15:52:46', 'John Doe', 'john@example.com', 'Male', '1990-01-01'),
(2, 'aa', 'aa', 'user', '2023-12-04 15:52:51', 'Jane Doe', 'jane@example.com', 'Female', '1992-05-15');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cancelrecord`
--
ALTER TABLE `cancelrecord`
  ADD PRIMARY KEY (`cancel_id`),
  ADD KEY `reservation_id` (`reservation_id`),
  ADD KEY `cancelled_by_user_id` (`cancelled_by_user_id`);

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`city_id`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`reservation_id`);

--
-- Indexes for table `trains`
--
ALTER TABLE `trains`
  ADD PRIMARY KEY (`train_id`),
  ADD UNIQUE KEY `train_number` (`train_number`),
  ADD KEY `source_city_id` (`source_city_id`),
  ADD KEY `destination_city_id` (`destination_city_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `reservation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cancelrecord`
--
ALTER TABLE `cancelrecord`
  ADD CONSTRAINT `cancelrecord_ibfk_1` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`reservation_id`),
  ADD CONSTRAINT `cancelrecord_ibfk_2` FOREIGN KEY (`cancelled_by_user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `trains`
--
ALTER TABLE `trains`
  ADD CONSTRAINT `trains_ibfk_1` FOREIGN KEY (`source_city_id`) REFERENCES `cities` (`city_id`),
  ADD CONSTRAINT `trains_ibfk_2` FOREIGN KEY (`destination_city_id`) REFERENCES `cities` (`city_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
