-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 08 Nov 2020 pada 13.46
-- Versi server: 10.4.13-MariaDB
-- Versi PHP: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bigstars_tab_absen_2`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `murid`
--

CREATE TABLE `murid` (
  `id_murid` char(5) NOT NULL,
  `id_wali_murid` char(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `foto` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `murid`
--

INSERT INTO `murid` (`id_murid`, `id_wali_murid`, `nama`, `foto`) VALUES
('MR00001', '1', 'Carlos', 'NONE'),
('MR00002', '1', 'Kevin SDK', 'NONE'),
('MR00003', '1', 'Michelle', 'NONE'),
('MR00004', '1', 'Heaven', 'NONE'),
('MR00005', '1', 'Kevin RH', 'NONE'),
('MR00006', '1', 'Lydia', 'NONE'),
('MR00007', '1', 'Electra', 'NONE'),
('MR00008', '1', 'Jenice', 'NONE'),
('MR00009', '1', 'Kayla', 'NONE'),
('MR00010', '1', 'Fael', 'NONE'),
('MR00011', '1', 'Tirza', 'NONE'),
('MR00012', '1', 'feli', 'NONE'),
('MR00013', '1', 'Grace Avarina', 'NONE'),
('MR00014', '1', 'Rangga', 'NONE'),
('MR00015', '1', 'Tata', 'NONE'),
('MR00016', '1', 'Gwen', 'NONE'),
('MR00017', '1', 'Matthew', 'NONE'),
('MR00018', '1', 'Irish', 'NONE'),
('MR00019', '1', 'Evan', 'NONE'),
('MR00020', '1', 'Ivan', 'NONE'),
('MR00021', '1', 'Twins', 'NONE'),
('MR00022', '1', 'Zea', 'NONE'),
('MR00023', '1', 'Rei', 'NONE'),
('MR00024', '1', 'Alice atau Christo', 'NONE'),
('MR00025', '1', 'Alice & Christo', 'NONE'),
('MR00026', '1', 'Michael PH', 'NONE'),
('MR00027', '1', 'Michael SBH', 'NONE'),
('MR00028', '1', 'Gladys', 'NONE'),
('MR00029', '1', 'Grace Amanda', 'NONE'),
('MR00030', '1', 'Alin', 'NONE'),
('MR00041', '1', 'Vino', 'NONE'),
('MR00042', '1', 'Atma', 'NONE'),
('MR00043', '1', 'Razan', 'NONE'),
('MR00044', '1', 'Marco', 'NONE'),
('MR00045', '1', 'Jason', 'NONE'),
('MR00046', '1', 'Devin', 'NONE'),
('MR00047', '1', 'Keno', 'NONE'),
('MR00048', '1', 'Alhabsi', 'NONE'),
('MR00049', '1', 'Azzahra', 'NONE'),
('MR00050', '1', 'vania', 'NONE'),
('MR00051', '1', 'Jeslin', 'NONE'),
('MR00052', '1', 'geo', 'NONE'),
('MR00053', '1', 'Aafiyah', 'NONE'),
('MR00054', '1', 'Lili', 'NONE'),
('MR00055', '1', 'Stanley', 'NONE'),
('MR00056', '1', 'rafael', 'NONE'),
('MR00057', '1', 'lyvia', 'NONE');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `murid`
--
ALTER TABLE `murid`
  ADD PRIMARY KEY (`id_murid`),
  ADD KEY `id_wali_murid` (`id_wali_murid`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `murid`
--
ALTER TABLE `murid`
  ADD CONSTRAINT `murid_ibfk_1` FOREIGN KEY (`id_wali_murid`) REFERENCES `wali_murid` (`id_wali_murid`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
