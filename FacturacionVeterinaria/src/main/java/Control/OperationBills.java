/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Product;
import java.util.List;
import java.util.Iterator;

/**
 *
 * @author juani
 */
public class OperationBills {

    public static float calculateSubtotal(List<Product> product, List<Integer> listAmount) {
        float subtotal = 0;
        Integer cant;
        Iterator iterator = product.iterator();
        Iterator iterator2 = listAmount.iterator();
        Product pr;

        while (iterator2.hasNext()) {
            pr = (Product) iterator.next();
            cant = (Integer) iterator2.next();
            subtotal += pr.getPrice() * cant;
        }
        
        return subtotal;
    }
    
    public static float calculateSubtotalP (int price, int amount) {
        float subtotalP = price * amount;
        
        return subtotalP;
    }

    public static float calculateIVA(float subtotal, float porIva) {
        float iva = subtotal * porIva;
        return iva;
    }

    public static float calculateTotal(float subtotal, float iva) {
        float total = subtotal + iva;
        return total;
    }
    
    
}
