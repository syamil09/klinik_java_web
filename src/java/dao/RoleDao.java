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
import model.Role;
import helper.*;
/**
 *
 * @author ASUS
 */
public class RoleDao {
        private final Connection koneksi;
        private PreparedStatement preSmt;
        private ResultSet rs;
        private Function f;
        // tanggal
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        public RoleDao(){
            koneksi = Koneksi.getConnection();
            f = new Function();
        }

        public ArrayList<Role> getAllRole(){
            ArrayList<Role> listRole = new ArrayList<>();
            try{
                String sqlAll = "CALL getRole()";
                preSmt = koneksi.prepareStatement(sqlAll);
                rs = preSmt.executeQuery();
                System.out.println("---- getting data ----");
                while(rs.next()){
                    Role model = new Role();
                    model.setId_role(rs.getString("id_role"));
                    model.setDes_role(rs.getString("des_role"));      
                    listRole.add(model);
                    
                    System.out.println("     id : "+rs.getString("id_role"));
                }
            }
            catch(SQLException e){
                System.out.println("Kesalahan mengambil data : " + e);
            }
            return listRole;
        }

        public Role getRecordByNIK(String nik){
            Role rol = new Role();
            String sqlSearch = "select * from Karyawan where nik=?";
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
            return rol;
        }
//
        public void simpanData(Role rol, String page){
            String sqlSimpan = null;
            if (page.equals("edit")){
                sqlSimpan = "CALL updateRole(?,?)";
            }
            else if (page.equals("tambah")){
                sqlSimpan = "CALL addRole(?,?)";
            }
            System.out.println("---- " + (page == "tambah" ? "adding data" : "updating data") + " ----");
            try {
                String id = getNewId();
                preSmt = koneksi.prepareStatement(sqlSimpan);
                preSmt.setString(1, rol.getDes_role());
                preSmt.setString(2, rol.getId_role());
                preSmt.setString(11, page == "tambah" ? id : rol.getId_role());
                preSmt.executeUpdate();
                System.out.println(page == "tambah" ? "success add data" : "success update data");

            }
            catch (SQLException se){
                System.out.println("ada kesalahan : " + se);
            }
        }

        public void hapusData(String id){
            String sqlHapus = "CALL deleteRole(?)";
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
        String sql = "SELECT id_role FROM Role ORDER BY id_user DESC LIMIT 1";
        String newId = "KR0001"; // jika data di database kosong pakai id ini
        try {
            preSmt = koneksi.prepareStatement(sql);
            rs = preSmt.executeQuery();
            if (rs.next()) {
                newId = f.generateId(rs.getString("id_role"));
            }
        } catch (SQLException e) {
            System.out.println("error generate new ID : " + e);
        }
        System.out.println("Generate new ID : " + newId);
        return newId;
    }


        public static void main(String[] args) {
            RoleDao dao = new RoleDao();
   
            Role model = new Role();
            model.setId_role("A1");
            model.setDes_role("Admin");
            dao.simpanData(model,"tambah");
              dao.hapusData("KR0010");
            System.out.println(dao.getAllRole());
        }
    
}
