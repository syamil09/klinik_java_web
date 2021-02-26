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
import model.RekamMedik;

/**
 *
 * @author syamil imdad
 */
public class RekamMedikDao {
        private final Connection koneksi;
        private PreparedStatement preSmt;
        private ResultSet rs;
        private Function f;
        // tanggal
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        public RekamMedikDao(){
            koneksi = Koneksi.getConnection();
            f = new Function();
        }

        public ArrayList<RekamMedik> getAllKaryawan(){
            ArrayList<RekamMedik> listKaryawan = new ArrayList<>();
            try{
                String sql = "CALL getRekamMedik()";
                preSmt = koneksi.prepareStatement(sql);
                rs = preSmt.executeQuery();
                System.out.println("---- getting data ----");
                while(rs.next()){
                    RekamMedik model = new RekamMedik();
//                    model.setId_pasien(rs.getString("id_pasien"));
                  
                    model.setId_pendaftaran(rs.getString("id_pendaftaran"));
                 
                    model.setTek_darah(rs.getString("tek_darah"));
                    model.setBerat(rs.getDouble("berat"));
                    model.setTinggi(rs.getDouble("tinggi"));
                    model.setKeluhan(rs.getString("keluhan"));
                    model.setTindakan(rs.getString("tindakan"));
                    model.setSaran(rs.getString("saran"));
                    model.setId_user(rs.getString("id_user"));
                    model.setId_dokter(rs.getString("id_dokter"));
                    model.setId_resep(rs.getString("id_resep"));
                    model.setDiagnosa(rs.getString("diagnosa"));
                    listKaryawan.add(model);
                    
                    System.out.println("     id : "+rs.getString("id_pendaftaran"));
                }
            }
            catch(SQLException e){
                System.out.println("Kesalahan mengambil data : " + e);
            }
            return listKaryawan;
        }

        public RekamMedik getRecordByNIK(String nik){
            RekamMedik kar = new RekamMedik();
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
            return kar;
        }
//
        public void simpanData(RekamMedik kar, String page){
            String sqlSimpan = null;
            if (page.equals("edit")){
                sqlSimpan = "CALL updateRekamMedik(?,?,?,?,?,?,?,?,?,?,?,?)";
            }
            else if (page.equals("tambah")){
                sqlSimpan = "CALL addRekamMedik(?,?,?,?,?,?,?,?,?,?,?,?)";
            }
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
            try {
                String id = getNewId();
                preSmt = koneksi.prepareStatement(sqlSimpan);
                // preSmt.setString(1, kar.getId_pasien());
           
                preSmt.setString(1, kar.getTek_darah());
                preSmt.setDouble(2, kar.getBerat());
                preSmt.setDouble(3, kar.getTinggi());
                preSmt.setString(4, kar.getKeluhan());
                preSmt.setString(5, kar.getTindakan());
                preSmt.setString(6, kar.getSaran());
                preSmt.setString(7, kar.getId_dokter());
                preSmt.setString(8, kar.getId_resep());
                preSmt.setString(9, kar.getDiagnosa());
                preSmt.setString(10, kar.getId_user());
                preSmt.setString(11, kar.getId_pendaftaran());
                preSmt.setString(12, kar.getTgl_daftar());
                preSmt.executeUpdate();
                System.out.println(page == "tambah" ? "success add data" : "success update data");

            }
            catch (SQLException se){
                System.out.println("ada kesalahan : " + se);
            }
        }

        public void hapusData(String id, String tglDaftar){
            String sqlHapus = "CALL deleteRekamMedik(?,?)";
            System.out.println("---- deleting data ----");
            try{
                preSmt = koneksi.prepareStatement(sqlHapus);
                preSmt.setString(1, id);
                preSmt.setString(2, tglDaftar);
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
            try {
                preSmt = koneksi.prepareStatement(sql);
                rs = preSmt.executeQuery();
                if (rs.next()) {
                    newId = f.generateId(rs.getString("id_karyawan"));
                }
            } catch (SQLException e) {
                System.out.println("error generate new ID : " + e);
            }
            System.out.println("Generate new ID : " + newId);
            return newId;
        }


        public static void main(String[] args) {
            RekamMedikDao dao = new RekamMedikDao();
   
            RekamMedik model = new RekamMedik();
            model.setId_pasien("PS001");
            model.setTgl_daftar("2021-02-21");
            model.setId_pendaftaran("PLG001");
            model.setTek_darah("170");
            model.setBerat(23.0);
            model.setTinggi(23.00);
            model.setKeluhan("Batuk Batuk");
            model.setTindakan("Operasi");
            model.setSaran("Istirahat");
            model.setId_user("US001");
            model.setId_dokter("DK001");
            model.setId_resep("RSP008");
            model.setDiagnosa("Covid");
            
//            dao.simpanData(model,"tambah");
              dao.hapusData("PLG001", "2021-02-21");
            System.out.println(dao.getAllKaryawan());
        }
}
