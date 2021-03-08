/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.BayarLayananDao;
import dao.PendaftaranDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BayarLayanan;
import model.Pendaftaran;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "BayarLayananCtr", urlPatterns = {"/BayarLayananCtr"})
public class BayarLayananCtr extends HttpServlet {

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
        BayarLayananDao dao = new BayarLayananDao();
        BayarLayanan model  = new BayarLayanan();
        Gson gson       = new Gson();
        
        page = "hapus";
        
        if (page == null) {
            List<BayarLayanan> listBayarLayanan = dao.getAllBayarLayanan();
            
            String jsonBayarLayanan = gson.toJson(listBayarLayanan);
            out.println(jsonBayarLayanan);
            System.out.println("berhasil get all data : "+jsonBayarLayanan);
        }
        else if ("tambah".equals(page)){
            String id = request.getParameter("nik");
            String id_bayar = "BL0004";

            if (dao.getRecordById(id_bayar).getId_bayar_layanan()!= null) {
                response.setContentType("text/html;charset=UTF-8");
                out.print("ID : " + id_bayar + " sudah terpakai");
            }
            else{
                model.setId_bayar_layanan(id_bayar);
                model.setId_layanan("CD");
                model.setId_detail_layanan("CD2");
                model.setId_pasien("PS001");
                model.setTgl_layanan("2021-03-10");
                model.setKeterangan("keterangan");

                dao.simpanData(model, page);

                response.setContentType("text/html;charset=UTF-8");
                out.print("Data Berhasil disimpan");
            }
        }
        else if("tampil".equals(page)){
            String json = gson.toJson(dao.getRecordById("BL0004"));
            response.setContentType("application/json");
            out.println(json);
        }      
        else if ("edit".equals(page)) {
                model.setId_bayar_layanan("BL0002");
                model.setId_layanan("CD");
                model.setId_detail_layanan("CD2");
                model.setId_pasien("PS001");
                model.setTgl_layanan("2021-03-10");
                model.setKeterangan("jelek");
                dao.simpanData(model, page);
                
                response.setContentType("text/html;charset=UTF-8");
                out.print("Data Berhasil diupdate");
        }
        else if ("hapus".equals(page)) {
            dao.hapusData("BL0004");
            
            response.setContentType("text/html;charset=UTF-8");
            out.print("Data Berhasil dihapus");
        }
        
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
