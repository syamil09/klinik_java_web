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
import model.Dokter;
import helper.*;
/**
 *
 * @author Lenovo
 */
public class DokterDao {
    private final Connection koneksi;
        private PreparedStatement preSmt;
        private ResultSet rs;
        private Function f;
        // tanggal
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        public DokterDao(){
            koneksi = Koneksi.getConnection();
            f = new Function();
        }

        public ArrayList<Dokter> getAllDokter(){
            ArrayList<Dokter> listDokter = new ArrayList<>();
            try{
                String sql = "CALL getDokter()";
                preSmt = koneksi.prepareStatement(sql);
                rs = preSmt.executeQuery();
                System.out.println("---- getting data ----");
                while(rs.next()){
                    Dokter model = new Dokter();
                    model.setIdDokter(rs.getString("id_dokter"));
                    model.setNamaDokter(rs.getString("nama_dokter"));
                    model.setTglLahir(rs.getString("tgl_lahir"));
                    model.setIdPoli(rs.getString("id_poli"));
                    model.setJenisKelamin(rs.getString("jenis_kelamin"));
                    model.setAlamat(rs.getString("alamat"));
                    model.setNoHp(rs.getString("no_hp"));
                    model.setNoKtp(rs.getString("no_ktp"));
                    model.setSpesialis(rs.getString("spesialis"));
                    model.setPassword(rs.getString("password"));
                    model.setEmail(rs.getString("email"));
                    model.setNoNpwp(rs.getString("no_npwp"));
                    model.setUserId(rs.getString("user_id"));
                    model.setWaktu(rs.getString("waktu"));
                    listDokter.add(model);
                    
                    System.out.println("     id : "+rs.getString("id_dokter"));
                }
            }
            catch(SQLException e){
                System.out.println("Kesalahan mengambil data : " + e);
            }
            return listDokter;
        }

        public Dokter getRecordByNIK(String nik){
            Dokter kar = new Dokter();
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
        public void simpanData(Dokter kar, String page){
            String sqlSimpan = null;
            if (page.equals("edit")){
                sqlSimpan = "CALL updateDokter(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }
            else if (page.equals("tambah")){
                sqlSimpan = "CALL addDokter(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
            try {
                String id = getNewId();
                preSmt = koneksi.prepareStatement(sqlSimpan);
                preSmt.setString(1, kar.getNamaDokter());
                preSmt.setString(2, kar.getTglLahir());
                preSmt.setString(3, kar.getIdPoli());
                preSmt.setString(4, kar.getJenisKelamin());
                preSmt.setString(5, kar.getAlamat());
                preSmt.setString(6, kar.getNoHp());
                preSmt.setString(7, kar.getNoKtp());
                preSmt.setString(8, kar.getSpesialis());
                preSmt.setString(9, kar.getPassword());
                preSmt.setString(10, kar.getEmail());
                preSmt.setString(11, kar.getNoNpwp());
                preSmt.setString(12, kar.getUserId());
                preSmt.setString(13, kar.getWaktu());
                preSmt.setString(14, kar.getIdDokter());
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
            DokterDao dao = new DokterDao();
   
            Dokter model = new Dokter();
            model.setNamaDokter("Marie");
            model.setTglLahir("2002-02-02");
            model.setIdPoli("P1");
            model.setJenisKelamin("P");
            model.setAlamat("Bekasi");
            model.setNoHp("08117881722");
            model.setNoKtp("918273645");
            model.setSpesialis("gigi");
            model.setPassword("987");
            model.setEmail("marie@gmail.com");
            model.setNoNpwp("2736891");
            model.setUserId("US0001");
            model.setWaktu("2021-02-20");
            model.setIdDokter("D02");
            dao.simpanData(model, "tambah");
            
//            dao.simpanData(model,"edit");
//              dao.hapusData("KR0010");
            System.out.println(dao.getAllDokter());
        }
}
