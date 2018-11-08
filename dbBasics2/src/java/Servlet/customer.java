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
@WebServlet(name = "customer", urlPatterns = {"/customer"})
public class customer extends HttpServlet {
    
    private String cust_name;
    private String cust_email;
    private String cust_phoneNo;
    private String cust_address1;
    private String cust_address2;
    private String cust_address3;
    private String cust_address4;
    Connection conn;
    PreparedStatement prepStat;
    Statement stat;
    
    
    public void init() {
        String URL = "jdbc:mysql://localhost:3306/";
        String DB = "samdb2";
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
        
        
        cust_name = request.getParameter("cust_name");
        cust_email = request.getParameter("cust_email");
        cust_phoneNo = request.getParameter("cust_phoneNo");
        cust_address1 = request.getParameter("cust_address1");
        cust_address2 = request.getParameter("cust_address2");
        cust_address3 = request.getParameter("cust_address3");
        cust_address4 = request.getParameter("cust_address4");
        
        
        try{
            String query = "INSERT INTO customer VALUES(?,?,?,?,?,?,?)";
            prepStat = (PreparedStatement)conn.prepareStatement(query);
            prepStat.setString(1, cust_name);
            prepStat.setString(2, cust_email);
            prepStat.setString(3, cust_phoneNo);
            prepStat.setString(4, cust_address1);
            prepStat.setString(5, cust_address2);
            prepStat.setString(6, cust_address3);
            prepStat.setString(7, cust_address4);
            prepStat.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet customer</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet customer at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
