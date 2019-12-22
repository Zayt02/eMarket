/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_bean;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import entity.Customer;
import entity.CustomerOrder;
import entity.OrderedProduct;
import entity.OrderedProductPK;
import entity.Product;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Zayt
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderManager {

    @EJB
    private CustomerOrderSessionBean customerOrderSB;
    @EJB
    private ProductSessionBean productSB;
    @EJB
    private OrderedProductSessionBean orderedProductSB;
    @EJB
    private CustomerSessionBean customerSB;
    @PersistenceContext(unitName = "eMarketPU")
    private EntityManager em;
    @Resource
    private SessionContext context;

    public OrderManager() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public int placeOrder(String name, String email, String phone, String cityRegion, String ccNumber, ShoppingCart cart) {
//        try {
//            Customer customer = addCustomer(name, email, phone,
//                    cityRegion, ccNumber);
////            System.out.println("!!!!!\n!!!!!!!!!\n!!!!!");
//            CustomerOrder order = addOrder(customer, cart);
//            addOrderedItems(order, cart);
//            return order.getOrderId();
//        } catch (Exception e) {
//            context.setRollbackOnly();
//            e.printStackTrace();
//            return 0;
//        }
//    }

    public int placeOrderWithoutCreateUser(Customer customer, ShoppingCart cart, String address) {
        try {

            CustomerOrder order = addOrder(customer, cart, address);
            addOrderedItems(order, cart);
            return order.getOrderId();
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
            return 0;
        }
    }

    private Customer addCustomer(String name, String email, String phone,
            String cityRegion, String ccNumber) {
        Customer customer = new Customer();
        customer.setCustomerId(customerSB.findAll().size() + 1);
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setCityRegion(cityRegion);
        customer.setCcNumber(ccNumber);
        customerSB.create(customer);
        return customer;
    }

    private CustomerOrder addOrder(Customer customer, ShoppingCart cart, String address) {
// set up customer order
        int id = customerOrderSB.findAll().size() + 1;
        CustomerOrder order = new CustomerOrder();
        order.setCustomerId(customer);
        cart.calculateTotal("5");
        order.setAmount(cart.getTotal());
        order.setOrderId(id);
        order.setAddress(address);
        order.setStatus("Processing");
// create confirmation number
        Random random = new Random();
        int i = random.nextInt(999999999);
        order.setConfirmationNumber(i);
//        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        order.setDateCreated(new Date());
        customerOrderSB.create(order);
        return order;
    }

    private List<OrderedProduct> addOrderedItems(CustomerOrder order, ShoppingCart cart) {
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
            orderedProductSB.create(orderedItem);
        }
        return result;
    }


    public Map getOrderDetails(int orderId) {
        Map orderMap = new HashMap();
// get order
        CustomerOrder order = customerOrderSB.find(orderId);
// get customer
        Customer customer = order.getCustomerId();
// get all ordered products
        List<OrderedProduct> orderedProducts
                = orderedProductSB.findByOrderId(orderId);
// get product details for ordered items
        List<Product> products = new ArrayList<Product>();
        for (OrderedProduct op : orderedProducts) {
            Product p = (Product) productSB.find(op.getOrderedProductPK().getProductId());
            products.add(p);
        }
// add each item to orderMap
        orderMap.put("orderRecord", order);
        orderMap.put("customer", customer);
        orderMap.put("orderedProducts", orderedProducts);
        orderMap.put("products", products);
        return orderMap;
    }
}
