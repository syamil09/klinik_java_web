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
import model.Supplier;
import helper.*;

/**
 *
 * @author Lenovo
 */
public class SupplierDao {
    private final Connection koneksi;
        private PreparedStatement preSmt;
        private ResultSet rs;
        private Function f;
        // tanggal
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        public SupplierDao(){
            koneksi = Koneksi.getConnection();
            f = new Function();
        }

        public ArrayList<Supplier> getAllSupplier(){
            ArrayList<Supplier> listSupplier = new ArrayList<>();
            try{
                String sql = "CALL getSupplier()";
                preSmt = koneksi.prepareStatement(sql);
                rs = preSmt.executeQuery();
                System.out.println("---- getting data ----");
                while(rs.next()){
                    Supplier model = new Supplier();
                    model.setId_supplier(rs.getString("id_supplier"));
                    model.setNama_suplier(rs.getString("nama_suplier"));
                       
                    listSupplier.add(model);
                    
                    System.out.println("     id : "+rs.getString("id_supplier"));
                }
            }
            catch(SQLException e){
                System.out.println("Kesalahan mengambil data : " + e);
            }
            return listSupplier;
        }

        public Supplier getRecordByNIK(String nik){
            Supplier kar = new Supplier();
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
        public void simpanData(Supplier kar, String page){
            String sqlSimpan = null;
            if (page.equals("edit")){
                sqlSimpan = "CALL updateSupplier(?,?,?,?,?,?,?)";
            }
            else if (page.equals("tambah")){
                sqlSimpan = "CALL addSupplier(?,?,?,?,?,?,?)";
            }
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
            try {
                String id = getNewId();
                preSmt = koneksi.prepareStatement(sqlSimpan);
                preSmt.setString(1, kar.getNama_suplier());
                preSmt.setString(2, kar.getAlamat());
                preSmt.setString(3, kar.getNo_telepon());
                preSmt.setString(4, kar.getEmail());
                preSmt.setString(5, kar.getUser_id());
                preSmt.setString(6, kar.getWaktu());
                preSmt.setString(7, kar.getId_supplier());
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
            SupplierDao dao = new SupplierDao();
   
            Supplier model = new Supplier();
            model.setId_supplier("SP0002");
            model.setNama_suplier("PT.Damai");
            model.setAlamat("bekasi");
            model.setNo_telepon("01818122");
            model.setEmail("8222771123");
            model.setUser_id("US002");
            model.setWaktu("2021-02-19");
            dao.simpanData(model, "tambah");
            
//            dao.simpanData(model,"edit");
//              dao.hapusData("KR0010");
            System.out.println(dao.getAllSupplier());
        }
}
