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
import Model.Detail;
import Model.Bill;
import Model.Product;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OperationsBD implements BDcontrol {

    public OperationsBD() {

    }

    // Método para agregar una persona a la base de datos
    @Override
    public void addPersonDB(Customer persona) {
        Connection conexion = ConnectionBD.obtenerConexion();
        try {
            String insercion = "INSERT INTO persona (cedula, Nombres, Apellidos, telefono, email, direccion) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(insercion);

            // Establece los valores de los parámetros
            preparedStatement.setInt(1, persona.getIdentificationCard());
            preparedStatement.setString(2, persona.getName());
            preparedStatement.setString(3, persona.getLastName());
            preparedStatement.setInt(4, persona.getPhone());
            preparedStatement.setString(5, persona.getMail());
            preparedStatement.setString(6, persona.getAddress());

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
        
        ConnectionBD.cerrarConexion(conexion);
    }

    // Método para agregar una factura a la base de datos y obtener la ID generada
    @Override
    public int addInvoiceDB(Bill factura){
        Connection conexion = ConnectionBD.obtenerConexion();
        int idGenerada = -1;  // Valor predeterminado si la inserción falla o no se obtiene la ID generada

        try {
            String insercion = "INSERT INTO facturas (id_persona, fecha, estado) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(insercion, Statement.RETURN_GENERATED_KEYS);

            // Establece los valores de los parámetros
            preparedStatement.setInt(1, factura.getIdPerson());
            preparedStatement.setDate(2, factura.getDate());
            preparedStatement.setString(3, factura.getState());

            // Ejecuta la inserción
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                // Obtiene las claves generadas
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    idGenerada = generatedKeys.getInt(1);
                    System.out.println("Factura agregada exitosamente a la base de datos. ID generada: " + idGenerada);
                } else {
                    System.err.println("Error al obtener la ID generada para la factura.");
                }

                generatedKeys.close();
            } else {
                System.err.println("Error al agregar factura a la base de datos.");
            }
            preparedStatement.close();
        } catch (Exception e) {
            System.err.println("Error al agregar factura: " + e.getMessage());
        }
        ConnectionBD.cerrarConexion(conexion);
        return idGenerada;
    }

    // Método para agregar detalles a la base de datos
    @Override
    public void addDetailsDB(Detail detalle) {
        Connection conexion = ConnectionBD.obtenerConexion();
        try {
            String insercion = "INSERT INTO detalles (id_producto1, id_factura1,cantidad) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(insercion);

            // Establece los valores de los parámetros
            preparedStatement.setInt(1, detalle.getIdProduct());
            preparedStatement.setInt(2, detalle.getIdBill());
            preparedStatement.setInt(3, detalle.getAmount());

            // Ejecuta la inserción
            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Detalle agregado exitosamente a la base de datos.");
            } else {
                System.err.println("Error al agregar detalle a la base de datos.");
            }

            preparedStatement.close();
        } catch (Exception e) {
            System.err.println("Error al agregar detalle: " + e.getMessage());
        }
        ConnectionBD.cerrarConexion(conexion);
    }

    // Método para obtener la lista de productos desde la base de datos
    @Override
    public List<Product> getProductsDB() {
        Connection conexion = ConnectionBD.obtenerConexion();
        List<Product> productos = new ArrayList<>();
        try {
            String consulta = "SELECT id_producto, nombre, precio, descripcion FROM productos";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                String nombre = resultSet.getString("nombre");
                int precio = resultSet.getInt("precio");
                String descripcion = resultSet.getString("descripcion");

                Product producto = new Product(nombre, precio, descripcion);
                producto.setIdProduct(idProducto);
                productos.add(producto);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }
        ConnectionBD.cerrarConexion(conexion);
        return productos;
    }
    
    // Método para obtener la ID de una persona a partir de su cédula
    @Override
    public Integer getIdCustomerByidentification(int identification) {
        Connection conexion = ConnectionBD.obtenerConexion();
        Integer idCustomer = -1;
        try {
            String consulta = "SELECT id_persona FROM persona WHERE cedula = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setInt(1, identification);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                idCustomer = resultSet.getInt("id_persona");
            }  

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener ID de usuario por cédula: " + e.getMessage());
        }
        ConnectionBD.cerrarConexion(conexion);
        return idCustomer;
    }
    
    // Método para obtener la lista de detalles de una factura por su ID
    @Override
    public List<Detail> getDetailsByInvoiceId(int idFactura){
        Connection conexion = ConnectionBD.obtenerConexion();
        List<Detail> productList = new ArrayList<>();
        try {
            String consulta = "SELECT id_producto1,cantidad FROM detalles WHERE id_factura1 = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setInt(1, idFactura);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto1");
                int cantidad = resultSet.getInt("cantidad");
                Detail detalle=new Detail(idProducto,idFactura,cantidad);
                productList.add(detalle);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener detalles: " + e.getMessage());
        }
        ConnectionBD.cerrarConexion(conexion);
        return productList;
    }
    
    
    // Método para obtener un producto por su ID
    @Override
    public Product getProductById(int idProducto) {
        Connection conexion = ConnectionBD.obtenerConexion();
        Product producto = null;
        try {
            String consulta = "SELECT nombre, precio, descripcion FROM productos WHERE id_producto = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setInt(1, idProducto);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int precio = resultSet.getInt("precio");
                String descripcion = resultSet.getString("descripcion");

                producto = new Product(nombre, precio, descripcion);
                producto.setIdProduct(idProducto); // Establecemos la id del producto
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener producto: " + e.getMessage());
        }
        ConnectionBD.cerrarConexion(conexion);
        return producto;
    }
    
    // Método para obtener una persona por su ID
    @Override
    public Customer getPersonById(int idPersona) {
        Connection conexion = ConnectionBD.obtenerConexion();
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
                persona.setIdPerson(idPersona); // Establecemos la id de la persona
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener persona: " + e.getMessage());
        }
        ConnectionBD.cerrarConexion(conexion);
        return persona;
    }
    
    // Método para buscar facturas por fecha
    @Override
    public List<Bill> searchInvoicesByDate(Date fecha) {
        Connection conexion = ConnectionBD.obtenerConexion();
        List<Bill> facturasEnFecha = new ArrayList<>();
        try {
            String consulta = "SELECT id_factura, id_persona, estado, fecha FROM facturas WHERE fecha = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setDate(1, fecha);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idFactura = resultSet.getInt("id_factura");
                int idPersona = resultSet.getInt("id_persona");
                String estado = resultSet.getString("estado");
                Date fechaFactura = resultSet.getDate("fecha");

                Bill factura = new Bill( idPersona, fechaFactura, estado);
                factura.setIdBill(idFactura);
                facturasEnFecha.add(factura);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al buscar facturas por fecha: " + e.getMessage());
        }
        ConnectionBD.cerrarConexion(conexion);
        return facturasEnFecha;
    }
    
    // Método para obtener la lista de todas las facturas
    @Override
    public List<Bill> getInvoicesDB() {
        Connection conexion = ConnectionBD.obtenerConexion();
        List<Bill> facturas = new ArrayList<>();
        try {
            String consulta = "SELECT id_factura, id_persona, estado, fecha FROM facturas";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idFactura = resultSet.getInt("id_factura");
                int idPersona = resultSet.getInt("id_persona");
                String estado = resultSet.getString("estado");
                Date fecha = resultSet.getDate("fecha");

                Bill factura = new Bill(idPersona, fecha, estado);
                factura.setIdBill(idFactura);
                facturas.add(factura);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener facturas: " + e.getMessage());
        }
        ConnectionBD.cerrarConexion(conexion);
        return facturas;
    }

    // Método para obtener la lista de todos los detalles
    @Override
    public List<Detail> getDetailsDB() {
        Connection conexion = ConnectionBD.obtenerConexion();
        List<Detail> detalles = new ArrayList<>();
        try {
            String consulta = "SELECT id_producto1, id_factura FROM detalles";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto1");
                int idFactura = resultSet.getInt("id_factura1");
                int cantidad = resultSet.getInt("cantidad");

                Detail detalle = new Detail(idProducto, idFactura,cantidad);
                detalle.setIdDetail(idFactura);
                detalles.add(detalle);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener detalles: " + e.getMessage());
        }
        ConnectionBD.cerrarConexion(conexion);
        return detalles;
    }
    
    // Método para obtener la lista de facturas en un rango de tiempo
    @Override
    public List<Bill> getBalance(Date fechaInicio, Date fechaFin) {
        Connection conexion = ConnectionBD.obtenerConexion();
        List<Bill> facturasEnRango = new ArrayList<>();
        try {
            String consulta = "SELECT id_factura, id_persona, estado, fecha FROM facturas WHERE fecha BETWEEN ? AND ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setDate(1, fechaInicio);
            preparedStatement.setDate(2, fechaFin);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idFactura = resultSet.getInt("id_factura");
                int idPersona = resultSet.getInt("id_persona");
                String estado = resultSet.getString("estado");
                Date fecha = resultSet.getDate("fecha");

                Bill factura = new Bill( idPersona, fecha,estado);
                factura.setIdBill(idFactura);
                facturasEnRango.add(factura);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener facturas en rango de tiempo: " + e.getMessage());
        }
        ConnectionBD.cerrarConexion(conexion);
        return facturasEnRango;
    }
}

