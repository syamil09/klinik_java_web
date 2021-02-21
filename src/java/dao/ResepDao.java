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
import model.Resep;
import helper.*;




public class ResepDao {
      private final Connection koneksi;
        private PreparedStatement preSmt;
        private ResultSet rs;
        private Function f;
        // tanggal
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        public ResepDao(){
            koneksi = Koneksi.getConnection();
            f = new Function();
        }

        public ArrayList<Resep> getAllResep(){
            ArrayList<Resep> listData = new ArrayList<>();
            try{
                String sql = "CALL getResep()";
                preSmt = koneksi.prepareStatement(sql);
                rs = preSmt.executeQuery();
                System.out.println("---- getting data ----");
                while(rs.next()){
                    Resep model = new Resep();
                    model.setId_resep(rs.getString("id_resep"));
                    model.setId_dokter(rs.getString("id_dokter"));
                    model.setTgl_resep(rs.getString("tgl_resep"));
                    model.setId_poli(rs.getString("id_poli"));
                    model.setUser_id(rs.getString("user_id"));
                    model.setWaktu(rs.getString("waktu"));
                
                    
               
                    listData.add(model);
                    
                    System.out.println("     id : "+rs.getString("id_resep"));
                }
            }
            catch(SQLException e){
                System.out.println("Kesalahan mengambil data : " + e);
            }
            return listData;
        }

        public Resep getRecordByNIK(String nik){
            Resep res = new Resep();
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
        public void simpanData(Resep res, String page){
            String sqlSimpan = null;
            if (page.equals("edit")){
                sqlSimpan = "CALL updateResep(?,?,?,?,?)";
            }
            else if (page.equals("tambah")){
                sqlSimpan = "CALL addResep(?,?,?,?,?)";
            }
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
            try {
                String id = getNewId();
                preSmt = koneksi.prepareStatement(sqlSimpan);
              
                preSmt.setString(1, res.getId_dokter());
                  preSmt.setString(2, res.getTgl_resep());
                    preSmt.setString(3, res.getId_poli());
                      preSmt.setString(4, res.getUser_id());
                        preSmt.setString(5, res.getId_resep());
                    
                
//              preSmt.setString(11, page == "tambah" ? id : lay.getIdLayanan());
                preSmt.executeUpdate();
                System.out.println(page == "tambah" ? "success add data" : "success update data");

            }
            catch (SQLException se){
                System.out.println("ada kesalahan : " + se);
            }
        }

        public void hapusData(String id){
            String sqlHapus = "CALL deleteResep(?)";
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
            ResepDao dao = new ResepDao();
   
            Resep model = new Resep();
              model.setId_resep("5");
              model.setId_dokter("12");
              model.setTgl_resep("2021-09-12");
              model.setId_poli("12");
              model.setUser_id("21");
          
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
            System.out.println(dao.getAllResep());
        }
}
