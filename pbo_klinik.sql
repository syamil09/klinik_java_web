-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 26, 2021 at 03:43 AM
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `addBayarLayanan` (IN `id_layanan` VARCHAR(3), IN `id_detail_layanan` VARCHAR(3), IN `id_pasien` VARCHAR(6), IN `tgl_layanan` DATE, IN `keterangan` VARCHAR(70), IN `id` VARCHAR(6))  NO SQL
INSERT INTO bayar_layanan (id_bayar_layanan,id_layanan,id_detail_layanan,id_pasien,tgl_layanan,keterangan)
	VALUES (id,id_layanan,id_detail_layanan,id_pasien,tgl_layanan,keterangan)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addBayarObat` (IN `tgl_pembayaran` DATE, IN `id_pasien` VARCHAR(10), IN `id_resep` VARCHAR(10), IN `jenis_pembayaran` ENUM('BPJS','NON BPJS'), IN `waktu` TIMESTAMP(1), IN `user_id` VARCHAR(10), IN `id` VARCHAR(10))  NO SQL
INSERT INTO bayar_obat (id_pembayaran, tgl_pembayaran, id_pasien, id_resep, jenis_pembayaran, waktu, user_id)
	VALUES (id, tgl_pembayaran, id_pasien, id_resep, jenis_pembayaran, waktu, user_id)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addDetailBayarObat` (IN `id_obat` VARCHAR(8), IN `harga` DECIMAL(12,2), IN `jumlah` DECIMAL(6,2), IN `id` VARCHAR(10))  NO SQL
INSERT INTO detail_bayar_obat
(id_pembayaran, id_obat, harga, jumlah)
VALUES
(id, id_obat, harga, jumlah)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addDetailLayanan` (IN `des_detail_layanan` VARCHAR(70), IN `biaya_layanan` DECIMAL(16,2), IN `keterangan` VARCHAR(50), IN `id_layanan` VARCHAR(3), IN `id_detail` VARCHAR(3))  NO SQL
INSERT INTO detail_layanan (id_layanan,id_detail_layanan,des_detail_layanan,biaya_layanan,keterangan)
	VALUES (id_layanan,id_detail,des_detail_layanan,biaya_layanan,keterangan)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addKamar` (IN `no_kamar` VARCHAR(4), IN `kelas` VARCHAR(10), IN `harga_perhari` DECIMAL(12,2), IN `des_kamar` VARCHAR(70), IN `kapasitas` INT(2), IN `terisi` INT(2), IN `status` ENUM('OK','Full','Dalam Perbaikan'), IN `nama_ruang` VARCHAR(30), IN `id_kamar` VARCHAR(6))  NO SQL
INSERT INTO kamar (id_kamar,nama_ruang,no_kamar,kelas,harga_perhari,des_kamar,kapasitas,terisi,status)
	VALUES (id_kamar,nama_ruang,no_kamar,kelas,harga_perhari,des_kamar,kapasitas,terisi,status)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addKaryawan` (IN `nama` VARCHAR(50), IN `tgl_lahir` DATE, IN `pekerjaan` VARCHAR(20), IN `jenis_kelamin` ENUM('L','P'), IN `alamat` VARCHAR(70), IN `no_hp` VARCHAR(20), IN `no_ktp` VARCHAR(20), IN `email` VARCHAR(20), IN `no_npwp` VARCHAR(30), IN `id_user` VARCHAR(6), IN `id` VARCHAR(6))  NO SQL
INSERT INTO karyawan (id_karyawan,
                     nama_karyawan,
                     tgl_lahir,
                     bidang_pekerjaan,
                     jenis_kelamin,
                     alamat,
                     no_hp,
                     no_ktp,
                     email,
                     no_npwp,
                     id_user)
            VALUES (id,nama,
                    tgl_lahir,
                    pekerjaan,
                    jenis_kelamin,
                    alamat,
                    no_hp,
                    no_ktp,
                    email,
                    no_npwp,
                    id_user)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addLayanan` (IN `des_layanan` VARCHAR(70), IN `id_layanan` VARCHAR(3))  NO SQL
