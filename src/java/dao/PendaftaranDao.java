/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.Koneksi;
import helper.Function;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Pendaftaran;

/**
 *
 * @author syamil imdad
 */
public class PendaftaranDao {
        private final Connection koneksi;
        private PreparedStatement preSmt;
        private ResultSet rs;
        private Function f;
        // tanggal
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        public PendaftaranDao(){
            koneksi = Koneksi.getConnection();
            f = new Function();
        }

        public ArrayList<Pendaftaran> getAll(){
            ArrayList<Pendaftaran> list = new ArrayList<>();
            try{
                String sql = "CALL getPendaftaran()";
                preSmt = koneksi.prepareStatement(sql);
                rs = preSmt.executeQuery();
                System.out.println("---- getting data ----");
                while(rs.next()){
                    Pendaftaran model = new Pendaftaran();        
                    model.setNoAntrian(rs.getString("no_antrian")); 
                    model.setIdPasien(rs.getString("id_pasien"));
                    model.setIdPoli(rs.getString("id_poli"));
                    model.setTglDaftar(rs.getString("tgl_daftar"));
                    model.setKeterangan(rs.getString("keterangan"));
                    model.setUserId(rs.getString("user_id"));          
                    list.add(model);
                    
                    System.out.println("     id : "+rs.getString("no_antrian"));
                }
            }
            catch(SQLException e){
                System.out.println("Kesalahan mengambil data : " + e);
            }
            return list;
        }

        public Pendaftaran getRecordByNIK(String nik, String tgl){
            Pendaftaran kar = new Pendaftaran();
            String sqlSearch = "SELECT * FROM pendaftaran WHERE no_antrian=? AND tgl_daftar=?";
            try {
                preSmt = koneksi.prepareStatement(sqlSearch);
                preSmt.setString(1, nik);
                preSmt.setString(2, tgl);
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
        public void simpanData(Pendaftaran model, String page){
            String sqlSimpan = null;
            if (page.equals("edit")){
                sqlSimpan = "CALL updatePendaftaran(?,?,?,?,?,?,?)";
            }
            else if (page.equals("tambah")){
                sqlSimpan = "CALL addPendaftaran(?,?,?,?,?,?)";
            }
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
            try {
                String date = java.time.LocalDate.now().toString();
                // date = "2021-02-25";
                String id = getNewId(model.getIdPoli(), date);
                preSmt = koneksi.prepareStatement(sqlSimpan);

                preSmt.setString(1, id);
                preSmt.setString(2, model.getIdPasien());
                preSmt.setString(3, model.getIdPoli());
                preSmt.setString(4, model.getTglDaftar().equals("") ? date  : model.getTglDaftar());
                preSmt.setString(5, model.getKeterangan());
                preSmt.setString(6, model.getUserId());
                if (page == "edit") {
                    preSmt.setInt(7, model.getDeleted());
                }
                preSmt.executeUpdate();
                System.out.println(page == "tambah" ? "success add data" : "success update data");

            }
            catch (SQLException se){
                System.out.println("ada kesalahan : " + se);
            }
        }

        public void hapusData(String id){
            String sqlHapus = "CALL deletePendaftaran(?)";
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
        
        public String getNewId(String idPoli, String date) {
        String sql = "SELECT no_antrian FROM pendaftaran WHERE id_poli=? AND tgl_daftar=? ORDER BY no_antrian DESC LIMIT 1";
        String newId = idPoli+"001"; // jika data di database kosong pakai id ini
        
        try {      
            preSmt = koneksi.prepareStatement(sql);
            preSmt.setString(1, idPoli);
            preSmt.setString(2, date);
            rs = preSmt.executeQuery();
            if (rs.next()) {
                newId = f.generateId(rs.getString("no_antrian"));
            }
        } catch (SQLException e) {
            System.out.println("error generate new ID : " + e);
        }
        System.out.println("date    : "+date);
        System.out.println("id poli : "+idPoli);
        System.out.println("Generate new no_antrian : " + newId);
            
        return newId;
    }


        public static void main(String[] args) {
            PendaftaranDao dao = new PendaftaranDao();
   
            Pendaftaran model = new Pendaftaran();
//            model.setNoAntrian(""); 
            model.setIdPasien("PS001");
            model.setIdPoli("PLU");
            model.setTglDaftar("");
            model.setKeterangan("Sakit Gigi");
            model.setUserId("US001");
            
//            dao.simpanData(model,"tambah");
//              dao.hapusData("AA4");
//            System.out.println(dao.getNewId("PLG"));
            System.out.println(dao.getAll());
            System.out.println(dao.f.generateId("ID999"));
        }
}
