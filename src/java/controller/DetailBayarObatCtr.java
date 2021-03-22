/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.DetailBayarObatDao;
import dao.KaryawanDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DetailBayarObat;
import model.Karyawan;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "DetailBayarObatCtr", urlPatterns = {"/DetailBayarObatCtr"})
public class DetailBayarObatCtr extends HttpServlet {

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
        DetailBayarObatDao dao = new DetailBayarObatDao();
        DetailBayarObat model  = new DetailBayarObat();
        Gson gson       = new Gson();
        
        page = "tambah";
        
        if (page == null) {
            List<DetailBayarObat> list = dao.getAllDetailBayarObat();
            
            String jsonKaryawan = gson.toJson(list);
            out.println(jsonKaryawan);
            System.out.println("berhasil get all data : "+jsonKaryawan);
        }
        else if ("tambah".equals(page)){
            String id = request.getParameter("nik");
            String pembayaran = "PY0O01";
            String obat = "OB0001"; 
            String nama = request.getParameter("nama");
            id = "KR0011";
            if (dao.getRecordById(pembayaran, obat).getId_pembayaran() != null) {
                response.setContentType("text/html;charset=UTF-8");
                out.print("ID : " + id + " sudah terpakai");
            }
            else{
                model.setId_pembayaran("BYR002");
                model.setId_obat("OB0001");
                model.setHarga(2000.00);
                model.setJumlah(100.00);

                dao.simpanData(model, page);

                response.setContentType("text/html;charset=UTF-8");
                out.print("Data Berhasil disimpan");
            }
        }
//        else if("tampil".equals(page)){
//            String json = gson.toJson(dao.getRecordById("KR0002"));
//            response.setContentType("application/json");
//            out.println(json);
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
