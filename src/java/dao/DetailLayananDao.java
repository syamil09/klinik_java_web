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
import model.DetailLayanan;
import helper.*;

public class DetailLayananDao {
    private final Connection koneksi;
        private PreparedStatement preSmt;
        private ResultSet rs;
        private Function f;
        // tanggal
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        public DetailLayananDao(){
            koneksi = Koneksi.getConnection();
            f = new Function();
        }

        public ArrayList<DetailLayanan> getAllDetailLayanan(){
            ArrayList<DetailLayanan> listDetailLayanan = new ArrayList<>();
            try{
                String sqlAllDetailLayanan = "CALL getDetailLayanan()";
                preSmt = koneksi.prepareStatement(sqlAllDetailLayanan);
                rs = preSmt.executeQuery();
                System.out.println("---- getting data ----");
                while(rs.next()){
                    DetailLayanan model = new DetailLayanan();
                    model.setId_layanan(rs.getString("id_layanan"));
                    model.setId_detail_layanan(rs.getString("id_detail_layanan"));
                    model.setDes_detail_layanan(rs.getString("des_detail_layanan"));
                    model.setBiaya_layan(rs.getString("biaya_layanan"));
                    model.setKeterangan(rs.getString("keterangan")); 
                    listDetailLayanan.add(model);
                    
                    System.out.println("     id : "+rs.getString("id_detail_layanan"));
                }
            }
            catch(SQLException e){
                System.out.println("Kesalahan mengambil data : " + e);
            }
            return listDetailLayanan;
        }

        public DetailLayanan getRecordByNIK(String nik){
            DetailLayanan kar = new DetailLayanan();
            String sqlSearch = "select * from detail_layanan where nik=?";
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
        public void simpanData(DetailLayanan kar, String page){
            String sqlSimpan = null;
            if (page.equals("edit")){
                sqlSimpan = "CALL updateDetailLayanan(?,?,?,?,?)";
            }
            else if (page.equals("tambah")){
                sqlSimpan = "CALL addDetailLayanan(?,?,?,?,?)";
            }
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
            try {
                String id = getNewId();
                preSmt = koneksi.prepareStatement(sqlSimpan);
                preSmt.setString(1, kar.getId_layanan());
                preSmt.setString(2, kar.getId_detail_layanan());
                preSmt.setString(3, kar.getDes_detail_layanan());
                preSmt.setString(4, kar.getBiaya_layan());
                preSmt.setString(5, kar.getKeterangan());
                preSmt.setString(11, page == "tambah" ? id : kar.getId_detail_layanan());
                preSmt.executeUpdate();
                System.out.println(page == "tambah" ? "success add data" : "success update data");

            }
            catch (SQLException se){
                System.out.println("ada kesalahan : " + se);
            }
        }

        public void hapusData(String id){
            String sqlHapus = "CALL deleteDetailLayanan(?)";
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
        String sql = "SELECT id_detail_layanan FROM detail_layanan ORDER BY id_user DESC LIMIT 1";
        String newId = "KR0001"; // jika data di database kosong pakai id ini
        try {
            preSmt = koneksi.prepareStatement(sql);
            rs = preSmt.executeQuery();
            if (rs.next()) {
                newId = f.generateId(rs.getString("id_detail_layanan"));
            }
        } catch (SQLException e) {
            System.out.println("error generate new ID : " + e);
        }
        System.out.println("Generate new ID : " + newId);
        return newId;
    }


        public static void main(String[] args) {
            KaryawanDao dao = new KaryawanDao();
   
//            DetailLayanan model = new DetailLayanan();
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
//            System.out.println(dao.getAllDetailLayanan());
        }
}
