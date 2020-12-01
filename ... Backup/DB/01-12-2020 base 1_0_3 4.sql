-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 01 Des 2020 pada 13.29
-- Versi server: 10.2.34-MariaDB-cll-lve
-- Versi PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bigstars_tab_absen`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id_admin` char(7) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` char(60) NOT NULL,
  `foto` char(7) NOT NULL,
  `status_data` enum('active','inactive') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id_admin`, `nama`, `username`, `password`, `foto`, `status_data`) VALUES
('AD00001', 'Admin Bigstars', 'bb110', '$2y$10$G.eqSW3QemJ15mXpIF/U3uezqDJHQPlPlvJUKY6TQZW2ugTwVGoYK', 'NONE', 'active'),
('AD00002', 'rizkika', 'kaka', '$2y$10$uNGzA6kD9BDaSRCirVujVOAY57Z4DsTpS3hVQQfpfwvWWHmk/JdcS', 'NONE', 'active');

-- --------------------------------------------------------

--
-- Struktur dari tabel `bayar_fee`
--

CREATE TABLE `bayar_fee` (
  `id_bayar_fee` char(13) NOT NULL,
  `id_pengajar` char(7) NOT NULL,
  `id_admin` char(7) NOT NULL,
  `waktu` datetime NOT NULL,
  `total_pertemuan` tinyint(4) NOT NULL,
  `total_harga_fee` int(11) NOT NULL,
  `status_data` enum('active','inactive') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `bayar_fee_detail`
--

CREATE TABLE `bayar_fee_detail` (
  `id_bayar_fee_detail` int(11) NOT NULL,
  `id_bayar_fee` char(13) NOT NULL,
  `id_pertemuan` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `bayar_spp`
--

CREATE TABLE `bayar_spp` (
  `id_bayar_spp` char(13) NOT NULL,
  `id_wali_murid` smallint(5) NOT NULL,
  `id_admin` char(7) NOT NULL,
  `waktu` datetime NOT NULL,
  `total_pertemuan` tinyint(4) NOT NULL,
  `total_harga_spp` int(11) NOT NULL,
  `status_data` enum('active','inactive') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `bayar_spp_detail`
--

CREATE TABLE `bayar_spp_detail` (
  `id_bayar_spp_detail` int(6) NOT NULL,
  `id_bayar_spp` char(13) NOT NULL,
  `id_pertemuan_detail` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `kelas_pertemuan`
--

CREATE TABLE `kelas_pertemuan` (
  `id_kelas_pertemuan` char(7) NOT NULL,
  `id_pengajar` char(7) NOT NULL,
  `id_mata_pelajaran` smallint(5) NOT NULL,
  `hari` varchar(50) NOT NULL,
  `jam_mulai` time NOT NULL,
  `jam_berakhir` time NOT NULL,
  `harga_fee` int(10) NOT NULL,
  `harga_spp` int(10) NOT NULL,
  `id_sharing` char(7) NOT NULL,
  `nama_sharing` varchar(50) NOT NULL,
  `status_data` enum('active','inactive') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `kelas_pertemuan`
--

INSERT INTO `kelas_pertemuan` (`id_kelas_pertemuan`, `id_pengajar`, `id_mata_pelajaran`, `hari`, `jam_mulai`, `jam_berakhir`, `harga_fee`, `harga_spp`, `id_sharing`, `nama_sharing`, `status_data`) VALUES
('KL00001', 'PE00001', 1, 'Senin - Sabtu', '11:00:00', '12:00:00', 27000, 45000, 'null', 'kosong', 'active'),
('KL00002', 'PE00001', 1, 'Senin, Selasa dan Jumat', '09:00:00', '10:30:00', 30000, 65000, 'null', 'kosong', 'active'),
('KL00003', 'PE00001', 1, 'Senin, Selasa dan Jumat', '09:00:00', '10:30:00', 25000, 40000, 'null', 'kosong', 'active'),
('KL00004', 'PE00001', 1, 'Senin, Selasa dan Jumat', '09:00:00', '10:30:00', 25000, 40000, 'null', 'kosong', 'active'),
('KL00005', 'PE00005', 1, 'Senin - Jumat', '15:00:00', '16:30:00', 36000, 50000, 'null', 'kosong', 'active'),
('KL00006', 'PE00007', 1, 'Senin dan Rabu', '15:00:00', '16:00:00', 25000, 43000, 'null', 'kosong', 'active'),
('KL00007', 'PE00007', 1, 'Jumat', '11:00:00', '12:00:00', 25000, 43000, 'null', 'kosong', 'active'),
('KL00008', 'PE00007', 3, 'Senin, Jumat, Sabtu', '16:30:00', '17:30:00', 22000, 35000, 'null', 'kosong', 'active'),
('KL00009', 'PE00008', 3, 'Senin - Rabu', '11:00:00', '12:00:00', 25000, 35000, 'null', 'kosong', 'active'),
('KL00010', 'PE00008', 3, 'Kamis', '14:00:00', '15:00:00', 25000, 35000, 'null', 'kosong', 'active'),
('KL00011', 'PE00008', 3, 'Jumat', '11:00:00', '12:00:00', 25000, 35000, 'null', 'kosong', 'active'),
('KL00012', 'PE00008', 3, 'Senin - Rabu', '13:00:00', '14:00:00', 28000, 38000, 'null', 'kosong', 'active'),
('KL00013', 'PE00011', 1, 'Senin, Rabu dan Jumat', '00:00:00', '00:00:00', 24000, 43000, 'null', 'kosong', 'active'),
('KL00014', 'PE00011', 1, 'Senin, Rabu dan Jumat', '00:00:00', '00:00:00', 27000, 45000, 'null', 'kosong', 'active'),
('KL00015', 'PE00013', 1, 'Senin - Kamis', '14:00:00', '15:30:00', 27000, 50000, 'null', 'kosong', 'active'),
('KL00016', 'PE00004', 1, 'Sesuai Request', '00:00:00', '00:00:00', 30000, 50000, 'null', 'kosong', 'active'),
('KL00017', 'PE00006', 1, 'Sesuai Request', '00:00:00', '00:00:00', 43000, 65000, 'null', 'kosong', 'active'),
('KL00018', 'PE00009', 1, 'Sesuai Request', '00:00:00', '00:00:00', 32000, 40000, 'null', 'kosong', 'active'),
('KL00019', 'PE00012', 1, 'Sesuai Request', '00:00:00', '00:00:00', 40000, 70000, 'null', 'kosong', 'active'),
('KL00020', 'PE00012', 1, 'Sesuai Request', '00:00:00', '00:00:00', 30000, 40000, 'null', 'kosong', 'active'),
('KL00021', 'PE00012', 1, 'Sesuai Request', '00:00:00', '00:00:00', 30000, 40000, 'null', 'kosong', 'active'),
('KL00022', 'PE00002', 1, 'Rabu dan Kamis', '09:00:00', '10:30:00', 30000, 65000, 'null', 'kosong', 'active'),
('KL00023', 'PE00002', 1, 'Rabu dan Kamis', '09:00:00', '10:30:00', 25000, 40000, 'null', 'kosong', 'active'),
('KL00024', 'PE00002', 1, 'Rabu dan Kamis', '09:00:00', '10:30:00', 25000, 40000, 'null', 'kosong', 'active'),
('KL00025', 'PE00001', 1, 'Selasa, Kamis dan Sabtu', '16:00:00', '16:00:00', 23000, 40000, 'null', 'kosong', 'active'),
('KL00026', 'PE00003', 1, 'Kosong', '00:00:00', '00:00:00', 43000, 75000, 'null', 'kosong', 'active'),
('KL00027', 'PE00003', 1, 'Sabtu', '00:00:00', '00:00:00', 24000, 40000, 'null', 'kosong', 'active'),
('KL00028', 'PE00007', 1, 'Selasa dan Kamis', '15:00:00', '16:00:00', 25000, 40000, 'null', 'kosong', 'active'),
('KL00029', 'PE00008', 3, 'Selasa - Kamis', '16:30:00', '17:30:00', 27000, 35000, 'null', 'kosong', 'active'),
('KL00030', 'PE00002', 1, 'Rabu dan Jumat', '12:00:00', '13:30:00', 24000, 35000, 'null', 'kosong', 'active'),
('KL00031', 'PE00010', 2, 'Selasa dan Rabu', '16:00:00', '18:00:00', 24000, 43000, 'null', 'kosong', 'active'),
('KL00032', 'PE00010', 2, 'Selasa dan Rabu', '16:00:00', '18:00:00', 27000, 45000, 'null', 'kosong', 'active'),
('KL00033', 'PE00007', 1, 'Rabu', '16:30:00', '17:30:00', 24000, 40000, 'null', 'kosong', 'active'),
('KL00034', 'PE00007', 1, 'Jumat', '09:00:00', '10:00:00', 24000, 40000, 'null', 'kosong', 'active');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kelas_pertemuan_detail`
--

CREATE TABLE `kelas_pertemuan_detail` (
  `id_kelas_pertemuan_detail` int(6) NOT NULL,
  `id_kelas_pertemuan` char(7) NOT NULL,
  `id_murid` char(7) NOT NULL,
  `status_data` enum('active','inactive') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `kelas_pertemuan_detail`
--

INSERT INTO `kelas_pertemuan_detail` (`id_kelas_pertemuan_detail`, `id_kelas_pertemuan`, `id_murid`, `status_data`) VALUES
(1, 'KL00001', 'MR00001', 'active'),
(2, 'KL00002', 'MR00003', 'active'),
(3, 'KL00002', 'MR00002', 'active'),
(4, 'KL00003', 'MR00003', 'active'),
(5, 'KL00004', 'MR00002', 'active'),
(6, 'KL00005', 'MR00008', 'active'),
(7, 'KL00006', 'MR00010', 'active'),
(8, 'KL00007', 'MR00010', 'active'),
(9, 'KL00008', 'MR00013', 'active'),
(10, 'KL00009', 'MR00014', 'active'),
(11, 'KL00010', 'MR00014', 'active'),
(12, 'KL00011', 'MR00014', 'active'),
(13, 'KL00012', 'MR00015', 'active'),
(14, 'KL00013', 'MR00018', 'active'),
(15, 'KL00014', 'MR00019', 'active'),
(16, 'KL00015', 'MR00022', 'active'),
(17, 'KL00016', 'MR00007', 'active'),
(18, 'KL00017', 'MR00009', 'active'),
(19, 'KL00018', 'MR00017', 'active'),
(20, 'KL00019', 'MR00020', 'active'),
(21, 'KL00019', 'MR00021', 'active'),
(22, 'KL00020', 'MR00020', 'active'),
(23, 'KL00021', 'MR00021', 'active'),
(24, 'KL00022', 'MR00003', 'active'),
(25, 'KL00022', 'MR00002', 'active'),
(26, 'KL00023', 'MR00003', 'active'),
(27, 'KL00024', 'MR00002', 'active'),
(28, 'KL00025', 'MR00004', 'active'),
(29, 'KL00026', 'MR00005', 'active'),
(30, 'KL00027', 'MR00006', 'active'),
(31, 'KL00028', 'MR00011', 'active'),
(32, 'KL00029', 'MR00013', 'active'),
(33, 'KL00030', 'MR00016', 'active'),
(34, 'KL00031', 'MR00018', 'active'),
(35, 'KL00032', 'MR00019', 'active'),
(36, 'KL00033', 'MR00012', 'active'),
(37, 'KL00034', 'MR00012', 'active');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mata_pelajaran`
--

CREATE TABLE `mata_pelajaran` (
  `id_mata_pelajaran` smallint(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `status_data` enum('active','inactive') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mata_pelajaran`
--

INSERT INTO `mata_pelajaran` (`id_mata_pelajaran`, `nama`, `status_data`) VALUES
(1, 'Mapel', 'active'),
(2, 'Inggris', 'active'),
(3, 'Calistung', 'active');

-- --------------------------------------------------------

--
-- Struktur dari tabel `murid`
--

CREATE TABLE `murid` (
  `id_murid` char(7) NOT NULL,
  `id_wali_murid` smallint(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `foto` char(7) NOT NULL,
  `status_data` enum('active','inactive') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `murid`
--

INSERT INTO `murid` (`id_murid`, `id_wali_murid`, `nama`, `foto`, `status_data`) VALUES
('MR00001', 1, 'Kehin Hoo', 'NONE', 'active'),
('MR00002', 2, 'Christo', 'NONE', 'active'),
('MR00003', 3, 'Alice', 'NONE', 'active'),
('MR00004', 4, 'Michelle', 'NONE', 'active'),
('MR00005', 5, 'Keiza', 'NONE', 'active'),
('MR00006', 6, 'Jessyln', 'NONE', 'active'),
('MR00007', 7, 'Carlos', 'NONE', 'active'),
('MR00008', 8, 'Denaya', 'NONE', 'active'),
('MR00009', 9, 'Janiece', 'NONE', 'active'),
('MR00010', 10, 'Kayla', 'NONE', 'active'),
('MR00011', 11, 'Alin', 'NONE', 'active'),
('MR00012', 12, 'Lody', 'NONE', 'active'),
('MR00013', 4, 'Meme', 'NONE', 'active'),
('MR00014', 13, 'Matthew', 'NONE', 'active'),
('MR00015', 14, 'Kevin', 'NONE', 'active'),
('MR00016', 15, 'Feli', 'NONE', 'active'),
('MR00017', 15, 'Grace', 'NONE', 'active'),
('MR00018', 16, 'Kevin Rh', 'NONE', 'active'),
('MR00019', 16, 'Lydia Rh', 'NONE', 'active'),
('MR00020', 17, 'Evan', 'NONE', 'active'),
('MR00021', 18, 'Ivan', 'NONE', 'active'),
('MR00022', 19, 'Gwen', 'NONE', 'active');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengajar`
--

CREATE TABLE `pengajar` (
  `id_pengajar` char(7) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` char(60) NOT NULL,
  `alamat` text NOT NULL,
  `no_hp` varchar(20) NOT NULL,
  `foto` char(7) NOT NULL,
  `status_data` enum('active','inactive') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pengajar`
--

INSERT INTO `pengajar` (`id_pengajar`, `nama`, `username`, `password`, `alamat`, `no_hp`, `foto`, `status_data`) VALUES
('PE00001', 'Ms. Novi', 'novibs', '$2y$10$5fhCErMtNJZoupKYI5dsmObwNr13XqE4l2/vxxKnvjT9oOs7tKE82', 'jember', '0', 'NONE', 'active'),
('PE00002', 'Ms. Nindi', 'nindibs', '$2y$10$ksgqMJ4DzqcpPBqGRbJC7.4K3UPbQL.rqBWRIz.X5GU6fXK1AeZz.', 'jember', '0', 'NONE', 'active'),
('PE00003', 'Ms. Putu', 'putubs', '$2y$10$QK1hZ2dBP9mMFaJAVrdAs.vdtzgSG9Rg.LzzdJy24DfoIk9pAKL8y', 'jember', '0', 'NONE', 'active'),
('PE00004', 'Ms. Ayu', 'ayubs', '$2y$10$8UlmYrMKPZaUY06gYDlUhOgQxw5Qota/8xkdXynCYm5Lr96lRJRgy', 'jember', '0', 'NONE', 'active'),
('PE00005', 'Ms. Riska', 'riskabs', '$2y$10$TALRvd1P8SMKYiU4vfErKuoGiYWVJRyG3vy5OT.S3rqeItNVbeBom', 'jember', '0', 'NONE', 'active'),
('PE00006', 'Mr. Anton', 'antonbs', '$2y$10$GNI01SIukb9Buliyu7Y/Iu/PZFSJ8eBMDAUKjGm8u7PnB/uKj9Uz2', 'jember', '0', 'NONE', 'active'),
('PE00007', 'Ms. Ninis', 'ninisbs', '$2y$10$q409QWwi1F/nIj3Gj5jSO.KbFvxtfognKz7aFoNFDHoWwoGRrSGM2', 'jember', '0', 'NONE', 'active'),
('PE00008', 'Ms. Devi', 'devibs', '$2y$10$TWj9/bkOYZsXv1FXbodIO.bpbd2sB1bmw1D2TeAiJTixxPU11fa2C', 'jember', '0', 'NONE', 'active'),
('PE00009', 'Mr. Iqbal', 'iqbalbs', '$2y$10$4cai5CiPUvxsf9qPzEP1NOd/VgB0ill4lVV5ALOWu9GplZPMc.qOi', 'jember', '0', 'NONE', 'active'),
('PE00010', 'Ms. Dita', 'ditabs', '$2y$10$qtgw36SQ9.hIJoHonqXJNu.Ou6iLO4K3pWsoHeykVCnun7YB9uTaS', 'jember', '0', 'NONE', 'active'),
('PE00011', 'Ms. Eka', 'ekabs', '$2y$10$1ygHHYt0mHKxK/QvR5Cco.7m0s.gIftzaJKKpvx04O18ysVBcSoxi', 'jember', '0', 'NONE', 'active'),
('PE00012', 'Ms. Dika', 'dikabs', '$2y$10$R.HktJSh/hTdnp/NI2QzQe2frKdNShWz24FFLA6EALm.r3VY0Ipei', 'jember', '0', 'NONE', 'active'),
('PE00013', 'Ms. Nurul', 'nurulbs', '$2y$10$y.0kkMhjgzEbI9v8lIL8muuELfNQG2OdZr8iJiXotuq2bJD/APHb.', 'jember', '0', 'NONE', 'active');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pertemuan`
--

CREATE TABLE `pertemuan` (
  `id_pertemuan` char(13) NOT NULL,
  `id_pengajar` char(7) NOT NULL,
  `id_kelas_pertemuan` char(7) NOT NULL,
  `hari` varchar(50) NOT NULL,
  `waktu_mulai` datetime NOT NULL,
  `waktu_berakhir` datetime NOT NULL,
  `lokasi_mulai_la` text NOT NULL,
  `lokasi_mulai_lo` text NOT NULL,
  `lokasi_berakhir_la` text NOT NULL,
  `lokasi_berakhir_lo` text NOT NULL,
  `deskripsi` text NOT NULL,
  `harga_fee` int(10) NOT NULL,
  `harga_spp` int(10) NOT NULL,
  `status_fee` enum('Belum Terbayar','Sudah Terbayar') NOT NULL,
  `status_spp` enum('Belum Lunas','Sudah Lunas') NOT NULL,
  `status_konfirmasi` enum('Valid','Invalid') NOT NULL,
  `status_pertemuan` enum('Selesai','Belum Selesai','Batal') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pertemuan_detail`
--

CREATE TABLE `pertemuan_detail` (
  `id_pertemuan_detail` int(6) NOT NULL,
  `id_pertemuan` char(13) NOT NULL,
  `id_murid` char(7) NOT NULL,
  `status_spp_detail` enum('Belum Lunas','Sudah Lunas') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_bayar_fee`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_bayar_fee` (
`id_bayar_fee` char(13)
,`waktu` datetime
,`total_pertemuan` tinyint(4)
,`total_harga_fee` int(11)
,`status_data` enum('active','inactive')
,`id_pengajar` char(7)
,`nama_pengajar` varchar(50)
,`id_admin` char(7)
,`nama_admin` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_bayar_fee_detail`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_bayar_fee_detail` (
`id_bayar_fee_detail` int(11)
,`id_bayar_fee` char(13)
,`id_pertemuan` char(13)
,`hari_pertemuan` varchar(50)
,`waktu_mulai` datetime
,`waktu_berakhir` datetime
,`lokasi_mulai_la` text
,`lokasi_mulai_lo` text
,`lokasi_berakhir_la` text
,`lokasi_berakhir_lo` text
,`deskripsi` text
,`harga_fee` int(10)
,`harga_spp` int(10)
,`status_fee` enum('Belum Terbayar','Sudah Terbayar')
,`status_spp` enum('Belum Lunas','Sudah Lunas')
,`status_konfirmasi` enum('Valid','Invalid')
,`status_pertemuan` enum('Selesai','Belum Selesai','Batal')
,`id_pengajar` char(7)
,`nama_pengajar` varchar(50)
,`username_pengajar` varchar(50)
,`alamat_pengajar` text
,`no_hp_pengajar` varchar(20)
,`foto_pengajar` char(7)
,`id_kelas_pertemuan` char(7)
,`hari_kelas_pertemuan` varchar(50)
,`jam_mulai` time
,`jam_berakhir` time
,`id_mata_pelajaran` smallint(5)
,`nama_mata_pelajaran` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_bayar_spp`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_bayar_spp` (
`id_bayar_spp` char(13)
,`waktu` datetime
,`total_pertemuan` tinyint(4)
,`total_harga_spp` int(11)
,`status_data` enum('active','inactive')
,`id_wali_murid` smallint(5)
,`nama_wali_murid` varchar(50)
,`id_admin` char(7)
,`nama_admin` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_bayar_spp_detail`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_bayar_spp_detail` (
`id_bayar_spp_detail` int(6)
,`id_bayar_spp` char(13)
,`id_pertemuan_detail` int(6)
,`status_spp_detail` enum('Belum Lunas','Sudah Lunas')
,`id_pertemuan` char(13)
,`hari_pertemuan` varchar(50)
,`waktu_mulai` datetime
,`waktu_berakhir` datetime
,`lokasi_mulai_la` text
,`lokasi_mulai_lo` text
,`lokasi_berakhir_la` text
,`lokasi_berakhir_lo` text
,`deskripsi` text
,`harga_fee` int(10)
,`harga_spp` int(10)
,`status_fee` enum('Belum Terbayar','Sudah Terbayar')
,`status_spp` enum('Belum Lunas','Sudah Lunas')
,`status_konfirmasi` enum('Valid','Invalid')
,`status_pertemuan` enum('Selesai','Belum Selesai','Batal')
,`id_murid` char(7)
,`nama_murid` varchar(50)
,`foto_murid` char(7)
,`id_wali_murid` smallint(5)
,`nama_wali_murid` varchar(50)
,`alamat` text
,`no_hp` varchar(20)
,`id_pengajar` char(7)
,`nama_pengajar` varchar(50)
,`username_pengajar` varchar(50)
,`alamat_pengajar` text
,`no_hp_pengajar` varchar(20)
,`foto_pengajar` char(7)
,`id_kelas_pertemuan` char(7)
,`hari_kelas_pertemuan` varchar(50)
,`jam_mulai` time
,`jam_berakhir` time
,`id_mata_pelajaran` smallint(5)
,`nama_mata_pelajaran` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_kelas_pertemuan`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_kelas_pertemuan` (
`id_kelas_pertemuan` char(7)
,`hari` varchar(50)
,`jam_mulai` time
,`jam_berakhir` time
,`harga_fee` int(10)
,`harga_spp` int(10)
,`id_sharing` char(7)
,`nama_sharing` varchar(50)
,`status_data` enum('active','inactive')
,`id_mata_pelajaran` smallint(5)
,`nama_mata_pelajaran` varchar(50)
,`id_pengajar` char(7)
,`nama_pengajar` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_kelas_pertemuan_detail`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_kelas_pertemuan_detail` (
`id_kelas_pertemuan_detail` int(6)
,`status_data` enum('active','inactive')
,`id_kelas_pertemuan` char(7)
,`id_murid` char(7)
,`nama` varchar(50)
,`foto` char(7)
,`id_wali_murid` smallint(5)
,`nama_wali_murid` varchar(50)
,`username` varchar(50)
,`alamat` text
,`no_hp` varchar(20)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_murid`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_murid` (
`id_murid` char(7)
,`nama` varchar(50)
,`foto` char(7)
,`status_data` enum('active','inactive')
,`id_wali_murid` smallint(5)
,`nama_wali_murid` varchar(50)
,`username` varchar(50)
,`alamat` text
,`no_hp` varchar(20)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_pertemuan`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_pertemuan` (
`id_pertemuan` char(13)
,`hari_pertemuan` varchar(50)
,`waktu_mulai` datetime
,`waktu_berakhir` datetime
,`lokasi_mulai_la` text
,`lokasi_mulai_lo` text
,`lokasi_berakhir_la` text
,`lokasi_berakhir_lo` text
,`deskripsi` text
,`harga_fee` int(10)
,`harga_spp` int(10)
,`status_fee` enum('Belum Terbayar','Sudah Terbayar')
,`status_spp` enum('Belum Lunas','Sudah Lunas')
,`status_konfirmasi` enum('Valid','Invalid')
,`status_pertemuan` enum('Selesai','Belum Selesai','Batal')
,`id_pengajar` char(7)
,`nama_pengajar` varchar(50)
,`username_pengajar` varchar(50)
,`alamat_pengajar` text
,`no_hp_pengajar` varchar(20)
,`foto_pengajar` char(7)
,`id_kelas_pertemuan` char(7)
,`hari_kelas_pertemuan` varchar(50)
,`jam_mulai` time
,`jam_berakhir` time
,`id_mata_pelajaran` smallint(5)
,`nama_mata_pelajaran` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_pertemuan_detail`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_pertemuan_detail` (
`id_pertemuan_detail` int(6)
,`status_spp_detail` enum('Belum Lunas','Sudah Lunas')
,`id_pertemuan` char(13)
,`hari_pertemuan` varchar(50)
,`waktu_mulai` datetime
,`waktu_berakhir` datetime
,`lokasi_mulai_la` text
,`lokasi_mulai_lo` text
,`lokasi_berakhir_la` text
,`lokasi_berakhir_lo` text
,`deskripsi` text
,`harga_fee` int(10)
,`harga_spp` int(10)
,`status_fee` enum('Belum Terbayar','Sudah Terbayar')
,`status_spp` enum('Belum Lunas','Sudah Lunas')
,`status_konfirmasi` enum('Valid','Invalid')
,`status_pertemuan` enum('Selesai','Belum Selesai','Batal')
,`id_pengajar` char(7)
,`nama_pengajar` varchar(50)
,`username_pengajar` varchar(50)
,`alamat_pengajar` text
,`no_hp_pengajar` varchar(20)
,`foto_pengajar` char(7)
,`id_kelas_pertemuan` char(7)
,`hari_kelas_pertemuan` varchar(50)
,`jam_mulai` time
,`jam_berakhir` time
,`id_mata_pelajaran` smallint(5)
,`nama_mata_pelajaran` varchar(50)
,`id_murid` char(7)
,`nama_siswa` varchar(50)
,`foto` char(7)
,`id_wali_murid` smallint(5)
,`nama_wali_murid` varchar(50)
,`alamat` text
,`no_hp` varchar(20)
);

-- --------------------------------------------------------

--
-- Struktur dari tabel `wali_murid`
--

CREATE TABLE `wali_murid` (
  `id_wali_murid` smallint(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` char(60) NOT NULL,
  `alamat` text NOT NULL,
  `no_hp` varchar(20) NOT NULL,
  `status_data` enum('active','inactive') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `wali_murid`
--

INSERT INTO `wali_murid` (`id_wali_murid`, `nama`, `username`, `password`, `alamat`, `no_hp`, `status_data`) VALUES
(1, 'Mama Kehin Hoo', 'mamakehinhoo', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(2, 'Mama Christo', 'mamachristo', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(3, 'Mama Alice', 'mamaalice', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(4, 'Mama Michelle dan Meme', 'mamamichelle', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(5, 'Mama Keiza', 'mamakeiza', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(6, 'Mama Jessyln', 'mamajessyln', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(7, 'Mama Carlos', 'mamacarlos', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(8, 'Mama Denaya', 'mamadenaya', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(9, 'Mama Janiece', 'mamajaniece', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(10, 'Mama Kayla', 'mamakayla', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(11, 'Mama Alin', 'mamaalin', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(12, 'Mama Lody', 'mamalody', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(13, 'Mama Matthew', 'mamamatthew', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(14, 'Mama Kevin', 'mamakevin', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(15, 'Mama Feli dan Grace', 'mamafeli', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(16, 'Mama Kevin Rh dan Lydia Rh', 'mamakevinrh', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(17, 'Mama Evan', 'mamaevan', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(18, 'Mama Ivan', 'mamaivan', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active'),
(19, 'Mama Gwen', 'mamagwen', '$2y$10$HWrk7j1HgJBhgIjg5frwkOpFkEZgpBnVmI2RjND2F4hYtIGG9hoGm', 'jember', '0', 'active');

-- --------------------------------------------------------

--
-- Struktur untuk view `view_bayar_fee`
--
DROP TABLE IF EXISTS `view_bayar_fee`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `view_bayar_fee`  AS  select `bf`.`id_bayar_fee` AS `id_bayar_fee`,`bf`.`waktu` AS `waktu`,`bf`.`total_pertemuan` AS `total_pertemuan`,`bf`.`total_harga_fee` AS `total_harga_fee`,`bf`.`status_data` AS `status_data`,`p`.`id_pengajar` AS `id_pengajar`,`p`.`nama` AS `nama_pengajar`,`a`.`id_admin` AS `id_admin`,`a`.`nama` AS `nama_admin` from ((`bayar_fee` `bf` join `pengajar` `p` on(`bf`.`id_pengajar` = `p`.`id_pengajar`)) join `admin` `a` on(`bf`.`id_admin` = `a`.`id_admin`)) order by `bf`.`waktu` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_bayar_fee_detail`
--
DROP TABLE IF EXISTS `view_bayar_fee_detail`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `view_bayar_fee_detail`  AS  select `bfd`.`id_bayar_fee_detail` AS `id_bayar_fee_detail`,`bfd`.`id_bayar_fee` AS `id_bayar_fee`,`p`.`id_pertemuan` AS `id_pertemuan`,`p`.`hari` AS `hari_pertemuan`,`p`.`waktu_mulai` AS `waktu_mulai`,`p`.`waktu_berakhir` AS `waktu_berakhir`,`p`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`p`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`p`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`p`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`p`.`deskripsi` AS `deskripsi`,`p`.`harga_fee` AS `harga_fee`,`p`.`harga_spp` AS `harga_spp`,`p`.`status_fee` AS `status_fee`,`p`.`status_spp` AS `status_spp`,`p`.`status_konfirmasi` AS `status_konfirmasi`,`p`.`status_pertemuan` AS `status_pertemuan`,`pe`.`id_pengajar` AS `id_pengajar`,`pe`.`nama` AS `nama_pengajar`,`pe`.`username` AS `username_pengajar`,`pe`.`alamat` AS `alamat_pengajar`,`pe`.`no_hp` AS `no_hp_pengajar`,`pe`.`foto` AS `foto_pengajar`,`kp`.`id_kelas_pertemuan` AS `id_kelas_pertemuan`,`kp`.`hari` AS `hari_kelas_pertemuan`,`kp`.`jam_mulai` AS `jam_mulai`,`kp`.`jam_berakhir` AS `jam_berakhir`,`mp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`mp`.`nama` AS `nama_mata_pelajaran` from ((((`bayar_fee_detail` `bfd` join `pertemuan` `p` on(`bfd`.`id_pertemuan` = `p`.`id_pertemuan`)) join `pengajar` `pe` on(`p`.`id_pengajar` = `pe`.`id_pengajar`)) join `kelas_pertemuan` `kp` on(`p`.`id_kelas_pertemuan` = `kp`.`id_kelas_pertemuan`)) join `mata_pelajaran` `mp` on(`kp`.`id_mata_pelajaran` = `mp`.`id_mata_pelajaran`)) order by `p`.`waktu_mulai` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_bayar_spp`
--
DROP TABLE IF EXISTS `view_bayar_spp`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `view_bayar_spp`  AS  select `bs`.`id_bayar_spp` AS `id_bayar_spp`,`bs`.`waktu` AS `waktu`,`bs`.`total_pertemuan` AS `total_pertemuan`,`bs`.`total_harga_spp` AS `total_harga_spp`,`bs`.`status_data` AS `status_data`,`wm`.`id_wali_murid` AS `id_wali_murid`,`wm`.`nama` AS `nama_wali_murid`,`a`.`id_admin` AS `id_admin`,`a`.`nama` AS `nama_admin` from ((`bayar_spp` `bs` join `wali_murid` `wm` on(`bs`.`id_wali_murid` = `wm`.`id_wali_murid`)) join `admin` `a` on(`bs`.`id_admin` = `a`.`id_admin`)) order by `bs`.`waktu` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_bayar_spp_detail`
--
DROP TABLE IF EXISTS `view_bayar_spp_detail`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `view_bayar_spp_detail`  AS  select `bsd`.`id_bayar_spp_detail` AS `id_bayar_spp_detail`,`bsd`.`id_bayar_spp` AS `id_bayar_spp`,`pd`.`id_pertemuan_detail` AS `id_pertemuan_detail`,`pd`.`status_spp_detail` AS `status_spp_detail`,`p`.`id_pertemuan` AS `id_pertemuan`,`p`.`hari` AS `hari_pertemuan`,`p`.`waktu_mulai` AS `waktu_mulai`,`p`.`waktu_berakhir` AS `waktu_berakhir`,`p`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`p`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`p`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`p`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`p`.`deskripsi` AS `deskripsi`,`p`.`harga_fee` AS `harga_fee`,`p`.`harga_spp` AS `harga_spp`,`p`.`status_fee` AS `status_fee`,`p`.`status_spp` AS `status_spp`,`p`.`status_konfirmasi` AS `status_konfirmasi`,`p`.`status_pertemuan` AS `status_pertemuan`,`m`.`id_murid` AS `id_murid`,`m`.`nama` AS `nama_murid`,`m`.`foto` AS `foto_murid`,`wm`.`id_wali_murid` AS `id_wali_murid`,`wm`.`nama` AS `nama_wali_murid`,`wm`.`alamat` AS `alamat`,`wm`.`no_hp` AS `no_hp`,`pe`.`id_pengajar` AS `id_pengajar`,`pe`.`nama` AS `nama_pengajar`,`pe`.`username` AS `username_pengajar`,`pe`.`alamat` AS `alamat_pengajar`,`pe`.`no_hp` AS `no_hp_pengajar`,`pe`.`foto` AS `foto_pengajar`,`kp`.`id_kelas_pertemuan` AS `id_kelas_pertemuan`,`kp`.`hari` AS `hari_kelas_pertemuan`,`kp`.`jam_mulai` AS `jam_mulai`,`kp`.`jam_berakhir` AS `jam_berakhir`,`mp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`mp`.`nama` AS `nama_mata_pelajaran` from (((((((`bayar_spp_detail` `bsd` join `pertemuan_detail` `pd` on(`bsd`.`id_pertemuan_detail` = `pd`.`id_pertemuan_detail`)) join `pertemuan` `p` on(`pd`.`id_pertemuan` = `p`.`id_pertemuan`)) join `murid` `m` on(`pd`.`id_murid` = `m`.`id_murid`)) join `wali_murid` `wm` on(`m`.`id_wali_murid` = `wm`.`id_wali_murid`)) join `pengajar` `pe` on(`p`.`id_pengajar` = `pe`.`id_pengajar`)) join `kelas_pertemuan` `kp` on(`p`.`id_kelas_pertemuan` = `kp`.`id_kelas_pertemuan`)) join `mata_pelajaran` `mp` on(`kp`.`id_mata_pelajaran` = `mp`.`id_mata_pelajaran`)) order by `p`.`waktu_mulai` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_kelas_pertemuan`
--
DROP TABLE IF EXISTS `view_kelas_pertemuan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `view_kelas_pertemuan`  AS  select `kp`.`id_kelas_pertemuan` AS `id_kelas_pertemuan`,`kp`.`hari` AS `hari`,`kp`.`jam_mulai` AS `jam_mulai`,`kp`.`jam_berakhir` AS `jam_berakhir`,`kp`.`harga_fee` AS `harga_fee`,`kp`.`harga_spp` AS `harga_spp`,`kp`.`id_sharing` AS `id_sharing`,`kp`.`nama_sharing` AS `nama_sharing`,`kp`.`status_data` AS `status_data`,`mp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`mp`.`nama` AS `nama_mata_pelajaran`,`p`.`id_pengajar` AS `id_pengajar`,`p`.`nama` AS `nama_pengajar` from ((`kelas_pertemuan` `kp` join `mata_pelajaran` `mp` on(`kp`.`id_mata_pelajaran` = `mp`.`id_mata_pelajaran`)) join `pengajar` `p` on(`kp`.`id_pengajar` = `p`.`id_pengajar`)) order by `kp`.`id_kelas_pertemuan` ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_kelas_pertemuan_detail`
--
DROP TABLE IF EXISTS `view_kelas_pertemuan_detail`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `view_kelas_pertemuan_detail`  AS  select `kpd`.`id_kelas_pertemuan_detail` AS `id_kelas_pertemuan_detail`,`kpd`.`status_data` AS `status_data`,`kp`.`id_kelas_pertemuan` AS `id_kelas_pertemuan`,`m`.`id_murid` AS `id_murid`,`m`.`nama` AS `nama`,`m`.`foto` AS `foto`,`wm`.`id_wali_murid` AS `id_wali_murid`,`wm`.`nama` AS `nama_wali_murid`,`wm`.`username` AS `username`,`wm`.`alamat` AS `alamat`,`wm`.`no_hp` AS `no_hp` from (((`kelas_pertemuan_detail` `kpd` join `kelas_pertemuan` `kp` on(`kpd`.`id_kelas_pertemuan` = `kp`.`id_kelas_pertemuan`)) join `murid` `m` on(`kpd`.`id_murid` = `m`.`id_murid`)) join `wali_murid` `wm` on(`m`.`id_wali_murid` = `wm`.`id_wali_murid`)) order by `kpd`.`id_kelas_pertemuan_detail` ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_murid`
--
DROP TABLE IF EXISTS `view_murid`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `view_murid`  AS  select `m`.`id_murid` AS `id_murid`,`m`.`nama` AS `nama`,`m`.`foto` AS `foto`,`m`.`status_data` AS `status_data`,`wm`.`id_wali_murid` AS `id_wali_murid`,`wm`.`nama` AS `nama_wali_murid`,`wm`.`username` AS `username`,`wm`.`alamat` AS `alamat`,`wm`.`no_hp` AS `no_hp` from (`murid` `m` join `wali_murid` `wm` on(`m`.`id_wali_murid` = `wm`.`id_wali_murid`)) order by `m`.`nama` ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_pertemuan`
--
DROP TABLE IF EXISTS `view_pertemuan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `view_pertemuan`  AS  select `p`.`id_pertemuan` AS `id_pertemuan`,`p`.`hari` AS `hari_pertemuan`,`p`.`waktu_mulai` AS `waktu_mulai`,`p`.`waktu_berakhir` AS `waktu_berakhir`,`p`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`p`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`p`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`p`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`p`.`deskripsi` AS `deskripsi`,`p`.`harga_fee` AS `harga_fee`,`p`.`harga_spp` AS `harga_spp`,`p`.`status_fee` AS `status_fee`,`p`.`status_spp` AS `status_spp`,`p`.`status_konfirmasi` AS `status_konfirmasi`,`p`.`status_pertemuan` AS `status_pertemuan`,`pe`.`id_pengajar` AS `id_pengajar`,`pe`.`nama` AS `nama_pengajar`,`pe`.`username` AS `username_pengajar`,`pe`.`alamat` AS `alamat_pengajar`,`pe`.`no_hp` AS `no_hp_pengajar`,`pe`.`foto` AS `foto_pengajar`,`kp`.`id_kelas_pertemuan` AS `id_kelas_pertemuan`,`kp`.`hari` AS `hari_kelas_pertemuan`,`kp`.`jam_mulai` AS `jam_mulai`,`kp`.`jam_berakhir` AS `jam_berakhir`,`mp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`mp`.`nama` AS `nama_mata_pelajaran` from (((`pertemuan` `p` join `pengajar` `pe` on(`p`.`id_pengajar` = `pe`.`id_pengajar`)) join `kelas_pertemuan` `kp` on(`p`.`id_kelas_pertemuan` = `kp`.`id_kelas_pertemuan`)) join `mata_pelajaran` `mp` on(`kp`.`id_mata_pelajaran` = `mp`.`id_mata_pelajaran`)) order by `p`.`waktu_mulai` desc ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_pertemuan_detail`
--
DROP TABLE IF EXISTS `view_pertemuan_detail`;

CREATE ALGORITHM=UNDEFINED DEFINER=`bigstars`@`localhost` SQL SECURITY DEFINER VIEW `view_pertemuan_detail`  AS  select `pd`.`id_pertemuan_detail` AS `id_pertemuan_detail`,`pd`.`status_spp_detail` AS `status_spp_detail`,`p`.`id_pertemuan` AS `id_pertemuan`,`p`.`hari` AS `hari_pertemuan`,`p`.`waktu_mulai` AS `waktu_mulai`,`p`.`waktu_berakhir` AS `waktu_berakhir`,`p`.`lokasi_mulai_la` AS `lokasi_mulai_la`,`p`.`lokasi_mulai_lo` AS `lokasi_mulai_lo`,`p`.`lokasi_berakhir_la` AS `lokasi_berakhir_la`,`p`.`lokasi_berakhir_lo` AS `lokasi_berakhir_lo`,`p`.`deskripsi` AS `deskripsi`,`p`.`harga_fee` AS `harga_fee`,`p`.`harga_spp` AS `harga_spp`,`p`.`status_fee` AS `status_fee`,`p`.`status_spp` AS `status_spp`,`p`.`status_konfirmasi` AS `status_konfirmasi`,`p`.`status_pertemuan` AS `status_pertemuan`,`pe`.`id_pengajar` AS `id_pengajar`,`pe`.`nama` AS `nama_pengajar`,`pe`.`username` AS `username_pengajar`,`pe`.`alamat` AS `alamat_pengajar`,`pe`.`no_hp` AS `no_hp_pengajar`,`pe`.`foto` AS `foto_pengajar`,`kp`.`id_kelas_pertemuan` AS `id_kelas_pertemuan`,`kp`.`hari` AS `hari_kelas_pertemuan`,`kp`.`jam_mulai` AS `jam_mulai`,`kp`.`jam_berakhir` AS `jam_berakhir`,`mp`.`id_mata_pelajaran` AS `id_mata_pelajaran`,`mp`.`nama` AS `nama_mata_pelajaran`,`m`.`id_murid` AS `id_murid`,`m`.`nama` AS `nama_siswa`,`m`.`foto` AS `foto`,`wm`.`id_wali_murid` AS `id_wali_murid`,`wm`.`nama` AS `nama_wali_murid`,`wm`.`alamat` AS `alamat`,`wm`.`no_hp` AS `no_hp` from ((((((`pertemuan_detail` `pd` join `pertemuan` `p` on(`pd`.`id_pertemuan` = `p`.`id_pertemuan`)) join `pengajar` `pe` on(`p`.`id_pengajar` = `pe`.`id_pengajar`)) join `kelas_pertemuan` `kp` on(`p`.`id_kelas_pertemuan` = `kp`.`id_kelas_pertemuan`)) join `mata_pelajaran` `mp` on(`kp`.`id_mata_pelajaran` = `mp`.`id_mata_pelajaran`)) join `murid` `m` on(`pd`.`id_murid` = `m`.`id_murid`)) join `wali_murid` `wm` on(`m`.`id_wali_murid` = `wm`.`id_wali_murid`)) order by `pd`.`id_pertemuan_detail` ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indeks untuk tabel `bayar_fee`
--
ALTER TABLE `bayar_fee`
  ADD PRIMARY KEY (`id_bayar_fee`);

--
-- Indeks untuk tabel `bayar_fee_detail`
--
ALTER TABLE `bayar_fee_detail`
  ADD PRIMARY KEY (`id_bayar_fee_detail`);

--
-- Indeks untuk tabel `bayar_spp`
--
ALTER TABLE `bayar_spp`
  ADD PRIMARY KEY (`id_bayar_spp`);

--
-- Indeks untuk tabel `bayar_spp_detail`
--
ALTER TABLE `bayar_spp_detail`
  ADD PRIMARY KEY (`id_bayar_spp_detail`);

--
-- Indeks untuk tabel `kelas_pertemuan`
--
ALTER TABLE `kelas_pertemuan`
  ADD PRIMARY KEY (`id_kelas_pertemuan`);

--
-- Indeks untuk tabel `kelas_pertemuan_detail`
--
ALTER TABLE `kelas_pertemuan_detail`
  ADD PRIMARY KEY (`id_kelas_pertemuan_detail`);

--
-- Indeks untuk tabel `mata_pelajaran`
--
ALTER TABLE `mata_pelajaran`
  ADD PRIMARY KEY (`id_mata_pelajaran`);

--
-- Indeks untuk tabel `murid`
--
ALTER TABLE `murid`
  ADD PRIMARY KEY (`id_murid`),
  ADD KEY `id_wali_murid` (`id_wali_murid`);

--
-- Indeks untuk tabel `pengajar`
--
ALTER TABLE `pengajar`
  ADD PRIMARY KEY (`id_pengajar`);

--
-- Indeks untuk tabel `pertemuan`
--
ALTER TABLE `pertemuan`
  ADD PRIMARY KEY (`id_pertemuan`),
  ADD KEY `id_pengajar` (`id_pengajar`,`id_kelas_pertemuan`);

--
-- Indeks untuk tabel `pertemuan_detail`
--
ALTER TABLE `pertemuan_detail`
  ADD PRIMARY KEY (`id_pertemuan_detail`);

--
-- Indeks untuk tabel `wali_murid`
--
ALTER TABLE `wali_murid`
  ADD PRIMARY KEY (`id_wali_murid`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `bayar_fee_detail`
--
ALTER TABLE `bayar_fee_detail`
  MODIFY `id_bayar_fee_detail` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `bayar_spp_detail`
--
ALTER TABLE `bayar_spp_detail`
  MODIFY `id_bayar_spp_detail` int(6) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `kelas_pertemuan_detail`
--
ALTER TABLE `kelas_pertemuan_detail`
  MODIFY `id_kelas_pertemuan_detail` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT untuk tabel `mata_pelajaran`
--
ALTER TABLE `mata_pelajaran`
  MODIFY `id_mata_pelajaran` smallint(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `pertemuan_detail`
--
ALTER TABLE `pertemuan_detail`
  MODIFY `id_pertemuan_detail` int(6) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `wali_murid`
--
ALTER TABLE `wali_murid`
  MODIFY `id_wali_murid` smallint(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