INSERT INTO layanan (id_layanan,des_layanan) VALUES (id_layanan,des_layanan)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addPasien` (IN `nama` VARCHAR(50), IN `tgl_lahir` DATE, IN `jenis_kelamin` ENUM('L','P'), IN `no_ktp` VARCHAR(20), IN `alamat` VARCHAR(70), IN `no_hp` VARCHAR(25), IN `gol_darah` VARCHAR(2), IN `password` VARCHAR(100), IN `id_user` VARCHAR(6), IN `id` VARCHAR(6))  NO SQL
INSERT INTO pasien(id_pasien,
                   nama_pasien,
                       tgl_lahir,
                       jenis_kelamin,
                       no_ktp,alamat,
                       no_hp,gol_darah,
                       password,
                       id_user) 
    		VALUES(id,
                   nama,
                   tgl_lahir,
                   jenis_kelamin,
                   no_ktp,
                   alamat,
                   no_hp,
                   gol_darah,
                   password,
                   id_user)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addPendaftaran` (IN `no_antrian` VARCHAR(6), IN `id_pasien` VARCHAR(6), IN `id_poli` VARCHAR(6), IN `tgl_daftar` DATE, IN `keterangan` VARCHAR(70), IN `user_id` VARCHAR(6))  NO SQL
INSERT INTO pendaftaran(no_antrian,id_pasien,id_poli,tgl_daftar,keterangan,user_id) VALUES (no_antrian,id_pasien,id_poli,tgl_daftar,keterangan,user_id)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addPoli` (IN `nama_poli` VARCHAR(70), IN `id` VARCHAR(2))  NO SQL
INSERT INTO poli 
(id_poli, nama_poli)
	VALUES (id, nama_poli)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addRawatInap` (IN `id_pasien` VARCHAR(6), IN `id_kamar` VARCHAR(6), IN `tgl_cekin` DATE, IN `tgl_cekout` DATE, IN `keterangan` VARCHAR(70), IN `id` VARCHAR(10))  NO SQL
INSERT INTO rawat_inap (id_rawat,id_pasien,id_kamar,tgl_cekin,tgl_cekout,keterangan)
	VALUES (id,id_pasien,id_kamar,tgl_cekin,tgl_cekout,keterangan)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addRekamMedik` (IN `tek_darah` VARCHAR(10), IN `berat` DECIMAL(6,2), IN `tinggi` DECIMAL(6,2), IN `keluhan` VARCHAR(100), IN `tindakan` VARCHAR(100), IN `saran` VARCHAR(100), IN `id_dokter` VARCHAR(6), IN `id_resep` VARCHAR(6), IN `diagnosa` VARCHAR(30), IN `id_user` VARCHAR(6), IN `id_pendaftaran` VARCHAR(6))  NO SQL
INSERT INTO rekam_medik(id_pendaftaran,
						tek_darah,
                        berat,tinggi,
                        keluhan,tindakan,
                        saran,id_dokter,
                        id_resep,diagnosa,
                        id_user) 
       VALUES(id_pendaftaran,
			  tek_darah,
              berat,tinggi,
              keluhan,tindakan,
              saran,id_dokter,
              id_resep,diagnosa,
              id_user)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addUser` (IN `nama_user` VARCHAR(50), IN `password` VARCHAR(200), IN `no_ktp` VARCHAR(20), IN `alamat` VARCHAR(70), IN `no_hp` VARCHAR(20), IN `id_role` VARCHAR(2), IN `aktif` ENUM('Y','T'), IN `id_user` VARCHAR(6))  NO SQL
