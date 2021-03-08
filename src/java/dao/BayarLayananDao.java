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
import model.BayarLayanan;
import helper.*;

public class BayarLayananDao {
    private final Connection koneksi;
        private PreparedStatement preSmt;
        private ResultSet rs;
        private Function f;
        // tanggal
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        public BayarLayananDao(){
            koneksi = Koneksi.getConnection();
            f = new Function();
        }

        public ArrayList<BayarLayanan> getAllBayarLayanan(){
            ArrayList<BayarLayanan> listBayarLayanan = new ArrayList<>();
            try{
                String sqlAllBayarLayanan = "CALL getBayarLayanan()";
                preSmt = koneksi.prepareStatement(sqlAllBayarLayanan);
                rs = preSmt.executeQuery();
                System.out.println("---- getting data ----");
                while(rs.next()){
                    BayarLayanan model = new BayarLayanan();
                    model.setId_layanan(rs.getString("id_layanan"));
                    model.setId_detail_layanan(rs.getString("id_detail_layanan"));
                    model.setId_bayar_layanan(rs.getString("id_bayar_layanan"));
                    model.setId_pasien(rs.getString("id_pasien"));
                    model.setTgl_layanan(rs.getString("tgl_layanan"));
                    model.setKeterangan(rs.getString("keterangan")); 
                    listBayarLayanan.add(model);
                    
                    System.out.println("     id : "+rs.getString("id_bayar_layanan"));
                }
            }
            catch(SQLException e){
                System.out.println("Kesalahan mengambil data : " + e);
            }
            return listBayarLayanan;
        }

        public BayarLayanan getRecordById(String id_bayar){
            BayarLayanan model = new BayarLayanan();
            String sqlSearch = "select * from bayar_layanan where id_bayar_layanan=?";
            try {
                preSmt = koneksi.prepareStatement(sqlSearch);
                preSmt.setString(1, id_bayar);
                rs = preSmt.executeQuery();
                if (rs.next()){
                    model.setId_bayar_layanan(rs.getString("id_bayar_layanan"));
                    model.setId_layanan(rs.getString("id_layanan"));
                    model.setId_detail_layanan(rs.getString("id_detail_layanan"));
                    model.setId_pasien(rs.getString("id_pasien"));
                    model.setTgl_layanan(rs.getString("tgl_layanan"));
                    model.setKeterangan(rs.getString("keterangan"));
                }
            }
            catch (SQLException se){
                System.out.println("kesalahan pada : " + se);
            }
            return model;
        }
        public void simpanData(BayarLayanan kar, String page){
            String sqlSimpan = null;
            if (page.equals("edit")){
                sqlSimpan = "CALL updateBayarLayanan(?,?,?,?,?,?)";
            }
            else if (page.equals("tambah")){
                sqlSimpan = "CALL addBayarLayanan(?,?,?,?,?,?)";
            }
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
            try {
                String id = getNewId();
                preSmt = koneksi.prepareStatement(sqlSimpan);
                preSmt.setString(1, kar.getId_layanan());
                preSmt.setString(2, kar.getId_detail_layanan());
                preSmt.setString(3, kar.getId_pasien());
                preSmt.setString(4, kar.getTgl_layanan());
                preSmt.setString(5, kar.getKeterangan());
                preSmt.setString(6, kar.getId_bayar_layanan());
                preSmt.executeUpdate();
                System.out.println(page == "tambah" ? "success add data" : "success update data");

            }
            catch (SQLException se){
                System.out.println("ada kesalahan : " + se);
            }
        }

        public void hapusData(String id_bayar){
            String sqlHapus = "CALL deleteBayarLayanan(?)";
            System.out.println("---- deleting data ----");
            try{
                preSmt = koneksi.prepareStatement(sqlHapus);
                preSmt.setString(1, id_bayar);
                preSmt.executeUpdate();
                System.out.println("success delete data, id : "+id_bayar);
            }
            catch(SQLException e){
                System.out.println("kesalahan hapus data: " + e);
            }
        }
        
        public String getNewId() {
        String sql = "SELECT id_detail_layanan FROM detail_layanan ORDER BY id_user DESC LIMIT 1";
        String newId = "KR0001"; // jika data di database kosong pakai id ini
        try {
            preSmt = koneksi.prepareStatement(sql);
            rs = preSmt.executeQuery();
            if (rs.next()) {
                newId = f.generateId(rs.getString("id_bayar_layanan"));
            }
        } catch (SQLException e) {
            System.out.println("error generate new ID : " + e);
        }
        System.out.println("Generate new ID : " + newId);
        return newId;
    }


        public static void main(String[] args) {
            BayarLayananDao dao = new BayarLayananDao();
   
            BayarLayanan model = new BayarLayanan();
//            model.setNama("Bowo");
//            model.setIdKaryawan("KR0005");
//            model.setTglLahir("1998-02-02");
//            model.setBidangPekerjaan("Kepala Apotik");
//            model.setJenisKelamin("L");
//            model.setAlamat("Jakarta");
//            model.setNoHp("293892839");
//            model.setNoKtp("2837283");
//            model.setEmail("bowo@gmail.com");
//            model.setNoNpwp("0111111");
//            model.setIdUser("US001");
            
//            dao.simpanData(model,"edit");
//              dao.hapusData("KR0010");
            System.out.println(dao.getAllBayarLayanan());
        }
}
