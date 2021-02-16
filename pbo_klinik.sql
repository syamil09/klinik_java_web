-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 16, 2021 at 01:58 AM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pbo_klinik`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `addUser` (IN `nama_user` VARCHAR(50), IN `password` VARCHAR(200), IN `no_ktp` VARCHAR(20), IN `alamat` VARCHAR(70), IN `no_hp` VARCHAR(20), IN `id_role` VARCHAR(2), IN `aktif` ENUM('Y','T'), IN `id_user` VARCHAR(6))  NO SQL
BEGIN
INSERT INTO user (nama_user,password,no_ktp,alamat,no_hp,id_role,aktif,id_user) VALUES (nama_user,password,no_ktp,alamat,no_hp,id_role,aktif,id_user);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUser` (IN `id` VARCHAR(6))  NO SQL
BEGIN
	UPDATE user SET aktif="T" WHERE id_user=id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUsers` ()  BEGIN
	SELECT * FROM user;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateUser` (IN `nama` VARCHAR(50), IN `password` VARCHAR(200), IN `no_ktp` VARCHAR(20), IN `alamat` VARCHAR(70), IN `no_hp` VARCHAR(20), IN `id_role` VARCHAR(2), IN `aktif` VARCHAR(1), IN `id` VARCHAR(6))  NO SQL
BEGIN
UPDATE user SET nama_user=nama, 
				password=password, 
                no_ktp=no_ktp, 
                alamat=alamat, 
                no_hp=no_hp, 
                id_role=id_role, 
                aktif=aktif
          	WHERE id_user=id;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `bayar_layanan`
--

