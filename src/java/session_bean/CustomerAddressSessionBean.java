
package session_bean;

import entity.CustomerAddress;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Zayt
 */
@Stateless
public class CustomerAddressSessionBean extends AbstractSessionBean<CustomerAddress>{
    @PersistenceContext(unitName = "eMarketPU")
//    private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("eMarketPU");
    private EntityManager em ;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerAddressSessionBean() {
        super(CustomerAddress.class);
    }
}
