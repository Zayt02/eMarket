/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import entity.Customer;
import entity.CustomerAddress;
import entity.CustomerAddressPK;
import entity.CustomerOrder;
import entity.OrderedProduct;
import entity.OrderedProductPK;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session_bean.CustomerAddressSessionBean;
import session_bean.CustomerOrderSessionBean;
import session_bean.CustomerSessionBean;
import session_bean.OrderManager;
import session_bean.ProductSessionBean;

/**
 *
 * @author Zayt
 */
@WebServlet(name = "UserServlet",
        //        loadOnStartup = 1,
        urlPatterns = {"/UserServlet",
            "/user_login",
            "/admin_login",
            "/logout",
            "/userProfile",
            "/editProfile",
            "/changePassword",
            "/checkout",
            "/addAddress",
            "/editAddress",
            "/updateAddress",
            "/confirm"
        })
public class UserServlet extends HttpServlet {

    @EJB
    private CustomerSessionBean customerSB;
    @EJB
    private CustomerAddressSessionBean customerAddressSB;
    @EJB
    private ProductSessionBean productSB;
    @EJB
    private OrderManager orderManager;
    @EJB
    private CustomerOrderSessionBean customerOrderSB;

    private String admin_name = "admin";
    private String admin_password = "admin123456";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userPath = request.getRequestURI().substring(request.getContextPath().length());
        if (userPath.equals("/logout")) {
            session.invalidate();
            userPath = "/index";
        } else if (userPath.equals("/checkout")) {
            Customer user = (Customer) session.getAttribute("user");
            if (user == null) {
                userPath = "/userLogin";
            } else {
                ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n!!!!!!!!!!");
                if (cart != null) {
                    CustomerOrder order = addOrder(user, cart, "");
                    List<OrderedProduct> orderedProducts = addOrderedItems(order, cart);
                    List<Product> products = new ArrayList<Product>();
                    for (OrderedProduct op : orderedProducts) {
                        Product p = (Product) productSB.find(op.getOrderedProductPK().getProductId());
                        products.add(p);
                    }
// place order details in request scope
                    request.setAttribute("customer",
                            user);
                    request.setAttribute("products",
                            products);
                    request.setAttribute("orderRecord",
                            order);
                    request.setAttribute("orderedProducts",
                            orderedProducts);
                    userPath = "/confirmation";
// otherwise, send back to checkout page and display error

                }
                userPath = "/before_cf";
            }
        } else if (userPath.equals("/confirm")) {
            Customer user = (Customer) session.getAttribute("user");
            if (user == null) {
                userPath = "/userLogin";
            } else {
                ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                String address = request.getParameter("selectedAddress");
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n!!!!!!!!!!");
                if (cart != null) {
                    int orderId = orderManager.placeOrderWithoutCreateUser(user, cart, address);
                    if (orderId != 0) {
// in case language was set using toggle, getlanguage choice before destroying session
                        Locale locale = (Locale) session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
                        String language = "";
                        if (locale != null) {
                            language = (String) locale.getLanguage();
                        }
// dissociate shopping cart from session
                        cart = null;
                        session.setAttribute("cart", cart);
// end session
                        if (!language.isEmpty()) { //if user changed language using the toggle,
//reset the language attribute - otherwise
                            request.setAttribute("language", language); //language will be switched on confirmation page!
                        }
// get order details    
                        Map orderMap = orderManager.getOrderDetails(orderId);
// place order details in request scope
                        request.setAttribute("customer",
                                orderMap.get("customer"));
                        request.setAttribute("products",
                                orderMap.get("products"));
                        request.setAttribute("orderRecord",
                                orderMap.get("orderRecord"));
                        request.setAttribute("orderedProducts",
                                orderMap.get("orderedProducts"));
                        userPath = "/confirmation";
// otherwise, send back to checkout page and display error
                    } else {
                        userPath = "/index";
                        request.setAttribute("orderFailureFlag", true);
                    }

                }
                userPath = "/confirmation";
            }
        } else if (userPath.equals("/editAddress")) {
            String addressIndex = request.getQueryString();
            if (addressIndex != null) {
                session.setAttribute("editedIndex", Integer.parseInt(addressIndex));
            }
        }