BEGIN
INSERT INTO user (nama_user,password,no_ktp,alamat,no_hp,id_role,aktif,id_user) VALUES (nama_user,password,no_ktp,alamat,no_hp,id_role,aktif,id_user);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteBayarLayanan` (IN `id` VARCHAR(6))  NO SQL
DELETE FROM bayar_layanan WHERE id_bayar_layanan=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteBayarObat` (IN `id` VARCHAR(10))  NO SQL
DELETE FROM bayar_obat WHERE id_pembayaran=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteDetailBayarObat` (IN `id` VARCHAR(10))  NO SQL
DELETE FROM detail_bayar_obat WHERE id_pembayaran=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteDetailLayanan` (IN `id_detail` VARCHAR(3))  NO SQL
DELETE FROM detail_layanan WHERE id_detail_layanan=id_detail$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteKamar` (IN `id` VARCHAR(6))  NO SQL
DELETE FROM kamar WHERE id_kamar=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteKaryawan` (IN `id` VARCHAR(6))  NO SQL
UPDATE karyawan SET deleted=1 WHERE id_karyawan=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteLayanan` (IN `id_layanan` VARCHAR(3))  NO SQL
DELETE FROM layanan WHERE id_layanan=id_layanan$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deletePasien` (IN `id` VARCHAR(6))  NO SQL
UPDATE pasien SET deleted=1 WHERE id_pasien=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deletePendaftaran` (IN `id_poli` VARCHAR(6), IN `tgl` DATE)  NO SQL
UPDATE pendaftaran SET deleted=1 WHERE id_poli=id_poli AND tgl_daftar=tgl$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deletePoli` (IN `id` VARCHAR(2))  NO SQL
DELETE FROM poli WHERE id_poli=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteRawatInap` (IN `id` VARCHAR(10))  NO SQL
DELETE FROM rawat_inap WHERE id_rawat=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteRekamMedik` (IN `id` VARCHAR(6))  NO SQL
DELETE FROM rekam_medik WHERE id_pendaftaran=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUser` (IN `id` VARCHAR(6))  NO SQL
UPDATE user SET aktif="T" WHERE id_user=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getBayarLayanan` ()  NO SQL
SELECT * FROM bayar_layanan ORDER BY id_bayar_layanan$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getBayarObat` ()  NO SQL
SELECT * FROM bayar_obat ORDER BY id_pembayaran$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getDetailBayarObat` ()  NO SQL
SELECT * FROM detail_bayar_obat ORDER BY id_pembayaran$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getDetailLayanan` ()  NO SQL
SELECT * FROM detail_layanan ORDER BY id_detail_layanan$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getKamar` ()  NO SQL
SELECT * FROM kamar ORDER BY id_kamar$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getKaryawan` ()  NO SQL
SELECT * FROM karyawan ORDER BY id_karyawan$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getLayanan` ()  NO SQL
SELECT * FROM layanan ORDER BY id_layanan$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getPasien` ()  BEGIN
	SELECT * FROM pasien;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getPendaftaran` ()  NO SQL
SELECT * FROM pendaftaran$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getPoli` ()  NO SQL
SELECT * FROM poli ORDER BY id_poli$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getRawatInap` ()  NO SQL
SELECT * FROM rawat_inap ORDER BY id_rawat$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getRekamMedik` ()  NO SQL
SELECT * FROM rekam_medik$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUsers` ()  BEGIN
	SELECT * FROM user;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateBayarLayanan` (IN `id_layanan` VARCHAR(3), IN `id_detail_layanan` VARCHAR(3), IN `id_pasien` VARCHAR(6), IN `tgl_layanan` DATE, IN `keterangan` VARCHAR(70), IN `id` VARCHAR(6))  NO SQL
UPDATE bayar_layanan SET id_layanan=id_layanan,
                    		id_detail_layanan=id_detail_layanan,
                            id_pasien=id_pasien,
                            tgl_layanan=tgl_layanan,
                    		keterangan=keterangan
                       WHERE id_bayar_layanan=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateBayarObat` (IN `tgl_pembayaran` DATE, IN `id_pasien` VARCHAR(10), IN `id_resep` VARCHAR(10), IN `jenis_pembayaran` ENUM('BPJS','NON BPJS'), IN `waktu` TIMESTAMP(1), IN `user_id` VARCHAR(10), IN `id` VARCHAR(10))  NO SQL
UPDATE bayar_obat SET tgl_pembayaran=tglpembayaran,
						id_pasien=id_pasien,
                        id_resep=id_resep,
                        jenis_pembayaran=jenis+pembayaran,
                        waktu=waktu,
                        user_id=user_id
                 WHERE id_pembayaran=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateDetailBayarObat` (IN `id_obat` VARCHAR(10), IN `harga` DECIMAL(12,2), IN `jumlah` DECIMAL(6,3), IN `id` VARCHAR(10))  NO SQL
UPDATE detail_bayar_obat SET id_obat=id_obat,
							 harga=harga,
                             jumlah=jumlah
                         WHERE id_pembayaran=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateDetailLayanan` (IN `des_detail_layanan` VARCHAR(70), IN `biaya_layanan` DECIMAL(16,2), IN `keterangan` VARCHAR(50), IN `id_layanan` VARCHAR(3), IN `id_detail` VARCHAR(3))  NO SQL
