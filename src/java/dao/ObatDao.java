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
import model.Obat;
import helper.*;

public class ObatDao {
     private final Connection koneksi;
        private PreparedStatement preSmt;
        private ResultSet rs;
        private Function f;
        // tanggal
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        public ObatDao(){
            koneksi = Koneksi.getConnection();
            f = new Function();
        }

        public ArrayList<Obat> getAllObat(){
            ArrayList<Obat> listData = new ArrayList<>();
            try{
                String sql = "CALL getObat()";
                preSmt = koneksi.prepareStatement(sql);
                rs = preSmt.executeQuery();
                System.out.println("---- getting data ----");
                while(rs.next()){
                    // tipe data varchar : getString
                    // date : getString()
                    // int : getInt()
                    // decimal : getDouble()
                  
                    Obat model = new Obat();
                    model.setIdObat(rs.getString("id_obat"));
                    model.setNamaObat(rs.getString("nama_obat"));
                    model.setSatuan(rs.getString("satuan"));
                    model.setStok(rs.getDouble("stok"));
                    model.setHargaJual(rs.getDouble("harga_jual"));
                    model.setWaktu(rs.getString("waktu"));
                    model.setIdUser(rs.getString("user_id"));
     
                    listData.add(model);
                    
                    System.out.println("     id : "+rs.getString("id_obat"));
                }
            }
            catch(SQLException e){
                System.out.println("Kesalahan mengambil data : " + e);
            }
            return listData;
        }

        public Obat getRecordByNIK(String nik){
            Obat kar = new Obat();
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
        public void simpanData(Obat kar, String page){
            String sqlSimpan = null;
            if (page.equals("edit")){
                sqlSimpan = "CALL updateObat(?,?,?,?,?,?,?)";
            }
            else if (page.equals("tambah")){
                sqlSimpan = "CALL addObat(?,?,?,?,?,?,?)";
            }
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
            try {
                String id = getNewId();
                preSmt = koneksi.prepareStatement(sqlSimpan); 
                preSmt.setString(1, kar.getNamaObat());
                preSmt.setString(2, kar.getSatuan());
                preSmt.setDouble(3, kar.getStok());
                preSmt.setDouble(4, kar.getHargaJual());
                preSmt.setString(5, kar.getWaktu());
                preSmt.setString(6, kar.getIdUser());
                preSmt.setString(7, kar.getIdObat());
//                preSmt.setString(6, kar.getId_rawat());
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
            ObatDao dao = new ObatDao();
   
            Obat model = new Obat();
            model.setIdObat("OB0002");
            model.setNamaObat("Paramex");
            model.setSatuan("2");
            model.setStok(2.00);
            model.setHargaJual(200.00);
            model.setWaktu("2021-02-02");
            model.setIdUser("US0002");
            dao.simpanData(model, "tambah");
            
//            dao.simpanData(model,"edit");
//              dao.hapusData("RT0005");
            System.out.println(dao.getAllObat());
           
        }

}
