-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 09 Nov 2020 pada 12.32
-- Versi server: 10.2.33-MariaDB-cll-lve
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
(1, 'All Mapel', 'active'),
(2, 'B Inggris', 'active');

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
('MR00001', 1, 'Carlos', 'NONE', 'active'),
('MR00002', 1, 'Kevin SDK', 'NONE', 'active'),
('MR00003', 1, 'Michelle', 'NONE', 'active'),
('MR00004', 1, 'Heaven', 'NONE', 'active'),
('MR00005', 1, 'Kevin RH', 'NONE', 'active'),
('MR00006', 1, 'Lydia', 'NONE', 'active'),
('MR00007', 1, 'Electra', 'NONE', 'active'),
('MR00008', 1, 'Jenice', 'NONE', 'active'),
('MR00009', 1, 'Kayla', 'NONE', 'active'),
('MR00010', 1, 'Fael', 'NONE', 'active'),
('MR00011', 1, 'Tirza', 'NONE', 'active'),
('MR00012', 1, 'Feli', 'NONE', 'active'),
('MR00013', 1, 'Grace Avarina', 'NONE', 'active'),
('MR00014', 1, 'Rangga', 'NONE', 'active'),
('MR00015', 1, 'Tata', 'NONE', 'active'),
('MR00016', 1, 'Gwen', 'NONE', 'active'),
('MR00017', 1, 'Matthew', 'NONE', 'active'),
('MR00018', 1, 'Irish', 'NONE', 'active'),
('MR00019', 1, 'Evan', 'NONE', 'active'),
('MR00020', 1, 'Ivan', 'NONE', 'active'),
('MR00021', 1, 'Twins', 'NONE', 'active'),
('MR00022', 1, 'Zea', 'NONE', 'active'),
('MR00023', 1, 'Rei', 'NONE', 'active'),
('MR00024', 1, 'Alice atau Christo', 'NONE', 'active'),
('MR00025', 1, 'Alice & Christo', 'NONE', 'active'),
('MR00026', 1, 'Michael PH', 'NONE', 'active'),
('MR00027', 1, 'Michael SBH', 'NONE', 'active'),
('MR00028', 1, 'Gladys', 'NONE', 'active'),
('MR00029', 1, 'Grace Amanda', 'NONE', 'active'),
('MR00030', 1, 'Alin', 'NONE', 'active'),
('MR00041', 1, 'Vino', 'NONE', 'active'),
('MR00042', 1, 'Atma', 'NONE', 'active'),
('MR00043', 1, 'Razan', 'NONE', 'active'),
('MR00044', 1, 'Marco', 'NONE', 'active'),
('MR00045', 1, 'Jason', 'NONE', 'active'),
('MR00046', 1, 'Devin', 'NONE', 'active'),
('MR00047', 1, 'Keno', 'NONE', 'active'),
('MR00048', 1, 'Alhabsi', 'NONE', 'active'),
('MR00049', 1, 'Azzahra', 'NONE', 'active'),
('MR00050', 1, 'Vania', 'NONE', 'active'),
('MR00051', 1, 'Jeslin', 'NONE', 'active'),
('MR00052', 1, 'Geo', 'NONE', 'active'),
('MR00053', 1, 'Aafiyah', 'NONE', 'active'),
('MR00054', 1, 'Lili', 'NONE', 'active'),
('MR00055', 1, 'Stanley', 'NONE', 'active'),
('MR00056', 1, 'Rafael', 'NONE', 'active'),
('MR00057', 1, 'lyvia', 'NONE', 'active');

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
('PE00001', 'Miss Novi', 'novibs', '$2y$10$wNmdFD00C/lAMCXkEcBuVOZ4OAhmwgohqojBnjFjDIFuv7UV.34FK', 'Jl. Kalimantan', '089624941716', 'NONE', 'active'),
('PE00002', 'Mr Anton', 'antonbs', '$2y$10$huumbKwGiq5omCcMZaTGV.4YZcDuHuYOYAVii1Lz/hNgo7mlDmH42', 'Jl. Mastrip', '082234164472', 'NONE', 'active'),
('PE00003', 'Miss Beta', 'betabs', '$2y$10$H.FXrfbrvAAmlKj79g8zw.e3PnouPKKCmn02Bp9ZHRizigGLj75CG', 'Jl. Jawa', '082231936063', 'NONE', 'active'),
('PE00004', 'Miss Della', 'dellabs', '$2y$10$S2rOSnOrlZqzovPZkc5ihuIDOQ1J8AjsbjIeOIVzsz.F.gNmg/1GO', 'Gebang Jember', '081231251178', 'NONE', 'active'),
('PE00005', 'Miss Devi', 'devibs', '$2y$10$d6LZmi8ue.ItzVgpaxJc4OBOU1YiFU7iyiwOzKbsihKyN0Ajgq1ly', 'Jl. Karimata', '081232383608', 'NONE', 'active'),
('PE00006', 'Miss Eka', 'ekabs', '$2y$10$sNQNAxjJAOU1YlNkaKN9euX6tAEZf.Ch5dOdopO2y5BWF/N3ZLM7u', 'Jl. Mastrip', '085259185433', 'NONE', 'active'),
('PE00007', 'Miss Fatimah', 'fatimahbs', '$2y$10$ScM/qe6kDx9eXWPXGJnkauSndnaQOHx5iq.ZZ6Ooq5K3pE7ndmyD6', 'Panti Jember', '082122015820', 'NONE', 'active'),
('PE00008', 'Miss Lela', 'lelabs', '$2y$10$GiWtbVCkEd/7Ajloa7zW9uzD0.mMsXEPfwkEdAu5Kzexhv1P.wEC6', 'Jl. Mojopahit', '085330765833', 'NONE', 'active'),
('PE00009', 'Miss Linda', 'lindabs', '$2y$10$wDPjoryRqs5U3IYrT61ouu1mj7qVUeHThLz4IoeYMn/lq/F/IJjmy', 'Mangli Jember', '089617399346', 'NONE', 'active'),
('PE00010', 'Miss Nona', 'nonabs', '$2y$10$p6cBv1.SyinrZqh16uyv9OGwtkSBDNdpXPoVepTEjP5ESiaZy34pe', 'Jl. Mastrip', '089635753244', 'NONE', 'active'),
('PE00011', 'Miss Nazila', 'nazilabs', '$2y$10$mLGDhCMSvkd8rxbC/U0rDO6HvmNS6Y3OxOrkv2ilBUPBOk/loEczm', 'Patrang Jember', '082132535366', 'NONE', 'active'),
('PE00012', 'Miss Nancy', 'nancybs', '$2y$10$TKyd1xokyPHjBTVfm0pqqu17fropLZ.96iMKcI3RG1sEmo.RgX64i', 'Kreongan Jember', '081357922644', 'NONE', 'active'),
('PE00013', 'Miss Ninis', 'ninisbs', '$2y$10$q7BPLVFwU5P1tqy509SUdOOOKvVeCXyddGdqz3/msoOijU/k6qheO', 'Patrang Jember', '089520900271', 'NONE', 'active'),
('PE00014', 'Miss Nurul', 'nurulbs', '$2y$10$GAZ1bmpkKxd1b1zZ0EJ/9eszeGDK0nLHcHgoQT2eb0fCHKYqML8D2', 'Mangli Jember', '089687141710', 'NONE', 'active'),
('PE00015', 'Miss Rara', 'rarabs', '$2y$10$ZHY69Zz566HQneVfXJeu0.EaJKtiPjNZM9ceVPR8p81/JlTDG2lBG', 'Jember', '085695618541', 'NONE', 'active'),
('PE00016', 'Miss Riska', 'riskabs', '$2y$10$QhcocgpUa3jKBWNy.tOx/OwyHlJoXaOSpVrSHL.zBe7nSS0eOi4uW', 'Jember', '082266626220', 'NONE', 'active'),
('PE00017', 'Miss Titis', 'titisbs', '$2y$10$at.YLkTVdPJoTBrGxjymYOFGucZ1MIHbV9T6kOlJIliDKwUDGHQJK', 'Jember', '081234016761', 'NONE', 'active'),
('PE00018', 'Miss Winnie', 'winniebs', '$2y$10$QKRubNRYe264mGmQWeDspOEChLs2wh.xE/4tcEtBlvFUQmy62Utqe', 'Jember', '-', 'NONE', 'active'),
('PE00019', 'Mr Zulfikar', 'zulfikarbs', '$2y$10$lW5Z8Wg8uFUU2b.5N0.24uDJe2y62quc788EsNHI3fScIKq0qrqG2', 'Antirogo - Jember', '081336414173', 'NONE', 'active'),
('PE00020', 'Miss Alvi', 'alvibs', '$2y$10$oInfKW/5gOH7qcyqlm/O3eIYARgt3W2n/k6o3AtEhblRa8KExBiuq', 'Jember', '08989796916', 'NONE', 'active'),
('PE00021', 'Miss Dita', 'ditabs', '$2y$10$lSFhg.GW5/KgwVjoHzUrSOz8o7/Un3lfBLdGEhJtRH3lXoR4NBMjq', 'Jember', '081333620754', 'NONE', 'active'),
('PE00022', 'Miss Bella', 'bellabs', '$2y$10$mQsAqK1cbrq0cQIJPaACI.ukvLhbX33fKk4QGuyiSUxcuFuJ.GqRu', 'Jember', '082231922238', 'NONE', 'active'),
('PE00023', 'Miss Ayu', 'ayubs', '$2y$10$fxElfiQo4KqPe4gfAuyvEOGgHdfx5b03l.TtM4.uNteqS9kOGht/e', 'Jember', '081917165152', 'NONE', 'active'),
('PE00024', 'Miss Nisa', 'nisabs', '$2y$10$ELpHq6.HbDpKamD75zVAROEGY88m4Su4r2i6dls5vzj4qlZaWnVte', 'Jember', '081233358836', 'NONE', 'active'),
('PE00025', 'Miss Dika', 'dikabs', '$2y$10$ztoVw.5z977AHmmNMmuD.OgeiKQaIJcgwukCx04lR94r8ztt/wA8e', 'Jember', '085204870798', 'NONE', 'active'),
('PE00026', 'Miss Deta', 'detabs', '$2y$10$CT5qcA8I6JjrJLHEuStxuuwV1sYbghW8BH6DZEnX5v636gImrSr2K', 'Jember', '-', 'NONE', 'active'),
('PE00027', 'Miss Febri', 'febribs', '$2y$10$lTNYyL0omGf01BR8j.5ZUewkY41Ss56EZo.qDBQX7/OjX0EqZum6q', 'jember', '083846707992', 'NONE', 'active'),
('PE00028', 'z ini data kosong', 'kaka2', '$2y$10$SbJQBtDj.3OBKoIJYjmJ4OhWjFMoh4UOtwr511Yo2uQbg8GYiMjvC', 'lumajang', '--', 'NONE', 'active');

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
(1, 'Ibu Lia', 'carlosbs', '$2y$10$D3wLXcD1iVCPhtUBz/LQDuenheCk5rNhlSYxuZGyN5I2bHnyKj5BO', 'Kaliwates Jember', '08113572881', 'active'),
(2, 'Mama Kevin dan Lydya', 'kevinrhbs', '$2y$10$gaIu8PxMvn8VJ521hLhMWeRjDQbClydq2W0qdXrS0nowGdD9fBCxu', 'Perumahan gunung batu blok f no.25', '081332159775', 'active'),
(3, 'Mama Kevin SDK', 'kevinsdkbs', '$2y$10$iRivNWs9a14y5Xd1NuOL8e1kDVkQhWQP4Fte6x4NqxI4DHi.xec3e', 'Kaliwates - Jember', '081234566157', 'active'),
(4, 'Mama Michelle', 'michellebs', '$2y$10$Sw8Kx3Txd0xqyoBAg0vy7.F9N19yQup3IiM5mND0MXhMz9M2gJaAy', 'Jl. Diponegoro - Jember', '081217385526', 'active'),
(5, 'Mama Heaven', 'heavenbs', '$2y$10$riFhU7EE4QDCsGXDCajnbezKseGTvENeRd8EuFhKGX0Htizl0zguS', 'Argopuro - Jember', '0811355036', 'active'),
(6, 'Mama Electra Dan Jenice', 'electrabs', '$2y$10$9wXyK/Ju/GWWPPsBrpa9eOgjKPRAgddKoHtuaJQ24q6M65eyzN3eG', 'Electra\nJln.Wijaya Kusuma no.66 Jember', '0811358851', 'active'),
(7, 'Mama Kayla', 'kaylabs', '$2y$10$Ne3y3.IDaEjbkRy8k74RHuW0fJ82R5YpyZUz2Bbe.E60oMGq3wDxy', 'Perum Bukit Permai Pajajaran Cluster No. 1 Jember', '082139377979', 'active'),
(8, 'Mama Fael dan Tirza', 'faelbs', '$2y$10$WRVmZ855sDYtiiHbBLMHVOrBu3Rl4roEJoJ4LU9WbiiBm.W6q/DGy', 'perumahan taman gading ww 9', '081252786950', 'active'),
(9, 'Mama Indah', 'gracebs', '$2y$10$35aPz8Pu6jLpmmsPt3U5quWIRlWEfjYZ5Rk0fEYHdqVZSV8gWt.p6', 'Perum san cefila b36 Jember', '082231264104', 'active'),
(10, 'Mama Rangga', 'ranggabs', '$2y$10$J4dYDUJVvI4GpCm9zKZtyOb4vzHY1qH0BLAfY6mDmzVkkFhYZyhFu', 'Perum Bernadyland cluster magnolia bloe E1 Jl. Cendrawasih Slawu Kec Patrang', '082332424949', 'active'),
(11, 'Mama Tata', 'tatabs', '$2y$10$TbHjL1L3D04GJDLnTg6xq.ii1Ysgwpf4C/y97LhSGbYGonMTxIQvu', 'Gebang Jember', '082139306666', 'active'),
(12, 'Mama Gwen', 'gwenbs', '$2y$10$Aim6V1j33tUixKzoO8l50eFz4ze6GTxNzpbkaYnCYkTg0AHfWKW1C', 'Jl. Dr. Soetomo No. 32 Jember', '081217167483', 'active'),
(13, 'Mama Matthew', 'matthewbs', '$2y$10$EZo7UPOo1w//XgYzpd958OWcQ5jt1Iw6pla7pZ66CWqkMWnI8vMUC', 'Jl letjen suprapto no 25. Jember', '082233573031', 'active'),
(14, 'Mama Irish', 'irishbs', '$2y$10$05gYGrpjvVx8Yl6royLEuuiAJnRl6sr4sLI7daHsdmoYlxPDDq0I6', 'Jember', '081232304439', 'active'),
(15, 'Mama Evan Ivan', 'twinbs', '$2y$10$JB9E9.Yhc9oAHZu4Zzxafej./HC.K7J3cnphllUdCIDtEyzKAqE3O', 'Perum argopuro wa 3 no 7', '082132082710', 'active'),
(16, 'Mama Zea', 'zeabs', '$2y$10$tp9oJ8YYodsPRuzHZ.fjIe91bvPvEvzEOpSpIAhfRQpNxBSJ./6Dq', 'Jl argopuro dusun satrean no 8 M rt 001 rw 008 kel. Rambigundam kec. Rambipuji', '081220161221', 'active'),
(17, 'Mama Alice Christo', 'alicebs', '$2y$10$xKPaF/J/k5N2Y7pQtWqcX.jiH114deil0CHJ6iLdsW.J.uTmIxVqG', 'Jember', '081336322266', 'active'),
(18, 'Mama Michael PH', 'michaelphbs', '$2y$10$ehdxdCdG.IdXxXpFl84JWON6Uh6gkVCcc0N2iu9Y27Ydf0V3QlUIC', 'Jember', '081230469356', 'active'),
(19, 'Mama Michael SBH', 'michaelsbh', '$2y$10$nJ4x9pRwhQNoVoBzjGnac.VtVqvoiE5Th8oHmtr9Eho.G.hUuaYRi', 'Jember', '081232323230', 'active'),
(20, 'Mama Gladys', 'gladysbs', '$2y$10$KkDzImriVWrqIg6I5HB6BOp8I4ekIuulYjNMqKretPqUJXavG6ozq', 'Jln bondoyudo 6 Jember', '08113501688', 'active'),
(21, 'Mama Alin', 'alinbs', '$2y$10$4819s4WD10fouPVhW9ey7ulT4ePYKPYVusn.pL8tpGyveHcFU3g8.', 'Perum Bukit Permai Pajajaran Cluster No. 3 Jember', '082234700724', 'active'),
(22, 'Mama Vino', 'vinobs', '$2y$10$1VNI9eh8oIjV/niA44iLEuJumE.ADFhiYBPCijgS6rDaIxxp6L0Au', 'Jember', '081336844974', 'active'),
(23, 'Mama Adma', 'admabs', '$2y$10$L.xMVBPTmCYjVky4BwQZ8u6D85HSl1NkYS9UNq5/102jfVu7De15C', 'Puri bunga nirwana tebet i-13 Jember', '082236429204', 'active'),
(24, 'Mama Razan', 'razanbs', '$2y$10$Dcnhkp24sHFPLKzLxfpajuriTqRAjkzdPpkV8CalRPpp6tAr2gfoK', 'Jember', '0852496108829', 'active'),
(25, 'Mama Marco', 'marcobs', '$2y$10$6/qYPfTuO87SomsrBOrBAuUvQhMq19MUX9oWbkfSqm8snE926vOIq', 'Jember', '081230860633', 'active'),
(26, 'Mama Devin', 'devinbs', '$2y$10$AjCMnoWkvBzAxQ0rH7G7Duy/fRrhiPiUpsJhyAp7nyvx5csmio8Ze', 'Jalan Majapahit R 25 A', '082231417878', 'active'),
(27, 'Mama Jason', 'jasonbs', '$2y$10$DgxX2C84oY5OFElMjwMQUOpATyWClxXzB7eGm8Gcd28h3WIvFIyrm', 'Jl. Majapahit Di blok EE no 4 Jember', '081221101999', 'active'),
(28, 'Mama Jeslin', 'jesbs', '$2y$10$eI7.zpclWWVzAbrzyc2J2eNXLiJNhS47s7FAreorv0/wvPvx7CQY.', 'Jember', '08123534585', 'active'),
(29, 'alhabsi', 'alhabsibs', '$2y$10$QgDT0.8JqGi6iCZYHCqaCeHPJsDRG3my.PWaO6l3m2iRu0khcbphK', '.', '.', 'active'),
(30, 'Azzahra', 'azzahrabs', '$2y$10$keJRCpN81RfVPC7x8c.HtOgBg/48ekNs6f.dGxS.CANGbhHtm0Q7m', '.', '.', 'active'),
(31, 'Mama Vania', 'vaniabs', '$2y$10$rlAylToWGdkCzwFRfXPOkOAS4mVW88J91.T0jtDJy.M1.F2vJZLAS', 'Jember', '081331908081', 'active'),
(32, 'mama geo', 'geobs', '$2y$10$t0mP.9GYz7FTcTqLY1BD8.5tnCCX4eDP7ifq57ol73odqYZrW5HXe', 'Jl.basuki rahmat 4 no 90 mam', '081346591060', 'active'),
(33, 'Bu Diah (Aafiyah)', 'aafiyahbs', '$2y$10$RlAVSHpXO0UmruZzpRURbOdLDss1eSos3D4as2/sZXiXWc4lSHsg6', 'Perum Tegal Besar Permai  I Blok F4A', '082245241114', 'active'),
(34, 'Mama Stanley', 'stanleybs', '$2y$10$aUpDLbyzzX7ezwH4m1pkouI6VnhxCxcHWbkfR6F0hlztoGqwKYoUS', 'Bumi Kaliwates Jember', '082112479295', 'active'),
(35, 'mama rafael', 'rafaelbs', '$2y$10$vAEsFzlYjDnFkFZB3Y5Nxe.pmUOlXISHZkwVBALa.gjpvarkHfRxm', 'Alamat di Jl nusantara IV/C4D bumi kaliwates Jember', '082333356528', 'active');

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
  MODIFY `id_kelas_pertemuan_detail` int(6) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `mata_pelajaran`
--
ALTER TABLE `mata_pelajaran`
  MODIFY `id_mata_pelajaran` smallint(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `pertemuan_detail`
--
ALTER TABLE `pertemuan_detail`
  MODIFY `id_pertemuan_detail` int(6) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `wali_murid`
--
ALTER TABLE `wali_murid`
  MODIFY `id_wali_murid` smallint(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
