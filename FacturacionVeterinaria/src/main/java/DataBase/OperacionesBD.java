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
import Model.Detalle;
import Model.Factura;
import Model.Producto;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperacionesBD implements BDcontrol {

    Connection conexion = ConexionBD.obtenerConexion();

    public OperacionesBD() {

    }

    @Override
    public void agregarPersona(Customer persona) {
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
    public void agregarFactura(Factura factura) {
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

    @Override
    public void agregarDetalles(Detalle detalle) {
        try {
            String insercion = "INSERT INTO detalles (id_producto1, id_factura) VALUES (?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(insercion);

            // Establece los valores de los parámetros
            preparedStatement.setInt(1, detalle.getId_producto());
            preparedStatement.setInt(2, detalle.getId_factura());

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

    @Override
    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        try {
            String consulta = "SELECT id_producto, nombre, precio, descripcion FROM productos";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                String nombre = resultSet.getString("nombre");
                int precio = resultSet.getInt("precio");
                String descripcion = resultSet.getString("descripcion");

                Producto producto = new Producto(nombre, precio, descripcion);
                producto.setId_producto(idProducto);
                productos.add(producto);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }
    
    @Override
    public List<Detalle> obtenerDetallesPorIdFactura(int idFactura){
        List<Detalle> productList = new ArrayList<>();
        try {
            String consulta = "SELECT id_producto1,cantidad FROM detalles WHERE id_factura1 = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setInt(1, idFactura);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto1");
                int cantidad = resultSet.getInt("cantidad");
                Detalle detalle=new Detalle(idFactura,idProducto,cantidad);
                productList.add(detalle);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener detalles: " + e.getMessage());
        }
        return productList;
    }
    
    
    @Override
    public Producto obtenerProductoPorId(int idProducto) {
    Producto producto = null;
    try {
        String consulta = "SELECT nombre, precio, descripcion FROM productos WHERE id_producto = ?";
        PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
        preparedStatement.setInt(1, idProducto);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String nombre = resultSet.getString("nombre");
            int precio = resultSet.getInt("precio");
            String descripcion = resultSet.getString("descripcion");

            producto = new Producto(nombre, precio, descripcion);
            producto.setId_producto(idProducto); // Establecemos la id del producto
        }

        resultSet.close();
        preparedStatement.close();
    } catch (SQLException e) {
        System.err.println("Error al obtener producto: " + e.getMessage());
    }
    return producto;
}
    
    @Override
public Customer obtenerPersonaPorId(int idPersona) {
    Customer persona = null;
    try {
        String consulta = "SELECT cedula, Nombres, Apellidos, telefono, email, direccion FROM persona WHERE id_persona = ?";
        PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
        preparedStatement.setInt(1, idPersona);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int cedula = resultSet.getInt("cedula");
            String nombres = resultSet.getString("Nombres");
            String apellidos = resultSet.getString("Apellidos");
            int telefono = resultSet.getInt("telefono");
            String email = resultSet.getString("email");
            String direccion = resultSet.getString("direccion");

            persona = new Customer(cedula, nombres, apellidos, telefono, email, direccion);
            persona.setId_persona(idPersona); // Establecemos la id de la persona
        }

        resultSet.close();
        preparedStatement.close();
    } catch (SQLException e) {
        System.err.println("Error al obtener persona: " + e.getMessage());
    }
    return persona;
}
    @Override
    public List<Factura> buscarFacturasPorFecha(Date fecha) {
        List<Factura> facturasEnFecha = new ArrayList<>();
        try {
            String consulta = "SELECT id_factura, id_persona, sucursal, estado, fecha FROM facturas WHERE fecha = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setDate(1, fecha);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idFactura = resultSet.getInt("id_factura");
                int idPersona = resultSet.getInt("id_persona");
                String sucursal = resultSet.getString("sucursal");
                String estado = resultSet.getString("estado");
                Date fechaFactura = resultSet.getDate("fecha");

                Factura factura = new Factura( idPersona, sucursal,fechaFactura, estado);
                factura.setId_factura(idFactura);
                facturasEnFecha.add(factura);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al buscar facturas por fecha: " + e.getMessage());
        }
        return facturasEnFecha;
    }
    @Override
    public List<Factura> obtenerFacturas() {
        List<Factura> facturas = new ArrayList<>();
        try {
            String consulta = "SELECT id_factura, id_persona, sucursal, estado, fecha FROM facturas";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idFactura = resultSet.getInt("id_factura");
                int idPersona = resultSet.getInt("id_persona");
                String sucursal = resultSet.getString("sucursal");
                String estado = resultSet.getString("estado");
                Date fecha = resultSet.getDate("fecha");

                Factura factura = new Factura(idPersona, sucursal, fecha, estado);
                factura.setId_factura(idFactura);
                facturas.add(factura);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener facturas: " + e.getMessage());
        }
        return facturas;
    }

    @Override
    public List<Detalle> obtenerDetalles() {
        List<Detalle> detalles = new ArrayList<>();
        try {
            String consulta = "SELECT id_producto1, id_factura FROM detalles";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto1");
                int idFactura = resultSet.getInt("id_factura1");
                int cantidad = resultSet.getInt("cantidad");

                Detalle detalle = new Detalle(idProducto, idFactura,cantidad);
                detalle.setId_detalle(idFactura);
                detalles.add(detalle);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener detalles: " + e.getMessage());
        }
        return detalles;
    }
    
    @Override
    public List<Factura> obtenerBalance(Date fechaInicio, Date fechaFin) {
    List<Factura> facturasEnRango = new ArrayList<>();
    try {
        String consulta = "SELECT id_factura, id_persona, sucursal, estado, fecha FROM facturas WHERE fecha BETWEEN ? AND ?";
        PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
        preparedStatement.setDate(1, fechaInicio);
        preparedStatement.setDate(2, fechaFin);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int idFactura = resultSet.getInt("id_factura");
            int idPersona = resultSet.getInt("id_persona");
            String sucursal = resultSet.getString("sucursal");
            String estado = resultSet.getString("estado");
            Date fecha = resultSet.getDate("fecha");

            Factura factura = new Factura( idPersona, sucursal, fecha,estado);
            factura.setId_factura(idFactura);
            facturasEnRango.add(factura);
        }

        resultSet.close();
        preparedStatement.close();
    } catch (SQLException e) {
        System.err.println("Error al obtener facturas en rango de tiempo: " + e.getMessage());
    }
    return facturasEnRango;
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
