/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author syamil imdad
 */
@WebServlet(name = "UserCtr", urlPatterns = {"/UserCtr"})
public class UserCtr extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String page = request.getParameter("page");
        PrintWriter out = response.getWriter();
        UserDao dao = new UserDao();
        User model  = new User();
        Gson gson   = new Gson();
        
        page = "tambah";
        System.out.println("page user : "+page);
        if (page == null) {
            List<User> list = dao.getAlluser();
            response.setContentType("application/json");
            String json = gson.toJson(list);
            out.println(json);
            System.out.println("berhasil get all data");
        }
        else if (page.equals("tambah")) {
            
            String id = "US001";
            if (dao.getRecordById(id) != null) {
                response.setContentType("text/html;charset=UTF-8");
                out.print("ID User : " + id + " sudah terpakai");
            } else {
                model.setIdUser(id);
                model.setNama("Juki");
                model.setPassword("password");
                model.setNoKtp("01920192109");
                model.setAlamat("Bandung Timur");
                model.setNoHp("0812399348");
                model.setIdRole("A1");
                model.setAktif("Y");
                dao.simpanData(model, page);

                response.setContentType("text/html;charset=UTF-8");
                out.print("Data Berhasil disimpan");
            }
            
        }
        else if (page.equals("tampil")) {
            String jsonKaryawan = gson.toJson(dao.getRecordById("US001"));
            response.setContentType("application/json");
            out.println(jsonKaryawan);
        }
        else if (page.equals("edit")) {
    
            model.setIdUser("US001");
            model.setNama("Joko Aja");
            model.setIdUser("");
            model.setNama("Juki");
            model.setPassword("password");
            model.setNoKtp("01920192109");
            model.setAlamat("Bandung Timur");
            model.setNoHp("0812399348");
            model.setIdRole("A1");
            model.setAktif("Y");
            dao.simpanData(model, page);
            
            response.setContentType("text/html;charset=UTF-8");
            out.print("Data Berhasil diupdate");
        }
        else if (page.equals("hapus")) {
            dao.hapusData("US002"); 
            response.setContentType("text/html;charset=UTF-8");
            out.print("Data Berhasil dihapus");
        }
        else if (page.equals("login")) {
            String userid = request.getParameter("userid");
            String password = request.getParameter("password");
            userid = "US001";
            password = "password";
            String login = dao.login(userid, password);
            
            
            if (login.equals("berhasil")) {
                response.setContentType("application/json");
                String userJson = gson.toJson(dao.getRecordById(userid));         
                out.println(userJson);
            }
            else {
                out.print(login);
            }
                
            
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
