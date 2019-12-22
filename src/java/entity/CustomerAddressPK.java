/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Zayt
 */
@Embeddable
public class CustomerAddressPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_id")
    private int customerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "address_id")
    private int addressId;

    public CustomerAddressPK() {
    }

    public CustomerAddressPK(int customerId, int addressId) {
        this.customerId = customerId;
        this.addressId = addressId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) customerId;
        hash += (int) addressId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerAddressPK)) {
            return false;
        }
        CustomerAddressPK other = (CustomerAddressPK) object;
        if (this.customerId != other.customerId) {
            return false;
        }
        if (this.addressId != other.addressId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerAddressPK[ customerId=" + customerId + ", addressId=" + addressId + " ]";
    }
    
}
