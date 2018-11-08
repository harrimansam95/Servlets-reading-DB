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
@WebServlet(name = "invoice", urlPatterns = {"/invoice"})
public class invoice extends HttpServlet {

    private String invoice_number;
    private String customer_number;
    private String invoice_payment;

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

            stat.execute("CREATE TABLE if not exists invoice (invoice_number CHAR(20), customer_number CHAR(20), invoice_payment CHAR(20))");

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

        invoice_number = request.getParameter("invoice_number");
        customer_number = request.getParameter("customer_number");
        invoice_payment = request.getParameter("invoice_payment");

        try {
            String query = "INSERT INTO invoice VALUES(?,?,?)";
            prepStat = (PreparedStatement)conn.prepareStatement(query);
            prepStat.setString(1, invoice_number);
            prepStat.setString(2, customer_number);
            prepStat.setString(3, invoice_payment);

            prepStat.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet invoice</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet invoice at " + request.getContextPath() + "</h1>");
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
