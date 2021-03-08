/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.DetailLayananDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DetailLayanan;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "DetailLayananCtr", urlPatterns = {"/DetailLayananCtr"})
public class DetailLayananCtr extends HttpServlet {

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
        DetailLayananDao dao = new DetailLayananDao();
        DetailLayanan model  = new DetailLayanan();
        Gson gson       = new Gson();
        
        page = "hapus";
        
        if (page == null) {
            List<DetailLayanan> listDetailLayanan = dao.getAllDetailLayanan();
            
            String jsonDetailLayanan = gson.toJson(listDetailLayanan);
            out.println(jsonDetailLayanan);
            System.out.println("berhasil get all data : "+jsonDetailLayanan);
        }
        else if ("tambah".equals(page)){
            String id = request.getParameter("nik");
            String id_layanan = "OP";
            String id_detail_layanan = "OPA";
            
            if (dao.getRecordById(id_layanan, id_detail_layanan).getId_layanan()!= null) {
                response.setContentType("text/html;charset=UTF-8");
                out.print("Id layanan : " + id_layanan + " - id_detail_layanan : "+id_detail_layanan+" sudah terpakai");
            }
            else{
                    model.setId_layanan(id_layanan);
                    model.setId_detail_layanan(id_detail_layanan);
                    model.setDes_detail_layanan("Operasi Amandel");
                    model.setBiaya_layanan(100000.00);
                    model.setKeterangan("bagus");

                dao.simpanData(model, page);

                response.setContentType("text/html;charset=UTF-8");
                out.print("Data Berhasil disimpan");
            }
        }
        else if("tampil".equals(page)){
            String json = gson.toJson(dao.getRecordById("CD", "CD1"));
            response.setContentType("application/json");
            out.println(json);
        }      
        else if ("edit".equals(page)) {
                    model.setId_layanan("CD");
                    model.setId_detail_layanan("CD1");
                    model.setDes_detail_layanan("Operasi Amandel");
                    model.setBiaya_layanan(10000.00);
                    model.setKeterangan("bagus");
                dao.simpanData(model, page);
                
                response.setContentType("text/html;charset=UTF-8");
                out.print("Data Berhasil diupdate");
        }
        else if ("hapus".equals(page)) {
            dao.hapusData("OP", "OPA");
            
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