UPDATE detail_layanan SET des_detail_layanan=des_detail_layanan,
                    		biaya_layanan=biaya_layanan,
                    		keterangan=keterangan
                       WHERE id_detail_layanan=id_detail$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateKamar` (IN `no_kamar` VARCHAR(4), IN `kelas` VARCHAR(10), IN `harga_perhari` DECIMAL(12,2), IN `des_kamar` VARCHAR(70), IN `kapasitas` INT(2), IN `terisi` INT(2), IN `status` ENUM('OK','Full','Dalam Perbaikan'), IN `nama_ruang` VARCHAR(30), IN `id` VARCHAR(6))  NO SQL
UPDATE kamar SET no_kamar=no_kamar,
					kelas=kelas,
                    harga_perhari=harga_perhari,
                    des_kamar=des_kamar,
                    kapasitas=kapasitas,
                    terisi=terisi,
                    status=status
             WHERE id_kamar=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateKaryawan` (IN `nama` VARCHAR(50), IN `tgl_lahir` DATE, IN `pekerjaan` VARCHAR(20), IN `jenis_kelamin` ENUM('L','P'), IN `alamat` VARCHAR(70), IN `no_hp` VARCHAR(20), IN `no_ktp` VARCHAR(20), IN `email` VARCHAR(20), IN `no_npwp` VARCHAR(30), IN `id_user` VARCHAR(6), IN `id` VARCHAR(6))  NO SQL
UPDATE karyawan as k SET k.nama_karyawan=nama, 
						k.tgl_lahir=tgl_lahir, 
                        k.bidang_pekerjaan=pekerjaan,
                        k.jenis_kelamin=jenis_kelamin,
                        k.alamat=alamat,
                        k.no_hp=no_hp,
                        k.no_ktp=no_ktp,
                        k.email=email,
                        k.no_npwp=no_npwp,
                        k.id_user=id_user
                        WHERE k.id_karyawan=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateLayanan` (IN `des_layanan` VARCHAR(70), IN `id_layanan` VARCHAR(3))  NO SQL
UPDATE layanan SET des_layanan=des_layanan
				   WHERE id_layanan=id_layanan$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePasien` (IN `nama` VARCHAR(50), IN `tgl_lahir` DATE, IN `jenis_kelamin` ENUM('L','P'), IN `no_ktp` VARCHAR(20), IN `alamat` VARCHAR(70), IN `no_hp` VARCHAR(20), IN `gol_darah` VARCHAR(2), IN `password` VARCHAR(100), IN `user_id` VARCHAR(6), IN `id` VARCHAR(6))  NO SQL
BEGIN 
	UPDATE pasien SET nama_pasien=nama,
                        tgl_lahir=tgl_lahir,
                        jenis_kelamin=jenis_kelamin,
                        no_ktp=no_ktp,
                        alamat=alamat,
                        no_hp=no_hp,
                        gol_darah=gol_darah,
                        password=password,
                        user_id=user_id
                   WHERE id_pasien=id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePendaftaran` (IN `no_antrian` VARCHAR(6), IN `id_pasien` VARCHAR(6), IN `id_poli` VARCHAR(6), IN `tgl_daftar` DATE, IN `keterangan` VARCHAR(70), IN `user_id` VARCHAR(6), IN `deleted` TINYINT(1))  NO SQL
UPDATE pendaftaran SET id_pasien=id_pasien, 
                    id_poli=id_poli, 
                    tgl_daftar=tgl_daftar, 
                    keterangan=keterangan, 
                    user_id=user_id, deleted=deleted 
                WHERE no_antrian=no_antrian AND
                	  id_poli=id_poli AND
                      tgl_daftar=tgl_daftar$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePoli` (IN `nama_poli` VARCHAR(70), IN `id` VARCHAR(2))  NO SQL
UPDATE poli SET nama_poli=nama_poli
		  WHERE id_poli=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateRawatInap` (IN `id_pasien` VARCHAR(6), IN `id_kamar` VARCHAR(6), IN `tgl_cekin` DATE, IN `tgl_cekout` DATE, IN `keterangan` VARCHAR(70), IN `id` VARCHAR(10))  NO SQL
UPDATE rawat_inap SET id_pasien=id_pasien,
						id_kamar=id_kamar, 
                        tgl_cekin=tgl_cekin,
                        tgl_cekout=tgl_cekout,
                        keterangan=keterangan
                   WHERE id_rawat=id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateRekamMedik` (IN `tek_darah` VARCHAR(10), IN `berat` DECIMAL(6,2), IN `tinggi` DECIMAL(6,2), IN `keluhan` VARCHAR(100), IN `tindakan` VARCHAR(100), IN `saran` VARCHAR(100), IN `id_dokter` VARCHAR(6), IN `id_resep` VARCHAR(6), IN `diagnosa` VARCHAR(30), IN `id_user` VARCHAR(6), IN `id` VARCHAR(6))  NO SQL
