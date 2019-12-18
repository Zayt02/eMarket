/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_bean;

import entity.ProductDetail;
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
public class ProductDetailSessionBean extends AbstractSessionBean<ProductDetail> {
    @PersistenceContext(unitName = "eMarketPU")
//    private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("eMarketPU");
    private EntityManager em ;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductDetailSessionBean() {
        super(ProductDetail.class);
    }
}