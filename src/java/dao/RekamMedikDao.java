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
                String sqlAllKaryawan = "CALL getKaryawan()";
                preSmt = koneksi.prepareStatement(sqlAllKaryawan);
                rs = preSmt.executeQuery();
                System.out.println("---- getting data ----");
                while(rs.next()){
                    RekamMedik model = new RekamMedik();
                    model.setId_pasien(rs.getString("id_pasien"));
                    model.setTgl_daftar(rs.getString("tgl_daftar"));
                    model.setId_pendaftaran(rs.getString("id_pendaftaran"));
                    model.setBidangPekerjaan(rs.getString("bidang_pekerjaan"));
                    model.setJenisKelamin(rs.getString("jenis_kelamin"));
                    model.setAlamat(rs.getString("alamat"));
                    model.setNoHp(rs.getString("no_hp"));
                    model.setNoKtp(rs.getString("no_ktp"));
                    model.setEmail(rs.getString("email"));
                    model.setNoNpwp(rs.getString("no_npwp"));
                    model.setIdUser(rs.getString("waktu"));
                    model.setDeleted(rs.getInt("deleted"));      
                    listKaryawan.add(model);
                    
                    System.out.println("     id : "+rs.getString("id_karyawan"));
                }
            }
            catch(SQLException e){
                System.out.println("Kesalahan mengambil data : " + e);
            }
            return listKaryawan;
        }

        public Karyawan getRecordByNIK(String nik){
            Karyawan kar = new Karyawan();
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
        public void simpanData(Karyawan kar, String page){
            String sqlSimpan = null;
            if (page.equals("edit")){
                sqlSimpan = "CALL updateKaryawan(?,?,?,?,?,?,?,?,?,?,?)";
            }
            else if (page.equals("tambah")){
                sqlSimpan = "CALL addKaryawan(?,?,?,?,?,?,?,?,?,?,?)";
            }
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
            try {
                String id = getNewId();
                preSmt = koneksi.prepareStatement(sqlSimpan);
                preSmt.setString(1, kar.getNama());
                preSmt.setString(2, kar.getTglLahir());
                preSmt.setString(3, kar.getBidangPekerjaan());
                preSmt.setString(4, kar.getJenisKelamin());
                preSmt.setString(5, kar.getAlamat());
                preSmt.setString(6, kar.getNoHp());
                preSmt.setString(7, kar.getNoKtp());
                preSmt.setString(8, kar.getEmail());
                preSmt.setString(9, kar.getNoNpwp());
                preSmt.setString(10, kar.getIdUser());
                preSmt.setString(11, page == "tambah" ? id : kar.getIdKaryawan());
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
            KaryawanDao dao = new KaryawanDao();
   
            Karyawan model = new Karyawan();
            model.setNama("Bowo");
            model.setIdKaryawan("KR0005");
            model.setTglLahir("1998-02-02");
            model.setBidangPekerjaan("Kepala Apotik");
            model.setJenisKelamin("L");
            model.setAlamat("Jakarta");
            model.setNoHp("293892839");
            model.setNoKtp("2837283");
            model.setEmail("bowo@gmail.com");
            model.setNoNpwp("0111111");
            model.setIdUser("US001");
            
//            dao.simpanData(model,"edit");
              dao.hapusData("KR0010");
            System.out.println(dao.getAllKaryawan());
        }
}