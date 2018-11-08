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
@WebServlet(name = "Read_All_DB", urlPatterns = {"/Read_All_DB"})
public class Read_All_DB extends HttpServlet {

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
        try {
            stmt = (Statement) conn.createStatement();

            String sql = "SELECT cust_name, cust_email, cust_phoneNo, cust_address1, cust_address2, cust_address3, cust_address4 FROM customer";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String cust_name = rs.getString("cust_name");
                String cust_email = rs.getString("cust_email");
                String cust_phoneNo = rs.getString("cust_phoneNo");
                String cust_address1 = rs.getString("cust_address1");
                String cust_address2 = rs.getString("cust_address2");
                String cust_address3 = rs.getString("cust_address3");
                String cust_address4 = rs.getString("cust_address4");

                out.println("<h2>" + "Customer Details" + "</h2>");
                out.println("Name: " + cust_name + "<br>");
                out.println("Email: " + cust_email + "<br>");
                out.println("Phone Number: " + cust_phoneNo + "<br>");
                out.println("Address 1: " + cust_address1 + "<br>");
                out.println("Address 2: " + cust_address2 + "<br>");
                out.println("Address 3: " + cust_address3 + "<br>");
                out.println("Address 4: " + cust_address4 + "<br><br>");

            }

            String sql2 = "SELECT invoice_number, customer_number, invoice_payment FROM invoice";
            ResultSet rs2 = stmt.executeQuery(sql2);

            while (rs2.next()) {
                String invoice_number = rs2.getString("invoice_number");
                String customer_number = rs2.getString("customer_number");
                String invoice_payment = rs2.getString("invoice_payment");

                out.println("<h2>" + "Invoice Details" + "</h2>");
                out.println("Invoice Number: " + invoice_number + "<br>");
                out.println("Customer Number: " + customer_number + "<br>");
                out.println("Invoice Payment: " + invoice_payment + "<br><br>");
            }

            String sql3 = "SELECT invoice_number, product_number, product_quantity FROM product_invoice";
            ResultSet rs3 = stmt.executeQuery(sql3);

            while (rs3.next()) {
                String invoice_number = rs3.getString("invoice_number");
                String product_number = rs3.getString("product_number");
                String product_quantity = rs3.getString("product_quantity");

                out.println("<h2>" + "Product Details" + "</h2>");
                out.println("Invoice Number: " + invoice_number + "<br>");
                out.println("Product Number: " + product_number + "<br>");
                out.println("Product Quantity: " + product_quantity + "<br><br>");
            }
            String sql4 = "SELECT product_code, product_description, product_price FROM product";
            ResultSet rs4 = stmt.executeQuery(sql4);
            while (rs4.next()) {
                String product_code = rs4.getString("product_code");
                String product_description = rs4.getString("product_description");
                String product_price = rs4.getString("product_price");

                out.println("Product Code: " + product_code + "<br>");
                out.println("Product Description: " + product_description + "<br>");
                out.println("Product Price: " + product_price + "<br><br>");
            }
            out.println("</body></html>");
        } catch (Exception e) {
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
