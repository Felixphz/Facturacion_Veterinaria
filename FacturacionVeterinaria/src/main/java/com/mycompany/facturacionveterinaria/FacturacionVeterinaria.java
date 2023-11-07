/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.facturacionveterinaria;

import DataBase.ConexionBD;
import java.sql.Connection;

/**
 *
 * @author Juan Felipe
 */
public class FacturacionVeterinaria {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Connection conexion = ConexionBD.obtenerConexion();
    }
}
