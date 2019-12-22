/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import session_bean.*;
import entity.*;
import cart.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ejb.EJB;
import javax.persistence.EntityManager;

/**
 *
 * @author Zayt
 */
@WebServlet(name = "ControllerServlet",
        //        loadOnStartup = 1,
        urlPatterns = {"/ControllerServlet",
            "/index",
            "/category",
            "/product",
            "/addToCart",
            "/viewCart",
            "/updateCart",
            "/purchase", 
            "/deleteProduct",
            "/search",
            "/viewAllOrder",
            "/viewOrder",
            "/edit_order",
            "/editOrder"
            })
public class ControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private CategorySessionBean categorySB;
    @EJB
    private ProductSessionBean productSB;
    @EJB
    private ProductDetailSessionBean productDetailSB;
    @EJB
    private OrderManager orderManager;
    @EJB
    private CustomerOrderSessionBean customerOrderSB;
    

//    @Override
//    public void init(ServletConfig servletConfig) throws ServletException {
//        super.init(servletConfig);
//// store new product list in servlet context
//        getServletContext().setAttribute("newProducts",
//                productSessionBean.findRange(new int[]{0, 5}));
//        
//    }
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userPath = request.getRequestURI().substring(request.getContextPath().length());
//        System.out.println("\n\n\n"+userPath + "\n\n\n");
        if (userPath.equals("/category")) {
            String categoryId = request.getQueryString();
//            System.out.println("\n\n\n"+categoryId + "\n\n\n");
            if (categoryId != null) {
                
                Category selectedCategory;
                List<Product> categoryProducts;

                selectedCategory = categorySB.find(Integer.parseInt(categoryId));
                session.setAttribute("selectedCategory", selectedCategory);

                categoryProducts = (List<Product>) selectedCategory.getProductCollection();
                session.setAttribute("categoryProducts", categoryProducts);
            }
        } else if (userPath.equals("/product")) {
            String productId = request.getQueryString();
            if (productId != null) {
                Product selectedProduct;
                ProductDetail selectedProductDetail;

                selectedProduct = productSB.find(Integer.parseInt(productId));
                selectedProductDetail = productDetailSB.find(Integer.parseInt(productId));

                session.setAttribute("selectedProduct", selectedProduct);
                session.setAttribute("selectedProductDetail", selectedProductDetail);
            }           
        }
        else if (userPath.equals("/deleteProduct")) {
            String productId = request.getQueryString();
            if (productId != null) {
                Product selectedProduct;
                ProductDetail selectedProductDetail;

                selectedProduct = productSB.find(Integer.parseInt(productId));
                selectedProductDetail = productDetailSB.find(Integer.parseInt(productId));

                productDetailSB.remove(selectedProductDetail);
                productSB.remove(selectedProduct);
            }   
            userPath = "/index";
        }
        else if (userPath.equals("/search"))
        {
            String searchString = (String) request.getParameter("search_string");
            String query1 = "SELECT p FROM Product p WHERE p.name like '%" + searchString +
                    "%' or p.description like '%" + searchString 
                    + "%' or p.descriptionDetail like '%" + searchString + "%'";
            Collection<Product> result = productSB.findByUserQuery(query1);
            request.setAttribute("search_result", result);
            userPath = "search";
        } else if (userPath.equals("/viewCart")) {
            String clear = request.getParameter("clear");
            if ((clear != null) && clear.equals("true")) {
                ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                cart.clear();
            }
        } else if (userPath.equals("/addToCart")) {
// if user is adding item to cart for first time
// create cart object and attach it to user session
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCart();
                session.setAttribute("cart", cart);
            }
// get user input from request
            String productId = request.getQueryString();
            if (!productId.isEmpty()) {
                Product product
                        = productSB.find(Integer.parseInt(productId));
                cart.addItem(product);
                cart.calculateTotal("5");
            }
            String userView = (String) session.getAttribute("view");
            userPath = userView;
        }
        else if (userPath.equals("/viewAllOrder"))
        {
            String query = "select c from CustomerOrder as c order by c.customerId.customerId";
            List<CustomerOrder> orderList = customerOrderSB.findByUserQuery(query);
            request.setAttribute("orderList", orderList);
        }
        else if (userPath.equals("/viewOrder"))
        {
            String orderId = request.getQueryString();
            if (orderId != null && ((Integer)session.getAttribute("admin_mode")) == 1)
            {
                CustomerOrder order = customerOrderSB.find(Integer.parseInt(orderId));
                request.setAttribute("order", order);
            }
            else userPath = "/index";
        }
        else if(userPath.equals("/edit_order"))
        {
            String orderId = request.getQueryString();

            if (orderId != null && ((Integer)session.getAttribute("admin_mode")) == 1)
            {
                session.setAttribute("editedOrderId", Integer.parseInt(orderId));
                request.setAttribute("editedOrder", customerOrderSB.find(Integer.parseInt(orderId)));
            }
            else userPath="/viewAllOrder";
        }

        String url = userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String userPath = request.getRequestURI().substring(request.getContextPath().length());
        request.setCharacterEncoding("UTF-8");
//        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        Validator validator = new Validator();
        
//        String aname = request.getParameter("name");
//        String password = request.getParameter("password");
//        if (aname != null && password != null) {
//            session.setAttribute("is_first", 0);
//            if (aname.equals(this.admin_name) && password.equals(this.password)) {
//                session.setAttribute("admin_mode", 1);
//                request.getRequestDispatcher("index.jsp").forward(request, response);
//            } else {
//                session.setAttribute("admin_mode", 0);
//                request.getRequestDispatcher("login.jsp").forward(request, response);
//            }
//        }
        if (userPath.equals("/updateCart")) {
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            String productId = request.getParameter("productId");
            String quantity = request.getParameter("quantity");
            if (productSB.find(Integer.parseInt(productId)) != null) {
                Product product
                        = productSB.find(Integer.parseInt(productId));
                cart.update(product, quantity);
            }
            userPath = "/viewCart";
        }
        else if (userPath.equals("/editOrder"))
        {
            if (((Integer)session.getAttribute("admin_mode")) == 0) userPath="/index";
            else{
                Integer orderId = (Integer) session.getAttribute("editedOrderId");
                CustomerOrder order = customerOrderSB.find(orderId);
                String status = request.getParameter("newStatus");
                order.setStatus(status);
                customerOrderSB.edit(order);
                session.setAttribute("editedOrderId", null);
                userPath = "/index";
            }
        }
        String url = userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
}