UPDATE rekam_medik SET tek_darah=tek_darah,berat=berat,tinggi=tinggi,keluhan=keluhan,tindakan=tindakan,saran=saran,id_dokter=id_dokter,id_resep=id_resep,diagnosa=diagnosa,id_user=id_user WHERE id_pendaftaran=id$$

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

--
-- Dumping data for table `bayar_obat`
--

INSERT INTO `bayar_obat` (`id_pembayaran`, `tgl_pembayaran`, `id_pasien`, `id_resep`, `jenis_pembayaran`, `waktu`, `user_id`) VALUES
('BYR001', '2021-02-19', 'PS001', 'RSP001', 'Cash', '2021-02-19 00:49:43', 'US001');

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

--
-- Triggers `detail_bayar_obat`
--
DELIMITER $$
CREATE TRIGGER `after_delete` AFTER DELETE ON `detail_bayar_obat` FOR EACH ROW UPDATE obat SET stok = stok + old.jumlah WHERE id_obat=old.id_obat
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `after_insert` AFTER INSERT ON `detail_bayar_obat` FOR EACH ROW UPDATE obat SET stok = stok - new.jumlah WHERE id_obat=new.id_obat
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `after_update` AFTER UPDATE ON `detail_bayar_obat` FOR EACH ROW UPDATE obat SET stok=stok-old.jumlah+new.jumlah WHERE id_obat=new.id_obat
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `check_stok` BEFORE INSERT ON `detail_bayar_obat` FOR EACH ROW BEGIN
  IF (SELECT obat.stok FROM obat WHERE obat.id_obat=new.id_obat <= 0)
  THEN
   SIGNAL SQLSTATE '02000' SET MESSAGE_TEXT = 'Stok obat kosong!!! Tidak bisa melakukan pembelian obat.';
  END IF;

END
$$
DELIMITER ;

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

--
-- Dumping data for table `detail_resep`
--

INSERT INTO `detail_resep` (`id_resep`, `id_obat`, `harga`, `jumlah`, `keterangan`, `user_id`, `waktu`) VALUES
('RSP001', 'OB0001', '2000.00', '10.00', '2x3 sehari', 'US001', '2021-02-19 00:47:56');

-- --------------------------------------------------------

--
-- Table structure for table `dokter`
--

CREATE TABLE `dokter` (
  `id_dokter` varchar(6) NOT NULL,
  `nama_dokter` varchar(50) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `id_poli` varchar(6) NOT NULL,
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

--
-- Dumping data for table `dokter`
--

INSERT INTO `dokter` (`id_dokter`, `nama_dokter`, `tgl_lahir`, `id_poli`, `jenis_kelamin`, `alamat`, `no_hp`, `no_ktp`, `spesialis`, `password`, `email`, `no_npwp`, `user_id`, `waktu`) VALUES
('DK001', 'Joko', '2021-02-02', 'PLG', 'L', 'Jakarta', '0812128182823', '02939232', 'UMUM', 'adadad', 'burhan@gmail.com', '232323', 'US001', '2021-02-19 00:45:19');

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

--
-- Dumping data for table `kamar`
--

INSERT INTO `kamar` (`id_kamar`, `nama_ruang`, `no_kamar`, `kelas`, `harga_perhari`, `des_kamar`, `kapasitas`, `terisi`, `status`) VALUES
('KM0001', 'ruang biasa', '909', 'vip', '90000.00', '-', 9, 1, 'OK');

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `id_karyawan` varchar(6) NOT NULL,
  `nama_karyawan` varchar(50) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `bidang_pekerjaan` varchar(20) NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `alamat` varchar(70) NOT NULL,
  `no_hp` varchar(25) NOT NULL,
  `no_ktp` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `no_npwp` varchar(30) NOT NULL,
  `id_user` varchar(10) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`id_karyawan`, `nama_karyawan`, `tgl_lahir`, `bidang_pekerjaan`, `jenis_kelamin`, `alamat`, `no_hp`, `no_ktp`, `email`, `no_npwp`, `id_user`, `waktu`, `deleted`) VALUES
