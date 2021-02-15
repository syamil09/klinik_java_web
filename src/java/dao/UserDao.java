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
import model.User;
import connection.Koneksi;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author syamil imdad
 */
public class UserDao {

    private final Connection koneksi;
    private PreparedStatement preSmt;
    private ResultSet rs;
    private Function f;

    public UserDao() {
        koneksi = Koneksi.getConnection();
        f = new Function();
    }

    public ArrayList<User> getAlluser() {
        ArrayList<User> listUser = new ArrayList<>();
        System.out.println("---- getting data -----");
        try {
            String sql = "CALL getUsers()";
            preSmt = koneksi.prepareStatement(sql);
            rs = preSmt.executeQuery();
            while (rs.next()) {
                User usr = new User();
                usr.setUserId(rs.getString("id_user"));
                usr.setAktif(rs.getString("aktif"));
                usr.setNoKtp(rs.getString("no_ktp"));
                usr.setIdRole(rs.getString("id_role"));
                usr.setAlamat(rs.getString("alamat"));
                usr.setNama(rs.getString("nama_user"));
                usr.setPassword(rs.getString("password"));
                listUser.add(usr);
                System.out.println("    id user : " + usr.getUserId());
            }

        } catch (SQLException e) {
            System.out.println("gagal get data user : " + e);
        }

        return listUser;
    }

    public User getRecordById(String userid) {
        User usr = new User();
        String sql = "SELECT u.*, k.nama FROM user u, karyawan k WHERE u.nik=k.nik AND u.userid=?";

        try {
            preSmt = koneksi.prepareStatement(sql);
            preSmt.setString(1, userid);
            rs = preSmt.executeQuery();

            if (rs.next()) {
                usr.setUserId(userid);
                usr.setAktif(rs.getString("aktif"));
                usr.setNoKtp(rs.getString("no_ktp"));
                usr.setIdRole(rs.getString("id_role"));
                usr.setAlamat(rs.getString("alamat"));
                usr.setNama(rs.getString("nama"));
                usr.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            System.out.println("error get data user by id : " + e);
        }

        return usr;
    }

    public void simpanData(User usr, String page) {
        System.out.println("page : " + page);
        String sqlSimpan = null;
        if (page.equals("edit")) {
            sqlSimpan = "CALL updateUser(?,?,?,?,?,?,?,?)";
        } else if (page.equals("tambah")) {
            sqlSimpan = "CALL addUser(?,?,?,?,?,?,?,?)";
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
        }
        try {
            String passHash = BCrypt.hashpw(usr.getPassword(), BCrypt.gensalt(12));
            preSmt = koneksi.prepareStatement(sqlSimpan);
            preSmt.setString(1, usr.getNama());
            preSmt.setString(2, passHash);
            preSmt.setString(3, usr.getNoKtp());
            preSmt.setString(4, usr.getAlamat());
            preSmt.setString(5, usr.getNoHp());
            preSmt.setString(6, usr.getIdRole());
            preSmt.setString(7, usr.getAktif());
            preSmt.setString(8, usr.getUserId());
            preSmt.executeUpdate();
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
        User usr = new User();
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
        String sql = "SELECT id_user FROM user ORDER BY id_user DESC LIMIT 1";
        String newId = "US001"; // jika data di database kosong pakai id ini
        try {
            preSmt = koneksi.prepareStatement(sql);
            rs = preSmt.executeQuery();
            if (rs.next()) {
                newId = f.generateId(rs.getString("id_user"));
            }
        } catch (SQLException e) {
            System.out.println("error generate new UserId : " + e);
        }
        System.out.println("Generate new UserId : " + newId);
        return newId;
    }

    public static void main(String[] args) {
        UserDao u = new UserDao();
        User um = new User();

        um.setUserId("US002");
        um.setNama("Budi Aleksander");
        um.setIdRole("A1");
        um.setNoKtp("00218723772");
        um.setNoHp("0812387232");
        um.setAlamat("Jakarta Timur");
        um.setAktif("T");
        um.setPassword("password");
        u.simpanData(um, "edit");

//        u.hapusData(um.getUserId());
        System.out.println(u.getAlluser());
        System.out.println("ID user baru : " + u.getNewId());
//            System.out.println(u.login("admin", "password"));
        System.out.println(u.login(um.getUserId(), "password"));
    }
}
