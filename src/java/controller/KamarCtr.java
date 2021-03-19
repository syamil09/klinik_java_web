/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.KamarDao;
import dao.KaryawanDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Kamar;
import model.Karyawan;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "KamarCtr", urlPatterns = {"/KamarCtr"})
public class KamarCtr extends HttpServlet {

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
        KamarDao dao = new KamarDao();
        Kamar model  = new Kamar();
        Gson gson       = new Gson();
        
        page = null;
        
        if (page == null) {
            List<Kamar> listKamar = dao.getAllKamar();
            
            String jsonKamar = gson.toJson(listKamar);
            out.println(jsonKamar);
            System.out.println("berhasil get all data : "+jsonKamar);
        }
        else if ("tambah".equals(page)){
            String id = request.getParameter("nik");
            String nama = request.getParameter("nama");
            String id_kamar = "004";
            String nama_ruang = "president";

            if (dao.getRecordById(id_kamar, nama_ruang).getIdKamar() != null) {
//                out.print(dao.getRecordById(id_kamar, nama_ruang));
                response.setContentType("text/html;charset=UTF-8");
                out.print("Id Kamar : " + id_kamar + " - nama : "+nama_ruang+" sudah terpakai");
            }
            else{
                    model.setIdKamar(id_kamar);
                    model.setNamaRuang(nama_ruang);
                    model.setNoKamar("71");
                    model.setKelas("VIP");
                    model.setHargaPerhari(100000.00);
                    model.setDesKamar("bagus");
                    model.setKapasitas(57);
                    model.setTerisi(2);
                    model.setStatus("Full");

                dao.simpanData(model, page);

                response.setContentType("text/html;charset=UTF-8");
                out.print("Data Berhasil disimpan");
            }
        }
        else if("tampil".equals(page)){
            String json = gson.toJson(dao.getRecordById("KM0001", "ruang biasa"));
            response.setContentType("application/json");
            out.println(json);
        }      
        else if ("edit".equals(page)) {
                model.setIdKamar("KM0001");
                    model.setNamaRuang("ruang biasa");
                    model.setNoKamar("80");
                    model.setKelas("VIP");
                    model.setHargaPerhari(100000.00);
                    model.setDesKamar("jelek");
                    model.setKapasitas(57);
                    model.setTerisi(2);
                    model.setStatus("Full");
                dao.simpanData(model, page);
                
                response.setContentType("text/html;charset=UTF-8");
                out.print("Data Berhasil diupdate");
        }
        else if ("hapus".equals(page)) {
            dao.hapusData("003", "sepatu");
            
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
