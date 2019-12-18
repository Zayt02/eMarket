/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_bean;

import entity.Category;
import entity.Product;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Zayt
 */
@Stateless
public class ProductSessionBean extends AbstractSessionBean<Product> {

    @PersistenceContext(unitName = "eMarketPU")
//    private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("eMarketPU");
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductSessionBean() {
        super(Product.class);
    }

    @Override
    public void remove(Product p) {
        super.remove(p);
        Category c = p.getCategoryId();
        Collection<Product> collection = c.getProductCollection();
        collection.remove(p);
        c.setProductCollection(collection);
        getEntityManager().merge(c);
    }

    @Override
    public void create(Product p) {
        super.create(p);
        Category c = p.getCategoryId();
        Collection<Product> collection = c.getProductCollection();
        collection.add(p);
        c.setProductCollection(collection);
        getEntityManager().merge(c);
    }
}
