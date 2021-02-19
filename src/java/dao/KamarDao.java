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
import model.Kamar;
import helper.*;

public class KamarDao {
        private final Connection koneksi;
        private PreparedStatement preSmt;
        private ResultSet rs;
        private Function f;
        // tanggal
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        public KamarDao(){
            koneksi = Koneksi.getConnection();
            f = new Function();
        }

        public ArrayList<Kamar> getAllKamar(){
            ArrayList<Kamar> listKamar = new ArrayList<>();
            try{
                String sqlAllKamar = "CALL getKamar()";
                preSmt = koneksi.prepareStatement(sqlAllKamar);
                rs = preSmt.executeQuery();
                System.out.println("---- getting data ----");
                while(rs.next()){
//  varchar : getString()
//  date : getString()
// int : getInt()
//decimal : getDouble()

                    Kamar model = new Kamar();
                    model.setIdKamar(rs.getString("id_kamar"));
                    model.setNamaRuang(rs.getString("nama_ruang"));
                    model.setNoKamar(rs.getString("no_kamar"));
                    model.setKelas(rs.getString("kelas"));
                    model.setHargaPerhari(rs.getDouble("harga_perhari"));
                    model.setDesKamar(rs.getString("des_kamar"));
                    model.setKapasitas(rs.getInt("kapasitas"));
                    model.setTerisi(rs.getInt("terisi"));
                    model.setStatus(rs.getString("status"));
                    listKamar.add(model);
                    
                    System.out.println("     id : "+rs.getString("id_kamar"));
                }
            }
            catch(SQLException e){
                System.out.println("Kesalahan mengambil data : " + e);
            }
            return listKamar;
        }

        public Kamar getRecordByNIK(String nik){
            Kamar kar = new Kamar();
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
        public void simpanData(Kamar kar, String page){
            String sqlSimpan = null;
            if (page.equals("edit")){
                sqlSimpan = "CALL updateKamar(?,?,?,?,?,?,?,?,?)";
            }
            else if (page.equals("tambah")){
                sqlSimpan = "CALL addKamar(?,?,?,?,?,?,?,?,?)";
            }
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
            try {
                String id = getNewId();
                preSmt = koneksi.prepareStatement(sqlSimpan);
                preSmt.setString(1, kar.getNoKamar());
                preSmt.setString(2, kar.getKelas());
                preSmt.setDouble(3, kar.getHargaPerhari());
                preSmt.setString(4, kar.getDesKamar());
                preSmt.setInt(5, kar.getKapasitas());
                preSmt.setInt(6, kar.getTerisi());
                preSmt.setString(7, kar.getStatus());
                preSmt.setString(8, kar.getNamaRuang());
                preSmt.setString(9, kar.getIdKamar());
                preSmt.setString(11, page == "tambah" ? id : kar.getIdKamar());
                preSmt.executeUpdate();
                System.out.println(page == "tambah" ? "success add data" : "success update data");

            }
            catch (SQLException se){
                System.out.println("ada kesalahan : " + se);
            }
        }

        public void hapusData(String id){
            String sqlHapus = "CALL deleteKamar(?)";
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
            KamarDao dao = new KamarDao();
   
            Kamar model = new Kamar();
            model.setIdKamar("002");
            model.setNamaRuang("mawar");
            model.setNoKamar("21");
            model.setKelas("Ekonomi");
            model.setHargaPerhari(200000.00);
            model.setKapasitas(8);
            model.setTerisi(1);
            model.setStatus("full");
            
//            dao.simpanData(model,"edit");
//              dao.hapusData("KR0010");
            System.out.println(dao.getAllKamar());
        }
    
}
