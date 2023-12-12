-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 12, 2023 at 11:17 PM
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
-- Table structure for table `cancellations`
--

CREATE TABLE `cancellations` (
  `cancellation_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `passenger_id` int(11) DEFAULT NULL,
  `pnr_number` varchar(255) DEFAULT NULL,
  `details` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `closed_by_user_id` int(11) DEFAULT NULL
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
(1, 'Αθήνα'),
(2, 'Θεσσαλονίκη'),
(3, 'Πάτρα'),
(4, 'Ηράκλειο'),
(5, 'Λάρισα'),
(6, 'Βόλος'),
(7, 'Ιωάννινα'),
(8, 'Τρίκαλα'),
(9, 'Σέρρες'),
(10, 'Καλαμάτα');

-- --------------------------------------------------------

--
-- Table structure for table `follows`
--

CREATE TABLE `follows` (
  `following_user_id` int(11) NOT NULL,
  `followed_user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `passengers`
--

CREATE TABLE `passengers` (
  `passenger_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pnr_enquiries`
--

CREATE TABLE `pnr_enquiries` (
  `pnr_enquiry_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `pnr_number` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `post_id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `body` text DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `train_number` int(11) NOT NULL,
  `class_type` varchar(255) NOT NULL,
  `date_of_journey` date NOT NULL,
  `source_location` varchar(255) NOT NULL,
  `destination_location` varchar(255) NOT NULL,
  `status` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `time_of_journey` time DEFAULT NULL,
  `seat` varchar(10) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT 0.00,
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `pnr_of_reservation` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`train_number`, `class_type`, `date_of_journey`, `source_location`, `destination_location`, `status`, `created_at`, `time_of_journey`, `seat`, `price`, `id`, `user_id`, `pnr_of_reservation`) VALUES
(289, 'Economy', '2023-12-07', 'Αθήνα', 'Αθήνα', 'Pending', '2023-12-12 20:40:06', '01:00:00', 'E1', 100.00, 1029, 5, 390105353),
(168, 'Economy', '2023-12-21', 'Αθήνα', 'Αθήνα', 'Pending', '2023-12-12 20:41:06', '01:00:00', 'E1', 100.00, 1030, 5, 594455953),
(549, 'Economy', '2023-12-06', 'Αθήνα', 'Αθήνα', 'Pending', '2023-12-12 22:16:18', '01:00:00', 'E1', 100.00, 1036, 5, 969617646);

-- --------------------------------------------------------

--
-- Table structure for table `trains`
--

CREATE TABLE `trains` (
  `train_id` int(11) NOT NULL,
  `train_number` int(11) NOT NULL,
  `seat_number` varchar(10) NOT NULL,
  `departure_time` datetime NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `class_type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `trains`
--

INSERT INTO `trains` (`train_id`, `train_number`, `seat_number`, `departure_time`, `price`, `class_type`) VALUES
(1, 12345, '12A', '2023-12-01 10:00:00', 50.00, 'First Class'),
(2, 54321, '7B', '2023-12-02 14:30:00', 60.00, 'Second Class'),
(3, 67890, '15C', '2023-12-03 12:30:00', 75.00, 'Economy'),
(4, 56789, '3A', '2023-12-03 12:30:00', 70.00, 'Business Class');

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
(1, 'test', 'test', 'user', '2023-11-29 00:43:55', 'test', 'test', 'Male', '2023-11-01'),
(2, 'test1', 'test1', 'user', '2023-11-29 00:45:10', 'test1', 'test1', 'Male', '2015-11-01'),
(3, 'testerr', 'testerr', 'user', '2023-11-29 19:55:30', 'testerr', 'testerr', 'Male', '2023-11-02'),
(4, 'tast', 'tast', 'user', '2023-11-29 20:32:58', 'tast', 'tast', 'Male', '2023-11-01'),
(5, 'a', 'a', 'user', '2023-11-29 23:13:28', 'a', 'a', 'Male', '2023-11-01'),
(6, 'aa', 'aa', 'user', '2023-12-02 15:00:01', 'aa', 'aa', 'Male', '2023-12-01'),
(7, '123', '123', 'user', '2023-12-06 21:39:10', '123', '123', 'Male', '2023-12-21'),
(8, '1', '1', 'user', '2023-12-06 22:29:25', '1', '1', 'Male', '2023-12-14');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cancellations`
--
ALTER TABLE `cancellations`
  ADD PRIMARY KEY (`cancellation_id`),
  ADD KEY `fk_closed_by_user_cancellation` (`closed_by_user_id`);

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`city_id`);

--
-- Indexes for table `follows`
--
ALTER TABLE `follows`
  ADD PRIMARY KEY (`following_user_id`,`followed_user_id`);

--
-- Indexes for table `passengers`
--
ALTER TABLE `passengers`
  ADD PRIMARY KEY (`passenger_id`);

--
-- Indexes for table `pnr_enquiries`
--
ALTER TABLE `pnr_enquiries`
  ADD PRIMARY KEY (`pnr_enquiry_id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`post_id`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `trains`
--
ALTER TABLE `trains`
  ADD PRIMARY KEY (`train_id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1037;

--
-- AUTO_INCREMENT for table `trains`
--
ALTER TABLE `trains`
  MODIFY `train_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
