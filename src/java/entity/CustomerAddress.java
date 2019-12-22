/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zayt
 */
@Entity
@Table(name = "customer_address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerAddress.findAll", query = "SELECT c FROM CustomerAddress c"),
    @NamedQuery(name = "CustomerAddress.findByCustomerId", query = "SELECT c FROM CustomerAddress c WHERE c.customerAddressPK.customerId = :customerId"),
    @NamedQuery(name = "CustomerAddress.findByAddress", query = "SELECT c FROM CustomerAddress c WHERE c.address = :address"),
    @NamedQuery(name = "CustomerAddress.findByAddressId", query = "SELECT c FROM CustomerAddress c WHERE c.customerAddressPK.addressId = :addressId")})
public class CustomerAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CustomerAddressPK customerAddressPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "address")
    private String address;

    public CustomerAddress() {
    }

    public CustomerAddress(CustomerAddressPK customerAddressPK) {
        this.customerAddressPK = customerAddressPK;
    }

    public CustomerAddress(CustomerAddressPK customerAddressPK, String address) {
        this.customerAddressPK = customerAddressPK;
        this.address = address;
    }

    public CustomerAddress(int customerId, int addressId) {
        this.customerAddressPK = new CustomerAddressPK(customerId, addressId);
    }

    public CustomerAddressPK getCustomerAddressPK() {
        return customerAddressPK;
    }

    public void setCustomerAddressPK(CustomerAddressPK customerAddressPK) {
        this.customerAddressPK = customerAddressPK;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerAddressPK != null ? customerAddressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerAddress)) {
            return false;
        }
        CustomerAddress other = (CustomerAddress) object;
        if ((this.customerAddressPK == null && other.customerAddressPK != null) || (this.customerAddressPK != null && !this.customerAddressPK.equals(other.customerAddressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerAddress[ customerAddressPK=" + customerAddressPK + " ]";
    }
    
}
