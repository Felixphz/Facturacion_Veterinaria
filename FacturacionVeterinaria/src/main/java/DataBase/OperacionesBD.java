/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

/**
 *
 * @author David Gomez
 */
import Control.BDcontrol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Model.Customer;
import Model.Factura;

public class OperacionesBD implements BDcontrol{
    Connection conexion = ConexionBD.obtenerConexion();
    public OperacionesBD(){
        
    }
    
    @Override
    public void agregarPersona( Customer persona) {
        try {
            String insercion = "INSERT INTO persona (cedula, Nombres, Apellidos, telefono, email, direccion) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(insercion);

            // Establece los valores de los parámetros
            preparedStatement.setInt(1, persona.getCedula());
            preparedStatement.setString(2, persona.getNombres());
            preparedStatement.setString(3, persona.getApellidos());
            preparedStatement.setInt(4, persona.getTelefono());
            preparedStatement.setString(5, persona.getCorreo());
            preparedStatement.setString(6, persona.getDireccion());

            // Ejecuta la inserción
            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Persona agregada exitosamente a la base de datos.");
            } else {
                System.err.println("Error al agregar persona a la base de datos.");
            }

            preparedStatement.close();
        } catch (Exception e) {
            System.err.println("Error al agregar persona: " + e.getMessage());
        }
    }
    
    @Override
    public void agregarFactura( Factura factura) {
        try {
            String insercion = "INSERT INTO facturas (id_persona, sucursal, estado, fecha) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(insercion);

            // Establece los valores de los parámetros
            preparedStatement.setInt(1, factura.getId_persona());
            preparedStatement.setString(2, factura.getSucursal());
            preparedStatement.setDate(4, factura.getFecha());
            preparedStatement.setString(3, factura.getEstado());

            // Ejecuta la inserción
            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("factura agregada exitosamente a la base de datos.");
            } else {
                System.err.println("Error al agregar factura a la base de datos.");
            }

            preparedStatement.close();
        } catch (Exception e) {
            System.err.println("Error al agregar factura: " + e.getMessage());
        }
    }
/*
    public static void main(String[] args) {
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion != null) {
            Customer nuevaPersona = new Customer("1000344432","juan", "felipe", 3214545, "sdf@sdf", "2345465ui");
            agregarPersona(conexion, nuevaPersona);
            ConexionBD.cerrarConexion(conexion);
        }
    }*/

    
}
