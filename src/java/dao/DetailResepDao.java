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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import helper.*;
import model.DetailResep;




public class DetailResepDao {
      private final Connection koneksi;
        private PreparedStatement preSmt;
        private ResultSet rs;
        private Function f;
        // tanggal
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        public DetailResepDao(){
            koneksi = Koneksi.getConnection();
            f = new Function();
        }

        public ArrayList<DetailResep> getAllDetailResep(){
            ArrayList<DetailResep> listData = new ArrayList<>();
            try{
                String sql = "CALL getDetail_Resep()";
                preSmt = koneksi.prepareStatement(sql);
                rs = preSmt.executeQuery();
                System.out.println("---- getting data ----");
                while(rs.next()){
                    DetailResep model = new DetailResep();
                    model.setId_resep(rs.getString("id_resep"));
                    model.setId_obat(rs.getString("id_obat"));
                    model.setHarga(rs.getDouble("harga"));
                    model.setJumlah(rs.getDouble("jumlah"));
                      model.setKeterangan(rs.getString("keterangan"));
                    model.setUser_id(rs.getString("user_id"));
                  
                
                    
               
                    listData.add(model);
                    
                    System.out.println("     id : "+rs.getString("id_resep"));
                }
            }
            catch(SQLException e){
                System.out.println("Kesalahan mengambil data : " + e);
            }
            return listData;
        }

        public DetailResep getRecordByNIK(String nik){
            DetailResep res = new DetailResep();
            String sqlSearch = "select * from karyawan where nik=?";
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
            return res;
        }
//
        public void simpanData(DetailResep res, String page){
            String sqlSimpan = null;
            if (page.equals("edit")){
                sqlSimpan = "CALL updateDetail_Resep(?,?,?,?,?,?)";
            }
            else if (page.equals("tambah")){
                sqlSimpan = "CALL addDetail_Resep(?,?,?,?,?,?)";
            }
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
            try {
                String id = getNewId();
                preSmt = koneksi.prepareStatement(sqlSimpan);
              
                preSmt.setString(1, res.getId_obat());
                  preSmt.setDouble(2, res.getHarga());
                    preSmt.setDouble(3, res.getJumlah());
                      preSmt.setString(4, res.getKeterangan());
                        preSmt.setString(5, res.getUser_id());
                        preSmt.setString(6, res.getId_resep());
                    
                
//              preSmt.setString(11, page == "tambah" ? id : lay.getIdLayanan());
                preSmt.executeUpdate();
                System.out.println(page == "tambah" ? "success add data" : "success update data");

            }
            catch (SQLException se){
                System.out.println("ada kesalahan : " + se);
            }
        }

        public void hapusData(String id){
            String sqlHapus = "CALL deleteDetail_Resep(?)";
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
        String sql = "SELECT id_karyawan FROM karyawan ORDER BY id_user DESC LIMIT 1";
        String newId = "KR0001"; // jika data di database kosong pakai id ini
//        try {
//            preSmt = koneksi.prepareStatement(sql);
//            rs = preSmt.executeQuery();
//            if (rs.next()) {
//                newId = f.generateId(rs.getString("id_karyawan"));
//            }
//        } catch (SQLException e) {
//            System.out.println("error generate new ID : " + e);
//        }
        System.out.println("Generate new ID : " + newId);
        return newId;
    }


        public static void main(String[] args) {
            DetailResepDao dao = new DetailResepDao();
   
            DetailResep model = new DetailResep();
              model.setId_resep("5");
              model.setId_obat("3");
              model.setHarga(200000.00);
              model.setJumlah(234.00);
              model.setKeterangan("hai");
               model.setUser_id("34");
          
//            model.setTglLahir("1998-02-02");
//            model.setBidangPekerjaan("Kepala Apotik");
//            model.setJenisKelamin("L");
//            model.setAlamat("Jakarta");
//            model.setNoHp("293892839");
//            model.setNoKtp("2837283");
//            model.setEmail("bowo@gmail.com");
//            model.setNoNpwp("0111111");
//            model.setIdUser("US001");
            
            dao.simpanData(model,"tambah");
//              dao.hapusData("KR0010");
            System.out.println(dao.getAllDetailResep());
        }
}
