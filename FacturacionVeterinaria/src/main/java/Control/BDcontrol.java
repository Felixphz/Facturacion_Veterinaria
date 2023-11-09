/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Control;

import Model.Customer;
import Model.Factura;

/**
 *
 * @author David Gomez
 */
public interface BDcontrol {
    public void agregarPersona( Customer persona);
    public void agregarFactura( Factura factura);
}
