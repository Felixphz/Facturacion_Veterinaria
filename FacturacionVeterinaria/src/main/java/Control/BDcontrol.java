/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Control;

import Model.Customer;
import Model.Detalle;
import Model.Factura;
import Model.Producto;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author David Gomez
 */
public interface BDcontrol {
    public void agregarPersona( Customer persona);
    public void agregarFactura( Factura factura);
    public void agregarDetalles( Detalle detalle);
    public List<Producto> obtenerProductos();
    public List<Factura> obtenerFacturas();
    public List<Detalle> obtenerDetalles();
    public List<Detalle> obtenerDetallesPorIdFactura(int idFactura);
    public Producto obtenerProductoPorId(int idProducto);
    public Customer obtenerPersonaPorId(int idPersona);
    public List<Factura> obtenerBalance(Date fechaInicio, Date fechaFin);
            
}
