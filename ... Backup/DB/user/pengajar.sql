-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 08 Nov 2020 pada 13.18
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
-- Database: `bigstars_tab_absen`
--

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
('PE00019', 'Zulfikar', 'zulfikarbs', '$2y$10$lW5Z8Wg8uFUU2b.5N0.24uDJe2y62quc788EsNHI3fScIKq0qrqG2', 'Antirogo - Jember', '081336414173', 'NONE', 'active'),
('PE00020', 'Miss Alvi', 'alvibs', '$2y$10$oInfKW/5gOH7qcyqlm/O3eIYARgt3W2n/k6o3AtEhblRa8KExBiuq', 'Jember', '08989796916', 'NONE', 'active'),
('PE00021', 'Miss dita', 'ditabs', '$2y$10$lSFhg.GW5/KgwVjoHzUrSOz8o7/Un3lfBLdGEhJtRH3lXoR4NBMjq', 'Jember', '081333620754', 'NONE', 'active'),
('PE00022', 'Miss Bella', 'bellabs', '$2y$10$mQsAqK1cbrq0cQIJPaACI.ukvLhbX33fKk4QGuyiSUxcuFuJ.GqRu', 'Jember', '082231922238', 'NONE', 'active'),
('PE00023', 'Miss Ayu', 'ayubs', '$2y$10$fxElfiQo4KqPe4gfAuyvEOGgHdfx5b03l.TtM4.uNteqS9kOGht/e', 'Jember', '081917165152', 'NONE', 'active'),
('PE00024', 'Miss Nisa', 'nisabs', '$2y$10$ELpHq6.HbDpKamD75zVAROEGY88m4Su4r2i6dls5vzj4qlZaWnVte', 'Jember', '081233358836', 'NONE', 'active'),
('PE00025', 'Miss Dika', 'dikabs', '$2y$10$ztoVw.5z977AHmmNMmuD.OgeiKQaIJcgwukCx04lR94r8ztt/wA8e', 'Jember', '085204870798', 'NONE', 'active'),
('PE00026', 'Deta', 'detabs', '$2y$10$CT5qcA8I6JjrJLHEuStxuuwV1sYbghW8BH6DZEnX5v636gImrSr2K', 'Jember', '-', 'NONE', 'active'),
('PE00027', 'ms febri', 'febribs', '$2y$10$lTNYyL0omGf01BR8j.5ZUewkY41Ss56EZo.qDBQX7/OjX0EqZum6q', 'jember', '083846707992', 'NONE', 'active'),
('PE00028', 'test', 'kaka2', '$2y$10$SbJQBtDj.3OBKoIJYjmJ4OhWjFMoh4UOtwr511Yo2uQbg8GYiMjvC', 'lumajang', '--', 'NONE', 'active');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `pengajar`
--
ALTER TABLE `pengajar`
  ADD PRIMARY KEY (`id_pengajar`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
