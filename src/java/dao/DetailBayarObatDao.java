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
import model.DetailBayarObat;
import helper.*;
/**
 *
 * @author Lenovo
 */
public class DetailBayarObatDao {
    private final Connection koneksi;
        private PreparedStatement preSmt;
        private ResultSet rs;
        private Function f;
        // tanggal
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        public DetailBayarObatDao(){
            koneksi = Koneksi.getConnection();
            f = new Function();
        }

        public ArrayList<DetailBayarObat> getAllDetailBayarObat(){
            ArrayList<DetailBayarObat> listdbo = new ArrayList<>();
            try{
                String sql = "CALL getDetailBayarObat()";
                preSmt = koneksi.prepareStatement(sql);
                rs = preSmt.executeQuery();
                System.out.println("---- getting data ----");
                while(rs.next()){
                    DetailBayarObat model = new DetailBayarObat();
                    model.setId_obat(rs.getString("id_obat"));
                    model.setHarga(rs.getDouble("harga"));
                    model.setJumlah(rs.getDouble("jumlah"));
                    model.setId_pembayaran(rs.getString("id_pembayaran"));
                       
                    listdbo.add(model);
                    
                    System.out.println("     id : "+rs.getString("id_pembayaran"));
                }
            }
            catch(SQLException e){
                System.out.println("Kesalahan mengambil data : " + e);
            }
            return listdbo;
        }

        public DetailBayarObat getRecordById(String pembayaran, String obat){
            DetailBayarObat model = new DetailBayarObat();
            String sqlSearch = "select * from karyawan where id_pembayaran=? AND id_obat=?";
            try {
                preSmt = koneksi.prepareStatement(sqlSearch);
                preSmt.setString(1, pembayaran);
                preSmt.setString(2, obat);
                rs = preSmt.executeQuery();
                if (rs.next()){
                    model.setId_pembayaran(rs.getString("id_pembayaran"));
                    model.setId_obat(rs.getString("id_obat"));
                    model.setHarga(rs.getDouble("harga"));
                    model.setJumlah(rs.getDouble("jumlah"));
                }
            }
            catch (SQLException se){
                System.out.println("kesalahan pada : " + se);
            }
            return model;
        }
//
        public void simpanData(DetailBayarObat kar, String page){
            String sqlSimpan = null;
            if (page.equals("edit")){
                sqlSimpan = "CALL updateDetailBayarObat(?,?,?,?";
            }
            else if (page.equals("tambah")){
                sqlSimpan = "CALL addDetailBayarObat(?,?,?,?)";
            }
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
            try {
                String id = getNewId();
                preSmt = koneksi.prepareStatement(sqlSimpan);
                preSmt.setString(1, kar.getId_obat());
                preSmt.setDouble(2, kar.getHarga());
                preSmt.setDouble(3, kar.getJumlah());
                preSmt.setString(4, kar.getId_pembayaran());
//                preSmt.setString(11, page == "tambah" ? id : kar.getIdKaryawan());
                preSmt.executeUpdate();
                System.out.println(page == "tambah" ? "success add data" : "success update data");

            }
            catch (SQLException se){
                System.out.println("ada kesalahan : " + se);
            }
        }

        public void hapusData(String id){
            String sqlHapus = "CALL deleteKaryawan(?)";
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
            DetailBayarObatDao dao = new DetailBayarObatDao();
   
            DetailBayarObat model = new DetailBayarObat();
            model.setId_pembayaran("PY0002");
            model.setId_obat("OB0002");
            model.setHarga(200.00);
            model.setJumlah(2.00);
            dao.simpanData(model, "tambah");
            
//            dao.simpanData(model,"edit");
//              dao.hapusData("KR0010");
            System.out.println(dao.getAllDetailBayarObat());
        }
}
