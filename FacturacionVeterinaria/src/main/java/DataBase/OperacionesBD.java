/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

/**
 *
 * @author David Gomez
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import Model.Customer;

public class OperacionesBD {
    
    public static void agregarPersona(Connection conexion, Customer persona) {
        try {
            String insercion = "INSERT INTO persona (Nombres, Apellidos, telefono, email, direccion) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(insercion);

            // Establece los valores de los parámetros
            preparedStatement.setString(1, persona.getNombres());
            preparedStatement.setString(2, persona.getApellidos());
            preparedStatement.setInt(3, persona.getTelefono());
            preparedStatement.setString(4, persona.getCorreo());
            preparedStatement.setString(5, persona.getDireccion());

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

    public static void main(String[] args) {
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion != null) {
            Customer nuevaPersona = new Customer("juan", "felipe", 3214545, "sdf@sdf", "2345465ui");
            agregarPersona(conexion, nuevaPersona);
            ConexionBD.cerrarConexion(conexion);
        }
    }
}
