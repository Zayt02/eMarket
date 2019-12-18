/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_bean;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Zayt
 */
public class Validator {
    private ProductSessionBean productSB;
    public Validator(){
        
    }
    
    public boolean validateQuantity(String productId, String quantity)
    {
        if (productSB.find(Integer.parseInt(productId)) != null) return true;
        return false;
    }
    
    public boolean validateForm(String name, String email, String phone, String address, String cityRegion, String ccNumber, HttpServletRequest request)
    {
        return false;
    }
}