('KR0001', 'Julaidin', '2020-01-13', 'Kasir', 'L', 'Jakarta', '0812128182823', '02939232', 'jula@gmail.com', '11111', 'US001', '2021-02-18 07:34:32', 0),
('KR0002', 'Budiyanto', '1998-09-09', 'Administrator', 'L', 'Jakarta', '232323', '232323', 'add@gmail.com', '22222', 'US001', '2021-02-18 07:34:32', 0),
('KR0003', 'Anto', '2001-09-23', 'Cleaning Service', 'L', 'Bandung', '12121212', '22121212', 'user_test@gmail.com', '1111111', 'US001', '2021-02-18 08:04:58', 0),
('KR0004', 'Suwandi', '1998-02-02', 'Kepala Apotik', 'L', 'Jakarta', '293892839', '2837283', 'suwan@gmail.com', '0111111', 'US001', '2021-02-18 10:03:51', 0),
('KR0005', 'Bowo', '1998-02-02', 'Kepala Apotik', 'L', 'Jakarta', '293892839', '2837283', 'bowo@gmail.com', '0111111', 'US001', '2021-02-18 10:04:08', 0),
('KR0006', 'Suwandi', '1998-02-02', 'Kepala Apotik', 'L', 'Jakarta', '293892839', '2837283', 'suwan@gmail.com', '0111111', 'US001', '2021-02-18 10:04:17', 0),
('KR0007', 'Suwandi', '1998-02-02', 'Kepala Apotik', 'L', 'Jakarta', '293892839', '2837283', 'suwan@gmail.com', '0111111', 'US001', '2021-02-18 10:04:19', 0),
('KR0008', 'Suwandi', '1998-02-02', 'Kepala Apotik', 'L', 'Jakarta', '293892839', '2837283', 'suwan@gmail.com', '0111111', 'US001', '2021-02-18 10:04:21', 0),
('KR0009', 'Suwandi', '1998-02-02', 'Kepala Apotik', 'L', 'Jakarta', '293892839', '2837283', 'suwan@gmail.com', '0111111', 'US001', '2021-02-18 10:04:23', 0),
('KR0010', 'Suwandi', '1998-02-02', 'Kepala Apotik', 'L', 'Jakarta', '293892839', '2837283', 'suwan@gmail.com', '0111111', 'US001', '2021-02-18 10:04:24', 1);

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

--
-- Dumping data for table `obat`
--

INSERT INTO `obat` (`id_obat`, `nama_obat`, `satuan`, `stok`, `harga_jual`, `waktu`, `user_id`) VALUES
('ddd', 'sdsd', 'sdsd', '0.00', '3000.00', '2021-02-17 15:03:59', 'US001'),
('OB0001', 'Konidin', 'saset', '100.00', '2000.00', '2021-02-17 11:19:55', 'US001');

-- --------------------------------------------------------

--
-- Table structure for table `pasien`
--

