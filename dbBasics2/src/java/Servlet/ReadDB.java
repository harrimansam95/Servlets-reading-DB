/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author K00200540
 */
@WebServlet(name = "ReadDB", urlPatterns = {"/ReadDB"})
public class ReadDB extends HttpServlet {
    
    Connection conn;
    PreparedStatement prepStat;
    Statement stat;
    
    public void init() {
        String URL = "jdbc:mysql://localhost:3306/";
        String DB = "samdb";
        String USERNAME = "root";
        String PASSWORD = "";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(URL + DB, USERNAME, PASSWORD);
            System.out.println("Connected");
            stat = conn.createStatement();
            stat.execute("CREATE TABLE if not exists customer (cust_name CHAR (20), cust_email CHAR(20), cust_phoneNo CHAR(20), cust_address1 CHAR(20), cust_address2 CHAR(20), cust_address3 CHAR(20), cust_address4 CHAR(20))");
         

        } catch (Exception e) {
            System.err.println(e);
        }
    }

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
        
        Statement stmt;
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Database Result";
        String docType = "<!doctype html>";
        out.println(docType + "<html>\n"
                + "<head><title>" + title + "</title></head>\n"
                        + "<h1 align=\"center\">" + title + "</h1>\n");
        try{
            stmt = (Statement)conn.createStatement();
            String sql;
            sql = "SELECT first_name, last_name FROM users";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                String first = rs.getString("first_name");
                String last = rs.getString("last_name");
                
                out.println("First name: " + first + "<br>");
                out.println("Last name: " + last + "<br>");
            }
            out.println("</body></html>");
        }
        catch(Exception e){
            e.printStackTrace();
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
