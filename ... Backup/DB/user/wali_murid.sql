-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 08 Nov 2020 pada 13.20
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
-- Struktur dari tabel `wali_murid`
--

CREATE TABLE `wali_murid` (
  `id_wali_murid` char(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` char(60) NOT NULL,
  `alamat` text NOT NULL,
  `no_hp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `wali_murid`
--

INSERT INTO `wali_murid` (`nama`, `username`, `password`, `alamat`, `no_hp`) VALUES
('Ibu Lia', 'carlosbs', '$2y$10$D3wLXcD1iVCPhtUBz/LQDuenheCk5rNhlSYxuZGyN5I2bHnyKj5BO', 'Kaliwates Jember', '08113572881'),
('Mama Kevin dan Lydya', 'kevinrhbs', '$2y$10$gaIu8PxMvn8VJ521hLhMWeRjDQbClydq2W0qdXrS0nowGdD9fBCxu', 'Perumahan gunung batu blok f no.25', '081332159775'),
('Mama Kevin SDK', 'kevinsdkbs', '$2y$10$iRivNWs9a14y5Xd1NuOL8e1kDVkQhWQP4Fte6x4NqxI4DHi.xec3e', 'Kaliwates - Jember', '081234566157'),
('Mama Michelle', 'michellebs', '$2y$10$Sw8Kx3Txd0xqyoBAg0vy7.F9N19yQup3IiM5mND0MXhMz9M2gJaAy', 'Jl. Diponegoro - Jember', '081217385526'),
('Mama Heaven', 'heavenbs', '$2y$10$riFhU7EE4QDCsGXDCajnbezKseGTvENeRd8EuFhKGX0Htizl0zguS', 'Argopuro - Jember', '0811355036'),
('Mama Electra Dan Jenice', 'electrabs', '$2y$10$9wXyK/Ju/GWWPPsBrpa9eOgjKPRAgddKoHtuaJQ24q6M65eyzN3eG', 'Electra\nJln.Wijaya Kusuma no.66 Jember', '0811358851'),
('Mama Kayla', 'kaylabs', '$2y$10$Ne3y3.IDaEjbkRy8k74RHuW0fJ82R5YpyZUz2Bbe.E60oMGq3wDxy', 'Perum Bukit Permai Pajajaran Cluster No. 1 Jember', '082139377979'),
('Mama Fael dan Tirza', 'faelbs', '$2y$10$WRVmZ855sDYtiiHbBLMHVOrBu3Rl4roEJoJ4LU9WbiiBm.W6q/DGy', 'perumahan taman gading ww 9', '081252786950'),
('Mama Indah', 'gracebs', '$2y$10$35aPz8Pu6jLpmmsPt3U5quWIRlWEfjYZ5Rk0fEYHdqVZSV8gWt.p6', 'Perum san cefila b36 Jember', '082231264104'),
('Mama Rangga', 'ranggabs', '$2y$10$J4dYDUJVvI4GpCm9zKZtyOb4vzHY1qH0BLAfY6mDmzVkkFhYZyhFu', 'Perum Bernadyland cluster magnolia bloe E1 Jl. Cendrawasih Slawu Kec Patrang', '082332424949'),
('Mama Tata', 'tatabs', '$2y$10$TbHjL1L3D04GJDLnTg6xq.ii1Ysgwpf4C/y97LhSGbYGonMTxIQvu', 'Gebang Jember', '082139306666'),
('Mama Gwen', 'gwenbs', '$2y$10$Aim6V1j33tUixKzoO8l50eFz4ze6GTxNzpbkaYnCYkTg0AHfWKW1C', 'Jl. Dr. Soetomo No. 32 Jember', '081217167483'),
('Mama Matthew', 'matthewbs', '$2y$10$EZo7UPOo1w//XgYzpd958OWcQ5jt1Iw6pla7pZ66CWqkMWnI8vMUC', 'Jl letjen suprapto no 25. Jember', '082233573031'),
('Mama Irish', 'irishbs', '$2y$10$05gYGrpjvVx8Yl6royLEuuiAJnRl6sr4sLI7daHsdmoYlxPDDq0I6', 'Jember', '081232304439'),
('Mama Evan Ivan', 'twinbs', '$2y$10$JB9E9.Yhc9oAHZu4Zzxafej./HC.K7J3cnphllUdCIDtEyzKAqE3O', 'Perum argopuro wa 3 no 7', '082132082710'),
('Mama Zea', 'zeabs', '$2y$10$tp9oJ8YYodsPRuzHZ.fjIe91bvPvEvzEOpSpIAhfRQpNxBSJ./6Dq', 'Jl argopuro dusun satrean no 8 M rt 001 rw 008 kel. Rambigundam kec. Rambipuji', '081220161221'),
('Mama Alice Christo', 'alicebs', '$2y$10$xKPaF/J/k5N2Y7pQtWqcX.jiH114deil0CHJ6iLdsW.J.uTmIxVqG', 'Jember', '081336322266'),
('Mama Michael PH', 'michaelphbs', '$2y$10$ehdxdCdG.IdXxXpFl84JWON6Uh6gkVCcc0N2iu9Y27Ydf0V3QlUIC', 'Jember', '081230469356'),
('Mama Michael SBH', 'michaelsbh', '$2y$10$nJ4x9pRwhQNoVoBzjGnac.VtVqvoiE5Th8oHmtr9Eho.G.hUuaYRi', 'Jember', '081232323230'),
('Mama Gladys', 'gladysbs', '$2y$10$KkDzImriVWrqIg6I5HB6BOp8I4ekIuulYjNMqKretPqUJXavG6ozq', 'Jln bondoyudo 6 Jember', '08113501688'),
('Mama Alin', 'alinbs', '$2y$10$4819s4WD10fouPVhW9ey7ulT4ePYKPYVusn.pL8tpGyveHcFU3g8.', 'Perum Bukit Permai Pajajaran Cluster No. 3 Jember', '082234700724'),
('Mama Vino', 'vinobs', '$2y$10$1VNI9eh8oIjV/niA44iLEuJumE.ADFhiYBPCijgS6rDaIxxp6L0Au', 'Jember', '081336844974'),
('Mama Adma', 'admabs', '$2y$10$L.xMVBPTmCYjVky4BwQZ8u6D85HSl1NkYS9UNq5/102jfVu7De15C', 'Puri bunga nirwana tebet i-13 Jember', '082236429204'),
('Mama Razan', 'razanbs', '$2y$10$Dcnhkp24sHFPLKzLxfpajuriTqRAjkzdPpkV8CalRPpp6tAr2gfoK', 'Jember', '0852496108829'),
('Mama Marco', 'marcobs', '$2y$10$6/qYPfTuO87SomsrBOrBAuUvQhMq19MUX9oWbkfSqm8snE926vOIq', 'Jember', '081230860633'),
('Mama Devin', 'devinbs', '$2y$10$AjCMnoWkvBzAxQ0rH7G7Duy/fRrhiPiUpsJhyAp7nyvx5csmio8Ze', 'Jalan Majapahit R 25 A', '082231417878'),
('Mama Jason', 'jasonbs', '$2y$10$DgxX2C84oY5OFElMjwMQUOpATyWClxXzB7eGm8Gcd28h3WIvFIyrm', 'Jl. Majapahit Di blok EE no 4 Jember', '081221101999'),
('Mama Jeslin', 'jesbs', '$2y$10$eI7.zpclWWVzAbrzyc2J2eNXLiJNhS47s7FAreorv0/wvPvx7CQY.', 'Jember', '08123534585'),
('alhabsi', 'alhabsibs', '$2y$10$QgDT0.8JqGi6iCZYHCqaCeHPJsDRG3my.PWaO6l3m2iRu0khcbphK', '.', '.'),
('Azzahra', 'azzahrabs', '$2y$10$keJRCpN81RfVPC7x8c.HtOgBg/48ekNs6f.dGxS.CANGbhHtm0Q7m', '.', '.'),
('Mama Vania', 'vaniabs', '$2y$10$rlAylToWGdkCzwFRfXPOkOAS4mVW88J91.T0jtDJy.M1.F2vJZLAS', 'Jember', '081331908081'),
('mama geo', 'geobs', '$2y$10$t0mP.9GYz7FTcTqLY1BD8.5tnCCX4eDP7ifq57ol73odqYZrW5HXe', 'Jl.basuki rahmat 4 no 90 mam', '081346591060'),
('Bu Diah (Aafiyah)', 'aafiyahbs', '$2y$10$RlAVSHpXO0UmruZzpRURbOdLDss1eSos3D4as2/sZXiXWc4lSHsg6', 'Perum Tegal Besar Permai  I Blok F4A', '082245241114'),
('Mama Stanley', 'stanleybs', '$2y$10$aUpDLbyzzX7ezwH4m1pkouI6VnhxCxcHWbkfR6F0hlztoGqwKYoUS', 'Bumi Kaliwates Jember', '082112479295'),
('mama rafael', 'rafaelbs', '$2y$10$vAEsFzlYjDnFkFZB3Y5Nxe.pmUOlXISHZkwVBALa.gjpvarkHfRxm', 'Alamat di Jl nusantara IV/C4D bumi kaliwates Jember', '082333356528');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `wali_murid`
--
ALTER TABLE `wali_murid`
  ADD PRIMARY KEY (`id_wali_murid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
