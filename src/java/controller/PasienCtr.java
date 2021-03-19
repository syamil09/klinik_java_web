/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.KaryawanDao;
import dao.PasienDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Karyawan;
import model.Pasien;

/**
 *
 * @author syamil imdad
 */
@WebServlet(name = "PasienCtr", urlPatterns = {"/PasienCtr"})
public class PasienCtr extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("application/json");
        String page = request.getParameter("page");
        PrintWriter out = response.getWriter();
        PasienDao dao = new PasienDao();
        Pasien model  = new Pasien();
        Gson gson       = new Gson();
        
        
        if (page == null || page.equals("active")) {
            List<Pasien> list = dao.getAllPasien(page);
            
            String jsonKaryawan = gson.toJson(list);
            out.println(jsonKaryawan);
            System.out.println("berhasil get all data : "+jsonKaryawan);
        }
//        else if ("tambah".equals(page)){
//            String id = request.getParameter("nik");
//            String nama = request.getParameter("nama");
//            id = "KR0011";
//            if (dao.getRecordById(id).getIdKaryawan() != null) {
//                response.setContentType("text/html;charset=UTF-8");
//                out.print(gson.toJson(dao.getRecordById(id)));
//                out.print("ID : " + id + " sudah terpakai");
//            }
//            else{
//                model.setIdKaryawan(id);
//                model.setNama("Halim");
//                model.setJenisKelamin("L");
//                model.setBidangPekerjaan("OB");
//                model.setTglLahir("1998-09-02");
//                model.setAlamat("Bandung TImur");
//                model.setNoHp("091238248");
//                model.setNoKtp("012398483");
//                model.setNoNpwp("0912222");
//                model.setEmail("halim@gmail.com");
//                model.setIdUser("US001");
//                model.setDeleted(0);
//
//                dao.simpanData(model, page);
//
//                response.setContentType("text/html;charset=UTF-8");
//                out.print("Data Berhasil disimpan");
//            }
//        }
//        else if("tampil".equals(page)){
//            String jsonKaryawan = gson.toJson(dao.getRecordById("KR0002"));
//            response.setContentType("application/json");
//            out.println(jsonKaryawan);
//        }      
//        else if ("edit".equals(page)) {
//                model.setIdKaryawan("KR0002");
//                model.setNama("Budiyanto");
//                model.setJenisKelamin("L");
//                model.setBidangPekerjaan("System Administrator");
//                model.setTglLahir("1998-09-09");
//                model.setAlamat("Ciamis");
//                model.setNoHp("08124834");
//                model.setNoKtp("09120392039");
//                model.setNoNpwp("11222111");
//                model.setDeleted(0);    
//                dao.simpanData(model, page);
//                
//                response.setContentType("text/html;charset=UTF-8");
//                out.print("Data Berhasil diupdate");
//        }
//        else if ("hapus".equals(page)) {
//            dao.hapusData("KR0002");
//            
//            response.setContentType("text/html;charset=UTF-8");
//            out.print("Data Berhasil dihapus");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
