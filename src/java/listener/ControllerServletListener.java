/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//import javax.servlet.http.HttpSessionBindingEvent;
//import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSession;
import session_bean.CategorySessionBean;
import session_bean.ProductSessionBean;

/**
 * Web application lifecycle listener.
 *
 * @author Zayt
 */
public class ControllerServletListener implements ServletContextListener
{

    @EJB
    ProductSessionBean productSB;
    @EJB
    CategorySessionBean categorySB;
    ServletContext context;
    HttpSession session;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        context = sce.getServletContext();
        context.setAttribute("newProducts", productSB.findRange(new int[]{0, 5}));
        context.setAttribute("categories", categorySB.findAll());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        this.context = null;
    }


}
