/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Juan Felipe
 */
public class Bill {
    private int id_factura;
    private int id_persona;
    private Date date;
    private String estado;

    public Bill(int id_persona, Date fecha, String estado) {
        this.id_persona = id_persona;
        this.date = fecha;
        this.estado = estado;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public Date getFecha() {
        return date;
    }

    public void setFecha(Date fecha) {
        this.date = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
