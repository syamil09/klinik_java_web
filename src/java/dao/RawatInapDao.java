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
import model.RawatInap;
import helper.*;
/**
 *
 * @author syamil imdad
 */
public class RawatInapDao {
        private final Connection koneksi;
        private PreparedStatement preSmt;
        private ResultSet rs;
        private Function f;
        // tanggal
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        public RawatInapDao(){
            koneksi = Koneksi.getConnection();
            f = new Function();
        }

        public ArrayList<RawatInap> getAllRawatInap(){
            ArrayList<RawatInap> listData = new ArrayList<>();
            try{
                String sql = "CALL getRawatInap()";
                preSmt = koneksi.prepareStatement(sql);
                rs = preSmt.executeQuery();
                System.out.println("---- getting data ----");
                while(rs.next()){
                    // tipe data varchar : getString
                    // date : getString()
                    // int : getInt()
                    // decimal : getDouble()
                  
                    RawatInap model = new RawatInap();
                    model.setId_kamar(rs.getString("id_kamar"));
                    model.setId_pasien(rs.getString("id_pasien"));
                    model.setId_kamar(rs.getString("id_kamar"));
                    model.setTgl_cekin(rs.getString("tgl_cekin"));
                    model.setTgl_cekout(rs.getString("tgl_cekout"));
                    model.setKeterangan(rs.getString("keterangan"));
     
                    listData.add(model);
                    
                    System.out.println("     id : "+rs.getString("id_rawat"));
                }
            }
            catch(SQLException e){
                System.out.println("Kesalahan mengambil data : " + e);
            }
            return listData;
        }

        public RawatInap getRecordByNIK(String nik){
            RawatInap kar = new RawatInap();
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
        public void simpanData(RawatInap kar, String page){
            String sqlSimpan = null;
            if (page.equals("edit")){
                sqlSimpan = "CALL updateRawatInap(?,?,?,?,?,?)";
            }
            else if (page.equals("tambah")){
                sqlSimpan = "CALL addRawatInap(?,?,?,?,?,?)";
            }
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
            try {
                String id = getNewId();
                preSmt = koneksi.prepareStatement(sqlSimpan);
                preSmt.setString(1, kar.getId_pasien());
                preSmt.setString(2, kar.getId_kamar());
                preSmt.setString(3, kar.getTgl_cekin());
                preSmt.setString(4, kar.getTgl_cekout());
                preSmt.setString(5, kar.getKeterangan());
//                preSmt.setString(6, kar.getId_rawat());
                preSmt.setString(6, page == "tambah" ? id : kar.getId_rawat());
                preSmt.executeUpdate();
                System.out.println(page == "tambah" ? "success add data" : "success update data");

            }
            catch (SQLException se){
                System.out.println("ada kesalahan : " + se);
            }
        }

        public void hapusData(String id){
            String sqlHapus = "CALL deleteRawatInap(?)";
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
        String sql = "SELECT id_rawat FROM rawat_inap ORDER BY id_rawat DESC LIMIT 1";
        String newId = "RT0001"; // jika data di database kosong pakai id ini
        try {
            preSmt = koneksi.prepareStatement(sql);
            rs = preSmt.executeQuery();
            if (rs.next()) {
                newId = f.generateId(rs.getString("id_rawat"));
            }
        } catch (SQLException e) {
            System.out.println("error generate new ID : " + e);
        }
        System.out.println("Generate new ID : " + newId);
        return newId;
    }


        public static void main(String[] args) {
            RawatInapDao dao = new RawatInapDao();
   
            RawatInap model = new RawatInap();
            model.setId_rawat("RT0006");
            model.setId_kamar("KM0001");
            model.setId_pasien("PS001");
            model.setTgl_cekin("1998-02-02");
            model.setTgl_cekout("1998-02-02");
            model.setKeterangan("ketereangan");
            dao.simpanData(model, "tambah");
            
//            dao.simpanData(model,"edit");
//              dao.hapusData("RT0005");
            System.out.println(dao.getAllRawatInap());
           
        }
}
