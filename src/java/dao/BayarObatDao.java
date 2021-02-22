/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.BayarObat;
import helper.*;
/**
 *
 * @author ASUS
 */
public class BayarObatDao {
        private final Connection koneksi;
        private PreparedStatement preSmt;
        private ResultSet rs;
        private Function f;
        // tanggal
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        public BayarObatDao(){
            koneksi = Koneksi.getConnection();
            f = new Function();
        }

        public ArrayList<BayarObat> getAllBayarObat(){
            ArrayList<BayarObat> listData = new ArrayList<>();
            try{
                String sql = "CALL getBayarObat()";
                preSmt = koneksi.prepareStatement(sql);
                rs = preSmt.executeQuery();
                System.out.println("---- getting data ----");
                while(rs.next()){
                    BayarObat model = new BayarObat();
                    model.setId_pembayaran(rs.getString("id_pembayaran"));
                    model.setTgl_pembayaran(rs.getString("tgl_pembayaran"));
                    model.setId_pasien(rs.getString("id_pasien"));
                    model.setId_resep(rs.getString("id_resep"));
                    model.setJenis_pembayaran(rs.getString("jenis_pembayaran"));
                    model.setUser_id(rs.getString("user_id"));    
                    listData.add(model);
                    
                    System.out.println("     id : "+rs.getString("id_karyawan"));
                }
            }
            catch(SQLException e){
                System.out.println("Kesalahan mengambil data : " + e);
            }
            return listData;
        }

        public BayarObat getRecordByNIK(String nik){
            BayarObat kar = new BayarObat();
            String sqlSearch = "select * from bayar_obat where id_pembayaran=id";
            try {
                preSmt = koneksi.prepareStatement(sqlSearch);
                preSmt.setString(1, nik);
                rs = preSmt.executeQuery();
                if (rs.next()){
//                    kar.setNik(rs.getString("nik"));
//                    kar.setNama(rs.getString("nama"));
//                    kar.setGender(rs.getString("gender"));
//                    kar.setTmpLahir(rs.getString("tmplahir"));
//                    kar.setTglLahir(rs.getString("tgllahir"));
//                    kar.setAlamat(rs.getString("alamat"));
//                    kar.setTelepon(rs.getString("telepon"));
                }
            }
            catch (SQLException se){
                System.out.println("kesalahan pada : " + se);
            }
            return kar;
        }
//
        public void simpanData(BayarObat kar, String page){
            String sqlSimpan = null;
            if (page.equals("edit")){
                sqlSimpan = "CALL updateBayarObat(?,?,?,?,?,?,?)";
            }
            else if (page.equals("tambah")){
                sqlSimpan = "CALL addBayarObat(?,?,?,?,?,?,?)";
            }
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
            try {
                String id = getNewId();
                preSmt = koneksi.prepareStatement(sqlSimpan);
                preSmt.setString(1, kar.getId_pembayaran());
                preSmt.setString(2, kar.getTgl_pembayaran());
                preSmt.setString(3, kar.getId_pasien());
                preSmt.setString(4, kar.getId_resep());
                preSmt.setString(5, kar.getJenis_pembayaran());
                preSmt.setString(6, kar.getUser_id());
                preSmt.setString(11, page == "tambah" ? id : kar.getId_pembayaran());
                preSmt.executeUpdate();
                System.out.println(page == "tambah" ? "success add data" : "success update data");

            }
            catch (SQLException se){
                System.out.println("ada kesalahan : " + se);
            }
        }

        public void hapusData(String id){
            String sqlHapus = "CALL deleteBayarObat(?)";
            System.out.println("---- deleting data ----");
            try{
                preSmt = koneksi.prepareStatement(sqlHapus);
                preSmt.setString(1, id);
                preSmt.executeUpdate();
                System.out.println("success delete data, id : "+id);
            }
            catch(SQLException e){
                System.out.println("kesalahan hapus data: " + e);
            }
        }
        
        public String getNewId() {
        String sql = "SELECT id_pembayaran FROM bayar_obat ORDER BY id_pembayaran DESC LIMIT 1";
        String newId = ""; // jika data di database kosong pakai id ini
        try {
            preSmt = koneksi.prepareStatement(sql);
            rs = preSmt.executeQuery();
            if (rs.next()) {
                newId = f.generateId(rs.getString("id_pembayaran"));
            }
        } catch (SQLException e) {
            System.out.println("error generate new ID : " + e);
        }
        System.out.println("Generate new ID : " + newId);
        return newId;
    }


        public static void main(String[] args) {
            BayarObatDao dao = new BayarObatDao();
   
            BayarObat model = new BayarObat();
            model.setId_pembayaran("");
            model.setTgl_pembayaran("");
            model.setId_pasien("");
            model.setId_resep("");
            model.setJenis_pembayaran("");
            model.setUser_id("");
            
//            dao.simpanData(model,"edit");
              dao.hapusData("");
            System.out.println(dao.getAllBayarObat());
        }   
}
