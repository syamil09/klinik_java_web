/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.KaryawanDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Karyawan;

/**
 *
 * @author syamil imdad
 */
@WebServlet(name = "KaryawanCtr", urlPatterns = {"/KaryawanCtr"})
public class KaryawanCtr extends HttpServlet {

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
        KaryawanDao dao = new KaryawanDao();
        Karyawan model  = new Karyawan();
        Gson gson       = new Gson();
        
//        page = "active";
        
        if (page == null || page.equals("active")) {
            List<Karyawan> listKaryawan = dao.getAllKaryawan(page);
            
            String jsonKaryawan = gson.toJson(listKaryawan);
            out.println(jsonKaryawan);
            System.out.println("berhasil get all data : "+jsonKaryawan);
        }
        else if ("tambah".equals(page)){
            String id = request.getParameter("id");
            String nama = request.getParameter("nama");
//            id = "KR0011";
            System.out.println("data : "+request.getParameter("pekerjaan"));
            if (dao.getRecordById(id).getIdKaryawan() != null) {
                response.setContentType("text/html;charset=UTF-8");
                out.print(gson.toJson(dao.getRecordById(id)));
                out.print("ID : " + id + " sudah terpakai");
            }
            else{   
                model.setIdKaryawan(id);
                model.setNama(nama);
                model.setJenisKelamin(request.getParameter("jenisKelamin"));
                model.setBidangPekerjaan(request.getParameter("pekerjaan"));
                model.setTglLahir(request.getParameter("tglLahir"));
                model.setAlamat(request.getParameter("alamat"));
                model.setNoHp(request.getParameter("noHp"));
                model.setNoKtp(request.getParameter("noKtp"));
                model.setNoNpwp(request.getParameter("noNpwp"));
                model.setEmail(request.getParameter("email"));
                model.setIdUser("US001");
                model.setDeleted(0);

                dao.simpanData(model, page);
                System.out.println("current page : "+page);
                response.setContentType("text/html;charset=UTF-8");
                out.print("Data Berhasil disimpan");
            }
        }
        else if("tampil".equals(page)){
            String jsonKaryawan = gson.toJson(dao.getRecordById(request.getParameter("id")));
            response.setContentType("application/json");
            out.println(jsonKaryawan);
        }      
        else if ("edit".equals(page)) {
                model.setIdKaryawan(request.getParameter("id"));
                model.setNama(request.getParameter("nama"));
                model.setJenisKelamin(request.getParameter("jenisKelamin"));
                model.setBidangPekerjaan(request.getParameter("pekerjaan"));
                model.setTglLahir(request.getParameter("tglLahir"));
                model.setAlamat(request.getParameter("alamat"));
                model.setNoHp(request.getParameter("noHp"));
                model.setNoKtp(request.getParameter("noKtp"));
                model.setNoNpwp(request.getParameter("noNpwp"));
                model.setEmail(request.getParameter("email"));
                model.setIdUser("US001");
                model.setDeleted(0);    
                dao.simpanData(model, page);
                
                response.setContentType("text/html;charset=UTF-8");
                out.print("Data Berhasil diupdate");
        }
        else if ("hapus".equals(page)) {
            dao.hapusData(request.getParameter("id"));
            
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
