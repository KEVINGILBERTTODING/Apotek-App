-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 08, 2022 at 05:33 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `apotek`
--

-- --------------------------------------------------------

--
-- Table structure for table `apoteker`
--

CREATE TABLE `apoteker` (
  `id` int(7) NOT NULL,
  `id_apoteker` char(7) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `kota` varchar(50) NOT NULL,
  `no_hp` char(16) NOT NULL,
  `alamat` varchar(150) NOT NULL,
  `shift` char(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `apoteker`
--

INSERT INTO `apoteker` (`id`, `id_apoteker`, `nama`, `kota`, `no_hp`, `alamat`, `shift`) VALUES
(1, 'AKY6899', 'James Anderson', 'Semarang', '081782932091', 'Jl. Pembangunan, No. 12 Semarang, Jawa Tengah', 'Malam'),
(2, 'KL05GYX', 'Teguh Bagus Cakra', 'Jakarta', '08533029200', 'Jl. Ki Hajar Dewantara, No. 78, Jakarta Selatan', 'Pagi');

-- --------------------------------------------------------

--
-- Table structure for table `obat`
--

CREATE TABLE `obat` (
  `id` int(5) NOT NULL,
  `kode_obat` char(10) NOT NULL,
  `nama_obat` varchar(40) NOT NULL,
  `satuan_obat` char(10) NOT NULL,
  `jumlah` int(12) NOT NULL,
  `jenis` varchar(50) NOT NULL,
  `deskripsi` varchar(255) NOT NULL,
  `expired` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `obat`
--

INSERT INTO `obat` (`id`, `kode_obat`, `nama_obat`, `satuan_obat`, `jumlah`, `jenis`, `deskripsi`, `expired`) VALUES
(19, 'K001', 'Paracetamol', 'PCS', 12, 'Tablet', 'Paracetamol adalah obat untuk meredakan demam Dan nyeri, termasuk nyeri said atau sakit Gigi. Paracetamol tersedia dalam bentuk table, sirop, tetes, suppositoria, Dan infus.', '2023-05-12'),
(20, 'K002', 'Ibuprofen', 'PCS', 34, 'Tablet', 'Ibuprofen adakah obat yang tergolong dalam kelompok obat anti-inflamasi nonsteroid dan digunakan untuk mengurangi rasa sakit akibat artritis.', '2023-05-19'),
(21, 'K003', 'Ultraflu', 'PCS', 58, 'Tablet', 'Ultraflu7 tablet adalah obat yang digunakan untuk mengobati gejala flu seperti demam, sakit kepala, hidung tersumbat dan bersin-bersin.', '2024-04-19'),
(22, 'K004', 'Minyak Kayu Putih', 'Botol', 90, 'Obat Oles', 'Minyak kayuu Putih dihasilkan dari hasil penyulingan daun Dan ranting kayu Putih yang merupakan metabolites sekunder yang dihasilkan oleh pohon tersebut.', '2024-04-01'),
(23, 'K005', 'Paratusin 2', 'PCS', 1034, 'Tablet', 'Obat paratusin adalah obat dengan kandungan prasetamol 500 mg Dan kombinasi it lainnya. Fungsi obat ini adalah untuk meredakan gejala flu. Bisa dibeli dengan atau tanpa resep dokter.', '2022-04-22'),
(24, 'K006', 'Ultraflu', 'Pcs', 12, 'Sirup', 'Aman untuk usia 12 tahun keatas', '2022-04-29'),
(26, 'K008', 'Misagrip', 'Botol', 67, 'Sirup', 'MIsagrip untuk anak', '2022-04-23'),
(27, 'K007', 'Minyak Gosok 40grm', 'Botol', 22, 'Obat Oles', 'Minyak gosok berfungsi untuk meringankan gejala masuk angin', '2024-07-19'),
(28, 'K0011', 'Misagrip', 'Pcs', 2555, 'Sirup', 'Misagrip adalah obat untuk meringankan gejala flu yang disertai dengan batuk berdahak.', '2022-05-27');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `kd_konsumen` int(6) NOT NULL,
  `nm_konsumen` varchar(50) NOT NULL,
  `alamat` varchar(150) NOT NULL,
  `kodepos` varchar(6) NOT NULL,
  `kota` varchar(150) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(80) NOT NULL,
  `group` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `apoteker`
--
ALTER TABLE `apoteker`
  ADD PRIMARY KEY (`id`,`id_apoteker`);

--
-- Indexes for table `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`kd_konsumen`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `apoteker`
--
ALTER TABLE `apoteker`
  MODIFY `id` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `obat`
--
ALTER TABLE `obat`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `kd_konsumen` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