CREATE TABLE `pasien` (
  `id_pasien` varchar(6) NOT NULL,
  `nama_pasien` varchar(50) NOT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `no_ktp` varchar(20) NOT NULL,
  `alamat` varchar(70) NOT NULL,
  `no_hp` varchar(25) NOT NULL,
  `gol_darah` varchar(2) NOT NULL,
  `password` varchar(100) NOT NULL,
  `id_user` varchar(10) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pasien`
--

INSERT INTO `pasien` (`id_pasien`, `nama_pasien`, `tgl_lahir`, `jenis_kelamin`, `no_ktp`, `alamat`, `no_hp`, `gol_darah`, `password`, `id_user`, `waktu`, `deleted`) VALUES
('PS001', 'Budi USni', '2002-07-02', 'L', '09129129', 'jakarta', '09129128', 'A', 'password', 'US001', '2021-02-16 03:24:50', 1),
('PS002', 'Badi', '2021-02-01', 'L', '02939232', 'asasas', '323232', 'B', 'sdsdsd', 'US001', '2021-02-17 10:49:01', 0),
('PS003', 'andi', '2002-02-09', 'L', '2323232', 'adadadad', '1212121212', 'A', 'pass', 'US031', '2021-02-18 01:02:39', 0);

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

--
-- Dumping data for table `pembelian_obat`
--

INSERT INTO `pembelian_obat` (`id_trans`, `id_supplier`, `no_faktur`, `tgl_faktur`, `id_obat`, `harga_beli`, `jumlah`, `keterangan`, `tgl_expired`, `id_user`, `waktu`) VALUES
('TX0001', 'SP0001', 'FK0001', '2021-02-17', 'OB0001', '1500.00', '120.00', '-', '2021-05-14', 'US001', '2021-02-17 14:53:53');

--
-- Triggers `pembelian_obat`
--
DELIMITER $$
CREATE TRIGGER `delete_obat` AFTER DELETE ON `pembelian_obat` FOR EACH ROW UPDATE obat SET stok = stok - old.jumlah WHERE id_obat = old.id_obat
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insert_obat` AFTER INSERT ON `pembelian_obat` FOR EACH ROW UPDATE obat SET stok=stok+new.jumlah WHERE id_obat=new.id_obat
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_obat` AFTER UPDATE ON `pembelian_obat` FOR EACH ROW UPDATE obat SET stok=stok - old.jumlah + new.jumlah WHERE id_obat=new.id_obat
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pendaftaran`
--

CREATE TABLE `pendaftaran` (
  `no_antrian` varchar(6) NOT NULL,
  `id_pasien` varchar(6) NOT NULL,
  `id_poli` varchar(6) NOT NULL,
  `tgl_daftar` date NOT NULL,
  `keterangan` varchar(70) NOT NULL,
  `user_id` varchar(10) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pendaftaran`
--

INSERT INTO `pendaftaran` (`no_antrian`, `id_pasien`, `id_poli`, `tgl_daftar`, `keterangan`, `user_id`, `waktu`, `deleted`) VALUES
('PLG001', 'PS003', 'PLG', '2021-02-21', '333', 'US001', '2021-02-21 16:22:22', 0),
('PLG001', 'PS001', 'PLG', '2021-02-24', 'biasalah', 'US001', '2021-02-23 19:19:39', 0),
('PLU001', 'PS001', 'PLU', '2021-02-21', '-', 'US001', '2021-02-21 10:52:38', 0),
('PLU001', 'PS003', 'PLU', '2021-02-22', '---', 'US001', '2021-02-22 13:48:15', 0),
('PLU002', 'PS002', 'PLU', '2021-02-21', 'sakit', 'US001', '2021-02-21 11:12:17', 0);

-- --------------------------------------------------------

--
-- Table structure for table `poli`
--

CREATE TABLE `poli` (
  `id_poli` varchar(6) NOT NULL,
  `nama_poli` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `poli`
--

INSERT INTO `poli` (`id_poli`, `nama_poli`) VALUES
('PLG', 'GIGI'),
('PLU', 'UMUM');

-- --------------------------------------------------------

--
-- Table structure for table `rawat_inap`
--

CREATE TABLE `rawat_inap` (
  `id_rawat` varchar(10) NOT NULL,
  `id_pasien` varchar(6) NOT NULL,
  `id_kamar` varchar(6) NOT NULL,
  `tgl_cekin` date NOT NULL,
  `tgl_cekout` date NOT NULL,
  `keterangan` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rawat_inap`
--

INSERT INTO `rawat_inap` (`id_rawat`, `id_pasien`, `id_kamar`, `tgl_cekin`, `tgl_cekout`, `keterangan`) VALUES
('RT0001', 'PS001', 'KM0001', '2021-02-16', '2021-02-18', '---'),
('RT0002', 'PS001', 'KM0001', '1998-02-02', '1998-02-02', 'ketereangan');

-- --------------------------------------------------------

--
-- Table structure for table `rekam_medik`
--

CREATE TABLE `rekam_medik` (
  `id_pendaftaran` varchar(6) NOT NULL,
  `tek_darah` varchar(10) NOT NULL,
  `berat` decimal(6,2) NOT NULL,
  `tinggi` decimal(6,2) NOT NULL,
  `keluhan` varchar(100) NOT NULL,
  `tindakan` varchar(100) NOT NULL,
  `saran` varchar(100) NOT NULL,
  `id_dokter` varchar(6) NOT NULL,
  `id_resep` varchar(6) NOT NULL,
  `diagnosa` varchar(30) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_user` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rekam_medik`
--

INSERT INTO `rekam_medik` (`id_pendaftaran`, `tek_darah`, `berat`, `tinggi`, `keluhan`, `tindakan`, `saran`, `id_dokter`, `id_resep`, `diagnosa`, `waktu`, `id_user`) VALUES
('PLU001', '90', '90.00', '190.00', 'Demam', 'Pemberian Obat', 'minum obat teratur dan istirahat yang cukup', 'DK001', 'RSP001', 'Deman Parah', '2021-02-21 11:07:17', ''),
('PLU002', '90', '90.00', '190.00', 'Demam', 'Pemberian Obat', 'minum obat teratur dan istirahat yang cukup', 'DK001', 'RSP001', 'Deman Parah', '2021-02-21 11:12:24', 'US001');

--
-- Triggers `rekam_medik`
--
DELIMITER $$
CREATE TRIGGER `add_resep` BEFORE INSERT ON `rekam_medik` FOR EACH ROW INSERT INTO resep (id_resep,id_dokter,tgl_resep,id_poli,user_id) 
	   VALUES (new.id_resep,new.id_dokter,CURRENT_DATE(),
              (SELECT id_poli 
               FROM pendaftaran 
               WHERE no_antrian=new.id_pendaftaran),
              new.id_user)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `resep`
--

CREATE TABLE `resep` (
  `id_resep` varchar(6) NOT NULL,
  `id_dokter` varchar(6) NOT NULL,
  `tgl_resep` date NOT NULL,
  `id_poli` varchar(6) NOT NULL,
  `user_id` varchar(10) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `resep`
--

INSERT INTO `resep` (`id_resep`, `id_dokter`, `tgl_resep`, `id_poli`, `user_id`, `waktu`) VALUES
('RSP001', 'DK001', '2021-02-19', 'PLG', 'US001', '2021-02-19 00:47:12'),
('RSP002', 'DK001', '2021-02-22', 'PLG', 'US001', '2021-02-22 15:24:32'),
('RSP005', 'DK001', '2021-02-22', 'PLU', '', '2021-02-22 15:22:15'),
('RSP006', 'DK001', '2021-02-22', 'PLU', 'US001', '2021-02-22 15:41:28');

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

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id_supplier`, `nama_suplier`, `alamat`, `no_telepon`, `email`, `user_id`, `waktu`) VALUES
('SP0001', 'PT.Kimia', 'bandung', '918281', 'burhan@gmail.com', 'US001', '2021-02-17 11:20:52');

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
('US001', 'pppppppppppp', '$2a$12$2UytMhTFJO1Q8qJ3o.SNq.aLQ./nEpsOY1SaYulQmClZ6QQSm8hmG', '00218723772', 'Jakarta Timur', '0812387232', 'A1', 'T'),
('US002', 'Budi Aleksander', '$2a$12$Xqx0tDBWIrUFSSXDNsDY9.m5myfUu6ee.U5k9NjKjbkHZbgb/bPK2', '00218723772', 'Jakarta Timur', '0812387232', 'A1', 'T'),
('US003', 'Budi Aleksander', '$2a$12$XsWr.EPA4HsfTK6OXG8.5epNcE.ysNxAM6217OcSMc3NVhc/vYnWu', '00218723772', 'Jakarta Timur', '0812387232', 'A1', 'T'),
('US004', 'Budi Aleksander', '$2a$12$6tg3GLa.kfT5s9cKJhy6bOfKtVnnkZSj6QAeN9yg6LZThF7WhL9P6', '00218723772', 'Jakarta Timur', '0812387232', 'A1', 'T'),
('US005', 'Budi', '$2a$12$q/iaATY0Db6bOKyGjT3AgeHZlumkDhZmuMnwPw2JRoYwzu5IHnnea', '00218723772', 'Jakarta Timur', '0812387232', 'A1', 'T'),
('US006', 'pppppppppppp', '$2a$12$fOjl8SBGtSR3biLbPPeL5eDFzHCHJ3jvG/Er7PRhfLFn0PG0ojrP6', '00218723772', 'Jakarta Timur', '0812387232', 'A1', 'T'),
('US007', 'pppppppppppp', '$2a$12$XilNONxmXDEVqO5faFA6.eLtsmcXB6c5m3xPTNAEt.48L7k2xPNAG', '00218723772', 'Jakarta Timur', '0812387232', 'A1', 'T');

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
  ADD PRIMARY KEY (`id_pasien`),
  ADD KEY `user_id` (`id_user`);

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
  ADD PRIMARY KEY (`no_antrian`,`id_poli`,`tgl_daftar`) USING BTREE,
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
  ADD PRIMARY KEY (`id_pendaftaran`),
  ADD KEY `id_dokter` (`id_dokter`,`id_resep`),
  ADD KEY `id_resep` (`id_resep`);

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
  ADD CONSTRAINT `rekam_medik_ibfk_2` FOREIGN KEY (`id_dokter`) REFERENCES `dokter` (`id_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rekam_medik_ibfk_5` FOREIGN KEY (`id_pendaftaran`) REFERENCES `pendaftaran` (`no_antrian`) ON UPDATE CASCADE,
  ADD CONSTRAINT `rekam_medik_ibfk_6` FOREIGN KEY (`id_resep`) REFERENCES `resep` (`id_resep`);

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
