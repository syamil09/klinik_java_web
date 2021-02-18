/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import helper.*;
import java.util.ArrayList;
import model.Pasien;
import connection.Koneksi;
import java.sql.SQLException;
import java.util.Arrays;
import java.text.SimpleDateFormat;
/**
 *
 * @author syamil imdad
 */
public class PasienDao {

    private final Connection koneksi;
    private PreparedStatement preSmt;
    private ResultSet rs;
    private Function f;
    // tanggal
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PasienDao() {
        koneksi = Koneksi.getConnection();
        f = new Function();
        
    }

    public ArrayList<Pasien> getAllPasien() {
        ArrayList<Pasien> listData = new ArrayList<>();
        System.out.println("---- getting data -----");
        try {
            String sql = "CALL getPasien()";
            preSmt = koneksi.prepareStatement(sql);
            rs = preSmt.executeQuery();
            while (rs.next()) {
                Pasien model = new Pasien();
                model.setIdPasien(rs.getString("id_pasien"));
                model.setNama(rs.getString("nama_pasien"));
                model.setTglLahir(rs.getString("tgl_lahir"));
                model.setJenisKelamin(rs.getString("jenis_kelamin"));
                model.setNoKtp(rs.getString("no_ktp"));
                model.setAlamat(rs.getString("alamat"));
                model.setNoHp(rs.getString("no_hp"));
                model.setGolDarah(rs.getString("gol_darah"));
                model.setPassword(rs.getString("password"));
                model.setIdUser(rs.getString("id_user"));
                model.setWaktu(rs.getString("waktu"));
                listData.add(model);
                System.out.println("    id : " + model.getIdPasien());
            }

        } catch (SQLException e) {
            System.out.println("gagal get data pasien : " + e);
        }

        return listData;
    }

    public Pasien getRecordById(String userid) {
        Pasien usr = new Pasien();
        String sql = "SELECT u.*, k.nama FROM user u, karyawan k WHERE u.nik=k.nik AND u.userid=?";

        try {
            preSmt = koneksi.prepareStatement(sql);
            preSmt.setString(1, userid);
            rs = preSmt.executeQuery();

            if (rs.next()) {
                usr.setIdUser(userid);
//                usr.setAktif(rs.getString("aktif"));
//                usr.setNoKtp(rs.getString("no_ktp"));
//                usr.setIdRole(rs.getString("id_role"));
//                usr.setAlamat(rs.getString("alamat"));
//                usr.setNama(rs.getString("nama"));
                usr.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            System.out.println("error get data user by id : " + e);
        }

        return usr;
    }
    
    public void simpanData(Pasien usr, String page) {
        System.out.println("page : " + page);
        String sqlSimpan = null;
        if (page.equals("edit")) {
            sqlSimpan = "CALL updatePasien(?,?,?,?,?,?,?,?,?,?)";
        } else if (page.equals("tambah")) {
            sqlSimpan = "CALL addPasien(?,?,?,?,?,?,?,?,?,?)";
            
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
        }
        try {

            String passHash = BCrypt.hashpw(usr.getPassword(), BCrypt.gensalt(12));
            preSmt = koneksi.prepareStatement(sqlSimpan);
            preSmt.setString(1, usr.getNama());
            preSmt.setString(2, usr.getTglLahir());
            preSmt.setString(3, usr.getJenisKelamin());
            preSmt.setString(4, usr.getNoKtp());
            preSmt.setString(5, usr.getAlamat());
            preSmt.setString(6, usr.getNoHp());
            preSmt.setString(7, usr.getGolDarah());
            preSmt.setString(8, passHash);
            preSmt.setString(9, usr.getIdUser());
            preSmt.setString(10, page == "tambah" ? getNewId() : usr.getIdPasien());
            System.out.println("nama : "+usr.getNama());
            System.out.println("lhr : "+usr.getTglLahir());
            preSmt.executeUpdate();
//            System.out.println(preSmt.executeQuery());
            System.out.println(page == "tambah" ? "success add data" : "success update data");
        } catch (SQLException se) {
            System.out.println("error add or update : " + se);
        }
    }

    public void hapusData(String id) {
        String sqlHapus = "CALL deleteUser(?)";
        System.out.println("---- deleting data -----");
        try {
            preSmt = koneksi.prepareStatement(sqlHapus);
            preSmt.setString(1, id);
            int execute = preSmt.executeUpdate();
            System.out.println(execute == 1 ? "Success delete" : "Failed delete");
        } catch (SQLException e) {
            System.out.println("error delete data : " + e);
        }
    }

    public String login(String userid, String password) {
        Pasien usr = new Pasien();
        try {
            String sql = "SELECT * FROM user WHERE id_user=?";
            preSmt = koneksi.prepareStatement(sql);
            preSmt.setString(1, userid);
            rs = preSmt.executeQuery();
            rs.last();
            System.out.println("password match : " + BCrypt.checkpw(password, rs.getString("password")));
            if (rs.getRow() == 1) {
                if (BCrypt.checkpw(password, rs.getString("password"))) {
                    if (rs.getString("aktif").equals("T")) {
                        return "akun tidak aktif";
                    }
                    System.out.println("berhasil login");
                    return "berhasil";
                } else {
                    System.out.println("password salah");
                }
            }
        } catch (SQLException e) {
            System.out.println("gagal login : " + e);
        }
        return "gagal";
    }

    public String getNewId() {
        String sql = "SELECT id_pasien FROM pasien ORDER BY id_pasien DESC LIMIT 1";
        String newId = "PS0001"; // jika data di database kosong pakai id ini
        try {
            preSmt = koneksi.prepareStatement(sql);
            rs = preSmt.executeQuery();
            if (rs.next()) {
                newId = f.generateId(rs.getString("id_pasien"));
            }
        } catch (SQLException e) {
            System.out.println("error generate new Id : " + e);
        }
        System.out.println("Generate new Id : " + newId);
        return newId;
    }

    public static void main(String[] args) {
        PasienDao dao = new PasienDao();
        Pasien model = new Pasien();

        model.setIdPasien("PS001");
        model.setNama("Budi Aleksander");
        model.setTglLahir("2001-09-01");
        model.setJenisKelamin("L");
        model.setNoKtp("00218723772");
        model.setNoHp("0812387232");
        model.setGolDarah("AB");
        model.setPassword("password");
        model.setAlamat("Jakarta Timur");
        model.setIdUser("US001");
        model.setPassword("password");
        dao.simpanData(model, "tambah");

//        u.hapusData(um.getUserId());
        System.out.println(dao.getAllPasien());
//        System.out.println("ID user baru : " + u.getNewId());
//            System.out.println(u.login("admin", "password"));
//        System.out.println(u.login(um.getIdUser(), "password"));
    }
}