CREATE TABLE `bayar_layanan` (
  `id_bayar_layanan` varchar(6) NOT NULL,
  `id_layanan` varchar(3) NOT NULL,
  `id_detail_layanan` varchar(3) NOT NULL,
  `id_pasien` varchar(6) NOT NULL,
  `tgl_layanan` date NOT NULL,
  `keterangan` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bayar_obat`
--

CREATE TABLE `bayar_obat` (
  `id_pembayaran` varchar(10) NOT NULL,
  `tgl_pembayaran` date NOT NULL,
  `id_pasien` varchar(10) NOT NULL,
  `id_resep` varchar(10) NOT NULL,
  `jenis_pembayaran` enum('Cash','e-trans','bpjs') NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detail_bayar_obat`
--

CREATE TABLE `detail_bayar_obat` (
  `id_pembayaran` varchar(10) NOT NULL,
  `id_obat` varchar(8) NOT NULL,
  `harga` decimal(12,2) NOT NULL,
  `jumlah` decimal(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detail_layanan`
--

CREATE TABLE `detail_layanan` (
  `id_layanan` varchar(3) NOT NULL,
  `id_detail_layanan` varchar(3) NOT NULL,
  `des_detail_layanan` varchar(70) NOT NULL,
  `biaya_layanan` decimal(16,2) NOT NULL,
  `keterangan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detail_resep`
--

CREATE TABLE `detail_resep` (
  `id_resep` varchar(6) NOT NULL,
  `id_obat` varchar(6) NOT NULL,
  `harga` decimal(12,2) NOT NULL,
  `jumlah` decimal(7,2) NOT NULL,
  `keterangan` varchar(50) NOT NULL,
  `user_id` varchar(10) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `dokter`
--

CREATE TABLE `dokter` (
  `id_dokter` varchar(3) NOT NULL,
  `nama_dokter` varchar(50) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `id_poli` varchar(2) NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `alamat` varchar(70) NOT NULL,
  `no_hp` varchar(25) NOT NULL,
  `no_ktp` varchar(20) NOT NULL,
  `spesialis` varchar(40) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(20) NOT NULL,
  `no_npwp` varchar(15) NOT NULL,
  `user_id` varchar(10) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `kamar`
--

CREATE TABLE `kamar` (
  `id_kamar` varchar(6) NOT NULL,
  `nama_ruang` varchar(30) NOT NULL,
  `no_kamar` varchar(4) NOT NULL,
  `kelas` varchar(10) NOT NULL,
  `harga_perhari` decimal(12,2) NOT NULL,
  `des_kamar` varchar(70) NOT NULL,
  `kapasitas` int(2) NOT NULL,
  `terisi` int(2) NOT NULL,
  `status` enum('OK','Full','Dalam Perbaikan') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `id_karyawan` varchar(3) NOT NULL,
  `nama_karyawan` varchar(50) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `bidang_pekerjaan` varchar(20) NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `alamat` varchar(70) NOT NULL,
  `no_hp` varchar(25) NOT NULL,
  `no_ktp` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `no_npwp` text NOT NULL,
  `user_id` varchar(10) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `layanan`
--

CREATE TABLE `layanan` (
  `id_layanan` varchar(3) NOT NULL,
  `des_layanan` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `obat`
--

CREATE TABLE `obat` (
  `id_obat` varchar(8) NOT NULL,
  `nama_obat` varchar(50) NOT NULL,
  `satuan` varchar(20) NOT NULL,
  `stok` decimal(6,2) NOT NULL,
  `harga_jual` decimal(12,2) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pasien`
--

CREATE TABLE `pasien` (
  `id_pasien` varchar(6) NOT NULL,
  `nama_pasien` varchar(50) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `no_ktp` varchar(20) NOT NULL,
  `alamat` varchar(70) NOT NULL,
  `no_hp` varchar(25) NOT NULL,
  `gol_darah` varchar(2) NOT NULL,
  `password` varchar(100) NOT NULL,
  `user_id` varchar(10) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pembelian_obat`
--

CREATE TABLE `pembelian_obat` (
  `id_trans` varchar(20) NOT NULL,
  `id_supplier` varchar(6) NOT NULL,
  `no_faktur` varchar(20) NOT NULL,
  `tgl_faktur` date NOT NULL,
  `id_obat` varchar(8) NOT NULL,
  `harga_beli` decimal(14,2) NOT NULL,
  `jumlah` decimal(6,2) NOT NULL,
  `keterangan` varchar(70) NOT NULL,
  `tgl_expired` date NOT NULL,
  `id_user` varchar(15) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pendaftaran`
--

CREATE TABLE `pendaftaran` (
  `no_antrian` varchar(3) NOT NULL,
  `id_pasien` varchar(6) NOT NULL,
  `id_poli` varchar(2) NOT NULL,
  `tgl_daftar` date NOT NULL,
  `keterangan` varchar(70) NOT NULL,
  `user_id` varchar(10) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `poli`
--

CREATE TABLE `poli` (
  `id_poli` varchar(2) NOT NULL,
  `nama_poli` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rawat_inap`
--

CREATE TABLE `rawat_inap` (
  `id_rawat` varchar(10) NOT NULL,
  `id_pasien` varchar(6) NOT NULL,
  `id_kamar` varchar(6) NOT NULL,
  `nama_ruang` varchar(30) NOT NULL,
  `tgl_cekin` date NOT NULL,
  `tgl_cekout` date NOT NULL,
  `keterangan` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rekam_medik`
--

CREATE TABLE `rekam_medik` (
  `id_pasien` varchar(6) NOT NULL,
  `tgl_daftar` date NOT NULL,
  `id_poli` varchar(2) NOT NULL,
  `tek_darah` varchar(10) NOT NULL,
  `berat` decimal(6,2) NOT NULL,
  `tinggi` decimal(6,2) NOT NULL,
  `keluhan` varchar(100) NOT NULL,
  `tindakan` varchar(100) NOT NULL,
  `saran` varchar(100) NOT NULL,
  `id_dokter` varchar(3) NOT NULL,
  `id_resep` varchar(6) NOT NULL,
  `diagnosa` varchar(30) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `resep`
--

CREATE TABLE `resep` (
  `id_resep` varchar(6) NOT NULL,
  `id_dokter` varchar(3) NOT NULL,
  `tgl_resep` date NOT NULL,
  `id_poli` varchar(2) NOT NULL,
  `user_id` varchar(10) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id_role` varchar(2) NOT NULL,
  `des_role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id_role`, `des_role`) VALUES
('A1', 'admin'),
('A2', 'karyawan');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id_supplier` varchar(6) NOT NULL,
  `nama_suplier` varchar(50) NOT NULL,
  `alamat` varchar(70) NOT NULL,
  `no_telepon` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `user_id` varchar(10) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` varchar(6) NOT NULL,
  `nama_user` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `no_ktp` varchar(20) NOT NULL,
  `alamat` varchar(70) NOT NULL,
  `no_hp` varchar(20) NOT NULL,
  `id_role` varchar(2) NOT NULL,
  `aktif` enum('Y','T') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nama_user`, `password`, `no_ktp`, `alamat`, `no_hp`, `id_role`, `aktif`) VALUES
('US001', 'Budi Aleksander', '$2a$12$NsT/gwZvesjexBMFQhRrGesV6ZVS8n1F0d0kmBQQnpcPXqwzSYnkK', '00218723772', 'Jakarta Timur', '0812387232', 'A1', 'Y'),
('US002', 'Budi Aleksander', '$2a$12$JxzrhdR6kmvhjxRTrpiGM.5/BxnNjCDL6BMHo.Tj4mN1C7ICqpD/u', '00218723772', 'Jakarta Timur', '0812387232', 'A1', 'T');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bayar_layanan`
--
ALTER TABLE `bayar_layanan`
  ADD PRIMARY KEY (`id_bayar_layanan`),
  ADD KEY `id_detail_layanan` (`id_detail_layanan`),
  ADD KEY `bayar_layanan_ibfk_1` (`id_layanan`);

--
-- Indexes for table `bayar_obat`
--
ALTER TABLE `bayar_obat`
  ADD PRIMARY KEY (`id_pembayaran`),
  ADD KEY `id_pasien` (`id_pasien`,`id_resep`),
  ADD KEY `id_resep` (`id_resep`);

--
-- Indexes for table `detail_bayar_obat`
--
ALTER TABLE `detail_bayar_obat`
  ADD PRIMARY KEY (`id_pembayaran`,`id_obat`),
  ADD KEY `id_obat` (`id_obat`);

--
-- Indexes for table `detail_layanan`
--
ALTER TABLE `detail_layanan`
  ADD PRIMARY KEY (`id_layanan`,`id_detail_layanan`);

--
-- Indexes for table `detail_resep`
--
ALTER TABLE `detail_resep`
  ADD PRIMARY KEY (`id_resep`,`id_obat`),
  ADD KEY `id_obat` (`id_obat`);

--
-- Indexes for table `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`id_dokter`),
  ADD KEY `id_poli` (`id_poli`);

--
-- Indexes for table `kamar`
--
ALTER TABLE `kamar`
  ADD PRIMARY KEY (`id_kamar`,`nama_ruang`);

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indexes for table `layanan`
--
ALTER TABLE `layanan`
  ADD PRIMARY KEY (`id_layanan`);

--
-- Indexes for table `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`id_obat`);

--
-- Indexes for table `pasien`
--
ALTER TABLE `pasien`
  ADD PRIMARY KEY (`id_pasien`);

--
-- Indexes for table `pembelian_obat`
--
ALTER TABLE `pembelian_obat`
  ADD PRIMARY KEY (`id_trans`,`id_obat`),
  ADD KEY `id_obat` (`id_obat`),
  ADD KEY `id_supplier` (`id_supplier`);

--
-- Indexes for table `pendaftaran`
--
ALTER TABLE `pendaftaran`
  ADD PRIMARY KEY (`no_antrian`,`id_pasien`,`id_poli`,`tgl_daftar`),
  ADD KEY `id_poli` (`id_poli`),
  ADD KEY `id_pasien` (`id_pasien`);

--
-- Indexes for table `poli`
--
ALTER TABLE `poli`
  ADD PRIMARY KEY (`id_poli`);

--
-- Indexes for table `rawat_inap`
--
ALTER TABLE `rawat_inap`
  ADD PRIMARY KEY (`id_rawat`),
  ADD KEY `id_pasien` (`id_pasien`,`id_kamar`),
  ADD KEY `id_kamar` (`id_kamar`);

--
-- Indexes for table `rekam_medik`
--
ALTER TABLE `rekam_medik`
  ADD PRIMARY KEY (`id_pasien`,`tgl_daftar`,`id_poli`),
  ADD KEY `id_dokter` (`id_dokter`,`id_resep`),
  ADD KEY `id_resep` (`id_resep`),
  ADD KEY `id_poli` (`id_poli`);

--
-- Indexes for table `resep`
--
ALTER TABLE `resep`
  ADD PRIMARY KEY (`id_resep`),
  ADD KEY `id_dokter` (`id_dokter`,`id_poli`),
  ADD KEY `id_poli` (`id_poli`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id_supplier`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bayar_layanan`
--
ALTER TABLE `bayar_layanan`
  ADD CONSTRAINT `bayar_layanan_ibfk_1` FOREIGN KEY (`id_layanan`) REFERENCES `detail_layanan` (`id_layanan`);

--
-- Constraints for table `bayar_obat`
--
ALTER TABLE `bayar_obat`
  ADD CONSTRAINT `bayar_obat_ibfk_1` FOREIGN KEY (`id_resep`) REFERENCES `resep` (`id_resep`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `detail_bayar_obat`
--
ALTER TABLE `detail_bayar_obat`
  ADD CONSTRAINT `detail_bayar_obat_ibfk_1` FOREIGN KEY (`id_pembayaran`) REFERENCES `bayar_obat` (`id_pembayaran`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_bayar_obat_ibfk_2` FOREIGN KEY (`id_obat`) REFERENCES `obat` (`id_obat`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `detail_layanan`
--
ALTER TABLE `detail_layanan`
  ADD CONSTRAINT `detail_layanan_ibfk_1` FOREIGN KEY (`id_layanan`) REFERENCES `layanan` (`id_layanan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `detail_resep`
--
ALTER TABLE `detail_resep`
  ADD CONSTRAINT `detail_resep_ibfk_1` FOREIGN KEY (`id_obat`) REFERENCES `obat` (`id_obat`),
  ADD CONSTRAINT `detail_resep_ibfk_2` FOREIGN KEY (`id_resep`) REFERENCES `resep` (`id_resep`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `dokter`
--
ALTER TABLE `dokter`
  ADD CONSTRAINT `dokter_ibfk_1` FOREIGN KEY (`id_poli`) REFERENCES `poli` (`id_poli`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pembelian_obat`
--
ALTER TABLE `pembelian_obat`
  ADD CONSTRAINT `pembelian_obat_ibfk_1` FOREIGN KEY (`id_obat`) REFERENCES `obat` (`id_obat`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pembelian_obat_ibfk_2` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id_supplier`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pendaftaran`
--
ALTER TABLE `pendaftaran`
  ADD CONSTRAINT `pendaftaran_ibfk_1` FOREIGN KEY (`id_poli`) REFERENCES `poli` (`id_poli`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pendaftaran_ibfk_2` FOREIGN KEY (`id_pasien`) REFERENCES `pasien` (`id_pasien`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rawat_inap`
--
ALTER TABLE `rawat_inap`
  ADD CONSTRAINT `rawat_inap_ibfk_1` FOREIGN KEY (`id_pasien`) REFERENCES `pasien` (`id_pasien`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rawat_inap_ibfk_2` FOREIGN KEY (`id_kamar`) REFERENCES `kamar` (`id_kamar`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rekam_medik`
--
ALTER TABLE `rekam_medik`
  ADD CONSTRAINT `rekam_medik_ibfk_1` FOREIGN KEY (`id_resep`) REFERENCES `resep` (`id_resep`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rekam_medik_ibfk_2` FOREIGN KEY (`id_dokter`) REFERENCES `dokter` (`id_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rekam_medik_ibfk_3` FOREIGN KEY (`id_pasien`) REFERENCES `pasien` (`id_pasien`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rekam_medik_ibfk_4` FOREIGN KEY (`id_poli`) REFERENCES `poli` (`id_poli`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `resep`
--
ALTER TABLE `resep`
  ADD CONSTRAINT `resep_ibfk_1` FOREIGN KEY (`id_dokter`) REFERENCES `dokter` (`id_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `resep_ibfk_2` FOREIGN KEY (`id_poli`) REFERENCES `poli` (`id_poli`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
