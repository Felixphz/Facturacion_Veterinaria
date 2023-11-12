/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Control;

import Model.Customer;
import Model.Detail;
import Model.Bill;
import Model.Product;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author David Gomez
 */
public interface BDcontrol {
    public void addPersonDB( Customer persona);
    public int addInvoiceDB( Bill factura);
    public void addDetailsDB( Detail detalle);
    public List<Product> getProductsDB();
    public List<Bill> getInvoicesDB();
    public List<Detail> getDetailsDB();
    public List<Detail> getDetailsByInvoiceId(int idFactura);
    public Product getProductById(int idProducto);
    public Customer getPersonById(int idPersona);
    public List<Bill> getBalance(Date fechaInicio, Date fechaFin);
    public List<Bill> searchInvoicesByDate(Date fecha);
    public Integer getIdCustomerByidentification(int identification);
}