        String url = userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
        HttpSession session = request.getSession();
        String userPath = request.getRequestURI().substring(request.getContextPath().length());
//        System.out.println(userPath + " \n11111111111111111111111111111111111");
        if (userPath.equals("/admin_login")) {
            String name = (String) request.getParameter("name");
            String password = (String) request.getParameter("password");
            if (name != null && password != null) {
                if (name.equals(admin_name) && password.equals(admin_password)) {
                    session.setAttribute("user_mode", null);
                    session.setAttribute("user", null);
                    session.setAttribute("admin_mode", 1);
                    userPath = "/index";
                } else {
                    session.setAttribute("admin_mode", 0);
                    userPath = "/adminLogin";
                }
            }
        } else if (userPath.equals("/user_login")) {
            String user_name = (String) request.getParameter("name");
            String password = (String) request.getParameter("password");
            if (user_name != null && password != null) {
                Customer c = checkExistCustomer(user_name, password);
                if (c != null) {
                    session.setAttribute("user", c);
                    session.setAttribute("addresses", findAllAddress(c));
                    session.setAttribute("user_mode", 1);
                    session.setAttribute("admin_mode", null);
                    userPath = "/index";
                } else {
                    request.setAttribute("user_mode", 0);
                    userPath = "/userLogin";
                }

            }
        } else if (userPath.equals("/userProfile")) {
            Customer user = (Customer) session.getAttribute("user");
            System.out.println(userPath);
            if (user == null) {
                userPath = "/index";
            }
        } else if (userPath.equals("/editProfile")) {
            Customer user = (Customer) session.getAttribute("user");

            if (user == null) {
                userPath = "/index";
            } else {
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String CcNumber = request.getParameter("creditcard");
                String cityRegion = request.getParameter("cityRegion");
                user.setEmail(email);
                user.setName(name);
                user.setPhone(phone);
                user.setCcNumber(CcNumber);
                user.setCityRegion(cityRegion);
                customerSB.edit(user);
                userPath = "/userProfile";
            }
        } else if (userPath.equals("/changePassword")) {
            Customer user = (Customer) session.getAttribute("user");
            if (user == null) {
                userPath = "/index";
            } else {
                String old_password = request.getParameter("old_password");
                if (user.getPassword().equals(old_password)) {
                    String new_password = request.getParameter("new_password");
                    user.setPassword(new_password);
                    customerSB.edit(user);
                } else {
                    request.setAttribute("not_equal", 1);
                }
                userPath = "/userProfile";
            }
            System.out.println(userPath);
        } else if (userPath.equals("/updateAddress")) {
            Integer index = (Integer) session.getAttribute("editedIndex");
            if (index != null) {
                List<CustomerAddress> addresses = (List<CustomerAddress>) session.getAttribute("addresses");
                String newAdd = request.getParameter("editedAddressInfo");
                addresses.get(index).setAddress(newAdd);
                customerAddressSB.edit(addresses.get(index));
                session.setAttribute("editedIndex", null);
            }
            userPath = "/userProfile";
        } else if (userPath.equals("/addAddress")) {
            Customer user = (Customer) session.getAttribute("user");
            if (user == null) {
                userPath = "/index";
            } else {
                String newAddress = request.getParameter("newAddress");
                int id = getNewAddressId();
                CustomerAddress address = new CustomerAddress();
                address.setAddress(newAddress);
                address.setCustomerAddressPK(new CustomerAddressPK(user.getCustomerId(), id));
                customerAddressSB.create(address);

                List<CustomerAddress> addresses = (List<CustomerAddress>) session.getAttribute("addresses");
                addresses.add(address);
                session.setAttribute("addresses", addresses);

                userPath = "/userProfile";
            }
        }

        String url = userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

    private Customer checkExistCustomer(String name, String password) {
        Customer c = null;
        String query = "SELECT c from Customer as c where c.userName = '" + name + "'";
        List<Customer> result = customerSB.findByUserQuery(query);
        if (result.size() == 0) {
            return null;
        } else if (password == null) {
            return null;
        } else {
            if (result.get(0).getPassword().equals(password)) {
                c = result.get(0);
            }
        }
        return c;
    }

    private List<CustomerAddress> findAllAddress(Customer user) {
        String query = "SELECT c FROM CustomerAddress as c WHERE c.customerAddressPK.customerId = " + user.getCustomerId();
        List<CustomerAddress> result = customerAddressSB.findByUserQuery(query);
        if (result == null || result.size() == 0) {
            return null;
        } else {
            return result;
        }
    }

    private int getNewAddressId() {
        int id = customerAddressSB.count() + 1;
        String query = "SELECT c FROM CustomerAddress c WHERE c.customerAddressPK.addressId = ";
        while (customerAddressSB.findByUserQuery(query + id).size() > 0) {
            id++;
        }
        return id;
    }

    public CustomerOrder addOrder(Customer customer, ShoppingCart cart, String address) {
// set up customer order
        int id = customerOrderSB.count() + 1;
        CustomerOrder order = new CustomerOrder();
        order.setCustomerId(customer);
        cart.calculateTotal("5");
        order.setAmount(cart.getTotal());
        order.setOrderId(id);
        order.setAddress(address);
// create confirmation number
        Random random = new Random();
        int i = random.nextInt(999999999);
        order.setConfirmationNumber(i);
//        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        order.setDateCreated(new Date());
        return order;
    }

    public List<OrderedProduct> addOrderedItems(CustomerOrder order, ShoppingCart cart) {
        List<ShoppingCartItem> items = cart.getItems();
        List<OrderedProduct> result = new ArrayList<OrderedProduct>();
// iterate through shopping cart and create OrderedProducts
        for (ShoppingCartItem scItem : items) {
            int productId = scItem.getProduct().getProductId();
// set up primary key object
            OrderedProductPK orderedProductPK = new OrderedProductPK();
            orderedProductPK.setOrderId(order.getOrderId());
            orderedProductPK.setProductId(productId);
// create ordered item using PK object
            OrderedProduct orderedItem = new OrderedProduct(orderedProductPK);
// set quantity
            orderedItem.setQuantity(scItem.getQuantity());
            result.add(orderedItem);
        }
        return result;
    }

}
