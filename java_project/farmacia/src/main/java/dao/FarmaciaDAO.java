/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import modelo.CargoEmpleado;
import modelo.Cliente;
import interfaces.ComponentesFarmacia;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Conexion;
import modelo.DetalleVenta;
import modelo.Empleado;
import modelo.Farmaceutica;
import modelo.Farmacia;
import modelo.Producto;
import modelo.TipoProducto;
import modelo.Venta;

/**
 *
 * @author milto
 */
public class FarmaciaDAO implements ComponentesFarmacia {
    private Conexion conexion;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public FarmaciaDAO() {
        conexion = new Conexion();
        conexion.conectar();
    }

    @Override
    public ArrayList<DetalleVenta> cargarDetallesVentas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Venta> cargarVentas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Farmaceutica> cargarFarmaceuticas() {
        ArrayList<Farmaceutica> farmaceuticas = new ArrayList<>();
        String sql = "SELECT * FROM FARMACEUTICA";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id_farmaceutica");
                String nombre = resultSet.getString("nombre");
                String telefono = resultSet.getString("telefono");
                String correo = resultSet.getString("correo_electronico");
                Farmaceutica farmaceutica = new Farmaceutica(id, nombre, telefono, correo);
                farmaceuticas.add(farmaceutica);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar farmaceuticas: " + e.getMessage());
        }
        return farmaceuticas;
    }

    @Override
    public ArrayList<TipoProducto> cargarTiposProductos() {
        ArrayList<TipoProducto> tiposProductos = new ArrayList<>();
        String sql = "SELECT * FROM TIPO_PRODUCTO";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id_tipo_producto");
                String nombre = resultSet.getString("nombre");
                TipoProducto tipoProducto = new TipoProducto(id, nombre);
                tiposProductos.add(tipoProducto);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar tipos de productos: " + e.getMessage());
        }
        return tiposProductos;
    }

    @Override
    public ArrayList<Producto> cargarProductos(Farmacia miFarmacia) {
        ArrayList<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTO";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id_producto");
                Farmaceutica farmaceutica = miFarmacia.buscarFarmaceuticaPorId(resultSet.getInt("id_farmaceutica"));
                TipoProducto tipoProducto = miFarmacia.buscarTipoProductoPorId(resultSet.getInt("id_tipo_producto"));
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                double precio = resultSet.getDouble("precio");
                int cantidadStock = resultSet.getInt("cantidad_stock");
                Producto producto = new Producto(farmaceutica, tipoProducto, nombre, descripcion, precio, cantidadStock);
                producto.setId(id);
                productos.add(producto);
                System.out.println("producto agregado: " + nombre);
            }
            //conexion.desconectar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return productos;
}

@Override
public ArrayList<CargoEmpleado> cargarCargosEmpleados() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Empleado> cargarEmpleados() {
    ArrayList<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM EMPLEADO";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id_empleado");
                String cargo = resultSet.getString("id_cargo");
                String cedula = resultSet.getString("cedula");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                double salario = resultSet.getDouble("salario");
                Date fechaContratacion= resultSet.getDate("fecha_contratacion");
                String clave = resultSet.getString("clave");
                
                Empleado emp = new Empleado(id, nombre, cargo, apellido, salario, fechaContratacion, cedula, clave);
                empleados.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar empleados: " + e.getMessage());
        }
        return empleados;
    }


    @Override
public ArrayList<Cliente> cargarClientes() {
    ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id_cliente");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String telefono = resultSet.getString("telefono");
                String correo = resultSet.getString("correo_electronico");
                String fecha_nacimiento = resultSet.getString("fecha_nacimiento");
                String genero = resultSet.getString("genero");

                Cliente cliente = new Cliente(nombre, apellido, telefono, correo, fecha_nacimiento, genero);
                
                cliente.setId(id);
                clientes.add(cliente);
                System.out.println("Cliente agregado: " + nombre);
            }
            //conexion.desconectar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clientes;
    }
    
}
