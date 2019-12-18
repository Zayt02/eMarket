/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import session_bean.*;
import entity.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ejb.EJB;

/**
 *
 * @author Zayt
 */
@WebServlet(name = "ProductServlet",
        //        loadOnStartup = 1,
        urlPatterns = {"/ProductServlet",
        "add_product"})
public class ProductServlet extends HttpServlet {

    @EJB
    private CategorySessionBean categorySB;
    @EJB
    private ProductSessionBean productSB;
    @EJB
    private ProductDetailSessionBean productDetailSB;

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
        //        int pid = Integer.parseInt(request.getParameter("product_id"));
        String url = "add_product.jsp";
        HttpSession sess = request.getSession();
        if (sess.getAttribute("admin_mode") == null || (Integer)sess.getAttribute("admin_mode") == 0)
        {
            url = "index.jsp";
        }   
        else {
            int pid = productSB.count() + 5;
            String image = (String) request.getParameter("image");
            String name = (String) request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            String description = (String) request.getParameter("description");
            String description_detail = (String) request.getParameter("description_detail");
            int category_id = Integer.parseInt(request.getParameter("category_id"));

            int date = Integer.parseInt(request.getParameter("day"));
            int month = Integer.parseInt(request.getParameter("month"));
            int year = Integer.parseInt(request.getParameter("year"));
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DATE, date);

            Product p = productSB.find(pid);
            Category c = categorySB.find(category_id);
            if (p == null && c != null) {
                p = new Product();
                p.setCategoryId(c);
                p.setDescription(description);
                p.setDescriptionDetail(description_detail);
                p.setImage(image);
                p.setName(name);
                p.setPrice(price);
                p.setProductId(pid);
                p.setLastUpdate(cal.getTime());
                request.setAttribute("is_not_exist", 1);
                productSB.create(p);

                ProductDetail pd = new ProductDetail();
                pd.setProduct(p);
                pd.setAccessories((String) request.getParameter("accessories"));
                pd.setGuaranty((String) request.getParameter("guaranty"));
                pd.setImage1(image);
//                pd.setImage2(null);
//                pd.setImage3(null);
//                pd.setImage4(null);
//                pd.setImage5(null);
                pd.setInformation(description_detail);
                pd.setProductId(pid);
                productDetailSB.create(pd);
            } else {
                request.setAttribute("is_not_exist", 0);
            }
        }
        
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         doPost(request, response);
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
