/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import modelo.CargoEmpleado;
import modelo.Cliente;
import interfaces.ComponentesFarmacia;
import java.sql.Connection;
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
    private Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<TipoProducto> cargarTiposProductos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Producto> cargarProductos(Farmacia miFarmacia) {
        ArrayList<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTO";

        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id_producto");
                Farmaceutica farmaceutica = null;
                //Farmaceutica farmaceutica = miFarmacia.buscarFarmaceutica(rs.getInt("id_farmaceutica"));
                TipoProducto tipoProducto = null;
                //TipoProducto tipoProducto = miFarmacia.buscarTipoProducto(rs.getInt("id_tipo_producto"));
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                double precio = resultSet.getDouble("precio");
                int cantidadStock = resultSet.getInt("cantidad_stock");
                Producto producto = new Producto(id, farmaceutica, tipoProducto, nombre, descripcion, precio, cantidadStock);
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
public ArrayList<Cliente> cargarClientes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
